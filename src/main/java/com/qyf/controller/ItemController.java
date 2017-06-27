package com.qyf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qyf.pojo.*;
import com.qyf.service.EmailRecordService;
import com.qyf.service.ItemService;
import com.qyf.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.mail.Multipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by Administrator on 2017/6/2.
 */
@Controller
@RequestMapping("item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private EmailRecordService emailRecordService;

    @RequestMapping(value = "sendEmail", method = RequestMethod.POST)
    @ResponseBody
    public ReturnJson sendEmail(HttpServletRequest request) {
        ReturnJson rj = new ReturnJson();
        //收件人
        EmailRecord mailHistory = new EmailRecord();
        mailHistory.setAddresser("gqy_you@163.com");//发件人
        mailHistory.setSendPwd("gaoqiyou");
        mailHistory.setRecipients("522047820@qq.com");//收件人
        // mailHistory.setSendpeople("634997327@qq.com,1521170425@qq.com");//密送人
        //  mailHistory.setCopyrecipients("634997327@qq.com,1521170425@qq.com");//抄送人
        mailHistory.setEmailtheme("商品信息表");//邮件主题
        mailHistory.setMailContent("商品信息excel<a href='http://weibo.com/login.php'>微博</a>");
        mailHistory.setUsername("乔二爷");//登录用户名
        emailRecordService.addMailHistroy(mailHistory);
        //附件名称
        //发送邮件
        int x = JavaMailUtil.sendJavaMail(request, mailHistory);
        if (x == 0) {
            rj.setMsg("发送成功");
        } else {
            rj.setMsg("失败");
        }

        return rj;
    }

    //导出Excel
    //根据条件导出Excel
    @RequestMapping("exportWhereExcel")
    @ResponseBody
    public ReturnJson exportWhereExcel(HttpServletRequest request, HttpServletResponse response, PageUtil<Item> pageitem) {
        ReturnJson rj = new ReturnJson();
        pageitem.setCpage(1);
        pageitem.setPageSize(3);
        pageitem = itemService.selectItrmList(pageitem);
        List<Item> itemList = pageitem.getList();
        //邮件是否发送成功
        int isSuccess = 0;

        if (null != itemList && itemList.size() > 0) {
            String realPath = request.getServletContext().getRealPath("/");
            System.err.println(realPath + "项目原始路径");
            String excelPath = NewPoiUtil.downExcel(itemList, realPath);
            System.err.println(excelPath);
            EmailRecord mailHistory = new EmailRecord();
            mailHistory.setAddresser("gqy_you@163.com");//发件人
            mailHistory.setSendPwd("gaoqiyou");
            mailHistory.setRecipients("522047820@qq.com");//收件人
            // mailHistory.setSendpeople("634997327@qq.com,1521170425@qq.com");//密送人
            //  mailHistory.setCopyrecipients("634997327@qq.com,1521170425@qq.com");//抄送人
            mailHistory.setEmailtheme("额外日期无法");//邮件主题
            mailHistory.setMailContent("商品信息excel<a href='http://weibo.com/login.php'>微博</a>");
            mailHistory.setUsername("乔大爷");//登录用户名
            mailHistory.setAccessoryname(excelPath);//附件名称
            mailHistory.setSuccess(String.valueOf(isSuccess));
            mailHistory.setIp(IpUtil.getIpAddr(request));//Ip
            mailHistory.setDate(new Date());
            isSuccess = JavaMailUtil.sendJavaMail(request, mailHistory);
            //mailHistory存储到 邮件历史记录表
            emailRecordService.addMailHistroy(mailHistory);
        }
        if (isSuccess == 0) {
            rj.setMsg("email发送成功");
            rj.setSuccess(true);
        } else {
            rj.setMsg("email发送失败");
        }
        return rj;
    }

    //商品列表
    @ResponseBody
    @RequestMapping(value = "selectItrmList", method = RequestMethod.POST)
//+httpclient了
    public DataGridJson selectItrmList(String page, String rows, PageUtil<Item> pageitem) {
        int p = Integer.parseInt(page);
        int r = Integer.parseInt(rows);
        DataGridJson dgj = new DataGridJson();
        pageitem.setCpage(p);
        pageitem.setPageSize(r);
        pageitem = itemService.selectItrmList(pageitem);
        dgj.setRows(pageitem.getList());
        dgj.setTotal(pageitem.getTotalCount());
        return dgj;
    }

    //类目查询treegrid展示
    @ResponseBody
    @RequestMapping(value = "selectittemcattreegrid", method = RequestMethod.POST)
    public List<Item_catagory> selectittemcattreegrid(String id) {
        List<Item_catagory> ittemcattreegrid = itemService.selectittemcattreegrid(id);
        return ittemcattreegrid;
    }

    //类目查询下拉框展示
    @ResponseBody
    @RequestMapping(value = "selectittemcatList", method = RequestMethod.POST)
    public List<Tree> selectittemcatList() {
        List<Item_catagory> list = itemService.selectittemcatList();

        ArrayList<Tree> treeList = new ArrayList<>();
        //一级节点
        Tree yiji = null;
        //子节点list
        ArrayList<Tree> childList = null;
        //子级节点
        Tree child = null;
        //节点的自定义属性 如 url等。。。
        HashMap<String, String> nodeAttr = null;
        for (int i = 0; i < list.size(); i++) {
            //pid== null 说明一级节点
            if (list.get(i).getParentId() == 0) {
                yiji = new Tree();
                yiji.setId(Integer.valueOf(list.get(i).getId()));
                yiji.setText(list.get(i).getName());
//                yiji.setIconCls(list.get(i).getIconCls());
                yiji.setState("open");
                childList = new ArrayList<>();

                //循环遍历子节点
                for (int j = 0; j < list.size(); j++) {
                    //当前循环的节点的父级id 等于  上层循环节点的id
                    if (list.get(j).getParentId() != null &&
                            list.get(i).getId().equals(list.get(j).getParentId())) {
                        //实例化子节点
                        child = new Tree();
                        //节点属性赋值
                        child.setId(Integer.valueOf(list.get(j).getId()));
                        child.setText(list.get(j).getName());
//                        child.setIconCls(list.get(j).getIconCls());
                        child.setPid(list.get(j).getParentId());
                        child.setState("open");
                        //实例化 自定义节点属性map
//                        nodeAttr = new HashMap<>();
//                        nodeAttr.put("url", list.get(j).getUrl());
//                        child.setAttributes(nodeAttr);
                        //子节点list 添加 child节点
                        childList.add(child);

                        //循环递归调用
                        selectChildList(list, child);
                    }
                }
                yiji.setChildren(childList);
                treeList.add(yiji);
            }
        }
        return treeList;
    }


    //封装递归方法
    private void selectChildList(List<Item_catagory> resourceList, Tree prarentNode) {
        //子节点list
        ArrayList<Tree> childList = new ArrayList<>();
        //子级节点
        Tree child = null;
        //节点的自定义属性 如 url等。。。
        HashMap<String, String> nodeAttr = null;
        //循环遍历子节点
        for (int j = 0; j < resourceList.size(); j++) {
            //当前循环的节点的父级id 等于  上层循环节点的id
            if (resourceList.get(j).getParentId() != null && resourceList.get(j).getParentId() != 0 &&
                    prarentNode.getId().equals(resourceList.get(j).getParentId())) {
                //实例化子节点
                child = new Tree();
                //节点属性赋值
                child.setId(Integer.valueOf(resourceList.get(j).getId()));
                child.setText(resourceList.get(j).getName());
                child.setPid(resourceList.get(j).getParentId());
                child.setState("open");
                //子节点list 添加 child节点
                childList.add(child);
                //递归调用查找子节点 n层
                selectChildList(resourceList, child);
            }
        }
        prarentNode.setChildren(childList);
    }

    //跳转到新增页面
    @RequestMapping(value = "showItemForm", method = RequestMethod.POST)
    public String showItemForm() {
        return "item/itemForm";
    }

    //单个删除商品
    @ResponseBody
    @RequestMapping(value = "deleteItem", method = RequestMethod.POST)
    public ReturnJson deleteItem(HttpServletRequest request, Integer id) {
        ReturnJson r = new ReturnJson();
        Boolean i = itemService.deleteItem(request, id);
        if (i) {
            r.setMsg("删除成功");
        } else {
            r.setMsg("删除失败");
        }
        return r;

    }

    //图片上传
    @ResponseBody
    @RequestMapping(value = "uploadItemImg", method = RequestMethod.POST)
    public String uploadItemImg(HttpServletRequest request, @RequestParam("image") MultipartFile file) {
        String showImg = null;
        if (null != file) {
            String imgUuidName = UploadFileUtil.uploadFile(request, file, "uploadImgPath");
            String uploadPath = PropertiesUtil.getProperties("uploadImgPath");
            showImg = uploadPath + "/" + imgUuidName;
        }
        return showImg;
    }

    //http图片下载
    @RequestMapping(value = "httpdownImg", method = RequestMethod.GET)
    public void httpdownImg(HttpServletRequest request, HttpServletResponse response) {
        String imgName = request.getHeader("imgName");
        String realPath = request.getServletContext().getRealPath("/img");
        File f = new File(realPath + "/" + imgName);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        if (f.exists()) {
            try {
                request.setCharacterEncoding("utf-8");
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/octet-stream");
                //读取file文件
                inputStream = new BufferedInputStream(new FileInputStream(f));
                //设置response响应的内容长度
                response.setContentLength((int) f.length());
                response.setHeader("Accept-Ranges", "bytes");
                //实例化响应流
                outputStream = new BufferedOutputStream(response.getOutputStream());
                int r = 0;
                byte[] b = new byte[1024];
                while ((r = inputStream.read(b)) != -1) {
                    outputStream.write(b, 0, r);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != outputStream) {
                    try {
                        outputStream.flush();
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (null != inputStream) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }


        }
    }

    //http图片上传
    @ResponseBody
    @RequestMapping(value = "httpuploadimages", method = RequestMethod.POST)
    public String uploadItemImg(@RequestParam("filebody") MultipartFile filebody, String Filename, HttpServletRequest request, HttpServletResponse response) {
        ReturnJson rj = new ReturnJson();
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        //获取图片后缀 比如 .png
        String substring = Filename.substring(Filename.lastIndexOf("."));
        String uuidname = UUID.randomUUID().toString() + substring;
        String realPath = request.getServletContext().getRealPath("/img");

        //创建文件夹
        File f = new File(realPath);
        if (!f.exists()) {
            f.mkdirs();
        }
        try {
            inputStream = filebody.getInputStream();
            fileOutputStream = new FileOutputStream(new File(realPath + "/" + uuidname));
            byte[] b = new byte[1024];
            int i = 0;
            while ((i = inputStream.read(b)) != -1) {
                fileOutputStream.write(b, 0, i);
                fileOutputStream.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fileOutputStream) {
                    fileOutputStream.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return uuidname;
    }
/*
        //http图片上传
    @ResponseBody
    @RequestMapping(value = "httpuploadimages" ,method = RequestMethod.POST)
    public ReturnJson uploadItemImg(HttpServletRequest request,HttpServletResponse response) {
       ReturnJson rj = new ReturnJson();
       //获取multipartRequest;
        MultipartHttpServletRequest request1 = (MultipartHttpServletRequest) request;
        String Filename = request1.getParameter("Filename");
        //获取图片后缀 比如 .png
        String substring = Filename.substring(Filename.lastIndexOf("."));
        MultipartFile file = request1.getFile("filebody");
        String realPath = request1.getServletContext().getRealPath("/img");
      //创建文件夹
        File f = new File(realPath);
        if(!f.exists()){
            f.mkdirs();
        }
        String uuidname = UUID.randomUUID().toString()+substring;
        File fileimg =new File(realPath+"/"+uuidname);

        try {
            //上传文件等同与IO流读写上传文件;
            file.transferTo(fileimg);
        } catch (IOException e){
            e.printStackTrace();
        }
        rj.setMsg(uuidname);
        rj.setSuccess(true);
        return rj;
    }

*/

    /**
     * 新增商品和弹框修改写在了一起
     *
     * @param
     * @return
     */
    @RequestMapping(value = "saveOrUpdateItem", method = RequestMethod.POST)
    @ResponseBody
    public ReturnJson saveOrUpdateItem(String item) {
        ReturnJson rj = new ReturnJson();
        Item item2 = JSON.parseObject(item, Item.class);

        int i = 0;
        if (null != item2.getId() && item2.getId() > 0) {
            item2.setUpdated(new Date());
            i = itemService.updateItemInfoById(item2);
        } else {
            i = itemService.itemService(item2);

        }
        if (i != 0) {
            rj.setSuccess(true);

        }

        return rj;
    }

    ;

    /**
     * 修商品基本信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "updateItemInfo", method = RequestMethod.POST)
    @ResponseBody
    public ReturnJson updateUserInfo(String item) {
        ReturnJson rj = new ReturnJson();
        /*json字符串--- json对象 --- java对象*/
        /*java对象---json对象----json字符串*/
        System.out.print(item);
           /*json字符串--- json对象 --- java对象*/
        Item u = JSON.parseObject(item, Item.class);
        //修改时间为当前系统时间
        u.setUpdated(new Date());

        //根据ID修改用户基本信息
        int x = itemService.updateItemInfoById(u);
        System.out.print(u);
        if (x > 0) {
            rj.setMsg("修改成功");
        } else {
            rj.setMsg("修改失败");

        }
        return rj;
    }

    //修改回写
    @RequestMapping(value = "httphuixieupdate", method = RequestMethod.GET)
    @ResponseBody
    public Item httphuixieupdate(String id) {

        Item uu = itemService.selectiemByiddange(id);
        // String s = JSONObject.toJSONStringWithDateFormat(uu, "yyyy-MM-dd");
        return uu;

    }


}
