package com.qyf.dao;

import com.qyf.pojo.Item_catagory;
import java.math.BigDecimal;
import java.util.List;

public interface Item_catagoryMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(Item_catagory record);

    int insertSelective(Item_catagory record);



    int updateByPrimaryKeySelective(Item_catagory record);

    int updateByPrimaryKey(Item_catagory record);
//查询类目集合

    List<Item_catagory> selectByPrimaryKey();

    //种类treegrid列表
    List<Item_catagory> selectittemcattreegrid(Item_catagory i);
}