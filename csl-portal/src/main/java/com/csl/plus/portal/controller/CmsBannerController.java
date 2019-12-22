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
import com.csl.plus.cms.entity.CmsBanner;
import com.csl.plus.portal.cms.service.ICmsBannerService;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * Banner List
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-22 14:02:33
 */
@Slf4j
@RestController
@Api(tags = "CmsBannerController", description = "Banner List管理")
@RequestMapping("/api/banner")
public class CmsBannerController {
	@Autowired
	private ICmsBannerService cmsBannerService;

	/**
	 * 列表
	 */
	@SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
	@ApiOperation("根据条件查询列表")
	@RequestMapping("/list")
	public Object getCmsBannerByPage(CmsBanner entity,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
		try {
			return new CommonResult()
					.success(cmsBannerService.page(new Page<CmsBanner>(pageNum, pageSize), new QueryWrapper<>(entity)));
		} catch (Exception e) {
			log.error("根据条件查询所有Banner List列表：%s", e.getMessage(), e);
		}
		return new CommonResult().failed();
	}

	/**
	 * 信息
	 */
	/**
	 * @SysLog(MODULE = "cms", REMARK = "根据条件查询Banner
	 *                List列表") @ApiOperation("根据条件查询Banner
	 *                List列表") @RequestMapping("/info/{id}") @PreAuthorize("hasAuthority('cms:cmsbanner:info')")
	 *                public R info(@PathVariable("id") Long id){ CmsBannerEntity
	 *                cmsBanner = cmsBannerService.getById(id);
	 * 
	 *                return R.ok().put("cmsBanner", cmsBanner); }
	 */
	/**
	 * 保存
	 */
	@SysLog(MODULE = "cms", REMARK = "保存Banner List")
	@ApiOperation("保存Banner List")
	@RequestMapping("/save")
	@PreAuthorize("hasAuthority('cms:cmsbanner:save')")
	public Object save(@RequestBody CmsBanner entity) {
		try {
			if (cmsBannerService.saves(entity)) {
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
	@SysLog(MODULE = "cms", REMARK = "修改Banner List")
	@ApiOperation("修改Banner List")
	@RequestMapping("/update")
	@PreAuthorize("hasAuthority('cms:cmsbanner:update')")
	public Object update(@RequestBody CmsBanner entity) {
		try {
			if (cmsBannerService.updateById(entity)) {
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
	@SysLog(MODULE = "cms", REMARK = "删除Banner List")
	@ApiOperation("删除Banner List")
	@RequestMapping("/delete")
	@PreAuthorize("hasAuthority('cms:cmsbanner:delete')")
	public Object delete(@ApiParam("id") @PathVariable Long id) {
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("帮助表id");
			}
			if (cmsBannerService.removeById(id)) {
				return new CommonResult().success();
			}
		} catch (Exception e) {
			log.error("删除帮助表：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
		return new CommonResult().failed();
	}

}
