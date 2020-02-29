package com.csl.plus.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.portal.res.service.IResTechnicalCategoryMtocService;
import com.csl.plus.res.entity.ResTechnicalCategoryMtoc;
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
 * 技术类别表
 *
 * @author David
 * @email
 * @date 2020-02-29 11:32:27
 */
@Slf4j
@RestController
@Api(tags = "/api/ResTechnicalCategoryMtocController", description = "军转民技术类别管理")
@RequestMapping("/api/res/restechcatemtoc")
public class ResTechnicalCategoryMtocController {
    @Autowired
    private IResTechnicalCategoryMtocService resTechnicalCategoryMtocService;

    /**
     * 列表
     */
    @SysLog(MODULE = "res", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('res:restechnicalcategorymtoc:list')")
    public Object getResTechnicalCategoryMtocByPage(ResTechnicalCategoryMtoc entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(resTechnicalCategoryMtocService.page(new Page<ResTechnicalCategoryMtoc>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有技术类别表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "res", REMARK = "根据条件查询技术类别表列表")
     @ApiOperation("根据条件查询技术类别表列表")
     @GetMapping("/info/{id}")
     @PreAuthorize("hasAuthority('res:restechnicalcategorymtoc:info')") public R info(@PathVariable("id") Long id){
     ResTechnicalCategoryMtocEntity resTechnicalCategoryMtoc = resTechnicalCategoryMtocService.getById(id);

     return R.ok().put("resTechnicalCategoryMtoc", resTechnicalCategoryMtoc);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "res", REMARK = "保存技术类别表")
    @ApiOperation("保存技术类别表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('res:restechnicalcategorymtoc:save')")
    public Object save(@RequestBody ResTechnicalCategoryMtoc entity) {
        try {
            if (resTechnicalCategoryMtocService.saves(entity)) {
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
    @SysLog(MODULE = "res", REMARK = "修改技术类别表")
    @ApiOperation("修改技术类别表")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('res:restechnicalcategorymtoc:update')")
    public Object update(@RequestBody ResTechnicalCategoryMtoc entity) {
        try {
            if (resTechnicalCategoryMtocService.updateById(entity)) {
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
    @SysLog(MODULE = "res", REMARK = "删除技术类别表")
    @ApiOperation("删除技术类别表")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('res:restechnicalcategorymtoc:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (resTechnicalCategoryMtocService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "res", REMARK = "查询技术类别表明细")
    @ApiOperation("查询技术类别表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('res:restechnicalcategorymtoc:read')")
    public Object getResTechnicalCategoryMtocById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("技术类别表id");
            }
            ResTechnicalCategoryMtoc object = resTechnicalCategoryMtocService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询技术类别表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
