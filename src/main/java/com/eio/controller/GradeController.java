package com.eio.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eio.pojo.Grade;
import com.eio.service.GradeService;
import com.eio.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("sms/gradeController")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    /**
     * 处理页面数据请求
     * 实现点击年级管理时，页面数据的查询，分页查询，条件查询
     *
     * @param pageNo    设置页数
     * @param pageSize  设置每页显示的条数
     * @param gradeName 用户输入的查询条件
     * @return
     */
    @GetMapping("/getGrades/{pageNo}/{pageSize}")
    public Result getGrades(
            @PathVariable("pageNo") Integer pageNo,
            @PathVariable("pageSize") Integer pageSize,
            String gradeName
    ) {
        //设置分页信息
        Page<Grade> page = new Page<>(pageNo, pageSize);
        //调用服务层查询方法，传入分页信息，和查询条件
        IPage<Grade> grades = gradeService.getGrades(page, gradeName);
        return Result.ok(grades);
    }


    /**
     * 处理添加和修改请求
     * @param gradeParam
     * @return
     */
    @PostMapping("/saveOrUpdateGrade")
    public Result saveOrUpdateGrade(@RequestBody Grade gradeParam) {

        return gradeService.saveOrUpdateGrade(gradeParam) ? Result.ok() : Result.fail().message("添加失败，请重试！");

    }

    /**
     * 处理删除和批量删除请求
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteGrade")
    public Result deleteGrades(@RequestBody List<Integer> ids) {

        return gradeService.deleteGrade(ids) ? Result.ok() : Result.fail().message("删除失败，请重试！");

    }


    /**
     * 处理回显班级信息请求
     * @return
     */
    @GetMapping("/getGrades")
    public Result getAllGrade(){
        List<Grade> list = gradeService.getAllGrade();
        return Result.ok(list);
    }

}
