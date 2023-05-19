package com.eio.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @project: sms
 * @description: 班级信息
 */

@TableName("tb_clazz")
public class Clazz {
    //班级信息
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;             //班级Id
    private String name;            //班级名称
    private String number;          //班级人数
    private String introducation;   //班级介绍
    //班主任信息
    private String headmaster;      //班主任姓名
    private String telephone;       //班主任电话
    private String email;           //班主任邮箱
    //所属年级
    private String gradeName;      //班级所属年级

    public Clazz() {
    }

    public Clazz(Integer id, String name, String number, String introducation, String headmaster, String telephone, String email, String gradeName) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.introducation = introducation;
        this.headmaster = headmaster;
        this.telephone = telephone;
        this.email = email;
        this.gradeName = gradeName;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIntroducation() {
        return introducation;
    }

    public void setIntroducation(String introducation) {
        this.introducation = introducation;
    }

    public String getHeadmaster() {
        return headmaster;
    }

    public void setHeadmaster(String headmaster) {
        this.headmaster = headmaster;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    @Override
    public String toString() {
        return "Clazz{" + "id=" + id + ", name='" + name + '\'' + ", number='" + number + '\'' + ", introducation='" + introducation + '\'' + ", headmaster='" + headmaster + '\'' + ", telephone='" + telephone + '\'' + ", email='" + email + '\'' + ", gradeName='" + gradeName + '\'' + '}';
    }
}
