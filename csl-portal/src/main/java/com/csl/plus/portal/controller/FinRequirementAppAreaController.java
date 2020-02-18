package com.csl.plus.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.portal.rms.service.IFinRequirementAppAreaService;
import com.csl.plus.rms.entity.FinRequirementAppArea;
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
 * 金融应用领域表
 *
 * @author David
 * @email
 * @date 2020-01-30 16:15:33
 */
@Slf4j
@RestController
@Api(tags = "/api/FinRequirementAppAreaController", description = "金融应用领域表管理")
@RequestMapping("/api/rms/finrequirementapparea")
public class FinRequirementAppAreaController {

    @Autowired
    private IFinRequirementAppAreaService finRequirementAppAreaService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
//	@PreAuthorize("hasAuthority('rms:finrequirementapparea:list')")
    public Object getFinRequirementAppAreaByPage(FinRequirementAppArea entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(finRequirementAppAreaService.page(new Page<FinRequirementAppArea>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有金融应用领域表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "cms", REMARK = "根据条件查询金融应用领域表列表")
     @ApiOperation("根据条件查询金融应用领域表列表")
     @RequestMapping("/info/{id}")
     @PreAuthorize("hasAuthority('rms:finrequirementapparea:info')") public R info(@PathVariable("id") Long id){
     FinRequirementAppAreaEntity finRequirementAppArea = finRequirementAppAreaService.getById(id);

     return R.ok().put("finRequirementAppArea", finRequirementAppArea);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存金融应用领域表")
    @ApiOperation("保存金融应用领域表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('rms:finrequirementapparea:save')")
    public Object save(@RequestBody FinRequirementAppArea entity) {
        try {
            if (finRequirementAppAreaService.saves(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "修改金融应用领域表")
    @ApiOperation("修改金融应用领域表")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('rms:finrequirementapparea:update')")
    public Object update(@RequestBody FinRequirementAppArea entity) {
        try {
            if (finRequirementAppAreaService.updateById(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "删除金融应用领域表")
    @ApiOperation("删除金融应用领域表")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('rms:finrequirementapparea:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (finRequirementAppAreaService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "查询金融应用领域表明细")
    @ApiOperation("查询金融应用领域表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('rms:finrequirementapparea:read')")
    public Object getFinRequirementAppAreaById(@ApiParam("新闻表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("金融应用领域表id");
            }
            FinRequirementAppArea object = finRequirementAppAreaService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询金融应用领域表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
