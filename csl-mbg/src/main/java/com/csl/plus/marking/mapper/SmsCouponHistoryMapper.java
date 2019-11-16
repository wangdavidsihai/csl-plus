package com.csl.plus.marking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csl.plus.marking.entity.SmsCouponHistory;
import com.csl.plus.marking.vo.SmsCouponHistoryDetail;

import java.util.List;

/**
 * <p>
 * 优惠券使用、领取历史表 Mapper 接口
 * </p>
 *
 * @since 2019-04-19
 */
public interface SmsCouponHistoryMapper extends BaseMapper<SmsCouponHistory> {

    List<SmsCouponHistoryDetail> getDetailList(Long memberId);
}
