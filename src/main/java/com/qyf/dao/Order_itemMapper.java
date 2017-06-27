package com.qyf.dao;

import com.qyf.pojo.Order_item;

public interface Order_itemMapper {
    int deleteByPrimaryKey(String id);

    int insert(Order_item record);

    int insertSelective(Order_item record);

    Order_item selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Order_item record);

    int updateByPrimaryKey(Order_item record);
}