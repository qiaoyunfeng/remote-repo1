package com.qyf.service;

import com.qyf.dao.EmailRecordMapper;
import com.qyf.pojo.EmailRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Created by Administrator on 2017/6/7.
 */
@Service
public class EmailRecordServiceImpl  implements  EmailRecordService{
    @Autowired
    private EmailRecordMapper emailRecordMapper;

    @Override
    public void addMailHistroy(EmailRecord mailHistory) {
       emailRecordMapper.insertSelective(mailHistory);
    }
}
