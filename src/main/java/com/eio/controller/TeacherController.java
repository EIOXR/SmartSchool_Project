package com.eio.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eio.pojo.Clazz;
import com.eio.pojo.Teacher;
import com.eio.service.TeacherService;
import com.eio.util.Result;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sms/teacherController")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    /**
     * 查询教师信息（带分页条件）
     * @param pageNo
     * @param pageSize
     * @param clazzName
     * @param name
     * @return
     */
    @GetMapping("/getTeachers/{pageNo}/{pageSize}")
    public Result getTeachers(
            @PathVariable("pageNo") Integer pageNo,
            @PathVariable("pageSize") Integer pageSize,
            String clazzName,
            String name
    ){
        //设置分页信息
        Page<Teacher> page = new Page<>(pageNo,pageSize);
        //调用service层方法，传入分页信息，和查询条件
        IPage<Teacher> iPage = teacherService.getTeachers(page,clazzName,name);
        return Result.ok(iPage);
    }


    /**
     * 处理添加和修改请求
     * @param teacher
     * @return
     */
    @PostMapping("/saveOrUpdateTeacher")
    public Result saveOrUpdateTeacher(@RequestBody Teacher teacher){

        return teacherService.saveOrUpdateTeacher(teacher) ?  Result.ok() : Result.fail().message("添加或修改失败，请重试！");

    }


    @DeleteMapping("/deleteTeacher")
    public Result deleteTeacher(@RequestBody List<Integer> ids){

        return teacherService.deleteTeacher(ids) ? Result.ok() : Result.fail().message("删除失败，请重试！");

    }
}
