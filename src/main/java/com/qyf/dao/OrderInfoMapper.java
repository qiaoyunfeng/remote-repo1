package com.qyf.dao;

import com.qyf.pojo.OrderInfo;
import com.qyf.util.PageUtil;

import java.util.List;

public interface OrderInfoMapper {
    int deleteByPrimary(List<String> List);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    int selectOrderCount(OrderInfo record);

    List<OrderInfo> selectOrderList(OrderInfo record);

    List<OrderInfo> selectOrderListExcel();
//5表查
List<OrderInfo> selectOrderLists(PageUtil pageutil);
//5表总条数
    int selectOrderCounts(PageUtil pageutil);
}