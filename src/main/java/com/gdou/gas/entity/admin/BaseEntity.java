package com.gdou.gas.entity.admin;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.AuditingBeanDefinitionParser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
/**
 * 基础共用实体
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name="id",nullable = false,length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;//唯一ID


    @Column(name="crea_time",nullable = false)
    @CreatedDate
    private Date creaTime;//创建时间



    @Column(name="update_time",nullable = false)
    @LastModifiedDate
    private Date updateTime;//更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreaTime() {
        return creaTime;
    }

    public void setCreaTime(Date creaTime) {
        this.creaTime = creaTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
