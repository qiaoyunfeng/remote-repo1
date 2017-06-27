package com.qyf.dao;

import com.qyf.pojo.Item;
import com.qyf.util.PageUtil;

import java.math.BigDecimal;
import java.util.List;

public interface ItemMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

    //商品列表查询
    List<Item> selectItrmList(PageUtil<Item> pageitem);

    //总条数
    int selectTOCount(PageUtil<Item> pageitem);

    //单个删除
    int deleteItem(Integer id);

    //查询单个商品
    List<Item> selectiemByid(Integer id);

    //修改
    int updateItemInfoById(Item u);

    Item selectiemByiddange(Integer id);
}