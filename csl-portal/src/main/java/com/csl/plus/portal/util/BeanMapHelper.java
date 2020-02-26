package com.csl.plus.portal.util;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.portal.common.CommonConstant;
import com.csl.plus.portal.res.service.*;
import com.csl.plus.portal.rms.service.IFinRequirementService;
import com.csl.plus.portal.rms.service.IPrdRequirementService;
import com.csl.plus.portal.rms.service.ITechRequirementService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class BeanMapHelper {

    @Resource
    private IFinRequirementService finRequirementService;
    @Resource
    private IPrdRequirementService prdRequirementService;
    @Resource
    private ITechRequirementService techRequirementService;
    @Resource
    private IResTalentService resTalentService;
    @Resource
    private IResTechnicalService resTechnicalService;
    @Resource
    private IResProjProductionService resProjProductionService;
    @Resource
    private IResProductService resProductService;
    @Resource
    private IResFinanceService resFinanceService;
    @Resource
    private IResExpertService resExpertService;

    @Bean(name = "beanServiceMap")
    public Map<String, IService> beanServiceMap() {
        Map<String, IService> beanServiceMap = new HashMap<String, IService>();
        beanServiceMap.put(CommonConstant.SYS_GROUP_RMS_FIN, finRequirementService);
        beanServiceMap.put(CommonConstant.SYS_GROUP_RMS_PROD, prdRequirementService);
        beanServiceMap.put(CommonConstant.SYS_GROUP_RMS_TECH, techRequirementService);
        beanServiceMap.put(CommonConstant.SYS_GROUP_RES_TAL, resTalentService);
        beanServiceMap.put(CommonConstant.SYS_GROUP_RES_TECH, resTechnicalService);
        beanServiceMap.put(CommonConstant.SYS_GROUP_RES_PROJ, resProjProductionService);
        beanServiceMap.put(CommonConstant.SYS_GROUP_RES_PROD, resProductService);
        beanServiceMap.put(CommonConstant.SYS_GROUP_RES_FIN, resFinanceService);
        beanServiceMap.put(CommonConstant.SYS_GROUP_RES_EXP, resExpertService);
        return beanServiceMap;
    }
}
