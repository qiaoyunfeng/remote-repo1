package com.qyf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qyf.pojo.Admin;
import com.qyf.pojo.Item;
import com.qyf.pojo.Tree;
import com.qyf.util.ConfigUtil;
import com.qyf.util.ReturnJson;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */

@Controller
@RequestMapping("httpsss")
public class Http {
    //http图片下载
    @RequestMapping(value = "httpdownImg", method = RequestMethod.GET)
    public void httpdownImg(String imgName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8080//item/httpdownImg.do");
        //向get请求的heand头信息传递参数
        httpGet.setHeader("imgName", imgName);
        InputStream inputStream = null;
        ServletOutputStream outputStream = null;
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            if (null != entity) {
                //获取服务段的文件流
                inputStream = entity.getContent();
                outputStream = response.getOutputStream();
                response.reset();
                response.setContentType("appliacation/x-msdownload");
                response.setHeader("Content-Disposition", "attachment:filename=" + imgName);
                byte[] b = new byte[1024];
                int s = 0;
                while ((s = inputStream.read(b)) != -1) {
                    outputStream.write(b, 0, s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                outputStream.flush();
                outputStream.close();
                outputStream = null;
            }
            if (null != inputStream) {
                inputStream.close();
                inputStream = null;
            }
            if (null != httpClient) {
                httpClient.close();
                httpClient = null;
            }
        }
    }

    //http图片展示
    @RequestMapping(value = "showimg", method = RequestMethod.GET)

    public void showimg(String imgName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8080//item/httpdownImg.do");
        //向get请求的heand头信息传递参数
        httpGet.setHeader("imgName", imgName);
        InputStream inputStream = null;
        ServletOutputStream outputStream = null;
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            if (null != entity) {
                //获取服务段的文件流
                inputStream = entity.getContent();
                outputStream = response.getOutputStream();

                byte[] b = new byte[1024];
                int s = 0;
                while ((s = inputStream.read(b)) != -1) {
                    outputStream.write(b, 0, s);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                outputStream.flush();
                outputStream.close();
                outputStream = null;
            }
            if (null != inputStream) {
                inputStream.close();
                inputStream = null;
            }
        }
    }

    //http图片上传
    @RequestMapping(value = "httpuploadImg", method = RequestMethod.POST)
    @ResponseBody
    public String httpuploadImg(@RequestParam("image") MultipartFile image, HttpServletResponse response) throws IOException {
        ReturnJson rj = new ReturnJson();
        String s1 = "";
        //获取文件名
        String Filename = image.getOriginalFilename();
        String s = Filename.substring(Filename.lastIndexOf("."));
        File tempFile = null;
        CloseableHttpClient httpClient = null;
        try {
            if (null != Filename) {
                //创建临时文件
                tempFile = File.createTempFile("tmp", s);
                //将MultipartFile 文件写入临时文件
                image.transferTo(tempFile);
                tempFile.deleteOnExit();
            }
            httpClient = HttpClients.createDefault();

            HttpPost httpPost = new HttpPost("http://localhost:8080//item/httpuploadimages.do");
            //创建MultipartFile
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            //设置浏览器兼容模式
            multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            //存放文本
            multipartEntityBuilder.addTextBody("Filename", Filename, ContentType.create("text/plain", Charset.forName("UTF-8")));
            //存放文件
//文件--->文件流
            multipartEntityBuilder.addPart("filebody", new FileBody(tempFile));
            HttpEntity entity = multipartEntityBuilder.build();
            httpPost.setEntity(entity);
            //设置头信息
            //httpPost.setHeader("Test","Helloworld");
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

            //获取response响应头信息
            //  Header[] allHeaders = httpResponse.getAllHeaders();
            // for (int i = 0; i < allHeaders.length; i++) {
            // System.err.print(allHeaders[i]);
            //  }
            //获取响应实体
            HttpEntity entity1 = httpResponse.getEntity();
            if (null != entity1) {
                s1 = EntityUtils.toString(entity1);
                //   rj = JSON.parseObject(s1,ReturnJson.class);
                System.err.print(rj);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != httpClient) {
                httpClient.close();
            }
        }
        return s1;
    }

    //登录
    @RequestMapping(value = "httpuser", method = RequestMethod.POST)
    @ResponseBody
    public ReturnJson httpuser(Admin user) {
        ReturnJson rj = new ReturnJson();
        String s = "";
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost

        HttpPost httppost = new HttpPost("http://localhost:8080//user/checkSysUserLogin.do");
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("phone", user.getPhone()));
        formparams.add(new BasicNameValuePair("password", user.getPassword()));
        UrlEncodedFormEntity uefEntity;
        CloseableHttpResponse response = null;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            System.out.println("请求地址" + httppost.getURI());
            //发送请求
            response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    s = EntityUtils.toString(entity, "UTF-8");
                    System.out.println("--------------------------------------");
                    System.out.println("Response content: " + s);
                    System.out.println("--------------------------------------");
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (s.contains("true")) {
            rj.setSuccess(true);
        } else {
            rj.setSuccess(false);
            rj.setMsg("账号或密码错误");
        }


        return rj;
    }

    //跳查询页面
    @RequestMapping(value = "tohttpitemlist", method = RequestMethod.GET)
    public String tohttpitemlist() {
        return "user/httpitemList";
    }

    //查询
    @RequestMapping("httpquery")
    @ResponseBody
    public String httpquery(Integer page, Integer rows) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://localhost:8080//item/selectItrmList.do");
        String s = "";
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("page", page.toString()));
        formparams.add(new BasicNameValuePair("rows", rows.toString()));
        UrlEncodedFormEntity uefEntity;
        CloseableHttpResponse response = null;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            System.out.println("请求地址" + httppost.getURI());
            //发送请求
            response = httpclient.execute(httppost);
            try {

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    s = EntityUtils.toString(entity, "UTF-8");
                    System.out.println("--------------------------------------");
                    System.out.println("Response content: " + s);
                    System.out.println("--------------------------------------");
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    //回写
    @RequestMapping(value = "toUpdate", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toUpdate(String id) {
        ModelAndView mv = new ModelAndView();
        Item i = null;
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet("http://localhost:8080//item/httphuixieupdate.do?id=" + id);
            CloseableHttpResponse Response = null;
            Response = httpClient.execute(httpget);
            HttpEntity entity = Response.getEntity();
            if (null != entity) {
                String s = EntityUtils.toString(entity, "UTF-8");
                i = JSON.parseObject(s, Item.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        mv.setViewName("user/http_update_item");
        mv.addObject("item", i);
        return mv;
    }

    ;

    //http删除
    @RequestMapping(value = "httpdelete", method = RequestMethod.POST)
    @ResponseBody
    public String httpquery(Integer id) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://localhost:8080//item/deleteItem.do");
        String s = "";
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("id", id.toString()));
        UrlEncodedFormEntity uefEntity;
        CloseableHttpResponse response = null;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            System.out.println("请求地址" + httppost.getURI());
            //发送请求
            response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    s = EntityUtils.toString(entity, "UTF-8");
                    System.out.println("--------------------------------------");
                    System.out.println("Response content: " + s);
                    System.out.println("--------------------------------------");
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    ;

    //跳转到新增页面
    @RequestMapping(value = "toinsertItemForm", method = RequestMethod.GET)
    public String toinsertItemForm() {
        return "user/http_itemForm";
    }

    /**
     * 新增商品或 普通修改
     *
     * @param item
     * @return
     */
    @RequestMapping(value = "addOrUpdateItem", method = RequestMethod.POST)
    @ResponseBody
    public String httpsaveItemForm(Item item) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://localhost:8080//item/saveOrUpdateItem.do");
        String s1 = JSONObject.toJSONStringWithDateFormat(item, "yyyy-MM-dd");
        String s = "";
        // 1.创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("item", s1));
        // UrlEncodedFormEntity是模拟form表单的形式
        UrlEncodedFormEntity uefEntity;
        CloseableHttpResponse response = null;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            //将参数 赋值给 post请求实例
            httppost.setEntity(uefEntity);
            System.out.println("请求地址" + httppost.getURI());
            //发送请求
            response = httpclient.execute(httppost);
            try {
                //响应得到的实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    s = EntityUtils.toString(entity, "UTF-8");
                    System.out.println("--------------------------------------");
                    System.out.println("Response content: " + s);
                    System.out.println("--------------------------------------");
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    ;

    //行内修改 参数json字符串
    @RequestMapping(value = "httpupdate", method = RequestMethod.POST)
    @ResponseBody
    public String httpupdate(String item) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://localhost:8080//item/updateItemInfo.do");
        String s = "";
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("item", item));
        // UrlEncodedFormEntity是模拟form表单的形式
        UrlEncodedFormEntity uefEntity;
        CloseableHttpResponse response = null;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            System.out.println("请求地址" + httppost.getURI());
            //发送请求
            response = httpclient.execute(httppost);
            try {
                //响应得到的实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    s = EntityUtils.toString(entity, "UTF-8");
                    System.out.println("--------------------------------------");
                    System.out.println("Response content: " + s);
                    System.out.println("--------------------------------------");
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    /*
           查询商品类目的下拉树
       * */
    @RequestMapping(value = "selectItemCatTree", method = RequestMethod.POST)
    @ResponseBody
    public List<Tree> selectItemCatTree() {
        List<Tree> treeList = null;
        CloseableHttpClient closeableHttpClient = null;
        try {
            //创建httpClient 实例
            closeableHttpClient = HttpClients.createDefault();
            //创建 post请求实例
            HttpPost httpPost = new HttpPost();
            httpPost.setURI(URI.create("http://localhost:8080/item/selectittemcatList.do"));
            //执行post请求
            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String s = response.getStatusLine().toString();
            if (entity != null) {
                String s1 = EntityUtils.toString(entity);
                //json字符串 --- >java对象
                treeList = JSONArray.parseArray(s1, Tree.class);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != closeableHttpClient) {
                try {
                    closeableHttpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return treeList;
    }


}
