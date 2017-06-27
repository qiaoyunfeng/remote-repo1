package com.qyf.service;

import com.qyf.pojo.Item;
import com.qyf.pojo.OrderInfo;
import com.qyf.util.PageUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */
public interface OrdersService {
    PageUtil selectOrderList(OrderInfo or);
    List<OrderInfo> selectOrderList();


    PageUtil selectOrderLists(PageUtil pageutil);

    Boolean deleteorders(HttpServletRequest request, String strId);
    //批量新增
    void saveItem(OrderInfo o);


}
