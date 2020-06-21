package com.gdou.gas.service.admin;

import com.gdou.gas.dao.admin.UserDao;
import com.gdou.gas.entity.admin.User;
import com.gdou.gas.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户管理service
 *
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;


    //根据用户id查询
    public User find(Long id){
        return userDao.find(id);
    }


    //按照用户名查找用户
    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }

    //按照用户查找，分页好的
    public PageBean<User> findByname(User user,PageBean<User> pageBean){
        ExampleMatcher withMatcher = ExampleMatcher.matching().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<User> example = Example.of(user, withMatcher);
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
        Page<User> findAll = userDao.findAll(example, pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }


    //用户添加/编辑
    public User save(User user){
        return userDao.save(user);
    }


    //获取所有的用户列表
    public List<User> findAll(){
        return userDao.findAll();
    }


    //根据id删除一条记录
   public void delete(Long id){
        userDao.deleteById(id);
    }
}
