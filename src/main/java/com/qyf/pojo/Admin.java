package com.qyf.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Admin implements Serializable{
    private static final long serialVersionUID = 3965300671123345803L;
    private BigDecimal id;

    private String adminName;

    private String phone;

    private String email;

    private String password;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date created;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updated;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date longinTime;
    //验证码
    private String imgcode;

    public String getImgcode() {
        return imgcode;
    }

    public void setImgcode(String imgcode) {
        this.imgcode = imgcode;
    }
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreated() {
        if(created !=null){
            return this.created;
        }
        return new Date();
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        if (this.updated != null)
            return this.updated;
        return new Date();
    }
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    public Date getLonginTime() {
        if (this.longinTime != null)
            return this.longinTime;
        return new Date();
    }
    public void setLonginTime(Date longinTime) {
        this.longinTime = longinTime;
    }
}