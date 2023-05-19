package com.eio.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eio.pojo.Grade;

import java.util.List;

public interface GradeService extends IService<Grade> {

    /**
     * 查询年级信息（带分页条件）
     * @param page 带分页信息的page对象
     * @param gradeName 查询条件
     * @return
     */
    IPage<Grade> getGrades(Page<Grade> page, String gradeName);

    /**
     * 年级管理中的添加和修改功能
     * @param gradeParam
     * @return
     */
    boolean saveOrUpdateGrade(Grade gradeParam);

    /**
     * 删除和批量删除用户信息
     * @param ids
     * @return
     */
    boolean deleteGrade(List<Integer> ids);

    /**
     * 回显班级信息
     * @return
     */
    List<Grade> getAllGrade();
}
