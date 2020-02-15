package com.csl.plus.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.portal.res.service.IResTechnicalCategoryService;
import com.csl.plus.res.entity.ResTechnicalCategory;
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
 * @date 2020-02-15 22:12:07
 */
@Slf4j
@RestController
@Api(tags = "/api/ResTechnicalCategoryController", description = "技术类别表管理")
@RequestMapping("res/restechnicalcategory")
public class ResTechnicalCategoryController {
    @Autowired
    private IResTechnicalCategoryService resTechnicalCategoryService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('res:restechnicalcategory:list')")
    public Object getResTechnicalCategoryByPage(ResTechnicalCategory entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(resTechnicalCategoryService.page(new Page<ResTechnicalCategory>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有技术类别表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "cms", REMARK = "根据条件查询技术类别表列表")
     @ApiOperation("根据条件查询技术类别表列表")
     @GetMapping("/info/{id}")
     @PreAuthorize("hasAuthority('res:restechnicalcategory:info')") public R info(@PathVariable("id") Long id){
     ResTechnicalCategoryEntity resTechnicalCategory = resTechnicalCategoryService.getById(id);

     return R.ok().put("resTechnicalCategory", resTechnicalCategory);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存技术类别表")
    @ApiOperation("保存技术类别表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('res:restechnicalcategory:save')")
    public Object save(@RequestBody ResTechnicalCategory entity) {
        try {
            if (resTechnicalCategoryService.saves(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "修改技术类别表")
    @ApiOperation("修改技术类别表")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('res:restechnicalcategory:update')")
    public Object update(@RequestBody ResTechnicalCategory entity) {
        try {
            if (resTechnicalCategoryService.updateById(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "删除技术类别表")
    @ApiOperation("删除技术类别表")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('res:restechnicalcategory:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (resTechnicalCategoryService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "查询技术类别表明细")
    @ApiOperation("查询技术类别表明细")
    @GetMapping(value = "/{id}")
//    @PreAuthorize("hasAuthority('cms:cmsarticle:read')")
    public Object getResTechnicalCategoryById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("技术类别表id");
            }
            ResTechnicalCategory object = resTechnicalCategoryService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询技术类别表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
