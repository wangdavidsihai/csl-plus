package com.csl.plus.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.cms.entity.CmsCategory;
import com.csl.plus.portal.cms.service.ICmsCategoryService;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 栏目表
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-22 14:02:33
 */
@ApiIgnore
@Slf4j
@RestController
@Api(tags = "CmsCategoryController", description = "栏目表管理")
@RequestMapping("/api/category")
public class CmsCategoryController {
	@Autowired
	private ICmsCategoryService cmsCategoryService;

	/**
	 * 列表
	 */
	@SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
	@ApiOperation("根据条件查询列表")
	@RequestMapping("/list")
	@PreAuthorize("hasAuthority('cms:cmscategory:list')")
	public Object getCmsCategoryByPage(CmsCategory entity,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
		try {
			return new CommonResult().success(
					cmsCategoryService.page(new Page<CmsCategory>(pageNum, pageSize), new QueryWrapper<>(entity)));
		} catch (Exception e) {
			log.error("根据条件查询所有栏目表列表：%s", e.getMessage(), e);
		}
		return new CommonResult().failed();
	}

	/**
	 * 信息
	 */
	/**
	 * @SysLog(MODULE = "cms", REMARK =
	 *                "根据条件查询栏目表列表") @ApiOperation("根据条件查询栏目表列表") @RequestMapping("/info/{id}") @PreAuthorize("hasAuthority('cms:cmscategory:info')")
	 *                public R info(@PathVariable("id") String id){
	 *                CmsCategoryEntity cmsCategory =
	 *                cmsCategoryService.getById(id);
	 * 
	 *                return R.ok().put("cmsCategory", cmsCategory); }
	 */
	/**
	 * 保存
	 */
	@SysLog(MODULE = "cms", REMARK = "保存栏目表")
	@ApiOperation("保存栏目表")
	@RequestMapping("/save")
	@PreAuthorize("hasAuthority('cms:cmscategory:save')")
	public Object save(@RequestBody CmsCategory entity) {
		try {
			if (cmsCategoryService.saves(entity)) {
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
	@SysLog(MODULE = "cms", REMARK = "修改栏目表")
	@ApiOperation("修改栏目表")
	@RequestMapping("/update")
	@PreAuthorize("hasAuthority('cms:cmscategory:update')")
	public Object update(@RequestBody CmsCategory entity) {
		try {
			if (cmsCategoryService.updateById(entity)) {
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
	@SysLog(MODULE = "cms", REMARK = "删除栏目表")
	@ApiOperation("删除栏目表")
	@RequestMapping("/delete")
	@PreAuthorize("hasAuthority('cms:cmscategory:delete')")
	public Object delete(@ApiParam("id") @PathVariable Long id) {
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("帮助表id");
			}
			if (cmsCategoryService.removeById(id)) {
				return new CommonResult().success();
			}
		} catch (Exception e) {
			log.error("删除帮助表：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
		return new CommonResult().failed();
	}

}
