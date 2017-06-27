package com.qyf.service;

import java.math.BigDecimal;
import java.util.*;

import com.qyf.dao.AdminMapper;

import com.qyf.dao.Item_catagoryMapper;
import com.qyf.dao.SysresourceTreeMapper;
import com.qyf.pojo.Admin;
import com.qyf.pojo.Item_catagory;
import com.qyf.pojo.SysresourceTree;
import com.qyf.pojo.Tree;
import com.qyf.util.MD5Util;
import com.qyf.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private Item_catagoryMapper item_catagoryMapper;
	@Autowired
	private SysresourceTreeMapper sysresourceTreeMapper;



	
	@Override
	public void saveSysUser(Admin user) {
		//随机数id
		// user.setId(UUID.randomUUID().toString() );
//		密码加密
		 user.setPassword(MD5Util.md5(user.getPassword()));

		 //创建时间 
		 user.setCreated(new Date());
		 //修改时间
		 user.setUpdated(new Date());
		//登录时间
		user.setLonginTime(new Date());
		adminMapper.saveSysUser(user);
	}
//查询用户是不是存在
	@Override
	public Admin checkSysUser(Admin admin) {

		return adminMapper.checkSysUser(admin);
	}

	@Override
	public List<Tree> selectMainMenu(){
		List<SysresourceTree> resourceList =sysresourceTreeMapper.selectByPrimaryKey();
		// 去重方式一
		resourceList = new ArrayList<SysresourceTree>(new HashSet<SysresourceTree>(resourceList));
		List<Tree> treeList = new ArrayList<Tree>();
		Tree fjtree = null;
		Tree zjtree = null;
		List<Tree> childTree = null;
		HashMap<String, String> url = null;
		for (SysresourceTree r : resourceList) {
			fjtree = new Tree();
			if (null == r.getPid() ) {
				childTree = new ArrayList<>();
				fjtree.setId(Integer.valueOf(r.getId()) );
				fjtree.setPid(Integer.valueOf(r.getPid()));
				fjtree.setText(r.getName());
				fjtree.setState("closed");
				fjtree.setIconCls(r.getIconcls());
				for (SysresourceTree zir : resourceList) {
					if (null != zir.getPid() && zir.getPid().equals(r.getId())) {
						zjtree = new Tree();
						zjtree.setId(Integer.valueOf(zir.getId()));
						zjtree.setPid(Integer.valueOf(zir.getPid()));
						zjtree.setText(zir.getName());
						zjtree.setIconCls(zir.getIconcls());
						url = new  HashMap<>();
						url.put("url", zir.getUrl());
						zjtree.setAttributes(url);
						childTree.add(zjtree);
					}
				}
				fjtree.setChildren(childTree);
			}else{
				continue;
			}
			treeList.add(fjtree);
		}
		return treeList;
	}
}
