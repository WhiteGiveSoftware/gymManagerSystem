package com.gdou.gas.service.equipment;



import com.gdou.gas.dao.equipment.EquipmentDao;
import com.gdou.gas.entity.equipment.Equipment;
import com.gdou.gas.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 器材管理service
 *
 */
@Service
public class EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;


    //根据用户id查询
    public Equipment find(Long id){
        return equipmentDao.find(id);
    }


    //按照用户名查找用户
    public Equipment findByEquipmentname(String equipmentname){
        return equipmentDao.findByEquipmentname(equipmentname);
    }

    //按照用户查找，分页好的
    public PageBean<Equipment> findByname(Equipment equipment, PageBean<Equipment> pageBean){
        ExampleMatcher withMatcher = ExampleMatcher.matching().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Equipment> example = Example.of(equipment, withMatcher);
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
        Page<Equipment> findAll = equipmentDao.findAll(example, pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }


    //用户添加/编辑
    public Equipment save(Equipment equipment){
        return equipmentDao.save(equipment);
    }


    //获取所有的用户列表
    public List<Equipment> findAll(){
        return equipmentDao.findAll();
    }


    //根据id删除一条记录
   public void delete(Long id){
       equipmentDao.deleteById(id);
    }
}
