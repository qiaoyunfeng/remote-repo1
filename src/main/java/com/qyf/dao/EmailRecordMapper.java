package com.qyf.dao;

import com.qyf.pojo.EmailRecord;
import java.math.BigDecimal;

public interface EmailRecordMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(EmailRecord record);

    int insertSelective(EmailRecord record);

    EmailRecord selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(EmailRecord record);

    int updateByPrimaryKey(EmailRecord record);
}