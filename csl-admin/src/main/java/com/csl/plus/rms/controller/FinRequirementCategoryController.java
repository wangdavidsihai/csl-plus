package com.csl.plus.rms.controller;

import com.csl.plus.annotation.SysLog;
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

import com.csl.plus.rms.entity.FinRequirementCategory;
import com.csl.plus.rms.service.IFinRequirementCategoryService;
import org.springframework.web.bind.annotation.*;

/**
 * 金融类别表
 *
 * @author David
 * @email 
 * @date 2020-01-30 16:15:33
 */
@Slf4j
@RestController
@Api(tags = "/api/FinRequirementCategoryController", description = "金融类别表管理")
@RequestMapping("rms/finrequirementcategory")
public class FinRequirementCategoryController {
    @Autowired
    private IFinRequirementCategoryService finRequirementCategoryService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('rms:finrequirementcategory:list')")
    public Object getFinRequirementCategoryByPage(FinRequirementCategory entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        try {
			return new CommonResult()
					.success(finRequirementCategoryService.page(new Page<FinRequirementCategory>(pageNum, pageSize), new QueryWrapper<>(entity)));
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
    @RequestMapping("/info/{id}")
    @PreAuthorize("hasAuthority('rms:finrequirementcategory:info')")
    public R info(@PathVariable("id") Long id){
		FinRequirementCategoryEntity finRequirementCategory = finRequirementCategoryService.getById(id);

        return R.ok().put("finRequirementCategory", finRequirementCategory);
    }
	*/
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存金融类别表")
    @ApiOperation("保存金融类别表")
    @RequestMapping("/save")
    @PreAuthorize("hasAuthority('rms:finrequirementcategory:save')")
    public Object save(@RequestBody FinRequirementCategory entity){
		try {
			if (finRequirementCategoryService.saves(entity)) {
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
    @RequestMapping("/update")
    @PreAuthorize("hasAuthority('rms:finrequirementcategory:update')")
    public Object update(@RequestBody FinRequirementCategory entity){
		try {
			if (finRequirementCategoryService.updateById(entity)) {
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
    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('rms:finrequirementcategory:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id){
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("帮助表id");
			}
			if (finRequirementCategoryService.removeById(id)) {
				return new CommonResult().success();
			}
		} catch (Exception e) {
			log.error("删除帮助表：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
		return new CommonResult().failed();
    }

	@SysLog(MODULE = "cms", REMARK = "查询金融类别表明细")
	@ApiOperation("查询金融类别表明细")
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('cms:cmsarticle:read')")
	public Object getFinRequirementCategoryById(@ApiParam("新闻表id") @PathVariable Long id) {
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("金融类别表id");
			}
			FinRequirementCategory object = finRequirementCategoryService.getById(id);
			return new CommonResult().success(object);
		} catch (Exception e) {
			log.error("查询金融类别表明细：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
	}
}
