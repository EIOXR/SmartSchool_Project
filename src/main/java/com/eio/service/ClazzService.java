package com.eio.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eio.pojo.Clazz;

import java.util.List;

public interface ClazzService extends IService<Clazz> {

    /**
     * 查询班级信息（带分页条件）
     * @param page
     * @param name
     * @param gradeName
     * @return
     */
    IPage<Clazz> getClazzs(Page<Clazz> page, String name,String gradeName);

    /**
     * 添加和修改班级信息
     * @param clazz
     * @return
     */
    boolean saveOrUpateClazz(Clazz clazz);

    /**
     * 删除和批量删除班级信息
     * @param ids
     * @return
     */
    boolean deleteClazz(List<Integer> ids);

    /**
     * 回显班级信息
     * @return
     */
    List<Clazz> getAllClazz();
}
