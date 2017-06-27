package com.qyf.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
public class OrderInfo implements Serializable{
    private static final long serialVersionUID = 147056138083426328L;
    private String orderId;//订单ID
    private String payment;//实付金额
    private Integer paymentType;//支付类型
        private WorkBook workbook;//
    public WorkBook getWorkbook() {
        return workbook;
    }
    public void setWorkbook(WorkBook workbook) {
        this.workbook = workbook;
    }
    private String postFee;//邮费
    private Integer status;//状态
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;//订单创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;//订单更新时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date paymentTime;//付款时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date consignTime;//发货时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;//交易完成时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date closeTime;//交易关闭时间

    private String shippingName;//物流名称

    private String shippingCode;//物流单号

    private Integer userId;//用户ID

    private String buyerMessage;//买家留言

    private String buyerNick;//买家昵称

    private Integer buyerRate;//买家是否已经评价




    private String sort;

    private String order;

    private Integer start;

    private Integer end;

   private  Order_item orderitem;
    private   Order_shipping ordershipping;
    public Order_item getOrderitem() {
        return orderitem;
    }

    public void setOrderitem(Order_item orderitem) {
        this.orderitem = orderitem;
    }

    public Order_shipping getOrdershipping() {
        return ordershipping;
    }

    public void setOrdershipping(Order_shipping ordershipping) {
        this.ordershipping = ordershipping;
    }


    //关联 字典表 支付方是
    private WorkBook pay;
    //关联 字典表 订单状态
    private WorkBook orderStatus;

    //关联 订单买家
    private Admin userInfo;


    public WorkBook getPay() {
        return pay;
    }

    public void setPay(WorkBook pay) {
        this.pay= pay;
    }

    public WorkBook getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(WorkBook orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Admin getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Admin userInfo) {
        this.userInfo = userInfo;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment == null ? null : payment.trim();
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getPostFee() {
        return postFee;
    }

    public void setPostFee(String postFee) {
        this.postFee = postFee == null ? null : postFee.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getConsignTime() {
        return consignTime;
    }

    public void setConsignTime(Date consignTime) {
        this.consignTime = consignTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName == null ? null : shippingName.trim();
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode == null ? null : shippingCode.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage == null ? null : buyerMessage.trim();
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick == null ? null : buyerNick.trim();
    }

    public Integer getBuyerRate() {
        return buyerRate;
    }

    public void setBuyerRate(Integer buyerRate) {
        this.buyerRate = buyerRate;
    }

    public OrderInfo(String orderId, String payment, Integer paymentType, WorkBook workbook, String postFee, Integer status, Date createTime, Date updateTime, Date paymentTime, Date consignTime, Date endTime, Date closeTime, String shippingName, String shippingCode, Integer userId, String buyerMessage, String buyerNick, Integer buyerRate, String sort, String order, Integer start, Integer end, Order_item orderitem, Order_shipping ordershipping, WorkBook pay, WorkBook orderStatus, Admin userInfo) {
        this.orderId = orderId;
        this.payment = payment;
        this.paymentType = paymentType;
        this.workbook = workbook;
        this.postFee = postFee;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.paymentTime = paymentTime;
        this.consignTime = consignTime;
        this.endTime = endTime;
        this.closeTime = closeTime;
        this.shippingName = shippingName;
        this.shippingCode = shippingCode;
        this.userId = userId;
        this.buyerMessage = buyerMessage;
        this.buyerNick = buyerNick;
        this.buyerRate = buyerRate;
        this.sort = sort;
        this.order = order;
        this.start = start;
        this.end = end;
        this.orderitem = orderitem;
        this.ordershipping = ordershipping;
        this.pay = pay;
        this.orderStatus = orderStatus;
        this.userInfo = userInfo;
    }

    public OrderInfo() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderInfo orderInfo = (OrderInfo) o;

        if (orderId != null ? !orderId.equals(orderInfo.orderId) : orderInfo.orderId != null) return false;
        if (payment != null ? !payment.equals(orderInfo.payment) : orderInfo.payment != null) return false;
        if (paymentType != null ? !paymentType.equals(orderInfo.paymentType) : orderInfo.paymentType != null)
            return false;
        if (workbook != null ? !workbook.equals(orderInfo.workbook) : orderInfo.workbook != null) return false;
        if (postFee != null ? !postFee.equals(orderInfo.postFee) : orderInfo.postFee != null) return false;
        if (status != null ? !status.equals(orderInfo.status) : orderInfo.status != null) return false;
        if (createTime != null ? !createTime.equals(orderInfo.createTime) : orderInfo.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(orderInfo.updateTime) : orderInfo.updateTime != null) return false;
        if (paymentTime != null ? !paymentTime.equals(orderInfo.paymentTime) : orderInfo.paymentTime != null)
            return false;
        if (consignTime != null ? !consignTime.equals(orderInfo.consignTime) : orderInfo.consignTime != null)
            return false;
        if (endTime != null ? !endTime.equals(orderInfo.endTime) : orderInfo.endTime != null) return false;
        if (closeTime != null ? !closeTime.equals(orderInfo.closeTime) : orderInfo.closeTime != null) return false;
        if (shippingName != null ? !shippingName.equals(orderInfo.shippingName) : orderInfo.shippingName != null)
            return false;
        if (shippingCode != null ? !shippingCode.equals(orderInfo.shippingCode) : orderInfo.shippingCode != null)
            return false;
        if (userId != null ? !userId.equals(orderInfo.userId) : orderInfo.userId != null) return false;
        if (buyerMessage != null ? !buyerMessage.equals(orderInfo.buyerMessage) : orderInfo.buyerMessage != null)
            return false;
        if (buyerNick != null ? !buyerNick.equals(orderInfo.buyerNick) : orderInfo.buyerNick != null) return false;
        if (buyerRate != null ? !buyerRate.equals(orderInfo.buyerRate) : orderInfo.buyerRate != null) return false;
        if (sort != null ? !sort.equals(orderInfo.sort) : orderInfo.sort != null) return false;
        if (order != null ? !order.equals(orderInfo.order) : orderInfo.order != null) return false;
        if (start != null ? !start.equals(orderInfo.start) : orderInfo.start != null) return false;
        if (end != null ? !end.equals(orderInfo.end) : orderInfo.end != null) return false;
        if (orderitem != null ? !orderitem.equals(orderInfo.orderitem) : orderInfo.orderitem != null) return false;
        if (ordershipping != null ? !ordershipping.equals(orderInfo.ordershipping) : orderInfo.ordershipping != null)
            return false;
        if (pay != null ? !pay.equals(orderInfo.pay) : orderInfo.pay != null) return false;
        if (orderStatus != null ? !orderStatus.equals(orderInfo.orderStatus) : orderInfo.orderStatus != null)
            return false;
        return userInfo != null ? userInfo.equals(orderInfo.userInfo) : orderInfo.userInfo == null;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (payment != null ? payment.hashCode() : 0);
        result = 31 * result + (paymentType != null ? paymentType.hashCode() : 0);
        result = 31 * result + (workbook != null ? workbook.hashCode() : 0);
        result = 31 * result + (postFee != null ? postFee.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (paymentTime != null ? paymentTime.hashCode() : 0);
        result = 31 * result + (consignTime != null ? consignTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (closeTime != null ? closeTime.hashCode() : 0);
        result = 31 * result + (shippingName != null ? shippingName.hashCode() : 0);
        result = 31 * result + (shippingCode != null ? shippingCode.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (buyerMessage != null ? buyerMessage.hashCode() : 0);
        result = 31 * result + (buyerNick != null ? buyerNick.hashCode() : 0);
        result = 31 * result + (buyerRate != null ? buyerRate.hashCode() : 0);
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (orderitem != null ? orderitem.hashCode() : 0);
        result = 31 * result + (ordershipping != null ? ordershipping.hashCode() : 0);
        result = 31 * result + (pay != null ? pay.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (userInfo != null ? userInfo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderId='" + orderId + '\'' +
                ", payment='" + payment + '\'' +
                ", paymentType=" + paymentType +
                ", workbook=" + workbook +
                ", postFee='" + postFee + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", paymentTime=" + paymentTime +
                ", consignTime=" + consignTime +
                ", endTime=" + endTime +
                ", closeTime=" + closeTime +
                ", shippingName='" + shippingName + '\'' +
                ", shippingCode='" + shippingCode + '\'' +
                ", userId=" + userId +
                ", buyerMessage='" + buyerMessage + '\'' +
                ", buyerNick='" + buyerNick + '\'' +
                ", buyerRate=" + buyerRate +
                ", sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", orderitem=" + orderitem +
                ", ordershipping=" + ordershipping +
                ", pay=" + pay +
                ", orderStatus=" + orderStatus +
                ", userInfo=" + userInfo +
                '}';
    }
}