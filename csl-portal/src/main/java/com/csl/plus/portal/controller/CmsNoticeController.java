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
import com.csl.plus.cms.entity.CmsNotice;
import com.csl.plus.common.utils.CommonCodeConst;
import com.csl.plus.portal.cms.service.ICmsNoticeService;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 文章表
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-22 14:02:33
 */
@Slf4j
@RestController
@Api(tags = "CmsNoticeController", description = "文章表管理")
@RequestMapping("/api/notice")
public class CmsNoticeController {
	@Autowired
	private ICmsNoticeService cmsNoticeService;

	/**
	 * 列表
	 */
	@SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
	@ApiOperation("根据条件查询列表")
	@RequestMapping("/list")
	public Object getCmsNoticeByPage(CmsNotice entity,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
		try {
			entity.setStatus(CommonCodeConst.STATUS_ACTIVE);
			return new CommonResult()
					.success(cmsNoticeService.page(new Page<CmsNotice>(pageNum, pageSize), new QueryWrapper<>(entity)));
		} catch (Exception e) {
			log.error("根据条件查询所有文章表列表：%s", e.getMessage(), e);
		}
		return new CommonResult().failed();
	}

	/**
	 * 信息
	 */
	/**
	 * @SysLog(MODULE = "cms", REMARK =
	 *                "根据条件查询文章表列表") @ApiOperation("根据条件查询文章表列表") @RequestMapping("/info/{id}") @PreAuthorize("hasAuthority('cms:cmsnotice:info')")
	 *                public R info(@PathVariable("id") String id){ CmsNoticeEntity
	 *                cmsNotice = cmsNoticeService.getById(id);
	 * 
	 *                return R.ok().put("cmsNotice", cmsNotice); }
	 */
	/**
	 * 保存
	 */
	@SysLog(MODULE = "cms", REMARK = "保存文章表")
	@ApiOperation("保存文章表")
	@RequestMapping("/save")
	@PreAuthorize("hasAuthority('cms:cmsnotice:save')")
	public Object save(@RequestBody CmsNotice entity) {
		try {
			if (cmsNoticeService.saves(entity)) {
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
	@SysLog(MODULE = "cms", REMARK = "修改文章表")
	@ApiOperation("修改文章表")
	@RequestMapping("/update")
	@PreAuthorize("hasAuthority('cms:cmsnotice:update')")
	public Object update(@RequestBody CmsNotice entity) {
		try {
			if (cmsNoticeService.updateById(entity)) {
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
	@SysLog(MODULE = "cms", REMARK = "删除文章表")
	@ApiOperation("删除文章表")
	@RequestMapping("/delete")
	@PreAuthorize("hasAuthority('cms:cmsnotice:delete')")
	public Object delete(@ApiParam("id") @PathVariable Long id) {
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("帮助表id");
			}
			if (cmsNoticeService.removeById(id)) {
				return new CommonResult().success();
			}
		} catch (Exception e) {
			log.error("删除帮助表：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
		return new CommonResult().failed();
	}

}
