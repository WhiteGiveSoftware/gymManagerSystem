package com.gdou.gas.dao.home;

import com.gdou.gas.entity.home.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccoutDao  extends JpaRepository<Account, Long> {

    //按照用户名查找用户信息
    public Account findByUsername(String username);

    //根据用户激活码查询
    @Query("select a from Account a where code = :code")
    public Account find(@Param("code")String code);


}
