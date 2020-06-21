package com.gdou.gas.dao.admin;

import com.gdou.gas.entity.admin.OperaterLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 后台操作日志类数据库操作层
 */
@Repository
public interface OperaterLogDao extends JpaRepository<OperaterLog,Long> {
    @Query("SELECT OL from OperaterLog OL WHERE id = :id")
    OperaterLog find(@Param("id")Long id);

    /**
     * 获取最近的指定条数的操作日志
     */
    @Query(value="select * from operater_log order by crea_time desc limit 0,:size",nativeQuery=true)
    List<OperaterLog> findLastestLog(@Param("size")int size);


}
