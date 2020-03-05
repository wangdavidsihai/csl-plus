package com.csl.plus.ums.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.ums.entity.UmsMemberLoginLog;
import com.csl.plus.ums.service.IUmsMemberLoginLogService;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 会员登录记录
 *
 * @author David
 * @email
 * @date 2020-03-04 14:52:45
 */
@Slf4j
@RestController
@Api(tags = "/api/UmsMemberLoginLogController", description = "会员登录记录管理")
@RequestMapping("oms/umsmemberloginlog")
public class UmsMemberLoginLogController {
    @Autowired
    private IUmsMemberLoginLogService umsMemberLoginLogService;

    /**
     * 列表
     */
    @SysLog(MODULE = "oms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('oms:umsmemberloginlog:list')")
    public Object getUmsMemberLoginLogByPage(UmsMemberLoginLog entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(umsMemberLoginLogService.page(new Page<UmsMemberLoginLog>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有会员登录记录列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "oms", REMARK = "根据条件查询会员登录记录列表")
     @ApiOperation("根据条件查询会员登录记录列表")
     @GetMapping("/info/{id}")
     @PreAuthorize("hasAuthority('oms:umsmemberloginlog:info')") public R info(@PathVariable("id") Long id){
     UmsMemberLoginLogEntity umsMemberLoginLog = umsMemberLoginLogService.getById(id);

     return R.ok().put("umsMemberLoginLog", umsMemberLoginLog);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "oms", REMARK = "保存会员登录记录")
    @ApiOperation("保存会员登录记录")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('oms:umsmemberloginlog:save')")
    public Object save(@RequestBody UmsMemberLoginLog entity) {
        try {
            if (umsMemberLoginLogService.saves(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    /**
     * 修改
     */
    @SysLog(MODULE = "oms", REMARK = "修改会员登录记录")
    @ApiOperation("修改会员登录记录")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('oms:umsmemberloginlog:update')")
    public Object update(@RequestBody UmsMemberLoginLog entity) {
        try {
            if (umsMemberLoginLogService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    /**
     * 删除
     */
    @SysLog(MODULE = "oms", REMARK = "删除会员登录记录")
    @ApiOperation("删除会员登录记录")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('oms:umsmemberloginlog:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (umsMemberLoginLogService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "查询会员登录记录明细")
    @ApiOperation("查询会员登录记录明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('oms:umsmemberloginlog:read')")
    public Object getUmsMemberLoginLogById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("会员登录记录id");
            }
            UmsMemberLoginLog object = umsMemberLoginLogService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询会员登录记录明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
