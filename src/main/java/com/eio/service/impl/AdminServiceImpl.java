package com.eio.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eio.mapper.AdminMapper;
import com.eio.pojo.Admin;
import com.eio.pojo.LoginForm;
import com.eio.service.AdminService;
import com.eio.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper,Admin> implements AdminService{

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 管理员登录
     * @param loginForm
     * @return
     */
    @Override
    public Admin Login(LoginForm loginForm) {

        QueryWrapper<Admin> queryWrapper = new QueryWrapper();
        queryWrapper.eq("name",loginForm.getUsername())
                //使用MD5算法将用户提交表单中的密码传入数据库进行核对
                .eq("password", MD5.encrypt(loginForm.getPassword()));
        Admin admin = adminMapper.selectOne(queryWrapper);
        return admin;
    }

    /**
     * 查询管理员信息
     * @param id
     * @return
     */
    @Override
    public Admin getAdminById(Long id) {
        return adminMapper.selectById(id);
    }

    /**
     * 实现查询所有管理员信息（带分页条件）
     * @param page
     * @param adminName
     * @return
     */
    @Override
    public IPage getAllAdmin(Page<Admin> page, String adminName) {

        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();

        if (adminName != null){
            queryWrapper.like("name",adminName);
        }
        return adminMapper.selectPage(page,queryWrapper);
    }


    /**
     * 实现修改密码时查询管理员信息
     * @param userId
     * @param oldPwd
     * @return
     */
    @Override
    public Admin getAdmin(Long userId, String oldPwd) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userId)
                .eq("password",oldPwd);
        return adminMapper.selectOne(queryWrapper);
    }
}
