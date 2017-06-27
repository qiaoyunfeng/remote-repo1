package com.qyf.dao;

import com.qyf.pojo.WorkBook;
import java.math.BigDecimal;

public interface WorkBookMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(WorkBook record);

    int insertSelective(WorkBook record);

    WorkBook selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(WorkBook record);

    int updateByPrimaryKey(WorkBook record);
}