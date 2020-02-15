package com.csl.plus.res.controller;

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

import com.csl.plus.res.entity.ResProjCategory;
import com.csl.plus.res.service.IResProjCategoryService;
import org.springframework.web.bind.annotation.*;

/**
 * 项目类别表
 *
 * @author David
 * @email 
 * @date 2020-02-15 22:12:07
 */
@Slf4j
@RestController
@Api(tags = "/api/ResProjCategoryController", description = "项目类别表管理")
@RequestMapping("res/resprojcategory")
public class ResProjCategoryController {
    @Autowired
    private IResProjCategoryService resProjCategoryService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('res:resprojcategory:list')")
    public Object getResProjCategoryByPage(ResProjCategory entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        try {
			return new CommonResult()
					.success(resProjCategoryService.page(new Page<ResProjCategory>(pageNum, pageSize), new QueryWrapper<>(entity)));
		} catch (Exception e) {
			log.error("根据条件查询所有项目类别表列表：%s", e.getMessage(), e);
		}
		return new CommonResult().failed();
    }


    /**
     * 信息
     */
     /**
    @SysLog(MODULE = "cms", REMARK = "根据条件查询项目类别表列表")
    @ApiOperation("根据条件查询项目类别表列表") 
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('res:resprojcategory:info')")
    public R info(@PathVariable("id") Long id){
		ResProjCategoryEntity resProjCategory = resProjCategoryService.getById(id);

        return R.ok().put("resProjCategory", resProjCategory);
    }
	*/
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存项目类别表")
    @ApiOperation("保存项目类别表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('res:resprojcategory:save')")
    public Object save(@RequestBody ResProjCategory entity){
		try {
			if (resProjCategoryService.saves(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "修改项目类别表")
    @ApiOperation("修改项目类别表")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('res:resprojcategory:update')")
    public Object update(@RequestBody ResProjCategory entity){
		try {
			if (resProjCategoryService.updateById(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "删除项目类别表")
    @ApiOperation("删除项目类别表")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('res:resprojcategory:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id){
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("帮助表id");
			}
			if (resProjCategoryService.removeById(id)) {
				return new CommonResult().success();
			}
		} catch (Exception e) {
			log.error("删除帮助表：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
		return new CommonResult().failed();
    }

	@SysLog(MODULE = "cms", REMARK = "查询项目类别表明细")
	@ApiOperation("查询项目类别表明细")
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('cms:cmsarticle:read')")
	public Object getResProjCategoryById(@ApiParam("新闻表id") @PathVariable Long id) {
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("项目类别表id");
			}
			ResProjCategory object = resProjCategoryService.getById(id);
			return new CommonResult().success(object);
		} catch (Exception e) {
			log.error("查询项目类别表明细：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
	}
}
