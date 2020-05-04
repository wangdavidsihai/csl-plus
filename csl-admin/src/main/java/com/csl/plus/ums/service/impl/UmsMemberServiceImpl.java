package com.csl.plus.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.common.utils.CommonCodes;
import com.csl.plus.ums.entity.UmsMember;
import com.csl.plus.ums.mapper.UmsMemberMapper;
import com.csl.plus.ums.service.IUmsMemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements IUmsMemberService {

    @Resource
    private UmsMemberMapper umsMemberMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean saves(UmsMember entity) {
        entity.setStatus(CommonCodes.STATUS_REVIEW);
        entity.setCreateTime(new Date());
        entity.setPassword(passwordEncoder.encode(entity.getPhone()));
        umsMemberMapper.insert(entity);
        return true;
    }
}
