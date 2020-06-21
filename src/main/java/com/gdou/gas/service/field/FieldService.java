package com.gdou.gas.service.field;


import com.gdou.gas.dao.field.FieldDao;
import com.gdou.gas.entity.field.Field;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 通过id查找某一场地
     * @param id
     * @return
     */
    public Field findByFieldId(Long id){
        return fieldDao.findByFieldId(id);
    }

    /**
     * 根据id删除一个场地
     */
    public void deleteById(Long id){
        fieldDao.deleteById(id);
    }
}
