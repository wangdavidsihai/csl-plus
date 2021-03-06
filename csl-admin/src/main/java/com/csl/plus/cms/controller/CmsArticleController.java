package com.csl.plus.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.cms.entity.CmsArticle;
import com.csl.plus.cms.entity.CmsArticleData;
import com.csl.plus.cms.service.ICmsArticleDataService;
import com.csl.plus.cms.service.ICmsArticleService;
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
@Api(tags = "CmsArticleController", description = "文章表管理")
@RequestMapping("cms/cmsarticle")
public class CmsArticleController {
	@Autowired
	private ICmsArticleService cmsArticleService;
	@Autowired
	private ICmsArticleDataService cmsArticleDataService;

	/**
	 * 列表
	 */
	@SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
	@ApiOperation("根据条件查询列表")
	@RequestMapping("/list")
	@PreAuthorize("hasAuthority('cms:cmsarticle:list')")
	public Object getCmsArticleByPage(CmsArticle entity,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
		try {
			return new CommonResult().success(
					cmsArticleService.page(new Page<CmsArticle>(pageNum, pageSize), new QueryWrapper<>(entity)));
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
	 *                "根据条件查询文章表列表") @ApiOperation("根据条件查询文章表列表") @RequestMapping("/info/{id}") @PreAuthorize("hasAuthority('cms:cmsarticle:info')")
	 *                public R info(@PathVariable("id") String id){ CmsArticleEntity
	 *                cmsArticle = cmsArticleService.getById(id);
	 *
	 *                return R.ok().put("cmsArticle", cmsArticle); }
	 */
	/**
	 * 保存
	 */
	@SysLog(MODULE = "cms", REMARK = "保存文章表")
	@ApiOperation("保存文章表")
	@PostMapping("/create")
	@PreAuthorize("hasAuthority('cms:cmsarticle:save')")
	public Object save(@RequestBody CmsArticle entity) {
		try {
			if (entity == null) {
				return new CommonResult().failed();
			}
			if (cmsArticleService.saves(entity)) {
				cmsArticleDataService.saves(entity);
				return new CommonResult().success();
			}
		} catch (Exception e) {
			log.error("保存文章表：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
		return new CommonResult().failed();
	}

	/**
	 * 修改
	 */
	@SysLog(MODULE = "cms", REMARK = "修改文章表")
	@ApiOperation("修改文章表")
	@PostMapping("/update")
	@PreAuthorize("hasAuthority('cms:cmsarticle:update')")
	public Object update(@RequestParam CmsArticle entity) {
		try {
			if (cmsArticleService.updateById(entity)) {
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
	@PreAuthorize("hasAuthority('cms:cmsarticle:delete')")
	public Object delete(@ApiParam("id") @PathVariable Long id) {
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("帮助表id");
			}
			if (cmsArticleService.removeById(id)) {
				return new CommonResult().success();
			}
		} catch (Exception e) {
			log.error("删除文章表：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
		return new CommonResult().failed();
	}

	@ApiOperation("修改展示状态")
	@RequestMapping(value = "/updateShowStatus")
	@ResponseBody
	@SysLog(MODULE = "cms", REMARK = "修改展示状态")
	public Object updateShowStatus(@RequestParam("id") Long ids, @RequestParam("showStatus") Integer showStatus) {
		int count = cmsArticleService.updateShowStatus(ids, showStatus);
		if (count > 0) {
			return new CommonResult().success(count);
		} else {
			return new CommonResult().failed();
		}
	}

	@SysLog(MODULE = "cms", REMARK = "根据ID新闻表")
	@ApiOperation("查询新闻表明细")
	@GetMapping(value = "/{id}")
	@PreAuthorize(value = "hasAnyAuthority('cms:cmsarticle:info','cms:cmsarticle:view')")
	public Object getCmsArticleById(@ApiParam("新闻表id") @PathVariable Long id) {
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("新闻表id");
			}
			CmsArticle object = cmsArticleService.getById(id);
			if (object != null) {
				CmsArticleData detail = cmsArticleDataService.getById(object.getId());
				object.setDetail(detail);
			}
			return new CommonResult().success(object);
		} catch (Exception e) {
			log.error("查询新闻表明细：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
	}

}
