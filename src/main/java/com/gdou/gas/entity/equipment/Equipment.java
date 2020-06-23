package com.gdou.gas.entity.equipment;

import com.gdou.gas.entity.admin.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

/**
 * 后台器材实体类
 */
@Entity
@Table(name="equipment")
@EntityListeners(AuditingEntityListener.class)
public class Equipment extends BaseEntity {

    private static final long serialVersionUID = 1L;


    @Column(name="equipmentname",nullable=false,length=18)
    private String equipmentname;//器材名


    @Column(name="description",nullable=false,length=128)
    private String description;//器材描述

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
