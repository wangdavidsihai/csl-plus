package com.csl.plus.portal.single;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.oms.entity.OmsOrder;
import com.csl.plus.oms.vo.GroupAndOrderVo;
import com.csl.plus.portal.annotation.IgnoreAuth;
import com.csl.plus.portal.annotation.SysLog;
import com.csl.plus.portal.marking.service.ISmsGroupService;
import com.csl.plus.portal.oms.service.IOmsOrderService;
import com.csl.plus.ums.entity.UmsMember;
import com.csl.plus.utils.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Date: 2019/4/2 15:02
 * @Description:
 */
@RestController
@Api(tags = "订单管理系统", description = "订单管理系统")
@RequestMapping("/api/single/oms")
public class SingleOmsController extends ApiBaseAction {

	@Resource
	private ISmsGroupService groupService;
	@Resource
	private IOmsOrderService orderService;

	@IgnoreAuth
	@SysLog(MODULE = "oms", REMARK = "查询订单列表")
	@ApiOperation(value = "查询订单列表")
	@GetMapping(value = "/order/list")
	public Object orderList(OmsOrder order,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
		return new CommonResult()
				.success(orderService.page(new Page<OmsOrder>(pageNum, pageSize), new QueryWrapper<>(order)));
	}

	/**
	 * 提交订单
	 * 
	 * @param orderParam
	 * @return
	 */
	@ApiOperation("商品详情预览订单")
	@SysLog(MODULE = "order", REMARK = "商品详情预览订单")
	@GetMapping(value = "/preOrder")
	public Object preOrder(GroupAndOrderVo orderParam) {
		UmsMember member = this.getCurrentMember();
		orderParam.setMemberId(member.getId());
		orderParam.setName(member.getNickname());
		return orderService.preSingelOrder(orderParam);
	}

	/**
	 * 提交订单
	 * 
	 * @param orderParam
	 * @return
	 */
	@ApiOperation("商品详情生成订单")
	@SysLog(MODULE = "order", REMARK = "商品详情生成订单")
	@PostMapping(value = "/bookOrder")
	public Object bookOrder(GroupAndOrderVo orderParam) {
		UmsMember member = this.getCurrentMember();
		return orderService.generateSingleOrder(orderParam, member);
	}
}
