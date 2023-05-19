package com.eio.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eio.pojo.LoginForm;
import com.eio.pojo.Student;

import java.util.List;

public interface StudentService extends IService<Student> {

    /**
     * 登录
     * @param loginForm
     * @return
     */
    Student Login(LoginForm loginForm);


    /**
     * 根据用户id查询学生信息
     * @param id
     * @return
     */
    Student getStudentById(Long id);

    /**
     * 查询学生信息（带分页条件）
     * @param page
     * @param name
     * @param clazzName
     * @return
     */
    IPage<Student> getStudents(Page<Student> page, String name, String clazzName);

    /**
     * 添加和修改功能
     * @param student
     * @return
     */
    boolean addOrUpdateStudent(Student student);

    /**
     * 删除和批量删除功能
     * @param ids
     * @return
     */
    boolean deleteStudent(List<Integer> ids);

    /**
     * 修改密码时查询学生信息
     * @param userId
     * @param oldPwd
     * @return
     */
    Student getStudent(Long userId, String oldPwd);
}
