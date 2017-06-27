package com.qyf.service;

import com.qyf.pojo.Item;
import com.qyf.pojo.Item_catagory;
import com.qyf.util.PageUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 */
public interface ItemService {
//商品list查询
    PageUtil<Item> selectItrmList(PageUtil<Item> pageitem);
//类目 下拉查询
    List<Item_catagory> selectittemcatList();
//单个删除商品
    Boolean deleteItem(HttpServletRequest request, Integer id);
//新增
    int itemService(Item item);
//修改
    int updateItemInfoById(Item u);
//种类treegrid列表
    List<Item_catagory> selectittemcattreegrid(String id);
    //修改 回写
    Item selectiemByiddange(String id);
}
