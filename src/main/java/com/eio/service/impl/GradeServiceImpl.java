package com.eio.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eio.mapper.GradeMapper;
import com.eio.pojo.Grade;
import com.eio.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    /**
     * 查询年级信息（带分页条件）
     *
     * @param page      带分页信息的page对象
     * @param gradeName 查询条件
     * @return
     */
    @Override
    public IPage<Grade> getGrades(Page<Grade> page, String gradeName) {

        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();

        //判断是否带条件
        if (gradeName != null) {
            queryWrapper.like("name", gradeName);
        }
        //设置排序规则
        queryWrapper.orderByAsc("id");

        return gradeMapper.selectPage(page, queryWrapper);
    }

    /**
     * 实现年级管理中信息的添加和修改
     *
     * @param gradeParam
     * @return
     */
    @Override
    public boolean saveOrUpdateGrade(Grade gradeParam) {

        if (gradeParam.getId() == null) {
            return gradeMapper.insert(gradeParam) == 1;
        }

        return gradeMapper.updateById(gradeParam) == 1;
    }

    /**
     * 实现删除和批量删除
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteGrade(List<Integer> ids) {

        return gradeMapper.deleteBatchIds(ids) == ids.size();

    }

    /**
     * 实现班级管理中页面数据的回显
     *
     * @return
     */
    @Override
    public List<Grade> getAllGrade() {
        return gradeMapper.selectList(null);
    }
}
