package com.csl.plus.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.portal.res.service.IResTechnicalAppAreaService;
import com.csl.plus.res.entity.ResTechnicalAppArea;
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
 * @date 2020-02-15 22:12:07
 */
@ApiIgnore
@Slf4j
@RestController
@Api(tags = "/api/ResTechnicalAppAreaController", description = "技术应用领域表管理")
@RequestMapping("/api/res/restechnicalapparea")
public class ResTechnicalAppAreaController {
    @Autowired
    private IResTechnicalAppAreaService resTechnicalAppAreaService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('res:restechnicalapparea:list')")
    public Object getResTechnicalAppAreaByPage(ResTechnicalAppArea entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(resTechnicalAppAreaService.page(new Page<ResTechnicalAppArea>(pageNum, pageSize), new QueryWrapper<>(entity)));
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
     @GetMapping("/info/{id}")
     @PreAuthorize("hasAuthority('res:restechnicalapparea:info')") public R info(@PathVariable("id") Long id){
     ResTechnicalAppAreaEntity resTechnicalAppArea = resTechnicalAppAreaService.getById(id);

     return R.ok().put("resTechnicalAppArea", resTechnicalAppArea);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存技术应用领域表")
    @ApiOperation("保存技术应用领域表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('res:restechnicalapparea:save')")
    public Object save(@RequestBody ResTechnicalAppArea entity) {
        try {
            if (resTechnicalAppAreaService.saves(entity)) {
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
    @PreAuthorize("hasAuthority('res:restechnicalapparea:update')")
    public Object update(@RequestBody ResTechnicalAppArea entity) {
        try {
            if (resTechnicalAppAreaService.updateById(entity)) {
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
    @PreAuthorize("hasAuthority('res:restechnicalapparea:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (resTechnicalAppAreaService.removeById(id)) {
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
//    @PreAuthorize("hasAuthority('cms:cmsarticle:read')")
    public Object getResTechnicalAppAreaById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("技术应用领域表id");
            }
            ResTechnicalAppArea object = resTechnicalAppAreaService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询技术应用领域表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
