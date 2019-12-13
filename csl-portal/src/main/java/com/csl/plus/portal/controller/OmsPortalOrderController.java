package com.csl.plus.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.csl.plus.exception.ApiMallPlusException;
import com.csl.plus.oms.entity.OmsOrder;
import com.csl.plus.oms.entity.OmsOrderItem;
import com.csl.plus.oms.vo.ConfirmOrderResult;
import com.csl.plus.oms.vo.OmsOrderDetail;
import com.csl.plus.oms.vo.OrderParam;
import com.csl.plus.oms.vo.TbThanks;
import com.csl.plus.portal.constant.RedisKey;
import com.csl.plus.portal.oms.service.IOmsOrderItemService;
import com.csl.plus.portal.oms.service.IOmsOrderService;
import com.csl.plus.portal.single.ApiBaseAction;
import com.csl.plus.portal.ums.service.IUmsMemberService;
import com.csl.plus.portal.ums.service.RedisService;
import com.csl.plus.portal.util.JsonUtil;
import com.csl.plus.ums.entity.UmsMember;
import com.csl.plus.utils.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 订单管理Controller https://github.com/shenzhuan/mallplus on 2018/8/30.
 */
@Slf4j
@Controller
@Api(tags = "OmsPortalOrderController", description = "订单管理")
@RequestMapping("/api/order")
public class OmsPortalOrderController extends ApiBaseAction {

	@Autowired
	private IOmsOrderService orderService;
	@Autowired
	private IUmsMemberService umsMemberService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private IOmsOrderItemService orderItemService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(OmsOrder queryParam, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		queryParam.setMemberId(umsMemberService.getCurrentMember().getId());
		List<OmsOrder> orderList = orderService.list(new QueryWrapper<>(queryParam));
		for (OmsOrder order : orderList) {
			OmsOrderItem query = new OmsOrderItem();
			query.setOrderId(queryParam.getId());
			List<OmsOrderItem> orderItemList = orderItemService.list(new QueryWrapper<>(query));
			order.setOrderItemList(orderItemList);
		}
		return new CommonResult().success(orderList);
	}

	@ApiOperation("获取订单详情:订单信息、商品信息、操作记录")
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	@ResponseBody
	public Object detail(@RequestParam(value = "id", required = false, defaultValue = "0") Long id) {
		OmsOrder orderDetailResult = null;
		String bannerJson = redisService.get(RedisKey.PmsProductResult + id);
		if (bannerJson != null) {
			orderDetailResult = JsonUtil.jsonToPojo(bannerJson, OmsOrderDetail.class);
		} else {
			orderDetailResult = orderService.getById(id);
			OmsOrderItem query = new OmsOrderItem();
			query.setOrderId(id);
			List<OmsOrderItem> orderItemList = orderItemService.list(new QueryWrapper<>(query));
			orderDetailResult.setOrderItemList(orderItemList);
			redisService.set(RedisKey.PmsProductResult + id, JsonUtil.objectToJson(orderDetailResult));
			redisService.expire(RedisKey.PmsProductResult + id, 10 * 60);
		}

		return new CommonResult().success(orderDetailResult);
	}

	@ResponseBody
	@GetMapping("/submitPreview")
	public Object submitPreview(OrderParam orderParam) {
		try {
			ConfirmOrderResult result = orderService.submitPreview(orderParam);
			return new CommonResult().success(result);
		} catch (ApiMallPlusException e) {
			return new CommonResult().failed(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 提交订单
	 * 
	 * @param orderParam
	 * @return
	 */
	@ApiOperation("根据购物车信息生成订单")
	@RequestMapping(value = "/generateOrder")
	@ResponseBody
	public Object generateOrder(OrderParam orderParam) {
		return orderService.generateOrder(orderParam);
	}

	@RequestMapping(value = "/payOrder")
	@ApiOperation(value = "支付订单")
	@ResponseBody
	public Object payOrder(TbThanks tbThanks) {
		int result = orderService.payOrder(tbThanks);
		return new CommonResult().success(result);
	}

	@ApiOperation("自动取消超时订单")
	@RequestMapping(value = "/cancelTimeOutOrder", method = RequestMethod.POST)
	@ResponseBody
	public Object cancelTimeOutOrder() {
		return orderService.cancelTimeOutOrder();
	}

	@ApiOperation("取消单个超时订单")
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
	@ResponseBody
	public Object cancelOrder(Long orderId) {
		orderService.sendDelayMessageCancelOrder(orderId);
		return new CommonResult().success(null);
	}

	/**
	 * 查看物流
	 */
	@ApiOperation("查看物流")
	@ResponseBody
	@RequestMapping("/getWayBillInfo")
	public Object getWayBillInfo(@RequestParam(value = "orderId", required = false, defaultValue = "0") Long orderId)
			throws Exception {
		try {
			UmsMember member = this.getCurrentMember();
			OmsOrder order = orderService.getById(orderId);
			if (order == null) {
				return null;
			}
			if (!order.getMemberId().equals(member.getId())) {
				return new CommonResult().success("非当前用户订单");
			}

			// ExpressInfoModel expressInfoModel = orderService.queryExpressInfo(orderId);
			return new CommonResult().success(null);
		} catch (Exception e) {
			log.error("get waybillInfo error. error=" + e.getMessage(), e);
			return new CommonResult().failed("获取物流信息失败，请稍后重试");
		}

	}
}
