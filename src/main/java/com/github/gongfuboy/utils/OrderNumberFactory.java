package com.github.gongfuboy.utils;

/**
 * 订单编号生成工具，只适合单机服务
 *
 * @author GongFuBoy
 * @date 2017/12/19
 * @time 10:10
 */
public class OrderNumberFactory {

    /**
     * 机器ID
     */
    private long workerId;
    /**
     * 分片ID
     */
    private long shardId;
    /**
     * 初始序号
     */
    private long sequence = 0L;
    /**
     * 05/10/2015 17:00:50
     */
    private final static long twepoch = 1431248450063L;
    /**
     * 机器ID位数
     */
    private final static long workerIdBits = 5L;
    /**
     * 分片ID位数
     */
    private final static long shardIdBits = 5L;
    /**
     * 最大机器ID值(11111)
     */
    private final static long maxWorkerId = -1L ^ -1L << workerIdBits;
    /**
     * 最大分片ID值(11111)
     */
    private final static long maxShardId = -1L ^ (-1L << shardIdBits);
    /**
     * 毫秒内自增位
     */
    private final static long sequenceBits = 12L;
    /**
     * 分片ID左移位数
     */
    private final static long shardIdShift = sequenceBits;
    /**
     * 机器ID左移位数
     */
    private final static long workerIdShift = sequenceBits + shardIdBits;
    /**
     * 时间毫秒左移位数
     */
    private final static long timestampLeftShift = sequenceBits + shardIdBits
            + workerIdBits;
    /**
     * 每毫秒内序号最大值
     */
    private final static long sequenceMask = -1L ^ -1L << sequenceBits;
    /**
     * 最后时间(毫秒)
     */
    private long lastTimestamp = -1L;

    public OrderNumberFactory() {
        this(0, 0);
    }

    public OrderNumberFactory(final long workerId) {
        this(workerId, 0);
    }

    public OrderNumberFactory(final long workerId, final long shardId) {
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format(
                    "worker Id can't be greater than %d or less than 0",
                    maxWorkerId));
        }
        if (shardId > maxShardId || shardId < 0) {
            throw new IllegalArgumentException(String.format(
                    "datacenter Id can't be greater than %d or less than 0",
                    maxShardId));
        }
        this.workerId = workerId;
        this.shardId = shardId;
    }

    /**
     * 说明: 41bits时间+5bits机器ID+5bits分片ID+12bits序列号
     */
    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) {
            // 当前毫秒内,则+1
            this.sequence = (this.sequence + 1) & sequenceMask;
            if (this.sequence == 0) {
                // 当前毫秒内计数满了,则等待下一毫秒
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }
        if (timestamp < this.lastTimestamp) {// 时间错误
            throw new RuntimeException(
                    String.format(
                            "系统时间回退. 回退回退时间戳%d 毫秒",
                            this.lastTimestamp - timestamp));
        }
        this.lastTimestamp = timestamp;
        // ID偏移组合生成最终的ID，并返回ID
        long nextId = ((timestamp - twepoch << timestampLeftShift))
                | (this.workerId << workerIdShift) | (shardId << shardIdShift)
                | (this.sequence);
        return nextId;
    }

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format(
                    "worker Id can't be greater than %d or less than 0",
                    maxWorkerId));
        }
        this.workerId = workerId;
    }

    public long getShardId() {
        return shardId;
    }

    public void setShardId(long shardId) {
        if (shardId > maxShardId || shardId < 0) {
            throw new IllegalArgumentException(String.format(
                    "datacenter Id can't be greater than %d or less than 0",
                    maxShardId));
        }
        this.shardId = shardId;
    }

    /**
     * 说明:等待下一毫秒的到来 <br>
     *
     * @param @param  lastTimestamp
     * @return long
     */
    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    /**
     * 说明:获取系统当前时间 <br>
     *
     * @param
     * @return
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }
}

