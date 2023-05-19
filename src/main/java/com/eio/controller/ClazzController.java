package com.eio.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eio.pojo.Clazz;
import com.eio.service.ClazzService;
import com.eio.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sms/clazzController")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 处理页面数据请求
     * 实现查询班级信息（带分页条件）
     * @param pageNo
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/getClazzsByOpr/{pageNo}/{pageSize}")
    public Result getClazzs(
            @PathVariable("pageNo") Integer pageNo,
            @PathVariable("pageSize") Integer pageSize,
            String gradeName,
            String name
    ){
        //设置分页信息
        Page<Clazz> page = new Page<>(pageNo,pageSize);
        //调用service层方法，传入分页信息和查询条件，返回IPage对象
        IPage<Clazz> iPage = clazzService.getClazzs(page,name,gradeName);
        return Result.ok(iPage);
    }


    /**
     * 处理添加和修改班级信息请求
     * @param clazz
     * @return
     */
    @PostMapping("/saveOrUpdateClazz")
    public Result saveOrUpdateClazz(@RequestBody Clazz clazz){

        return clazzService.saveOrUpateClazz(clazz) ?  Result.ok() : Result.fail().message("添加失败，请重试！");

    }


    /**
     * 处理删除和批量删除请求
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteClazz")
    public Result deleteClazz(@RequestBody List<Integer> ids){

        return clazzService.deleteClazz(ids) ? Result.ok() : Result.fail().message("删除失败，请重试！");

    }


    /**
     * 处理回显班级信息请求
     * @return
     */
    @GetMapping("/getClazzs")
    public Result getAllClazz(){
        List<Clazz> list = clazzService.getAllClazz();
        return Result.ok(list);
    }
}
