package com.qyf.service;

import com.qyf.dao.OrderInfoMapper;
import com.qyf.pojo.Item;
import com.qyf.pojo.OrderInfo;
import com.qyf.util.PageUtil;
import freemarker.core.ReturnInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/6/5.
 */
@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrderInfoMapper ordersMapper;

    @Override
    public PageUtil selectOrderList(OrderInfo or) {
        PageUtil rePage = new PageUtil();
        int i = ordersMapper.selectOrderCount(or);
        List<OrderInfo> list = ordersMapper.selectOrderList(or);

        rePage.setList(list);
//        rePage.setTotalCount(i);
        return rePage;
    }

    @Override
    public List<OrderInfo> selectOrderList() {
        return ordersMapper.selectOrderListExcel();
    }

    @Override
    public PageUtil selectOrderLists(PageUtil pageutil) {
        int i = ordersMapper.selectOrderCounts(pageutil);
        List<OrderInfo> list = ordersMapper.selectOrderLists(pageutil);
        pageutil.setList(list);
        pageutil.setTotalCount(i);
        return pageutil;
    }

    //批量删除
    @Override
    public Boolean deleteorders(HttpServletRequest request, String strId) {
        String[] split = strId.split(",");
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < split.length; i++) {
            list.add(split[i].trim());
        }
        int i = ordersMapper.deleteByPrimary(list);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    //批量新增
    @Override
    public void saveItem(OrderInfo o) {
        String s = UUID.randomUUID().toString();
        o.setOrderId(s);
        ordersMapper.insert(o);
    }


}
