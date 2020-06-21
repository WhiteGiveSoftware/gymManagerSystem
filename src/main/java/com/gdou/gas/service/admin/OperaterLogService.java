package com.gdou.gas.service.admin;

import com.gdou.gas.dao.admin.OperaterLogDao;
import com.gdou.gas.entity.admin.OperaterLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//后台操作类 数据库操作service
@Service
public class OperaterLogService {
    @Autowired
    private OperaterLogDao operaterLogDao;

    public OperaterLog save(OperaterLog operaterLog){
        return operaterLogDao.save(operaterLog);

    }
    //根据ID查询单条数据
    public OperaterLog findById(Long id){
        return operaterLogDao.find(id);
    }
     //返回所有数据
    public List<OperaterLog> findAll(){
        return operaterLogDao.findAll();
    }
    //删除单条记录
    public void delete(Long id){
        operaterLogDao.deleteById(id);

    }
    //清空所有记录
    public void deleteAll(){
        operaterLogDao.deleteAll();

    }
    /**
     * 获取指定条数的操作日志列表
     */
    public List<OperaterLog> findLastestLog(int size){
        return operaterLogDao.findLastestLog(size);
    }


}
