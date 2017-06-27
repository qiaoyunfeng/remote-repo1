package com.qyf.util;

import com.qyf.pojo.OrderInfo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/10.
 */
public class PoiZipUtiltype {


    public static void downZip(List<OrderInfo> list, HttpServletRequest req, HttpServletResponse resp, String realPath){



            List<String> fileName=new ArrayList<>();
            String path=realPath+"zipwjj";
            int m=0;


       for (int i = 0; i <=list.size() ; i++) {
                if (i == list.size() && i % 200 != 0) {
               String excelName = PoiZipUtiltype.makeExcel(m, i, list, path);
               fileName.add(excelName);
                }
           if(i%200==0 && i!=0){
                String excelName = PoiZipUtiltype.makeExcel(m, i, list, path);
                m=i;
                fileName.add(excelName);
            }
        }

        ZipUtill.ZipFiles(req, resp, fileName, "订单信息表.zip", path);
    }



    private static String makeExcel(int m, int j, List<OrderInfo> list, String path) {
        List<OrderInfo> list1 = list.subList(m, j);
        //设置sheet的名称
        String title = "商品表";
        //设置文件的名称（注意需加上后缀.xls或者.xlsx，用于后面对Excel的版本的判断）
        String adr = "商品信息表" + m + ".xls";
        int sheetNum = 1;//用于多sheet的名称拼接
        int bodyRowCount = 2;//正文内容行号
        //增加map集合，用来作为Excel表格中的表头
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        map.put("订单id", 5000);
        map.put("订单状态", 5000);
        map.put("商品卖点", 5000);
        map.put("商品价格", 5000);
        map.put("库存数量", 5000);
        map.put("所属类目", 5000);
        map.put("商品状态", 5000);
        map.put("生产日期", 5000);
        map.put("销售日期", 5000);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(title + sheetNum);
        //设置单元格日期格式
        dataStyle(workbook, sheet, 9);
        dataStyle(workbook, sheet, 10);
        headerAndfooter(sheet, list1);

        titleRow(workbook, map, sheet);//写入标题

        long a = 0;
        // 根据数据库查出的集合长度设置行的个数并创建
        for (int i = 0; i < list1.size(); i++) {
            //创建行 ， Row 和 Cell 都是从0开始计数的，0行用在了列头显示，所以i从2开始

            HSSFRow row = sheet.createRow(bodyRowCount);

            // 在row行上创建一个个单元格
            HSSFCell cell = row.createCell(2);
            cell.setCellValue(list1.get(i).getOrderId().toString());
            cell.setCellStyle(contentStyle(workbook));


            cell = row.createCell(3);
            cell.setCellValue(list1.get(i).getPay().getDescribe());
            cell.setCellStyle(lieStyle(workbook));

//            cell = row.createCell(4);
//            cell.setCellValue(list1.get(i).getSellPoint());
//            cell.setCellStyle(lieStyle(workbook));
//
//            cell = row.createCell(5);
//            cell.setCellValue(list1.get(i).getPrice());
//            cell.setCellStyle(lieStyle(workbook));
//
//
//            cell = row.createCell(6);
//            cell.setCellValue(list1.get(i).getNum());
//
//            cell = row.createCell(7);
//            if (list.get(i).getCat().getName() != null) {
//                cell.setCellValue(list1.get(i).getCat().getName());
//                cell.setCellStyle(contentStyle(workbook));
//            } else {
//                cell.setCellValue("");
//                cell.setCellStyle(contentStyle(workbook));
//            }
//
//            String status = "";
//            cell = row.createCell(8);
//            if (list1.get(i).getStatus() == 1) {
//                status = "正在售卖中";
//            } else {
//                status = "已售罄";
//            }
//            cell.setCellValue(status);
//
//            cell = row.createCell(9);
//            cell.setCellValue(list.get(i).getCreated());
//
//            cell = row.createCell(10);
//            cell.setCellValue(list.get(i).getUpdated());

            bodyRowCount++;
            if (i != 0) {
                if (i % 3 == 0) {//每个工作薄显示50000条数据
                    sheet = null;
                    sheetNum++;//工作薄编号递增1
                    bodyRowCount = 2;
                    sheet = (HSSFSheet) workbook.createSheet(title + sheetNum);//创建一个新的sheet
                    titleRow(workbook, map, sheet);//写入标题
                    //设置单元格日期格式
                    dataStyle(workbook, sheet, 9);
                    dataStyle(workbook, sheet, 10);
                    //a=0;
                }
            }
            //a++;
        }

        //创建输出流输出下载
        //excel输出的文件夹
//        String path = realPath + "/" + "excel";
        File f = new File(path);

        if (!f.exists()) {
            f.mkdirs();
        }

        //excel文件名称
        String excelName = adr;

        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(path + "/" + excelName));
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //                k++;

//        fileList.add(excelName);
        return excelName;
    }





    //设置页眉页脚
    private static void headerAndfooter(Sheet sheet, List<OrderInfo> list){
        Header header = sheet.getHeader();
        header.setLeft("第一页");
        Footer footer = sheet.getFooter();
        footer.setRight( "总共 " + list.size() + " 条数据 ");
    }




    //设置单元格日期格式
    private static void dataStyle(HSSFWorkbook workbook, HSSFSheet sheet, int i) {
        HSSFCellStyle DateStyle = workbook.createCellStyle();
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        DateStyle.setDataFormat(dataFormat.getFormat("yyyy/mm/dd"));
        DateStyle.setAlignment(HorizontalAlignment.CENTER);
        sheet.setDefaultColumnStyle(i,DateStyle);
    }

    //表头样式
    private static CellStyle titleStyle(HSSFWorkbook workbook){
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 20);
        font.setColor(IndexedColors.RED.getIndex());
        font.setBold(true);
        //设置列头样式
        HSSFCellStyle titleCellStyleColumn = workbook.createCellStyle();
        titleCellStyleColumn.setFont(font);
        titleCellStyleColumn.setBorderLeft(BorderStyle.THIN);
        titleCellStyleColumn.setBorderRight(BorderStyle.THIN);
        titleCellStyleColumn.setBorderTop(BorderStyle.THIN);
        titleCellStyleColumn.setBorderBottom(BorderStyle.THIN);
        titleCellStyleColumn.setAlignment(HorizontalAlignment.CENTER);
        titleCellStyleColumn.setFillForegroundColor((short) 15);// 设置背景色
        titleCellStyleColumn.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return titleCellStyleColumn;
    }

    //列名的样式
    private static CellStyle topStyle(HSSFWorkbook workbook){

        // createFont()创建字体并设置样式

        HSSFFont fontColumn = workbook.createFont();
        fontColumn.setFontHeightInPoints((short) 15);
        fontColumn.setColor(IndexedColors.BLACK.getIndex());
        fontColumn.setBold(true);

        //设置列头样式
        HSSFCellStyle cellStyleColumn = workbook.createCellStyle();
        cellStyleColumn.setFont(fontColumn);
        cellStyleColumn.setBorderLeft(BorderStyle.THIN);
        cellStyleColumn.setBorderRight(BorderStyle.THIN);
        cellStyleColumn.setBorderTop(BorderStyle.THIN);
        cellStyleColumn.setBorderBottom(BorderStyle.THIN);
        cellStyleColumn.setAlignment(HorizontalAlignment.CENTER);
        return cellStyleColumn;
    }


    //内容样式
    private static CellStyle contentStyle(HSSFWorkbook workbook){
        //内容样式

        HSSFFont fontColumn = workbook.createFont();
        fontColumn.setFontHeightInPoints((short) 10);
        fontColumn.setColor(IndexedColors.BLACK.getIndex());
        fontColumn.setBold(false);

        HSSFCellStyle cellStyleContent = workbook.createCellStyle();
        cellStyleContent.setFont(fontColumn);
        cellStyleContent.setBorderLeft(BorderStyle.THIN);
        cellStyleContent.setBorderRight(BorderStyle.THIN);
        cellStyleContent.setBorderTop(BorderStyle.THIN);
        cellStyleContent.setBorderBottom(BorderStyle.THIN);
        cellStyleContent.setAlignment(HorizontalAlignment.CENTER);

        return cellStyleContent;
    }

    //自定义列样式
    private static CellStyle lieStyle(HSSFWorkbook workbook){
        HSSFFont fontContent = workbook.createFont();
        fontContent.setFontHeightInPoints((short) 10);
        fontContent.setColor(IndexedColors.BLACK.getIndex());
        fontContent.setBold(false);

        HSSFCellStyle cellStyleContent = workbook.createCellStyle();
        cellStyleContent.setFont(fontContent);
        cellStyleContent.setBorderLeft(BorderStyle.THIN);
        cellStyleContent.setBorderRight(BorderStyle.THIN);
        cellStyleContent.setBorderTop(BorderStyle.THIN);
        cellStyleContent.setBorderBottom(BorderStyle.THIN);
        cellStyleContent.setAlignment(HorizontalAlignment.LEFT);
        cellStyleContent.setFillForegroundColor((short) 15);// 设置背景色
        cellStyleContent.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        return cellStyleContent;
    }

    //设置表头和列名
    private static void titleRow(HSSFWorkbook workbook,Map<String,Integer> map,HSSFSheet sheet){
        //合并单元格（起始行，终止行，起始列，终止列  map.size()-1）
        CellRangeAddress cra=new CellRangeAddress(0, 0, 2,11 );
        sheet.addMergedRegion(cra);

        HSSFRow topRow = sheet.createRow(0);
        HSSFCell topCell = topRow.createCell(2);
        topCell.setCellValue("订单信息表");
        topCell.setCellStyle(titleStyle(workbook));

        // 创建表头并设置名称通过createRow(1)来获取表格的第二行
        HSSFRow columnRow = sheet.createRow(1);
        int m = 2;
        //创建表头单元格并对单元格循环赋值
        for(String s : map.keySet()){
            //设置宽度
            sheet.setColumnWidth(m,map.get(s));
            //创建单元格
            HSSFCell columnCell = columnRow.createCell(m);
            //给单元格赋值
            columnCell.setCellValue(s);
            //将表头的样式赋值给表头
            columnCell.setCellStyle(topStyle(workbook));
            m++;
        }
    }

}
