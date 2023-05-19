package com.eio.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eio.mapper.TeacherMapper;
import com.eio.pojo.LoginForm;
import com.eio.pojo.Teacher;
import com.eio.service.TeacherService;
import com.eio.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 教师端登录
     * @param loginForm
     * @return
     */
    @Override
    public Teacher Login(LoginForm loginForm) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",loginForm.getUsername())
                .eq("password", MD5.encrypt(loginForm.getPassword()));
        Teacher teacher = teacherMapper.selectOne(queryWrapper);
        return teacher;
    }


    /**
     * 查询教师信息
     * @param id
     * @return
     */
    @Override
    public Teacher getTeacherById(Long id) {
        return teacherMapper.selectById(id);
    }

    /**
     * 实现查询所有教师信息（带分页条件）
     * @param page
     * @param clazzName
     * @param name
     * @return
     */
    @Override
    public IPage<Teacher> getTeachers(Page<Teacher> page, String clazzName, String name) {

        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();

        if (clazzName != null){
            queryWrapper.like("clazz_name",clazzName);
        }
        if (name != null){
            queryWrapper.like("name",name);
        }

        return teacherMapper.selectPage(page,queryWrapper);
    }

    /**
     * 实现添加和修改功能
     * @param teacher
     * @return
     */
    @Override
    public boolean saveOrUpdateTeacher(Teacher teacher) {
        if (teacher.getId() ==null ){
            return teacherMapper.insert(teacher) == 1;
        }

        return teacherMapper.updateById(teacher) == 1;
    }

    /**
     * 实现删除和批量删除功能
     * @param ids
     * @return
     */
    @Override
    public boolean deleteTeacher(List<Integer> ids) {
        return teacherMapper.deleteBatchIds(ids) == ids.size();
    }

    /**
     * 实现修改密码时查询教师信息
     * @param userId
     * @param oldPwd
     * @return
     */
    @Override
    public Teacher getTeacher(Long userId, String oldPwd) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userId)
                .eq("password",oldPwd);
        return teacherMapper.selectOne(queryWrapper);
    }
}
