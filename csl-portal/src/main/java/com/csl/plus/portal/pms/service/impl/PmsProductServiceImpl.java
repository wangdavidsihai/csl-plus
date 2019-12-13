package com.csl.plus.portal.pms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.marking.entity.SmsGroup;
import com.csl.plus.marking.entity.SmsGroupMember;
import com.csl.plus.marking.mapper.SmsGroupMapper;
import com.csl.plus.marking.mapper.SmsGroupMemberMapper;
import com.csl.plus.pms.entity.PmsProduct;
import com.csl.plus.pms.mapper.PmsProductMapper;
import com.csl.plus.pms.vo.PmsProductAndGroup;
import com.csl.plus.pms.vo.PmsProductResult;
import com.csl.plus.portal.pms.service.IPmsProductService;
import com.csl.plus.portal.util.DateUtils;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements IPmsProductService {

	@Resource
	private PmsProductMapper productMapper;
	@Resource
	private SmsGroupMapper groupMapper;
	@Resource
	private SmsGroupMemberMapper groupMemberMapper;

	@Override
	public PmsProductAndGroup getProductAndGroup(Long id) {
		PmsProduct goods = productMapper.selectById(id);
		PmsProductAndGroup vo = new PmsProductAndGroup();
		try {
			BeanUtils.copyProperties(goods, vo);
			SmsGroup queryG = new SmsGroup();
			queryG.setGoodsId(id);
			SmsGroup group = groupMapper.selectOne(new QueryWrapper<>(queryG));
			SmsGroupMember newG = new SmsGroupMember();
			newG.setGoodsId(id);
			List<SmsGroupMember> list = groupMemberMapper.selectList(new QueryWrapper<>(newG));
			if (group != null) {
				Map<String, List<SmsGroupMember>> map = groupMemberByMainId(list, group);
				vo.setMap(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	/**
	 * 按照异常批次号对已开单数据进行分组
	 *
	 * @param billingList
	 * @return
	 * @throws Exception
	 */
	private Map<String, List<SmsGroupMember>> groupMemberByMainId(List<SmsGroupMember> billingList, SmsGroup group)
			throws Exception {
		Map<String, List<SmsGroupMember>> resultMap = new HashMap<String, List<SmsGroupMember>>();
		Map<String, List<SmsGroupMember>> map = new HashMap<String, List<SmsGroupMember>>();
		try {
			List<Long> ids = new ArrayList<>();
			for (SmsGroupMember tmExcpNew : billingList) {
				if (tmExcpNew.getMemberId().equals(tmExcpNew.getMainId())) {
					Date cr = tmExcpNew.getCreateTime();
					Long nowT = System.currentTimeMillis();
					Date endTime = DateUtils.convertStringToDate(DateUtils.addHours(cr, group.getHours()),
							"yyyy-MM-dd HH:mm:ss");
					if (nowT <= endTime.getTime()) {
						ids.add(tmExcpNew.getMainId());
					}
				}
				if (resultMap.containsKey(tmExcpNew.getMainId() + "")) {// map中异常批次已存在，将该数据存放到同一个key（key存放的是异常批次）的map中
					resultMap.get(tmExcpNew.getMainId() + "").add(tmExcpNew);
				} else {// map中不存在，新建key，用来存放数据
					List<SmsGroupMember> tmpList = new ArrayList<SmsGroupMember>();
					tmpList.add(tmExcpNew);
					resultMap.put(tmExcpNew.getMainId() + "", tmpList);
				}
			}
			for (Long id : ids) {
				if (resultMap.get(id + "") != null) {
					map.put(id + "", resultMap.get(id + ""));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("按照异常批次号对已开单数据进行分组时出现异常", e);
		}

		return map;
	}

	@Override
	public PmsProductResult getUpdateInfo(Long id) {
		return (PmsProductResult) productMapper.selectById(id);
	}
}
