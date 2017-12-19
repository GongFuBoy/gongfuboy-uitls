package com.github.gongfuboy.utils.wechat.recharge.bean;

/**
 * 微信充值类
 *
 * @author GongFuBoy
 * @date 2017/12/19
 * @time 10:45
 */
public class WechatRechargeRequestBean {

    // 以分为单位的总金额
    private Integer total_fee;

    // 订单编号
    private String out_trade_no;


    public WechatRechargeRequestBean() {

    }

    /**
     * 初始化微信充值bean
     * @param total_fee
     * @param out_trade_no
     */
    public WechatRechargeRequestBean(Integer total_fee, String out_trade_no) {
        this.total_fee = total_fee;
        this.out_trade_no = out_trade_no;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

}
