package com.gdou.gas.service.field;


import com.gdou.gas.dao.field.FieldDao;
import com.gdou.gas.entity.field.Field;
import com.gdou.gas.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {
    @Autowired
    private FieldDao fieldDao;

    /**
     * 获取所有的场地信息
     */
    public List<Field> findAll(){
        return fieldDao.findAll();
    }

    /**
     *
     */
    public Field findByFieldName(String fieldName){
        return fieldDao.findByFieldName(fieldName);
    }
    /**
     * 分页查找
     */
    public PageBean<Field> findByField(Field field, PageBean<Field> pageBean){
        ExampleMatcher withMatcher = ExampleMatcher.matching().withMatcher("id",
                ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Field> example =  Example.of(field, withMatcher);
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
        Page<Field> findAll =  fieldDao.findAll(example, pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }


    /**
     * 通过id查找某一场地
     * @param id
     * @return
     */
    public Field find(Long id){
        return fieldDao.find(id);
    }

    /**
     * 根据id删除一个场地
     */

    public void delete(Long id){
        fieldDao.deleteById(id);
    }

    /**
     * 编辑
     */
    public Field save(Field field){
        return fieldDao.save(field);
    }
}
