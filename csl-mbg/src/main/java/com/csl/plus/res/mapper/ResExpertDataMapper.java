package com.csl.plus.res.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csl.plus.res.entity.ResExpertData;
import org.apache.ibatis.annotations.Mapper;

/**
 * 专家表
 *
 * @author David
 * @email
 * @date 2020-02-15 22:48:23
 */
@Mapper
public interface ResExpertDataMapper extends BaseMapper<ResExpertData> {

    public ResExpertData getExpertDataByExpId(Long expId);
}
