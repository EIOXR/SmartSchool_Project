package com.eio.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eio.mapper.StudentMapper;
import com.eio.pojo.LoginForm;
import com.eio.pojo.Student;
import com.eio.service.StudentService;
import com.eio.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 学生端登录
     *
     * @param loginForm
     * @return
     */
    @Override
    public Student Login(LoginForm loginForm) {

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", loginForm.getUsername())
                .eq("password", MD5.encrypt(loginForm.getPassword()));
        Student student = studentMapper.selectOne(queryWrapper);
        return student;
    }


    /**
     * 查询学生信息
     *
     * @param id
     * @return
     */
    @Override
    public Student getStudentById(Long id) {
        return studentMapper.selectById(id);
    }


    /**
     * 实现查询学生信息（带分页条件）
     *
     * @param page
     * @param name
     * @param clazzName
     * @return
     */
    @Override
    public IPage<Student> getStudents(Page<Student> page, String name, String clazzName) {

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();

        if (name != null) {
            queryWrapper.like("name", name);
        }
        if (clazzName != null) {
            queryWrapper.like("clazz_name", clazzName);
        }

        return studentMapper.selectPage(page, queryWrapper);
    }


    /**
     * 实现添加和修改功能
     *
     * @param student
     * @return
     */
    @Override
    public boolean addOrUpdateStudent(Student student) {
        if (student.getId() == null) {
            return studentMapper.insert(student) == 1;
        }
        return studentMapper.updateById(student) == 1;
    }

    /**
     * 实现删除和批量删除功能
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteStudent(List<Integer> ids) {
        return studentMapper.deleteBatchIds(ids) == ids.size();
    }

    /**
     * @param userId
     * @param oldPwd
     * @return
     */
    @Override
    public Student getStudent(Long userId, String oldPwd) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId)
                .eq("password", oldPwd);
        return studentMapper.selectOne(queryWrapper);

    }
}
