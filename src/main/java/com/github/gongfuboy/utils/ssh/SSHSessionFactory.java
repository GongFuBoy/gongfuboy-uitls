package com.github.gongfuboy.utils.ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * Created by ZhouLiMing on 2018/8/10.
 */
public class SSHSessionFactory {

    /**
     * SSH客户端相关类
     */
    private JSch jsch = new JSch();

    /**
     * 回话字段
     */
    private Session session = null;

    private int localApplicationPort;

    private int SSHRemotePort;

    private String SSHRemoteIP;

    private String SSHRemoteUsername;

    private String SSHRemotePassword;

    private int remoteApplicationPort;

    private String remoteApplicationIP;

    public SSHSessionFactory(int localApplicationPort, int SSHRemotePort, String SSHRemoteIP, String SSHRemoteUsername, String SSHRemotePassword, int remoteApplicationPort, String remoteApplicationIP) {
        super();
        this.localApplicationPort = localApplicationPort;
        this.SSHRemotePort = SSHRemotePort;
        this.SSHRemoteIP = SSHRemoteIP;
        this.SSHRemoteUsername = SSHRemoteUsername;
        this.SSHRemotePassword = SSHRemotePassword;
        this.remoteApplicationPort = remoteApplicationPort;
        this.remoteApplicationIP = remoteApplicationIP;
    }

    /**
     * 连接远程程序，映射到本地
     * @return
     */
    public synchronized boolean connect() {
        if (session == null || !session.isConnected()) {
            try {
                session = jsch.getSession(SSHRemoteUsername, SSHRemoteIP, SSHRemotePort);
                session.setPassword(SSHRemotePassword);
                session.setConfig("StrictHostKeyChecking", "no");
                session.connect();
                session.setPortForwardingL(localApplicationPort, remoteApplicationIP, remoteApplicationPort);
            } catch (JSchException e) {
                throw new RuntimeException(e);
            }
        }
        return session.isConnected();
    }

    /**
     * 断开远程程序
     */
    public synchronized void disconnet() {
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }

}
