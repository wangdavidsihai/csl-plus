package com.csl.plus.marking.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.marking.entity.SmsUserRedPacket;
import com.csl.plus.marking.service.ISmsUserRedPacketService;
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
 * 用户红包
 * </p>
 *
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "SmsUserRedPacketController", description = "用户红包管理")
@RequestMapping("/marking/SmsUserRedPacket")
public class SmsUserRedPacketController {
    @Resource
    private ISmsUserRedPacketService ISmsUserRedPacketService;

    @SysLog(MODULE = "marking", REMARK = "根据条件查询所有用户红包列表")
    @ApiOperation("根据条件查询所有用户红包列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('marking:SmsUserRedPacket:read')")
    public Object getSmsUserRedPacketByPage(SmsUserRedPacket entity,
                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        try {
            return new CommonResult().success(ISmsUserRedPacketService.page(new Page<SmsUserRedPacket>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有用户红包列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "marking", REMARK = "保存用户红包")
    @ApiOperation("保存用户红包")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('marking:SmsUserRedPacket:create')")
    public Object saveSmsUserRedPacket(@RequestBody SmsUserRedPacket entity) {
        try {
            if (ISmsUserRedPacketService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存用户红包：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "marking", REMARK = "更新用户红包")
    @ApiOperation("更新用户红包")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('marking:SmsUserRedPacket:update')")
    public Object updateSmsUserRedPacket(@RequestBody SmsUserRedPacket entity) {
        try {
            if (ISmsUserRedPacketService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新用户红包：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "marking", REMARK = "删除用户红包")
    @ApiOperation("删除用户红包")
    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('marking:SmsUserRedPacket:delete')")
    public Object deleteSmsUserRedPacket(@ApiParam("用户红包id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("用户红包id");
            }
            if (ISmsUserRedPacketService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除用户红包：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "marking", REMARK = "给用户红包分配用户红包")
    @ApiOperation("查询用户红包明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('marking:SmsUserRedPacket:read')")
    public Object getSmsUserRedPacketById(@ApiParam("用户红包id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("用户红包id");
            }
            SmsUserRedPacket coupon = ISmsUserRedPacketService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询用户红包明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除用户红包")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除用户红包")
    @PreAuthorize("hasAuthority('marking:SmsUserRedPacket:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = ISmsUserRedPacketService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}
