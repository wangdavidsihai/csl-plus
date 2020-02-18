package com.csl.plus.res.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csl.plus.res.entity.ResTalentData;
import org.apache.ibatis.annotations.Mapper;

/**
 * 专家表
 *
 * @author David
 * @email
 * @date 2020-02-18 21:43:07
 */
@Mapper
public interface ResTalentDataMapper extends BaseMapper<ResTalentData> {

    public ResTalentData getTalentDataByTalId(Long talId);
}
