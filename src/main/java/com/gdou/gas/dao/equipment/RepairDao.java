package com.gdou.gas.dao.equipment;




import com.gdou.gas.entity.equipment.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * 维修数据库处理层
 * @author Administrator
 *
 */
@Repository
public interface RepairDao extends JpaRepository<Repair, Long>{


    //按照用户名查找用户信息
    public Repair findByname(String name);


     //根据用户id查询
    @Query("select i from Repair i where id = :id")
    public Repair find(@Param("id")Long id);

}
