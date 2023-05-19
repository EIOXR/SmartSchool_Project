package com.eio.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eio.pojo.LoginForm;
import com.eio.pojo.Teacher;

import java.util.List;

public interface TeacherService extends IService<Teacher> {

    /**
     * 登录
     * @param loginForm
     * @return
     */
    Teacher Login(LoginForm loginForm);

    /**
     * 根据id查询教师信息
     * @param id
     * @return
     */
    Teacher getTeacherById(Long id);

    /**
     * 查询教师信息（带分页条件）
     * @param page
     * @param clazzName
     * @param name
     * @return
     */
    IPage<Teacher> getTeachers(Page<Teacher> page, String clazzName, String name);

    /**
     * 添加和修改功能
     * @param teacher
     * @return
     */
    boolean saveOrUpdateTeacher(Teacher teacher);

    /**
     * 删除和批量删除功能
     * @param ids
     * @return
     */
    boolean deleteTeacher(List<Integer> ids);

    /**
     * 修改密码时查询教师信息
     * @param userId
     * @param oldPwd
     * @return
     */
    Teacher getTeacher(Long userId, String oldPwd);
}
