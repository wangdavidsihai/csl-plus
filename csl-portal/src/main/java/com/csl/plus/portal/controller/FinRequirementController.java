package com.csl.plus.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.portal.rms.service.IFinRequirementService;
import com.csl.plus.rms.entity.FinRequirement;
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
 * 金融需求表
 *
 * @author David
 * @email
 * @date 2020-01-30 16:15:33
 */
@Slf4j
@RestController
@Api(tags = "/api/FinRequirementController", description = "金融需求表管理")
@RequestMapping("/api/rms/finrequirement")
public class FinRequirementController {
    @Autowired
    private IFinRequirementService finRequirementService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('rms:finrequirement:list')")
    public Object getFinRequirementByPage(FinRequirement entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(finRequirementService.page(new Page<FinRequirement>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有金融需求表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据CatId查询列表")
    @GetMapping("/{catid}/list")
    public Object getFinRequirementByCatId(FinRequirement entity, @ApiParam("category Id") @PathVariable Long catid, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            entity = new FinRequirement();
            entity.setCategoryId(catid);
            return new CommonResult()
                    .success(finRequirementService.page(new Page<FinRequirement>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有需求表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "cms", REMARK = "根据条件查询金融需求表列表")
     @ApiOperation("根据条件查询金融需求表列表")
     @RequestMapping("/info/{id}")
     @PreAuthorize("hasAuthority('rms:finrequirement:info')") public R info(@PathVariable("id") Long id){
     FinRequirementEntity finRequirement = finRequirementService.getById(id);

     return R.ok().put("finRequirement", finRequirement);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存金融需求表")
    @ApiOperation("保存金融需求表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('rms:finrequirement:save')")
    public Object save(@RequestBody FinRequirement entity) {
        try {
            if (finRequirementService.saves(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "修改金融需求表")
    @ApiOperation("修改金融需求表")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('rms:finrequirement:update')")
    public Object update(@RequestBody FinRequirement entity) {
        try {
            if (finRequirementService.updateById(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "删除金融需求表")
    @ApiOperation("删除金融需求表")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('rms:finrequirement:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (finRequirementService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "查询金融需求表明细")
    @ApiOperation("查询金融需求表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('cms:cmsarticle:read')")
    public Object getFinRequirementById(@ApiParam("新闻表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("金融需求表id");
            }
            FinRequirement object = finRequirementService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询金融需求表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
