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
import com.csl.plus.cms.entity.CmsExpert;
import com.csl.plus.common.utils.CommonCodeConst;
import com.csl.plus.portal.cms.service.ICmsExpertService;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 专家表
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-22 13:11:01
 */
@ApiIgnore
@Slf4j
@RestController
@Api(tags = "CmsExpertController", description = "专家表管理")
@RequestMapping("/api/expert")
public class CmsExpertController {
	@Autowired
	private ICmsExpertService cmsExpertService;

	/**
	 * 列表
	 */
	@SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
	@ApiOperation("根据条件查询列表")
	@RequestMapping("/list")
	public Object getCmsExpertByPage(CmsExpert entity,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
		try {
			entity.setStatus(CommonCodeConst.STATUS_ACTIVE);
			return new CommonResult()
					.success(cmsExpertService.page(new Page<CmsExpert>(pageNum, pageSize), new QueryWrapper<>(entity)));
		} catch (Exception e) {
			log.error("根据条件查询所有专家表列表：%s", e.getMessage(), e);
		}
		return new CommonResult().failed();
	}

	/**
	 * 信息
	 */
	/**
	 * @SysLog(MODULE = "cms", REMARK =
	 *                "根据条件查询专家表列表") @ApiOperation("根据条件查询专家表列表") @RequestMapping("/info/{id}") @PreAuthorize("hasAuthority('cms:cmsexpert:info')")
	 *                public R info(@PathVariable("id") Long id){ CmsExpertEntity
	 *                cmsExpert = cmsExpertService.getById(id);
	 * 
	 *                return R.ok().put("cmsExpert", cmsExpert); }
	 */
	/**
	 * 保存
	 */
	@SysLog(MODULE = "cms", REMARK = "保存专家表")
	@ApiOperation("保存专家表")
	@RequestMapping("/save")
	@PreAuthorize("hasAuthority('cms:cmsexpert:save')")
	public Object save(@RequestBody CmsExpert entity) {
		try {
			if (cmsExpertService.saves(entity)) {
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
	@SysLog(MODULE = "cms", REMARK = "修改专家表")
	@ApiOperation("修改专家表")
	@RequestMapping("/update")
	@PreAuthorize("hasAuthority('cms:cmsexpert:update')")
	public Object update(@RequestBody CmsExpert entity) {
		try {
			if (cmsExpertService.updateById(entity)) {
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
	@SysLog(MODULE = "cms", REMARK = "删除专家表")
	@ApiOperation("删除专家表")
	@RequestMapping("/delete")
	@PreAuthorize("hasAuthority('cms:cmsexpert:delete')")
	public Object delete(@ApiParam("id") @PathVariable Long id) {
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("帮助表id");
			}
			if (cmsExpertService.removeById(id)) {
				return new CommonResult().success();
			}
		} catch (Exception e) {
			log.error("删除帮助表：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
		return new CommonResult().failed();
	}

}
