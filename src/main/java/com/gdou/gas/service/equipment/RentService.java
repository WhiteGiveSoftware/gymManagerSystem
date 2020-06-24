package com.gdou.gas.service.equipment;



import com.gdou.gas.dao.equipment.RentDao;
import com.gdou.gas.entity.equipment.Rent;
import com.gdou.gas.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 租用器材管理service
 *
 */
@Service
public class RentService {
    @Autowired
    private RentDao rentDao;


    //根据用户id查询
    public Rent find(Long id){
        return rentDao.find(id);
    }


    //按照用户名查找用户n
    public Rent findByUsername(String name){
        return rentDao.findByname(name);
    }

    //按照用户查找，分页好的n
    public PageBean<Rent> findByname(Rent rent, PageBean<Rent> pageBean){
        ExampleMatcher withMatcher = ExampleMatcher.matching().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Rent> example = Example.of(rent, withMatcher);
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
        Page<Rent> findAll = rentDao.findAll(example, pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }


    //用户添加/编辑
    public Rent save(Rent rent){
        return rentDao.save(rent);
    }


    //获取所有的用户列表
    public List<Rent> findAll(){
        return rentDao.findAll();
    }


    //根据id删除一条记录
   public void delete(Long id){
       rentDao.deleteById(id);
    }
}
