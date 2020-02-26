package com.csl.plus.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.sys.entity.SysGroup;
import com.csl.plus.sys.service.ISysGroupService;
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
 * 系统类别表
 *
 * @author David
 * @email
 * @date 2020-02-26 13:23:47
 */
@Slf4j
@RestController
@Api(tags = "/api/SysGroupController", description = "系统类别表管理")
@RequestMapping("sys/sysgroup")
public class SysGroupController {
    @Autowired
    private ISysGroupService sysGroupService;

    /**
     * 列表
     */
    @SysLog(MODULE = "sys", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:sysgroup:list')")
    public Object getSysGroupByPage(SysGroup entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(sysGroupService.page(new Page<SysGroup>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有系统类别表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "sys", REMARK = "根据条件查询系统类别表列表")
     @ApiOperation("根据条件查询系统类别表列表")
     @GetMapping("/info/{id}")
     @PreAuthorize("hasAuthority('sys:sysgroup:info')") public R info(@PathVariable("id") Long id){
     SysGroupEntity sysGroup = sysGroupService.getById(id);

     return R.ok().put("sysGroup", sysGroup);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "sys", REMARK = "保存系统类别表")
    @ApiOperation("保存系统类别表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sys:sysgroup:save')")
    public Object save(@RequestBody SysGroup entity) {
        try {
            if (sysGroupService.saves(entity)) {
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
    @SysLog(MODULE = "sys", REMARK = "修改系统类别表")
    @ApiOperation("修改系统类别表")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('sys:sysgroup:update')")
    public Object update(@RequestBody SysGroup entity) {
        try {
            if (sysGroupService.updateById(entity)) {
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
    @SysLog(MODULE = "sys", REMARK = "删除系统类别表")
    @ApiOperation("删除系统类别表")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('sys:sysgroup:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (sysGroupService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sys", REMARK = "查询系统类别表明细")
    @ApiOperation("查询系统类别表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('sys:sysgroup:read')")
    public Object getSysGroupById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("系统类别表id");
            }
            SysGroup object = sysGroupService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询系统类别表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
