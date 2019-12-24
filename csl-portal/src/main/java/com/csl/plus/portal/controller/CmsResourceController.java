package com.csl.plus.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.cms.entity.CmsResource;
import com.csl.plus.portal.cms.service.ICmsResourceService;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 资源表
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-22 13:11:01
 */
@Slf4j
@RestController
@Api(tags = "CmsResourceController", description = "资源表管理")
@RequestMapping("/api/resource")
public class CmsResourceController {
	@Autowired
	private ICmsResourceService cmsResourceService;

	/**
	 * 列表
	 */
	@SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
	@ApiOperation("根据条件查询列表")
	@GetMapping("/list")
	public Object getCmsResourceByPage(CmsResource entity,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
		try {
			return new CommonResult().success(
					cmsResourceService.page(new Page<CmsResource>(pageNum, pageSize), new QueryWrapper<>(entity)));
		} catch (Exception e) {
			log.error("根据条件查询所有资源表列表：%s", e.getMessage(), e);
		}
		return new CommonResult().failed();
	}

	/**
	 * 信息
	 */
	/**
	 * @SysLog(MODULE = "cms", REMARK =
	 *                "根据条件查询资源表列表") @ApiOperation("根据条件查询资源表列表") @RequestMapping("/info/{id}") @PreAuthorize("hasAuthority('cms:cmsresource:info')")
	 *                public R info(@PathVariable("id") Long id){ CmsResourceEntity
	 *                cmsResource = cmsResourceService.getById(id);
	 * 
	 *                return R.ok().put("cmsResource", cmsResource); }
	 */
	/**
	 * 保存
	 */
	@SysLog(MODULE = "cms", REMARK = "保存资源表")
	@ApiOperation("保存资源表")
	@PostMapping("/save")
	@PreAuthorize("hasAuthority('cms:cmsresource:save')")
	public Object save(@RequestBody CmsResource entity) {
		try {
			if (cmsResourceService.saves(entity)) {
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
	@SysLog(MODULE = "cms", REMARK = "修改资源表")
	@ApiOperation("修改资源表")
	@PostMapping("/update")
	@PreAuthorize("hasAuthority('cms:cmsresource:update')")
	public Object update(@RequestBody CmsResource entity) {
		try {
			if (cmsResourceService.updateById(entity)) {
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
	@SysLog(MODULE = "cms", REMARK = "删除资源表")
	@ApiOperation("删除资源表")
	@DeleteMapping("/delete")
	@PreAuthorize("hasAuthority('cms:cmsresource:delete')")
	public Object delete(@ApiParam("id") @PathVariable Long id) {
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("帮助表id");
			}
			if (cmsResourceService.removeById(id)) {
				return new CommonResult().success();
			}
		} catch (Exception e) {
			log.error("删除帮助表：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
		return new CommonResult().failed();
	}

}
