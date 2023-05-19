package com.eio.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eio.pojo.Student;
import com.eio.service.StudentService;
import com.eio.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sms/studentController")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
    public Result getStudents(
            @PathVariable("pageNo") Integer pageNo,
            @PathVariable("pageSize") Integer pageSize,
            String name,
            String clazzName
    ){
        //设置分页信息
        Page<Student> page = new Page<>(pageNo,pageSize);
        //调用service层方法，传入分页信息和查询条件，返回IPage对象
        IPage<Student> iPage =  studentService.getStudents(page,name,clazzName);
        return Result.ok(iPage);
    }


    /**
     * 处理添加和修改请求
     * @param student
     * @return
     */
    @PostMapping("/addOrUpdateStudent")
    public Result addOrUpdateStudent(@RequestBody Student student){
        return studentService.addOrUpdateStudent(student) ? Result.ok() : Result.fail().message("操作失败，请重试！");
    }


    /**
     * 处理删除和批量删除请求
     * @param ids
     * @return
     */
    @DeleteMapping("/delStudentById")
    public Result deleteStudent(@RequestBody List<Integer> ids){
        return studentService.deleteStudent(ids) ? Result.ok() : Result.fail().message("删除失败，请重试！");
    }

}
