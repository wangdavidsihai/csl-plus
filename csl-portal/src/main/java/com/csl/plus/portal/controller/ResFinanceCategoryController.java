package com.csl.plus.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.portal.res.service.IResFinanceCategoryService;
import com.csl.plus.res.entity.ResFinanceCategory;
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
 * 金融类别表
 *
 * @author David
 * @email
 * @date 2020-02-15 22:12:07
 */
@Slf4j
@RestController
@Api(tags = "/api/ResFinanceCategoryController", description = "金融资源类别表管理")
@RequestMapping("/api/res/resfinancecategory")
public class ResFinanceCategoryController {
    @Autowired
    private IResFinanceCategoryService resFinanceCategoryService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('res:resfinancecategory:list')")
    public Object getResFinanceCategoryByPage(ResFinanceCategory entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(resFinanceCategoryService.page(new Page<ResFinanceCategory>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有金融类别表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "cms", REMARK = "根据条件查询金融类别表列表")
     @ApiOperation("根据条件查询金融类别表列表")
     @GetMapping("/info/{id}")
     @PreAuthorize("hasAuthority('res:resfinancecategory:info')") public R info(@PathVariable("id") Long id){
     ResFinanceCategoryEntity resFinanceCategory = resFinanceCategoryService.getById(id);

     return R.ok().put("resFinanceCategory", resFinanceCategory);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存金融类别表")
    @ApiOperation("保存金融类别表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('res:resfinancecategory:save')")
    public Object save(@RequestBody ResFinanceCategory entity) {
        try {
            if (resFinanceCategoryService.saves(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "修改金融类别表")
    @ApiOperation("修改金融类别表")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('res:resfinancecategory:update')")
    public Object update(@RequestBody ResFinanceCategory entity) {
        try {
            if (resFinanceCategoryService.updateById(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "删除金融类别表")
    @ApiOperation("删除金融类别表")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('res:resfinancecategory:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (resFinanceCategoryService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "res", REMARK = "查询金融类别表明细")
    @ApiOperation("查询金融类别表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('res:resfinancecategory:read')")
    public Object getResFinanceCategoryById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("金融类别表id");
            }
            ResFinanceCategory object = resFinanceCategoryService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询金融类别表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
