package com.gdou.gas.dao.equipment;




import com.gdou.gas.entity.equipment.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * 租用数据库处理层
 * @author Administrator
 *
 */
@Repository
public interface RentDao extends JpaRepository<Rent, Long>{


    //按照用户名查找用户信息
    public Rent findByname(String name);


     //根据用户id查询
    @Query("select r from Rent r where id = :id")
    public Rent find(@Param("id")Long id);

}
