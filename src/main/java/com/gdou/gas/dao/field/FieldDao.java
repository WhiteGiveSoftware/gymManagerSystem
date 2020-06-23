package com.gdou.gas.dao.field;

import com.gdou.gas.entity.field.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldDao extends JpaRepository<Field, Long> {

    public Field findByFieldName(String fieldname);


    @Query("select f from Field f where id = :id")
    public Field find(@Param("id")Long id);

}
