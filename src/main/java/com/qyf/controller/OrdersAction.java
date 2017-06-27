package com.qyf.controller;

import com.qyf.pojo.OrderInfo;
import com.qyf.service.OrdersService;
import com.qyf.util.DataGridJson;
import com.qyf.util.PageUtil;
import com.qyf.util.PoiZipUtil;
import com.qyf.util.ReturnJson;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


/**
 * Created by Administrator on 2017/6/5.
 */
@Controller
@RequestMapping("order")
public class OrdersAction {
    @Autowired
    private OrdersService ordersService;
    /**
     * 读取zip文件内容
     */
    @RequestMapping(value="readZip",method= RequestMethod.POST )
    public void readZip(HttpServletRequest req,@RequestParam("image") MultipartFile files) {
        //存放多个excel的文件名
        List<String> strList=new ArrayList<>();
        //存放所有的excel中所有的order数据
        List<OrderInfo> list=new ArrayList<>();
        String realPath = req.getServletContext().getRealPath("/");
        //path 多个excel存放的文件夹路径
        String path=realPath+"aa";

        /*******MultipartFile 转换成  File  **************/

        File ff = null;
        try {
            //File类中jdk创建临时文件的方法 createTempFile("临时文件名称","临时文件的后缀名")
            //如果后缀名为null采用默认后缀名.tmp
            ff=File.createTempFile("tmp", null);
            //      ff=File.createTempFile("tmp", ".zip");
            // 将压缩包files赋值给ff空临时文件  transferTo()转换文件内容
            files.transferTo(ff);
            //临时文件使用完成之后自动删除
            ff.deleteOnExit();
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        /*******MultipartFile 转换成  File  **************/

        //****************************************
        //File fil = new File("D:\\itemdent.zip");
        String fileName=null;
        /*******ZipInputStream 压缩字节输入流 读取 ff该File文件  **************/
        ZipInputStream zipIn = null;
        try {
//        	new FileInputStream(fil)

            Charset gbk = Charset.forName("gbk");
//        	ZipInputStream zin = new ZipInputStream(fileInputStream,gbk);
            zipIn = new ZipInputStream(new FileInputStream(ff),gbk);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*******ZipInputStream 压缩字节输入流 读取 ff该File文件  **************/


        ZipEntry zipEn = null;
        ZipFile zfil = null;
        try {
//            zfil = new ZipFile("D:\\itemdent.zip");
            zfil = new ZipFile(ff);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            while ((zipEn = zipIn.getNextEntry()) != null) {
                if (!zipEn.isDirectory()) { // 判断此zip项是否为目录
                    System.out.println(zipEn.getName() + ":\t");
                    /**
                     * 把是文件的zip项读出缓存，
                     * zfil.getInputStream(zipEn)：返回输入流读取指定zip文件条目的内容 zfil：new
                     * ZipFile();供阅读的zip文件 zipEn：zip文件中的某一项
                     */
                    fileName=zipEn.getName();
                    strList.add(fileName);
                    File f = new File(path);

                    if (!f.exists()){
                        f.mkdirs();
                    }

                    OutputStream zos = new FileOutputStream(f+"/"+fileName);
                    InputStream in = zfil.getInputStream(zipEn);



                    byte[] b = new byte[3072];
                    int s = 0 ;
                    while (-1!=(s = in.read(b))) {
                        zos.write(b);
                        zos.flush();
                    }
                    in.close();
                    zos.close();
                }
                zipIn.closeEntry();// 关闭当前打开的项
            }
            File file =null;
            for (int a = 0; a < strList.size(); a++) {
                file = new File(path+"/"+strList.get(a));

                FileInputStream input = new FileInputStream(file);

               Workbook workbook = null;
                Sheet sheet = null;
                Row row = null;
                Cell cell = null;
                workbook = new HSSFWorkbook(input);

              int numberOfSheets = workbook.getNumberOfSheets();
               System.out.println("====================excel表格中sheet的数量："+numberOfSheets);

                //获取文档表格
                sheet = workbook.getSheetAt(0);
                //获取sheet的总行数
                //int rowsNum = sheet.getLastRowNum();
                //获取sheet的总列数
               int colsNum = sheet.getRow(3).getLastCellNum();

                String cellValue ;

                for(int i = 4 ; sheet.getRow(i)!=null ; i++)
                {
                    OrderInfo item=new OrderInfo();
                    //获取行对象
                    row = sheet.getRow(i);
                    //从第一列开始查下标为0
                    for(int j = 3 ; j < colsNum ; j++){
                        //获取单元格对象
                        cell = row.getCell(j);
                        //将对应的单元格信息读取出来后赋值给对应的属性


                        if(j==3){
                            cellValue = cell.getStringCellValue();
                            item.setPayment(cellValue);  ;
                        }
                       /* if(j==5){
                            cellValue = cell.getStringCellValue();
                                item.setPostFee(cellValue);
                            ;
                        }*/
                        if(j==7){
                            //将单元格内容设置为String，取的时候再转其他所需要的类型
                            cell.setCellType(CellType.STRING);;
                            cellValue = cell.getStringCellValue();
                            SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd");
                            Date d=null;
                            try {
                                d=sdf.parse(cellValue);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            item.setCreateTime(d); ;
                        }



                    }
                    //将对象放入集合中
                    list.add(item);
                }
            }

            //批量新增item
            for (int i = 0; i < list.size(); i++) {
                ordersService.saveItem(list.get(i));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                zfil.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }



    @RequestMapping(value="selectOrderList",method= RequestMethod.POST)
    @ResponseBody
    public DataGridJson selectOrderList(HttpServletRequest request, Integer page, Integer rows, String sort, String order, OrderInfo or){
        DataGridJson dj=new DataGridJson();
        or.setStart((page-1)*rows);
        or.setEnd((page*rows)+1);
        or.setSort(sort);
        or.setOrder(order);
        PageUtil rePage=new PageUtil();
        rePage=ordersService.selectOrderList(or);
        dj.setRows(rePage.getList());
        dj.setTotal(rePage.getTotalCount());
        return dj;
    }
    //压缩导出多表分sheet按数量分的Excel
    @RequestMapping("selectOrderListzipsheet")
    public void selectOrderListzipsheet(HttpServletRequest request, HttpServletResponse rep){
        List<OrderInfo> orderInfoList = ordersService.selectOrderList();
        String realPath = request.getServletContext().getRealPath("/");
        PoiZipUtil.downZip(orderInfoList,request,rep,realPath);
    }


    //跳转到订单展示页面
    @RequestMapping("ShowOrderZip")
    public String ShowOrderZip(){
        return "order/orderzip";
    }
    //跳转到订单展示页面
    @RequestMapping("toOrder")
    public String toOrder(){
        return "order/showOrder";
    }
    //跳转到5表订单展示页面
    @RequestMapping("toOrderfivebiao")
    public String toOrderfivebiao(){
        return "order/fivebiao_orderlist";
    }


//5表查

    @RequestMapping(value="selectOrderLists",method= RequestMethod.POST)
    @ResponseBody
    public DataGridJson selectOrderLists(HttpServletRequest request, Integer page, Integer rows, String sort, String order, PageUtil pageutil){
        DataGridJson dj=new DataGridJson();
        pageutil.setCpage(page);
        pageutil.setPageSize(rows);

        pageutil.setSort(sort);
        pageutil.setOrder(order);

        pageutil=ordersService.selectOrderLists(pageutil);
        dj.setRows(pageutil.getList());
        dj.setTotal(pageutil.getTotalCount());
        return dj;
    }
    //批量删除

    @RequestMapping(value="deleteorders",method= RequestMethod.POST)
    @ResponseBody
    public ReturnJson deleteorders(HttpServletRequest request, HttpServletResponse response, String strId){
        ReturnJson rj=new ReturnJson();
        Boolean boo=ordersService.deleteorders(request,strId);
        if(boo){
            rj.setMsg("删除成功");
        }else{
            rj.setMsg("不行就拉到");
        }
        return rj;
    }
}
