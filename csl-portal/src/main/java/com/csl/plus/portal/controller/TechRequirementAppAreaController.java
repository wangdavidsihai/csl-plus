package com.csl.plus.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.portal.rms.service.ITechRequirementAppAreaService;
import com.csl.plus.rms.entity.TechRequirementAppArea;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 技术应用领域表
 *
 * @author David
 * @email
 * @date 2020-01-30 10:58:45
 */
@ApiIgnore
@Slf4j
@RestController
@Api(tags = "/api/TechRequirementAppAreaController", description = "技术应用领域表管理")
@RequestMapping("/api/rms/techrequirementapparea")
public class TechRequirementAppAreaController {
    @Autowired
    private ITechRequirementAppAreaService techRequirementAppAreaService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('rms:techrequirementapparea:list')")
    public Object getTechRequirementAppAreaByPage(TechRequirementAppArea entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(techRequirementAppAreaService.page(new Page<TechRequirementAppArea>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有技术应用领域表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "cms", REMARK = "根据条件查询技术应用领域表列表")
     @ApiOperation("根据条件查询技术应用领域表列表")
     @RequestMapping("/info/{id}")
     @PreAuthorize("hasAuthority('rms:techrequirementapparea:info')") public R info(@PathVariable("id") Long id){
     TechRequirementAppAreaEntity techRequirementAppArea = techRequirementAppAreaService.getById(id);

     return R.ok().put("techRequirementAppArea", techRequirementAppArea);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存技术应用领域表")
    @ApiOperation("保存技术应用领域表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('rms:techrequirementapparea:save')")
    public Object save(@RequestBody TechRequirementAppArea entity) {
        try {
            if (techRequirementAppAreaService.saves(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "修改技术应用领域表")
    @ApiOperation("修改技术应用领域表")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('rms:techrequirementapparea:update')")
    public Object update(@RequestBody TechRequirementAppArea entity) {
        try {
            if (techRequirementAppAreaService.updateById(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "删除技术应用领域表")
    @ApiOperation("删除技术应用领域表")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('rms:techrequirementapparea:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (techRequirementAppAreaService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "查询技术应用领域表明细")
    @ApiOperation("查询技术应用领域表明细")
    @GetMapping(value = "/{id}")
//	@PreAuthorize("hasAuthority('cms:cmsarticle:read')")
    public Object getTechRequirementAppAreaById(@ApiParam("新闻表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("技术应用领域表id");
            }
            TechRequirementAppArea object = techRequirementAppAreaService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询技术应用领域表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
