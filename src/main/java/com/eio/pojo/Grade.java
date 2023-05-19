package com.eio.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @project: sms
 * @description: 年级及年级主任信息
 */

@TableName("tb_grade")
public class Grade {

    //年级信息
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;             //年级ID
    private String name;            //年级名称
    private String introducation;   //年级介绍
    //年级主任信息
    private String manager;         //年级主任姓名
    private String email;           //年级主任邮箱
    private String telephone;       //年级主任电话

    public Grade() {

    }

    public Grade(Integer id, String name, String introducation, String manager, String email, String telephone) {
        this.id = id;
        this.name = name;
        this.introducation = introducation;
        this.manager = manager;
        this.email = email;
        this.telephone = telephone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroducation() {
        return introducation;
    }

    public void setIntroducation(String introducation) {
        this.introducation = introducation;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", introducation='" + introducation + '\'' +
                ", manager='" + manager + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}