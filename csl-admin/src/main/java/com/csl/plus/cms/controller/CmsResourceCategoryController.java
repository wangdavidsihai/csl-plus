package com.csl.plus.cms.controller;

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

import com.csl.plus.cms.entity.CmsResourceCategory;
import com.csl.plus.cms.service.ICmsResourceCategoryService;

/**
 * 产品类别表
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-22 13:11:01
 */
@Slf4j
@RestController
@Api(tags = "CmsResourceCategoryController", description = "产品类别表管理")
@RequestMapping("cms/cmsresourcecategory")
public class CmsResourceCategoryController {
    @Autowired
    private ICmsResourceCategoryService cmsResourceCategoryService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('cms:cmsresourcecategory:list')")
    public Object getCmsResourceCategoryByPage(CmsResourceCategory entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        try {
			return new CommonResult()
					.success(cmsResourceCategoryService.page(new Page<CmsResourceCategory>(pageNum, pageSize), new QueryWrapper<>(entity)));
		} catch (Exception e) {
			log.error("根据条件查询所有产品类别表列表：%s", e.getMessage(), e);
		}
		return new CommonResult().failed();
    }


    /**
     * 信息
     */
     /**
    @SysLog(MODULE = "cms", REMARK = "根据条件查询产品类别表列表")
    @ApiOperation("根据条件查询产品类别表列表") 
    @RequestMapping("/info/{id}")
    @PreAuthorize("hasAuthority('cms:cmsresourcecategory:info')")
    public R info(@PathVariable("id") Long id){
		CmsResourceCategoryEntity cmsResourceCategory = cmsResourceCategoryService.getById(id);

        return R.ok().put("cmsResourceCategory", cmsResourceCategory);
    }
	*/
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存产品类别表")
    @ApiOperation("保存产品类别表")
    @RequestMapping("/save")
    @PreAuthorize("hasAuthority('cms:cmsresourcecategory:save')")
    public Object save(@RequestBody CmsResourceCategory entity){
		try {
			if (cmsResourceCategoryService.saves(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "修改产品类别表")
    @ApiOperation("修改产品类别表")
    @RequestMapping("/update")
    @PreAuthorize("hasAuthority('cms:cmsresourcecategory:update')")
    public Object update(@RequestBody CmsResourceCategory entity){
		try {
			if (cmsResourceCategoryService.updateById(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "删除产品类别表")
    @ApiOperation("删除产品类别表")
    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('cms:cmsresourcecategory:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id){
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("帮助表id");
			}
			if (cmsResourceCategoryService.removeById(id)) {
				return new CommonResult().success();
			}
		} catch (Exception e) {
			log.error("删除帮助表：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
		return new CommonResult().failed();
    }

}
