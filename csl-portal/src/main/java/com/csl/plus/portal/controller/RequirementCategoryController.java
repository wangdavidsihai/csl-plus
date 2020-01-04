package com.csl.plus.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.portal.rms.service.IRequirementCategoryService;
import com.csl.plus.rms.entity.RequirementCategory;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 需求类别表
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2020-01-04 20:03:44
 */
@Slf4j
@RestController
@Api(tags = "RequirementCategoryController", description = "需求类别表管理")
@RequestMapping("/api/rms/requirementcategory")
public class RequirementCategoryController {
    @Autowired
    private IRequirementCategoryService requirementCategoryService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @RequestMapping("/list")
//    @PreAuthorize("hasAuthority('rms:requirementcategory:list')")
    public Object getRequirementCategoryByPage(RequirementCategory entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        try {
			return new CommonResult()
					.success(requirementCategoryService.page(new Page<RequirementCategory>(pageNum, pageSize), new QueryWrapper<>(entity)));
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
    @RequestMapping("/info/{id}")
    @PreAuthorize("hasAuthority('rms:requirementcategory:info')")
    public R info(@PathVariable("id") Long id){
		RequirementCategoryEntity requirementCategory = requirementCategoryService.getById(id);

        return R.ok().put("requirementCategory", requirementCategory);
    }
	*/
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存需求类别表")
    @ApiOperation("保存需求类别表")
    @RequestMapping("/save")
    @PreAuthorize("hasAuthority('rms:requirementcategory:save')")
    public Object save(@RequestBody RequirementCategory entity){
		try {
			if (requirementCategoryService.saves(entity)) {
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
    @RequestMapping("/update")
    @PreAuthorize("hasAuthority('rms:requirementcategory:update')")
    public Object update(@RequestBody RequirementCategory entity){
		try {
			if (requirementCategoryService.updateById(entity)) {
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
    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('rms:requirementcategory:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id){
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("帮助表id");
			}
			if (requirementCategoryService.removeById(id)) {
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
	public Object getRequirementCategoryById(@ApiParam("新闻表id") @PathVariable Long id) {
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("需求类别表id");
			}
			RequirementCategory object = requirementCategoryService.getById(id);
			return new CommonResult().success(object);
		} catch (Exception e) {
			log.error("查询需求类别表明细：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
	}
}
