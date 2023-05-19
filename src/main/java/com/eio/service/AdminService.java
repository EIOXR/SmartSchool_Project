package com.eio.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eio.pojo.Admin;
import com.eio.pojo.LoginForm;

public interface AdminService extends IService<Admin> {

    /**
     * 登录功能
     * @param loginForm
     * @return
     */
    Admin Login(LoginForm loginForm);

    /**
     * 通过用户id查询管理员信息
     * @param id
     * @return
     */
    Admin getAdminById(Long id);

    /**
     * 查询所有管理员信息（带分页条件）
     * @param page
     * @param adminName
     * @return
     */
    IPage getAllAdmin(Page<Admin> page, String adminName);

    /**
     * 修改密码是查询管理员信息
     * @param userId
     * @param oldPwd
     * @return
     */
    Admin getAdmin(Long userId, String oldPwd);
}
