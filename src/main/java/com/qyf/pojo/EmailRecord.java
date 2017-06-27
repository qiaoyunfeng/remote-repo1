package com.qyf.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EmailRecord implements Serializable{

    private static final long serialVersionUID = -605071011527352447L;
    private BigDecimal id;//

    private String addresser;//发件人

    private String recipients;//收件人

    private String copyrecipients;//抄送人

    private String sendpeople;//密送人

    private String emailtheme;//邮件主题

    private String accessoryname;//附件名称

    private Date date;//发送时间

    private String success;//是否成功

    private String username;//登录用户名

    private String ip;//
    //业务字段
    //邮件正文--文本
    private String mailContent;

    //业务字段
    //发件人密码
    private String sendPwd;

    public String getSendPwd() {
        return sendPwd;
    }

    public void setSendPwd(String sendPwd) {
        this.sendPwd = sendPwd;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getAddresser() {
        return addresser;
    }

    public void setAddresser(String addresser) {
        this.addresser = addresser == null ? null : addresser.trim();
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients == null ? null : recipients.trim();
    }

    public String getCopyrecipients() {
        return copyrecipients;
    }

    public void setCopyrecipients(String copyrecipients) {
        this.copyrecipients = copyrecipients == null ? null : copyrecipients.trim();
    }

    public String getSendpeople() {
        return sendpeople;
    }

    public void setSendpeople(String sendpeople) {
        this.sendpeople = sendpeople == null ? null : sendpeople.trim();
    }

    public String getEmailtheme() {
        return emailtheme;
    }

    public void setEmailtheme(String emailtheme) {
        this.emailtheme = emailtheme == null ? null : emailtheme.trim();
    }

    public String getAccessoryname() {
        return accessoryname;
    }

    public void setAccessoryname(String accessoryname) {
        this.accessoryname = accessoryname == null ? null : accessoryname.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success == null ? null : success.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getIp() {
        return ip;
    }

    @Override
    public String toString() {
        return "EmailRecord{" +
                "id=" + id +
                ", addresser='" + addresser + '\'' +
                ", recipients='" + recipients + '\'' +
                ", copyrecipients='" + copyrecipients + '\'' +
                ", sendpeople='" + sendpeople + '\'' +
                ", emailtheme='" + emailtheme + '\'' +
                ", accessoryname='" + accessoryname + '\'' +
                ", date=" + date +
                ", success='" + success + '\'' +
                ", username='" + username + '\'' +
                ", ip='" + ip + '\'' +
                ", mailContent='" + mailContent + '\'' +
                ", sendPwd='" + sendPwd + '\'' +
                '}';
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }
}