package com.gdou.gas.service.home;

import com.gdou.gas.dao.home.AccoutDao;
import com.gdou.gas.entity.admin.User;
import com.gdou.gas.entity.home.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 客户管理service
 *
 */
@Service
public class AccoutService {
    @Autowired
    private AccoutDao accoutDao;

    //根据客户名查找客户
    public Account findByUsername(String username){
        return accoutDao.findByUsername(username);
    }

    //根据激活码查找客户
    public Account findByCode(String code){
        return accoutDao.find(code);
    }

    //用户添加/编辑
    public Account save(Account account){
        return accoutDao.save(account);
    }


}
