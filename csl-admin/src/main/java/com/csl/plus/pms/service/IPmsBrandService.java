package com.csl.plus.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.pms.entity.PmsBrand;

import java.util.List;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @since 2019-04-19
 */
public interface IPmsBrandService extends IService<PmsBrand> {

    int updateShowStatus(List<Long> ids, Integer showStatus);

    int updateFactoryStatus(List<Long> ids, Integer factoryStatus);
}
