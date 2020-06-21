package com.gdou.gas.entity.field;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="field")
@Data
public class Field {

    @Id
    @Column(name="fieldId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fieldId; // id,不为空，自增

    @Column(name="field_type", nullable=false, length = 32)
    private String fieldType;  // 场地类型  长度32  不为空

    @Column(name="field_name", nullable=false, length = 32)
    private String fieldName;  // 场地名字 长度32 不为空

    @Column(name="is_booked", nullable = false)
    private int isBooked;  // 是否被预约  0 表示未被预约 1表示已经预约
    @Column(name="is_deleted", nullable = false)
    private int isDeleted;  // 是否被删除  0表示未被删除  1表示已经删除
}
