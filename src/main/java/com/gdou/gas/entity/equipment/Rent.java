package com.gdou.gas.entity.equipment;

import com.gdou.gas.entity.admin.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

/**
 * 后台租用单实体类
 */
@Entity
@Table(name="rent")
@EntityListeners(AuditingEntityListener.class)
public class Rent extends BaseEntity {

    private static final long serialVersionUID = 1L;


    @Column(name="name",nullable=false,length=18)
    private String name;//租用人

    @Column(name="tel",nullable=false,length=18)
    private String tel;//租用人电话

    @Column(name="equipmentname",nullable=false,length=18)
    private String equipmentname;//租用器材名

    @Column(name="description",nullable=false,length=128)
    private String description;//租用描述

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
}
