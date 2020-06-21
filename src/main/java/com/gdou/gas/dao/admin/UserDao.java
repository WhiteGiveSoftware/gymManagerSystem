package com.gdou.gas.dao.admin;

import com.gdou.gas.entity.admin.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



/**
 * 用户数据库处理层
 * @author Administrator
 *
 */
@Repository
public interface UserDao extends JpaRepository<User, Long>{


    //按照用户名查找用户信息
    public User findByUsername(String username);


     //根据用户id查询
    @Query("select u from User u where id = :id")
    public User find(@Param("id")Long id);

}
