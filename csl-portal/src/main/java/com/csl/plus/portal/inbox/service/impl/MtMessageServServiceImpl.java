package com.csl.plus.portal.inbox.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.exception.BusinessException;
import com.csl.plus.inbox.entity.MtMessageServ;
import com.csl.plus.inbox.mapper.MtMessageServMapper;
import com.csl.plus.portal.inbox.service.IMtMessageServService;
import com.csl.plus.portal.sys.service.ISysGroupService;
import com.csl.plus.portal.ums.service.IUmsMemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;


@Service("mtMessageServService")
public class MtMessageServServiceImpl extends ServiceImpl<MtMessageServMapper, MtMessageServ> implements IMtMessageServService {

    @Resource
    private MtMessageServMapper mtMessageServMapper;
    @Resource
    private IUmsMemberService umsMemberService;
    @Resource
    private ISysGroupService sysGroupService;
    @Resource(name = "beanServiceMap")
    private Map<String, IService> beanServiceMap;


    @Transactional
    public boolean saves(MtMessageServ entity) throws BusinessException {
        if (validate(entity)) {
            entity.setSendDate(new Date());
            mtMessageServMapper.insert(entity);
        }
        return true;
    }

    private boolean validate(MtMessageServ entity) {
        if (umsMemberService.getById(entity.getSendid()) == null) {
            throw new BusinessException("用户信息不存在");
        }
        if (!StringUtils.isEmpty(entity.getRefid())) {
            IService service = beanServiceMap.get(entity.getSysGroup());
            if (service == null || service.getById(entity.getRefid()) == null) {
                throw new BusinessException("对接资源信息不存在");
            }
        }
        return true;
    }

}