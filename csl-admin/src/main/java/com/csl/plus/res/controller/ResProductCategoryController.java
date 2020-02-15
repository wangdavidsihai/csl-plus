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

import com.csl.plus.res.entity.ResProductCategory;
import com.csl.plus.res.service.IResProductCategoryService;
import org.springframework.web.bind.annotation.*;

/**
 * 需求类别表
 *
 * @author David
 * @email 
 * @date 2020-02-15 22:12:07
 */
@Slf4j
@RestController
@Api(tags = "/api/ResProductCategoryController", description = "需求类别表管理")
@RequestMapping("res/resproductcategory")
public class ResProductCategoryController {
    @Autowired
    private IResProductCategoryService resProductCategoryService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('res:resproductcategory:list')")
    public Object getResProductCategoryByPage(ResProductCategory entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        try {
			return new CommonResult()
					.success(resProductCategoryService.page(new Page<ResProductCategory>(pageNum, pageSize), new QueryWrapper<>(entity)));
		} catch (Exception e) {
			log.error("根据条件查询所有需求类别表列表：%s", e.getMessage(), e);
		}
		return new CommonResult().failed();
    }


    /**
     * 信息
     */
     /**
    @SysLog(MODULE = "cms", REMARK = "根据条件查询需求类别表列表")
    @ApiOperation("根据条件查询需求类别表列表") 
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('res:resproductcategory:info')")
    public R info(@PathVariable("id") Long id){
		ResProductCategoryEntity resProductCategory = resProductCategoryService.getById(id);

        return R.ok().put("resProductCategory", resProductCategory);
    }
	*/
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存需求类别表")
    @ApiOperation("保存需求类别表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('res:resproductcategory:save')")
    public Object save(@RequestBody ResProductCategory entity){
		try {
			if (resProductCategoryService.saves(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "修改需求类别表")
    @ApiOperation("修改需求类别表")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('res:resproductcategory:update')")
    public Object update(@RequestBody ResProductCategory entity){
		try {
			if (resProductCategoryService.updateById(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "删除需求类别表")
    @ApiOperation("删除需求类别表")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('res:resproductcategory:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id){
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("帮助表id");
			}
			if (resProductCategoryService.removeById(id)) {
				return new CommonResult().success();
			}
		} catch (Exception e) {
			log.error("删除帮助表：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
		return new CommonResult().failed();
    }

	@SysLog(MODULE = "cms", REMARK = "查询需求类别表明细")
	@ApiOperation("查询需求类别表明细")
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('cms:cmsarticle:read')")
	public Object getResProductCategoryById(@ApiParam("新闻表id") @PathVariable Long id) {
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("需求类别表id");
			}
			ResProductCategory object = resProductCategoryService.getById(id);
			return new CommonResult().success(object);
		} catch (Exception e) {
			log.error("查询需求类别表明细：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
	}
}
