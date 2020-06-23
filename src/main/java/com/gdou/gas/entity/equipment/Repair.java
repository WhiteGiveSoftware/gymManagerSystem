package com.gdou.gas.entity.equipment;

import com.gdou.gas.entity.admin.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

/**
 * 后台维修单实体类
 */
@Entity
@Table(name="repair")
@EntityListeners(AuditingEntityListener.class)
public class Repair extends BaseEntity {

    private static final long serialVersionUID = 1L;


    @Column(name="name",nullable=false,length=18)
    private String name;//报修人

    @Column(name="tel",nullable=false,length=18)
    private String tel;//报修人电话

    @Column(name="equipmentname",nullable=false,length=18)
    private String equipmentname;//报修器材名

    @Column(name="description",nullable=false,length=128)
    private String description;//维修问题描述

    @Column(name="status",nullable=false,length=18)
    private String status;//维修状态

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEquipmentname() {
        return equipmentname;
    }

    public void setEquipmentname(String equipmentname) {
        this.equipmentname = equipmentname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
