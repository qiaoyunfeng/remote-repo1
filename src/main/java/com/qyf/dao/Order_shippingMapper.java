package com.qyf.dao;

import com.qyf.pojo.Order_shipping;

public interface Order_shippingMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Order_shipping record);

    int insertSelective(Order_shipping record);

    Order_shipping selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order_shipping record);

    int updateByPrimaryKey(Order_shipping record);
}