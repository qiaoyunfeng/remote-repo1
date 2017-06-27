package com.qyf.dao;

import com.qyf.pojo.SysresourceTree;

import java.util.List;

public interface SysresourceTreeMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysresourceTree record);

    int insertSelective(SysresourceTree record);

    List<SysresourceTree> selectByPrimaryKey();

    int updateByPrimaryKeySelective(SysresourceTree record);

    int updateByPrimaryKey(SysresourceTree record);
}