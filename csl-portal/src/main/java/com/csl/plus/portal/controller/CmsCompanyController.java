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
import com.csl.plus.cms.entity.CmsCompany;
import com.csl.plus.portal.cms.service.ICmsCompanyService;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * Company
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-19 20:56:57
 */
@Slf4j
@RestController
@Api(tags = "CmsCompanyController", description = "Company管理")
@RequestMapping("/api/company")
public class CmsCompanyController {
	@Autowired
	private ICmsCompanyService cmsCompanyService;

	/**
	 * 列表
	 */
	@SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
	@ApiOperation("根据条件查询列表")
	@RequestMapping("/list")
	public Object getCmsCompanyByPage(CmsCompany entity,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
		try {
			return new CommonResult().success(
					cmsCompanyService.page(new Page<CmsCompany>(pageNum, pageSize), new QueryWrapper<>(entity)));
		} catch (Exception e) {
			log.error("根据条件查询所有Company列表：%s", e.getMessage(), e);
		}
		return new CommonResult().failed();
	}

	/**
	 * 信息
	 */
	/**
	 * @SysLog(MODULE = "cms", REMARK =
	 *                "根据条件查询Company列表") @ApiOperation("根据条件查询Company列表") @RequestMapping("/info/{id}") @PreAuthorize("hasAuthority('cms:cmscompany:info')")
	 *                public R info(@PathVariable("id") Long id){ CmsCompanyEntity
	 *                cmsCompany = cmsCompanyService.getById(id);
	 * 
	 *                return R.ok().put("cmsCompany", cmsCompany); }
	 */
	/**
	 * 保存
	 */
	@SysLog(MODULE = "cms", REMARK = "保存Company")
	@ApiOperation("保存Company")
	@RequestMapping("/save")
	@PreAuthorize("hasAuthority('cms:cmscompany:save')")
	public Object save(@RequestBody CmsCompany entity) {
		try {
			if (cmsCompanyService.saves(entity)) {
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
	@SysLog(MODULE = "cms", REMARK = "修改Company")
	@ApiOperation("修改Company")
	@RequestMapping("/update")
	@PreAuthorize("hasAuthority('cms:cmscompany:update')")
	public Object update(@RequestBody CmsCompany entity) {
		try {
			if (cmsCompanyService.updateById(entity)) {
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
	@SysLog(MODULE = "cms", REMARK = "删除Company")
	@ApiOperation("删除Company")
	@RequestMapping("/delete")
	@PreAuthorize("hasAuthority('cms:cmscompany:delete')")
	public Object delete(@ApiParam("id") @PathVariable Long id) {
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("company id");
			}
			if (cmsCompanyService.removeById(id)) {
				return new CommonResult().success();
			}
		} catch (Exception e) {
			log.error("删除表：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
		return new CommonResult().failed();
	}

}
