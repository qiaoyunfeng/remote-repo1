package com.qyf.service;

import com.qyf.pojo.Admin;
import com.qyf.pojo.Tree;
import com.qyf.util.PageUtil;

import java.util.List;


public interface SysUserService {



	//注册用户--新增
	void saveSysUser(Admin user);

	//校验用户
	Admin checkSysUser(Admin user);

	//左侧菜单树
    List<Tree> selectMainMenu();
}
