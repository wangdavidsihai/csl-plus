package com.csl.plus.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.portal.rms.service.IRequirementService;
import com.csl.plus.rms.entity.Requirement;
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
 * 需求表
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2020-01-04 20:03:44
 */
@Slf4j
@RestController
@Api(tags = "RequirementController", description = "需求表管理")
@RequestMapping("/api/rms/requirement")
public class RequirementController {
    @Autowired
    private IRequirementService requirementService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('rms:requirement:list')")
    public Object getRequirementByPage(Requirement entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        try {
			return new CommonResult()
					.success(requirementService.page(new Page<Requirement>(pageNum, pageSize), new QueryWrapper<>(entity)));
		} catch (Exception e) {
			log.error("根据条件查询所有需求表列表：%s", e.getMessage(), e);
		}
		return new CommonResult().failed();
    }


    /**
     * 信息
     */
     /**
    @SysLog(MODULE = "cms", REMARK = "根据条件查询需求表列表")
    @ApiOperation("根据条件查询需求表列表") 
    @RequestMapping("/info/{id}")
    @PreAuthorize("hasAuthority('rms:requirement:info')")
    public R info(@PathVariable("id") Long id){
		RequirementEntity requirement = requirementService.getById(id);

        return R.ok().put("requirement", requirement);
    }
	*/
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存需求表")
    @ApiOperation("保存需求表")
    @RequestMapping("/save")
    @PreAuthorize("hasAuthority('rms:requirement:save')")
    public Object save(@RequestBody Requirement entity){
		try {
			if (requirementService.saves(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "修改需求表")
    @ApiOperation("修改需求表")
    @RequestMapping("/update")
    @PreAuthorize("hasAuthority('rms:requirement:update')")
    public Object update(@RequestBody Requirement entity){
		try {
			if (requirementService.updateById(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "删除需求表")
    @ApiOperation("删除需求表")
    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('rms:requirement:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id){
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("帮助表id");
			}
			if (requirementService.removeById(id)) {
				return new CommonResult().success();
			}
		} catch (Exception e) {
			log.error("删除帮助表：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
		return new CommonResult().failed();
    }

	@SysLog(MODULE = "cms", REMARK = "查询需求表明细")
	@ApiOperation("查询需求表明细")
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('cms:cmsarticle:read')")
	public Object getRequirementById(@ApiParam("新闻表id") @PathVariable Long id) {
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("需求表id");
			}
			Requirement object = requirementService.getById(id);
			return new CommonResult().success(object);
		} catch (Exception e) {
			log.error("查询需求表明细：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
	}
}
