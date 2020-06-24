package com.gdou.gas.service.equipment;



import com.gdou.gas.dao.equipment.RepairDao;
import com.gdou.gas.entity.equipment.Repair;
import com.gdou.gas.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 维修器材管理service
 *
 */
@Service
public class RepairService {
    @Autowired
    private RepairDao repairDao;


    //根据用户id查询
    public Repair find(Long id){
        return repairDao.find(id);
    }


    //按照用户名查找用户N
    public Repair findByName(String name){
        return repairDao.findByname(name);
    }

    //按照用户查找，分页好的
    public PageBean<Repair> findByname(Repair repair, PageBean<Repair> pageBean){
        ExampleMatcher withMatcher = ExampleMatcher.matching().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Repair> example = Example.of(repair, withMatcher);
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
        Page<Repair> findAll = repairDao.findAll(example, pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }


    //用户添加/编辑
    public Repair save(Repair repair){
        return repairDao.save(repair);
    }


    //获取所有的用户列表
    public List<Repair> findAll(){
        return repairDao.findAll();
    }


    //根据id删除一条记录
   public void delete(Long id){
       repairDao.deleteById(id);
    }
}
