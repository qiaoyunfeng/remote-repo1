package com.qyf.service;

import com.qyf.dao.ItemMapper;
import com.qyf.dao.Item_catagoryMapper;
import com.qyf.pojo.Item;
import com.qyf.pojo.Item_catagory;
import com.qyf.util.PageUtil;
import com.qyf.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 */
@Service
public class IetmServiceImpl  implements ItemService{
    @Autowired
    private ItemMapper ItemMapperdao;
    @Autowired
    private Item_catagoryMapper item_catagoryMapper;
//查询商品列表
    @Override
    public PageUtil<Item> selectItrmList(PageUtil<Item> pageitem) {
       List<Item> im = ItemMapperdao.selectItrmList(pageitem);
     int l =ItemMapperdao.selectTOCount(pageitem);
        pageitem.setTotalCount(l);
        pageitem.setList(im);
        return pageitem;
    }
    //类目查询
    @Override
    public List<Item_catagory> selectittemcatList() {
        return item_catagoryMapper.selectByPrimaryKey();
    }




    public Boolean deleteItem(HttpServletRequest request, Integer id) {
        boolean bool = true;

       List<Item> e =    ItemMapperdao.selectiemByid(id);
        int y=  ItemMapperdao.deleteItem(id);
        String imgPath = request.getServletContext().getRealPath(PropertiesUtil.getProperties("uploadImgPath"));
        File f = null;
        for (Item itm : e){
            f = new File(imgPath+"/"+itm.getImage());
            //判断 文件是否存在
            if (f.exists()){
                f.delete();
                bool=true;
            }
        }
        return bool;

    }
    //新增
    @Override
    public int itemService(Item item) {
        item.setCreated(new Date());
        item.setUpdated(new Date());
        return ItemMapperdao.insertSelective(item);
    }

    @Override
    public int updateItemInfoById(Item u) {
        return ItemMapperdao.updateItemInfoById(u);
    }

    @Override
    public List<Item_catagory> selectittemcattreegrid(String id) {
        Item_catagory  i=new Item_catagory();
            i.setId(id);
        return item_catagoryMapper.selectittemcattreegrid(i);
    }
    //修改回写
    @Override
    public Item selectiemByiddange(String id) {

        return  ItemMapperdao.selectiemByiddange(Integer.valueOf(id));
    }



    ;
}
