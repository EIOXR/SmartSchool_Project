package com.eio.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eio.pojo.Admin;
import com.eio.service.AdminService;
import com.eio.util.MD5;
import com.eio.util.Result;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sms/adminController")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 处理查询管理员信息请求（带分页条件）
     * @param pageNo
     * @param pageSize
     * @param adminName
     * @return
     */
    @GetMapping("/getAllAdmin/{pageNo}/{pageSize}")
    public Result getAllAdmin(
            @PathVariable("pageNo") Integer pageNo,
            @PathVariable("pageSize") Integer pageSize,
            String adminName
    ) {
        //设置分页条件
        Page<Admin> page = new Page<>(pageNo, pageSize);
        //调用service层方法，传入分页对象和查询条件，返回IPage对象
        IPage iPage = adminService.getAllAdmin(page, adminName);
        return Result.ok(iPage);
    }


    /**
     * 处理添加和修改管理员信息请求
     * @param admin
     * @return
     */
    @PostMapping("/saveOrUpdateAdmin")
    public Result saveOrUpdateAdmin(@RequestBody Admin admin) {
        adminService.saveOrUpdate(admin);
        return Result.ok();
    }


    /**
     * 处理删除和批量删除请求
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteAdmin")
    public Result deleteAdmin(@RequestBody List<Integer> ids){

        return adminService.removeByIds(ids) ? Result.ok() : Result.fail().message("删除失败，请重试！");

    }
}
