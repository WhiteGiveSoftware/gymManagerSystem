package com.gdou.gas.dao.equipment;



import com.gdou.gas.entity.equipment.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * 器材数据库处理层
 * @author Administrator
 *
 */
@Repository
public interface EquipmentDao extends JpaRepository<Equipment, Long>{


    //按照用户名查找用户信息
    public Equipment findByEquipmentname(String name);


     //根据用户id查询
    @Query("select e from Equipment e where id = :id")
    public Equipment find(@Param("id")Long id);

}
