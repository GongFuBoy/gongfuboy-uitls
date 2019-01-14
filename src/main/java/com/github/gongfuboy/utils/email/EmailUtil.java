package com.github.gongfuboy.utils.email;

import com.sun.mail.util.MailSSLSocketFactory;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Properties;


/**
 * 
 * @author deep_feel
 * @date 2019年01月04日 下午18:16:33
 */
public class EmailUtil {

    /**
     * 发送带附件的邮件
     * @param from 发件人邮箱
     * @param password 发件人邮箱密码
     * @param tos 收件人邮箱
     * @param subject 邮件主题
     * @param msg 邮件内容
     * @param fileNames 附件地址List
     * @param host 发送邮箱服务器, etc: smtp.exmail.qq.com/smtp.163.com
     * @return boolean
     */
    public static boolean sendMail(String from, String password, List<String> tos, String subject, String msg, List<String> fileNames, String host) {
        boolean result = false;
        if (tos.size() < 1) {
            throw new IllegalArgumentException("to address is must");
        }
        // 获取系统属性
        Properties properties = System.getProperties();
        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() { // qq邮箱服务器账户、第三方登录授权码
                return new PasswordAuthentication(from, password); // 发件人邮件用户名、密码
            }
        });
        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));
            // Set To: 头部头字段
            Address[] a = new Address[tos.size()];
            for (int i = 0; i < tos.size(); i++) {
                a[i] = new InternetAddress(tos.get(i));
            }
            message.addRecipients(Message.RecipientType.TO, a);
            // Set Subject: 主题文字
            message.setSubject(subject);
            // 创建消息部分
            BodyPart messageBodyPart = new MimeBodyPart();
            // 消息
            messageBodyPart.setText(msg);
            // 创建多重消息
            Multipart multipart = new MimeMultipart();
            // 设置文本消息部分
            multipart.addBodyPart(messageBodyPart);

            for (int i = 0; i < fileNames.size(); i++) {
                String fileName = fileNames.get(i);
                // 附件部分
                BodyPart messageBodyPartForFile = new MimeBodyPart();
                // 设置要发送附件的文件路径
                DataSource source = new FileDataSource(fileName);
                messageBodyPartForFile.setDataHandler(new DataHandler(source));
                messageBodyPartForFile.setFileName(MimeUtility.encodeText(source.getName()));
                multipart.addBodyPart(messageBodyPartForFile);
            }
            // 发送完整消息
            message.setContent(multipart);
            // 发送消息
            Transport.send(message);
            result = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}