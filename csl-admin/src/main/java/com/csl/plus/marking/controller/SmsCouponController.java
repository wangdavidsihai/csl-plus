package com.csl.plus.marking.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.marking.entity.SmsCoupon;
import com.csl.plus.marking.service.ISmsCouponService;
import com.csl.plus.marking.vo.SmsCouponParam;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 邀请码表
 * </p>
 *
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "SmsCouponController", description = "邀请码表管理")
@RequestMapping("/marking/SmsCoupon")
public class SmsCouponController {
    @Resource
    private ISmsCouponService ISmsCouponService;

    @SysLog(MODULE = "marking", REMARK = "根据条件查询所有邀请码表列表")
    @ApiOperation("根据条件查询所有邀请码表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('marking:SmsCoupon:read')")
    public Object getSmsCouponByPage(SmsCoupon entity,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        try {
            return new CommonResult().success(ISmsCouponService.page(new Page<SmsCoupon>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有邀请码表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "marking", REMARK = "保存邀请码表")
    @ApiOperation("保存邀请码表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('marking:SmsCoupon:create')")
    public Object saveSmsCoupon(@RequestBody SmsCouponParam entity) {
        try {
            if (ISmsCouponService.saves(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存邀请码表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "marking", REMARK = "更新邀请码表")
    @ApiOperation("更新邀请码表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('marking:SmsCoupon:update')")
    public Object updateSmsCoupon(@RequestBody SmsCouponParam entity) {
        try {
            if (ISmsCouponService.updateByIds(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新邀请码表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "marking", REMARK = "删除邀请码表")
    @ApiOperation("删除邀请码表")
    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('marking:SmsCoupon:delete')")
    public Object deleteSmsCoupon(@ApiParam("邀请码表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("邀请码表id");
            }
            if (ISmsCouponService.delete(id) > 0) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除邀请码表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "marking", REMARK = "给邀请码表分配邀请码表")
    @ApiOperation("查询邀请码表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('marking:SmsCoupon:read')")
    public Object getSmsCouponById(@ApiParam("邀请码表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("邀请码表id");
            }
            SmsCouponParam coupon = ISmsCouponService.getItem(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询邀请码表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }


}
