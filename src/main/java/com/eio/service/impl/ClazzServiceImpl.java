package com.eio.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eio.mapper.ClazzMapper;
import com.eio.pojo.Clazz;
import com.eio.service.ClazzService;
import com.eio.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 实现班级信息查询（带分页条件）
     * @param pageParam
     * @param name
     * @param gradeName
     * @return
     */
    @Override
    public IPage<Clazz> getClazzs(Page<Clazz> pageParam, String name,String gradeName) {

        QueryWrapper<Clazz> queryWrapper = new QueryWrapper<>();

        if (name != null){
            queryWrapper.like("name",name);
        }
        if (gradeName != null){
            queryWrapper.like("grade_name",gradeName);
        }
        //设置排序条件
        queryWrapper.orderByAsc("id");

        return clazzMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public boolean saveOrUpateClazz(Clazz clazz) {

        if (clazz.getId() == null){
            return clazzMapper.insert(clazz) == 1;
        }

        return clazzMapper.updateById(clazz) == 1;
    }

    /**
     * 实现删除和批量删除
     * @param ids
     * @return
     */
    @Override
    public boolean deleteClazz(List<Integer> ids) {

        return clazzMapper.deleteBatchIds(ids) == ids.size();

    }

    @Override
    public List<Clazz> getAllClazz() {
        return clazzMapper.selectList(null);
    }
}
