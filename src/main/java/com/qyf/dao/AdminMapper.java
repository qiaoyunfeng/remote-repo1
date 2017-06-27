package com.qyf.dao;

import com.qyf.pojo.Admin;
import java.math.BigDecimal;

public interface AdminMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin checkSysUser(Admin admin);
//新增注册用户
    void saveSysUser(Admin user);
}