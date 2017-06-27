package com.qyf.quartz;

import com.qyf.pojo.EmailRecord;
import com.qyf.pojo.Item;
import com.qyf.service.EmailRecordService;
import com.qyf.service.ItemService;
import com.qyf.service.OrdersService;
import com.qyf.util.IpUtil;
import com.qyf.util.JavaMailUtil;
import com.qyf.util.NewPoiUtil;
import com.qyf.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/8.
 */
public class QuartzJobs {
    /*利用RequestContextListener监听器获取request对象的方式一 */
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ItemService itemService;
    @Autowired
    private EmailRecordService emailRecordService;
    @Autowired
    private OrdersService ordersService;
    private static int counter = 0;
    public void execute(){
        int isSuccess = 0;
        System.err.println("( 第" + counter++ + "个)");
        PageUtil pageitem=new PageUtil();
        pageitem.setCpage(1);
        pageitem.setPageSize(3);
        pageitem= itemService.selectItrmList(pageitem);
        List<Item> itemList = pageitem.getList();
//项目路径
       String href= "F:\\IdeaProjects\\qyfparent\\qyf-moudules\\md-backstage\\target\\md-backstage";
        String excelPath =   NewPoiUtil.downExcel(itemList,href);
        EmailRecord mailHistory = new EmailRecord();
        mailHistory.setAddresser("gqy_you@163.com");//发件人
        mailHistory.setSendPwd("gaoqiyou");
        mailHistory.setRecipients("522047820@qq.com");//收件人
        // mailHistory.setSendpeople("634997327@qq.com,1521170425@qq.com");//密送人
        //  mailHistory.setCopyrecipients("634997327@qq.com,1521170425@qq.com");//抄送人
        mailHistory.setEmailtheme("啊哈");//邮件主题
        mailHistory.setMailContent("奶奶个啥子哦信息excel<a href='http://weibo.com/login.php'>微博</a>");
        mailHistory.setUsername("大爷");//登录用户名
        mailHistory.setAccessoryname(excelPath);//附件名称
        mailHistory.setSuccess(String.valueOf(isSuccess));
        mailHistory.setIp("127.0.0.1");//Ip
        mailHistory.setDate(new Date());
         JavaMailUtil.sendJavaMail(request,mailHistory);
        //mailHistory存储到 邮件历史记录表
        emailRecordService.addMailHistroy(mailHistory);
    }

   public void  order(){



   }



}
