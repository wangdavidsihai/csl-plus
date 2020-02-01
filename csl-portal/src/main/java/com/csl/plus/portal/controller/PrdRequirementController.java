package com.csl.plus.portal.controller;

import com.csl.plus.annotation.SysLog;
import com.csl.plus.portal.rms.service.IPrdRequirementService;
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

import com.csl.plus.rms.entity.PrdRequirement;
import org.springframework.web.bind.annotation.*;

/**
 * 需求表
 *
 * @author David
 * @email 
 * @date 2020-01-29 11:36:20
 */
@Slf4j
@RestController
@Api(tags = "/api/PrdRequirementController", description = "产品需求表管理")
@RequestMapping("/api/rms/prdrequirement")
public class PrdRequirementController {
    @Autowired
    private IPrdRequirementService prdRequirementService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @RequestMapping("/list")
//    @PreAuthorize("hasAuthority('rms:prdrequirement:list')")
    public Object getPrdRequirementByPage(PrdRequirement entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        try {
			return new CommonResult()
					.success(prdRequirementService.page(new Page<PrdRequirement>(pageNum, pageSize), new QueryWrapper<>(entity)));
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
    @PreAuthorize("hasAuthority('rms:prdrequirement:info')")
    public R info(@PathVariable("id") Long id){
		PrdRequirementEntity prdRequirement = prdRequirementService.getById(id);

        return R.ok().put("prdRequirement", prdRequirement);
    }
	*/
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存需求表")
    @ApiOperation("保存需求表")
    @RequestMapping("/save")
    @PreAuthorize("hasAuthority('rms:prdrequirement:save')")
    public Object save(@RequestBody PrdRequirement entity){
		try {
			if (prdRequirementService.saves(entity)) {
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
    @PreAuthorize("hasAuthority('rms:prdrequirement:update')")
    public Object update(@RequestBody PrdRequirement entity){
		try {
			if (prdRequirementService.updateById(entity)) {
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
    @PreAuthorize("hasAuthority('rms:prdrequirement:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id){
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("帮助表id");
			}
			if (prdRequirementService.removeById(id)) {
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
//	@PreAuthorize("hasAuthority('cms:cmsarticle:read')")
	public Object getPrdRequirementById(@ApiParam("新闻表id") @PathVariable Long id) {
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("需求表id");
			}
			PrdRequirement object = prdRequirementService.getById(id);
			return new CommonResult().success(object);
		} catch (Exception e) {
			log.error("查询需求表明细：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
	}
}
