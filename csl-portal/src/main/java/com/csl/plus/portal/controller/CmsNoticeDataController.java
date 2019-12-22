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
import com.csl.plus.cms.entity.CmsNoticeData;
import com.csl.plus.common.utils.CommonCodeConst;
import com.csl.plus.portal.cms.service.ICmsNoticeDataService;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 文章详表
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-22 14:02:33
 */
@Slf4j
@RestController
@Api(tags = "CmsNoticeDataController", description = "文章详表管理")
@RequestMapping("/api/noticedata")
public class CmsNoticeDataController {
	@Autowired
	private ICmsNoticeDataService cmsNoticeDataService;

	/**
	 * 列表
	 */
	@SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
	@ApiOperation("根据条件查询列表")
	@RequestMapping("/list")
	public Object getCmsNoticeDataByPage(CmsNoticeData entity,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
		try {
			entity.setStatus(CommonCodeConst.STATUS_ACTIVE);
			return new CommonResult().success(
					cmsNoticeDataService.page(new Page<CmsNoticeData>(pageNum, pageSize), new QueryWrapper<>(entity)));
		} catch (Exception e) {
			log.error("根据条件查询所有文章详表列表：%s", e.getMessage(), e);
		}
		return new CommonResult().failed();
	}

	/**
	 * 信息
	 */
	/**
	 * @SysLog(MODULE = "cms", REMARK =
	 *                "根据条件查询文章详表列表") @ApiOperation("根据条件查询文章详表列表") @RequestMapping("/info/{id}") @PreAuthorize("hasAuthority('cms:cmsnoticedata:info')")
	 *                public R info(@PathVariable("id") String id){
	 *                CmsNoticeDataEntity cmsNoticeData =
	 *                cmsNoticeDataService.getById(id);
	 * 
	 *                return R.ok().put("cmsNoticeData", cmsNoticeData); }
	 */
	/**
	 * 保存
	 */
	@SysLog(MODULE = "cms", REMARK = "保存文章详表")
	@ApiOperation("保存文章详表")
	@RequestMapping("/save")
	@PreAuthorize("hasAuthority('cms:cmsnoticedata:save')")
	public Object save(@RequestBody CmsNoticeData entity) {
		try {
			if (cmsNoticeDataService.saves(entity)) {
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
	@SysLog(MODULE = "cms", REMARK = "修改文章详表")
	@ApiOperation("修改文章详表")
	@RequestMapping("/update")
	@PreAuthorize("hasAuthority('cms:cmsnoticedata:update')")
	public Object update(@RequestBody CmsNoticeData entity) {
		try {
			if (cmsNoticeDataService.updateById(entity)) {
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
	@SysLog(MODULE = "cms", REMARK = "删除文章详表")
	@ApiOperation("删除文章详表")
	@RequestMapping("/delete")
	@PreAuthorize("hasAuthority('cms:cmsnoticedata:delete')")
	public Object delete(@ApiParam("id") @PathVariable Long id) {
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("帮助表id");
			}
			if (cmsNoticeDataService.removeById(id)) {
				return new CommonResult().success();
			}
		} catch (Exception e) {
			log.error("删除帮助表：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
		return new CommonResult().failed();
	}

}
