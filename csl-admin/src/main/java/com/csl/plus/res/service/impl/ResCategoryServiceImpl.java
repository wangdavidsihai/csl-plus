package com.csl.plus.res.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.res.entity.ResCategory;
import com.csl.plus.res.mapper.ResCategoryMapper;
import com.csl.plus.res.service.IResCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Service("resCategoryService")
public class ResCategoryServiceImpl extends ServiceImpl<ResCategoryMapper, ResCategory> implements IResCategoryService {

    @Resource
    private ResCategoryMapper resCategoryMapper;

    @Transactional
    public boolean saves(ResCategory entity) {
        ResCategory resCategory = new ResCategory();
        resCategory.setName(entity.getName());
        //同时更新筛选属性的信息
        if (resCategoryMapper.selectOne(new QueryWrapper<>(resCategory)) != null) {
            log.warn("名字重复");
            return false;
        }
        entity.setCreateDate(new Date());
        resCategoryMapper.insert(entity);
        return true;
    }

    @Override
    public int updateShowStatus(Integer id, Integer showStatus) {
        ResCategory resCategory = new ResCategory();
        resCategory.setId(id);
        resCategory.setShowStatus(showStatus);
        return resCategoryMapper.updateById(resCategory);
    }

    @Override
    public boolean updateAnd(ResCategory entity) {
//        setCategoryLevel(resCategory);
        ResCategory resCategory = new ResCategory();
        resCategory.setName(entity.getName());
        //同时更新筛选属性的信息
        if (resCategoryMapper.selectOne(new QueryWrapper<>(resCategory)) != null) {
            log.warn("名字重复");
            return false;
        }
        resCategoryMapper.updateById(entity);
        return true;
    }

    @Override
    public boolean saveAnd(ResCategory entity) {
        return false;
    }

}