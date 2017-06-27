package com.qyf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import com.qyf.pojo.Admin;
import com.qyf.pojo.SessionInfo;
import com.qyf.pojo.Tree;
import com.qyf.service.SysUserService;
import com.qyf.util.ConfigUtil;
import com.qyf.util.MD5Util;
import com.qyf.util.ReturnJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("user")
public class SysUserController {
	
	@Autowired
	private SysUserService userService;

	/**
	 * 左侧菜单tree  resourceType=0   菜单类型会显示在系统首页左侧菜单中
	 * @return
	 */
	@RequestMapping("selectMainMenu")
	@ResponseBody
	public List<Tree> selectMainMenu(HttpServletRequest request){

		SessionInfo sessionInfo =  (SessionInfo) request.getSession().getAttribute(ConfigUtil.getSessionInfoName());

		//String userId =  sessionInfo.getUser().getId();

		List<Tree> treeList =  userService.selectMainMenu();

		return treeList;

	}


	/**
	 * 注销退出系统
	 * @param request
	 */
	@RequestMapping("logoutSysUser")
	@ResponseBody
	public void logoutSysUser(HttpServletRequest request){
		request.getSession().removeAttribute(ConfigUtil.getSessionInfoName());
	}
	
	

	
	

	/**
	 * 校验用户---账户名称是否已存在
	 * @return
	 */
	@RequestMapping(value="checkSysUser",method=RequestMethod.POST)
	@ResponseBody
	public ReturnJson checkSysUser(Admin admin){
		ReturnJson rj = new ReturnJson();

		Admin u = userService.checkSysUser(admin);
		
	    if (null != u) {
			rj.setSuccess(false);//已经被注册
		}else{
			rj.setSuccess(true);
		}
		return rj;
	}
	
	
	
	/**
	 * 用户注册  ---添加用户
	 * @param
	 * @return
	 */
	@RequestMapping(value="registerSysUser",method=RequestMethod.POST)
	@ResponseBody
	public ReturnJson registerSysUser(Admin admin, HttpServletRequest request){
		ReturnJson  rj = new ReturnJson();
		//获取session中的验证码
		String code = (String) request.getSession().getAttribute("imageCode");
		//校验验证码是否正确
		if (null != admin && !"".equals(admin.getImgcode().trim()) && !"".equals(code)) {
			//验证码正确---不区分大小写
			if (admin.getImgcode().trim().toUpperCase().equals(code.toUpperCase())) {
				userService.saveSysUser(admin);
				// 主键id ,布尔类型 ,影响数据库的条数
				rj.setSuccess(true);
				rj.setMsg("注册成功");
			}else{
				rj.setSuccess(false);
				rj.setMsg("验证码错误");
			}
		}
		return rj;
		
	}

	/**
	 * 校验用户登录
	 * @return
	 */
	@RequestMapping(value="checkSysUserLogin",method=RequestMethod.POST)
	@ResponseBody
	public ReturnJson checkSysUserLogin(Admin user,HttpServletRequest request,Integer flag){
		ReturnJson rj = new ReturnJson();

		//获取session中的验证码
		String code = (String) request.getSession().getAttribute("imageCode");


		if (null !=flag && 1 !=flag) {
			//校验验证码是否正确
			if (null != user && !"".equals(user.getImgcode().trim()) && !"".equals(code)) {
				//验证码正确---不区分大小写
				if (user.getImgcode().trim().toUpperCase().equals(code.toUpperCase())) {
					userFeng(user,rj,request);
				}
			}
		} else {
			userFeng(user,rj,request);
		}



		return rj;
	}


	public void userFeng(Admin user,ReturnJson rj,HttpServletRequest request) {
		//u 查询数据库的信息
		//user 用户登录时表单


		if (user.getPhone() != null && user.getPhone() != "" && user.getPhone().contains("@163") || user.getPhone().contains("@sina")) {
			user.setEmail(user.getPhone());
			user.setPhone("");
		}
		Admin u = userService.checkSysUser(user);

			if (null != u && user.getPassword() != null && user.getPassword() != "") {//用户名正确
				if (u.getPassword().equals(MD5Util.md5(user.getPassword()))) {//密码正确
					rj.setSuccess(true);
					rj.setMsg("登录成功");
					//登录成功之后将用户信息存放到session中
					SessionInfo sessionInfo = new SessionInfo();
					//用户密码置 空
					u.setPassword(null);
					sessionInfo.setUser(u);
					request.getSession().setAttribute(ConfigUtil.getSessionInfoName(), sessionInfo);
				} else {
					rj.setSuccess(false);
					rj.setMsg("密码错误");
				}
			} else {//用户名粗我
				rj.setSuccess(false);
				rj.setMsg("用户名错误");
			}
		}


	//跳转到种类查询页面

	@RequestMapping("toitemcatList")
	public String toitemList(){

		return "user/rtemcatList";
	}

	//跳转到查询页面

	@RequestMapping("toitemListjsp")
	public String toitemListjsp(){
		
		return "user/itemList";
	}
//跳转到新增页面
	@RequestMapping("showItemForm")
	public String showItemForm(){

		return "user/itemForm";
	}
	

}

/*public static void main(String[] args){
//json字符串
//"{name:张三,age:15,sex:男}" 只认识双引号
//name:张三,age:15,sex:男
String s = "{"+"name:\"张三\",age:15,sex:\"男\""+"}";
//json字符串---->json对象
JSONObject parseObject = JSONObject.parseObject(s);
//json对象  keySet() 获取所有key的set集合
Set<String> keySet = parseObject.keySet();
// 获取key对应的set集合的迭代器
Iterator<String> iterator = keySet.iterator();
while (iterator.hasNext()) {
	String key = iterator.next();
	System.out.println(key+"---"+parseObject.get(key));
}
}*/
