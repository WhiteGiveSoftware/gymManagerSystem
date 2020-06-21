package com.gdou.gas.entity.admin;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
/**
 * 操作日志表
 */
@Entity
@Table(name = "operater_log")
@EntityListeners(AuditingEntityListener.class)
public class OperaterLog extends BaseEntity{
    private static final long serialVersionUID = 1L;

    @Column(name="operator",nullable = false,length = 18)
    private String operator;//操作者

    @Column(name="content",nullable = false,length = 128)
    private String content;//操作内容

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
