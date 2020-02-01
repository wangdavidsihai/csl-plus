package com.csl.plus.portal.controller;

import com.csl.plus.annotation.SysLog;
import com.csl.plus.portal.rms.service.IPrdRequirementCategoryService;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

import com.csl.plus.rms.entity.PrdRequirementCategory;
import org.springframework.web.bind.annotation.*;

/**
 * 需求类别表
 *
 * @author David
 * @email 
 * @date 2020-01-29 11:36:20
 */
@Slf4j
@RestController
@Api(tags = "/api/PrdRequirementCategoryController", description = "产品需求类别表管理")
@RequestMapping("/api/rms/prdrequirementcategory")
public class PrdRequirementCategoryController {
    @Autowired
    private IPrdRequirementCategoryService prdRequirementCategoryService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @RequestMapping("/list")
//    @PreAuthorize("hasAuthority('rms:prdrequirementcategory:list')")
    public Object getPrdRequirementCategoryByPage(PrdRequirementCategory entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        try {
			return new CommonResult()
					.success(prdRequirementCategoryService.page(new Page<PrdRequirementCategory>(pageNum, pageSize), new QueryWrapper<>(entity)));
		} catch (Exception e) {
			log.error("根据条件查询所有需求类别表列表：%s", e.getMessage(), e);
		}
		return new CommonResult().failed();
    }


    /**
     * 信息
     */
     /**
    @SysLog(MODULE = "cms", REMARK = "根据条件查询需求类别表列表")
    @ApiOperation("根据条件查询需求类别表列表") 
    @RequestMapping("/info/{id}")
    @PreAuthorize("hasAuthority('rms:prdrequirementcategory:info')")
    public R info(@PathVariable("id") Long id){
		PrdRequirementCategoryEntity prdRequirementCategory = prdRequirementCategoryService.getById(id);

        return R.ok().put("prdRequirementCategory", prdRequirementCategory);
    }
	*/
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存需求类别表")
    @ApiOperation("保存需求类别表")
    @RequestMapping("/save")
    @PreAuthorize("hasAuthority('rms:prdrequirementcategory:save')")
    public Object save(@RequestBody PrdRequirementCategory entity){
		try {
			if (prdRequirementCategoryService.saves(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "修改需求类别表")
    @ApiOperation("修改需求类别表")
    @RequestMapping("/update")
    @PreAuthorize("hasAuthority('rms:prdrequirementcategory:update')")
    public Object update(@RequestBody PrdRequirementCategory entity){
		try {
			if (prdRequirementCategoryService.updateById(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "删除需求类别表")
    @ApiOperation("删除需求类别表")
    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('rms:prdrequirementcategory:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id){
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("帮助表id");
			}
			if (prdRequirementCategoryService.removeById(id)) {
				return new CommonResult().success();
			}
		} catch (Exception e) {
			log.error("删除帮助表：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
		return new CommonResult().failed();
    }

	@SysLog(MODULE = "cms", REMARK = "查询需求类别表明细")
	@ApiOperation("查询需求类别表明细")
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('cms:cmsarticle:read')")
	public Object getPrdRequirementCategoryById(@ApiParam("新闻表id") @PathVariable Long id) {
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("需求类别表id");
			}
			PrdRequirementCategory object = prdRequirementCategoryService.getById(id);
			return new CommonResult().success(object);
		} catch (Exception e) {
			log.error("查询需求类别表明细：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
	}
}
