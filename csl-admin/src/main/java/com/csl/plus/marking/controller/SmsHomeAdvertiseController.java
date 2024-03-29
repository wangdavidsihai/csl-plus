package com.csl.plus.marking.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.marking.entity.SmsHomeAdvertise;
import com.csl.plus.marking.entity.SmsHomeNewProduct;
import com.csl.plus.marking.service.ISmsHomeAdvertiseService;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 首页轮播广告表
 * </p>
 *
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "SmsHomeAdvertiseController", description = "首页轮播广告表管理")
@RequestMapping("/marking/SmsHomeAdvertise")
public class SmsHomeAdvertiseController {
    @Resource
    private ISmsHomeAdvertiseService ISmsHomeAdvertiseService;

    @SysLog(MODULE = "marking", REMARK = "根据条件查询所有首页轮播广告表列表")
    @ApiOperation("根据条件查询所有首页轮播广告表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('marking:SmsHomeAdvertise:read')")
    public Object getSmsHomeAdvertiseByPage(SmsHomeAdvertise entity,
                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        try {
            return new CommonResult().success(ISmsHomeAdvertiseService.page(new Page<SmsHomeAdvertise>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有首页轮播广告表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "marking", REMARK = "保存首页轮播广告表")
    @ApiOperation("保存首页轮播广告表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('marking:SmsHomeAdvertise:create')")
    public Object saveSmsHomeAdvertise(@RequestBody SmsHomeAdvertise entity) {
        try {
            entity.setClickCount(0);
            entity.setOrderCount(0);
            if (ISmsHomeAdvertiseService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存首页轮播广告表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "marking", REMARK = "更新首页轮播广告表")
    @ApiOperation("更新首页轮播广告表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('marking:SmsHomeAdvertise:update')")
    public Object updateSmsHomeAdvertise(@RequestBody SmsHomeAdvertise entity) {
        try {
            if (ISmsHomeAdvertiseService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新首页轮播广告表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "marking", REMARK = "删除首页轮播广告表")
    @ApiOperation("删除首页轮播广告表")
    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('marking:SmsHomeAdvertise:delete')")
    public Object deleteSmsHomeAdvertise(@ApiParam("首页轮播广告表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("首页轮播广告表id");
            }
            if (ISmsHomeAdvertiseService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除首页轮播广告表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "marking", REMARK = "给首页轮播广告表分配首页轮播广告表")
    @ApiOperation("查询首页轮播广告表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('marking:SmsHomeAdvertise:read')")
    public Object getSmsHomeAdvertiseById(@ApiParam("首页轮播广告表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("首页轮播广告表id");
            }
            SmsHomeAdvertise coupon = ISmsHomeAdvertiseService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询首页轮播广告表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除首页轮播广告表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除首页轮播广告表")
    @PreAuthorize("hasAuthority('marking:SmsHomeAdvertise:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = ISmsHomeAdvertiseService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }
    @SysLog(MODULE = "sms", REMARK = "修改上下线状态")
    @ApiOperation("修改上下线状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object updateStatus(@PathVariable Long id, Integer status) {
        SmsHomeAdvertise record = new SmsHomeAdvertise();
        record.setId(id);
        record.setStatus(status);
        return ISmsHomeAdvertiseService.updateById(record);

    }



    @ApiOperation("修改推荐排序")
    @RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object updateSort(@PathVariable Long id, Integer sort) {
        int count = ISmsHomeAdvertiseService.updateSort(id, sort);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    @ApiOperation("批量修改推荐状态")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public Object updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        int count = ISmsHomeAdvertiseService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }
}
