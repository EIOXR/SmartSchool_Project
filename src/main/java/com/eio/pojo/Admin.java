package com.eio.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Objects;

@TableName("tb_admin")//mybatis-plus的注解，可以将实体类与数据库中对于表名中的字段一一对应
public class Admin {

    @TableId(value = "id",type = IdType.AUTO)//mybatis-plus的注解,设置主键的类型
    private Integer id;

    private String name;

    private Character gender;

    private String password;

    private String email;

    private String telephone;

    private String address;

    private String portraitPath;

    public Admin() {
    }

    public Admin(Integer id, String name, Character gender, String password, String email, String telephone, String address, String portraitPath) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.portraitPath = portraitPath;
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

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPortraitPath() {
        return portraitPath;
    }

    public void setPortraitPath(String portraitPath) {
        this.portraitPath = portraitPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(id, admin.id) && Objects.equals(name, admin.name) && Objects.equals(gender, admin.gender) && Objects.equals(password, admin.password) && Objects.equals(email, admin.email) && Objects.equals(telephone, admin.telephone) && Objects.equals(address, admin.address) && Objects.equals(portraitPath, admin.portraitPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, password, email, telephone, address, portraitPath);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", portraitPath='" + portraitPath + '\'' +
                '}';
    }
}
