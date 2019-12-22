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
import com.csl.plus.cms.entity.CmsResourceAppArea;
import com.csl.plus.portal.cms.service.ICmsResourceAppAreaService;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 资源领域表
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-22 13:11:01
 */
@Slf4j
@RestController
@Api(tags = "CmsResourceAppAreaController", description = "资源领域表管理")
@RequestMapping("/api/resourceapparea")
public class CmsResourceAppAreaController {
	@Autowired
	private ICmsResourceAppAreaService cmsResourceAppAreaService;

	/**
	 * 列表
	 */
	@SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
	@ApiOperation("根据条件查询列表")
	@RequestMapping("/list")
	public Object getCmsResourceAppAreaByPage(CmsResourceAppArea entity,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
		try {
			return new CommonResult().success(cmsResourceAppAreaService
					.page(new Page<CmsResourceAppArea>(pageNum, pageSize), new QueryWrapper<>(entity)));
		} catch (Exception e) {
			log.error("根据条件查询所有资源领域表列表：%s", e.getMessage(), e);
		}
		return new CommonResult().failed();
	}

	/**
	 * 信息
	 */
	/**
	 * @SysLog(MODULE = "cms", REMARK =
	 *                "根据条件查询资源领域表列表") @ApiOperation("根据条件查询资源领域表列表") @RequestMapping("/info/{id}") @PreAuthorize("hasAuthority('cms:cmsresourceapparea:info')")
	 *                public R info(@PathVariable("id") Long id){
	 *                CmsResourceAppAreaEntity cmsResourceAppArea =
	 *                cmsResourceAppAreaService.getById(id);
	 * 
	 *                return R.ok().put("cmsResourceAppArea", cmsResourceAppArea); }
	 */
	/**
	 * 保存
	 */
	@SysLog(MODULE = "cms", REMARK = "保存资源领域表")
	@ApiOperation("保存资源领域表")
	@RequestMapping("/save")
	@PreAuthorize("hasAuthority('cms:cmsresourceapparea:save')")
	public Object save(@RequestBody CmsResourceAppArea entity) {
		try {
			if (cmsResourceAppAreaService.saves(entity)) {
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
	@SysLog(MODULE = "cms", REMARK = "修改资源领域表")
	@ApiOperation("修改资源领域表")
	@RequestMapping("/update")
	@PreAuthorize("hasAuthority('cms:cmsresourceapparea:update')")
	public Object update(@RequestBody CmsResourceAppArea entity) {
		try {
			if (cmsResourceAppAreaService.updateById(entity)) {
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
	@SysLog(MODULE = "cms", REMARK = "删除资源领域表")
	@ApiOperation("删除资源领域表")
	@RequestMapping("/delete")
	@PreAuthorize("hasAuthority('cms:cmsresourceapparea:delete')")
	public Object delete(@ApiParam("id") @PathVariable Long id) {
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("帮助表id");
			}
			if (cmsResourceAppAreaService.removeById(id)) {
				return new CommonResult().success();
			}
		} catch (Exception e) {
			log.error("删除帮助表：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
		return new CommonResult().failed();
	}

}
