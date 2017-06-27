package com.qyf.pojo;

import java.io.Serializable;
import java.util.Date;

public class Order_shipping implements Serializable{
    private static final long serialVersionUID = -6524711740084416837L;
    private String orderId;//订单ID

    private String receiverName;//收货人全名

    private String receiverPhone;//固定电话

    private String receiverMobile;//移动电话

    private String receiverState;//省份

    private String receiverCity;//城市

    private String receiverDistrict;//区/县

    private String receiverAddres;//收货地址

    private String receiverZip;//邮政编码

    private Date created;//创建时间

    private Date updated;//更新时间

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile == null ? null : receiverMobile.trim();
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState == null ? null : receiverState.trim();
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity == null ? null : receiverCity.trim();
    }

    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict == null ? null : receiverDistrict.trim();
    }

    public String getReceiverAddres() {
        return receiverAddres;
    }

    public void setReceiverAddres(String receiverAddres) {
        this.receiverAddres = receiverAddres == null ? null : receiverAddres.trim();
    }

    public String getReceiverZip() {
        return receiverZip;
    }

    public void setReceiverZip(String receiverZip) {
        this.receiverZip = receiverZip == null ? null : receiverZip.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Order_shipping(String orderId, String receiverName, String receiverPhone, String receiverMobile, String receiverState, String receiverCity, String receiverDistrict, String receiverAddres, String receiverZip, Date created, Date updated) {
        this.orderId = orderId;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverMobile = receiverMobile;
        this.receiverState = receiverState;
        this.receiverCity = receiverCity;
        this.receiverDistrict = receiverDistrict;
        this.receiverAddres = receiverAddres;
        this.receiverZip = receiverZip;
        this.created = created;
        this.updated = updated;
    }

    public Order_shipping() {
    }

    @Override
    public String toString() {
        return "Order_shipping{" +
                "orderId='" + orderId + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverMobile='" + receiverMobile + '\'' +
                ", receiverState='" + receiverState + '\'' +
                ", receiverCity='" + receiverCity + '\'' +
                ", receiverDistrict='" + receiverDistrict + '\'' +
                ", receiverAddres='" + receiverAddres + '\'' +
                ", receiverZip='" + receiverZip + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order_shipping that = (Order_shipping) o;

        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (receiverName != null ? !receiverName.equals(that.receiverName) : that.receiverName != null) return false;
        if (receiverPhone != null ? !receiverPhone.equals(that.receiverPhone) : that.receiverPhone != null)
            return false;
        if (receiverMobile != null ? !receiverMobile.equals(that.receiverMobile) : that.receiverMobile != null)
            return false;
        if (receiverState != null ? !receiverState.equals(that.receiverState) : that.receiverState != null)
            return false;
        if (receiverCity != null ? !receiverCity.equals(that.receiverCity) : that.receiverCity != null) return false;
        if (receiverDistrict != null ? !receiverDistrict.equals(that.receiverDistrict) : that.receiverDistrict != null)
            return false;
        if (receiverAddres != null ? !receiverAddres.equals(that.receiverAddres) : that.receiverAddres != null)
            return false;
        if (receiverZip != null ? !receiverZip.equals(that.receiverZip) : that.receiverZip != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        return updated != null ? updated.equals(that.updated) : that.updated == null;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (receiverName != null ? receiverName.hashCode() : 0);
        result = 31 * result + (receiverPhone != null ? receiverPhone.hashCode() : 0);
        result = 31 * result + (receiverMobile != null ? receiverMobile.hashCode() : 0);
        result = 31 * result + (receiverState != null ? receiverState.hashCode() : 0);
        result = 31 * result + (receiverCity != null ? receiverCity.hashCode() : 0);
        result = 31 * result + (receiverDistrict != null ? receiverDistrict.hashCode() : 0);
        result = 31 * result + (receiverAddres != null ? receiverAddres.hashCode() : 0);
        result = 31 * result + (receiverZip != null ? receiverZip.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (updated != null ? updated.hashCode() : 0);
        return result;
    }
}