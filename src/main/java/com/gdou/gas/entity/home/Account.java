package com.gdou.gas.entity.home;

import com.gdou.gas.annotion.ValidateEntity;
import com.gdou.gas.entity.admin.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

/**
 * 前台客户实体类
 */
@Entity
@Table(name="accout")
@EntityListeners(AuditingEntityListener.class)
public class Account extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(name="username",nullable=false,length=18,unique=true)
    private String username;//用户名

    @Column(name="password",nullable=false,length=32)
    private String password;//登录密码

    @Column(name="email",nullable=false,length=32)
    private String email;//邮箱

    @Column(name="status",nullable=false,length=1)
    private String status;//状态

    @Column(name="code",nullable=false,length=32)
    private String code;//激活码


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Account(String username, String password, String email, String status, String code) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = status;
        this.code = code;
    }

    public Account(String code) {
        this.code = code;
    }

    public Account(){

    }

}
