package com.csl.plus.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.ums.entity.UmsMember;
import com.csl.plus.ums.mapper.UmsMemberMapper;
import com.csl.plus.ums.service.IUmsMemberService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements IUmsMemberService {

}
