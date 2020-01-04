package com.csl.plus.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.portal.rms.service.IRequirementAppAreaService;
import com.csl.plus.rms.entity.RequirementAppArea;
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
 * 资源应用领域表
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2020-01-04 20:03:44
 */
@Slf4j
@RestController
@Api(tags = "RequirementAppAreaController", description = "资源应用领域表管理")
@RequestMapping("/api/rms/requirementapparea")
public class RequirementAppAreaController {
    @Autowired
    private IRequirementAppAreaService requirementAppAreaService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('rms:requirementapparea:list')")
    public Object getRequirementAppAreaByPage(RequirementAppArea entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        try {
			return new CommonResult()
					.success(requirementAppAreaService.page(new Page<RequirementAppArea>(pageNum, pageSize), new QueryWrapper<>(entity)));
		} catch (Exception e) {
			log.error("根据条件查询所有资源应用领域表列表：%s", e.getMessage(), e);
		}
		return new CommonResult().failed();
    }


    /**
     * 信息
     */
     /**
    @SysLog(MODULE = "cms", REMARK = "根据条件查询资源应用领域表列表")
    @ApiOperation("根据条件查询资源应用领域表列表") 
    @RequestMapping("/info/{id}")
    @PreAuthorize("hasAuthority('rms:requirementapparea:info')")
    public R info(@PathVariable("id") Long id){
		RequirementAppAreaEntity requirementAppArea = requirementAppAreaService.getById(id);

        return R.ok().put("requirementAppArea", requirementAppArea);
    }
	*/
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存资源应用领域表")
    @ApiOperation("保存资源应用领域表")
    @RequestMapping("/save")
    @PreAuthorize("hasAuthority('rms:requirementapparea:save')")
    public Object save(@RequestBody RequirementAppArea entity){
		try {
			if (requirementAppAreaService.saves(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "修改资源应用领域表")
    @ApiOperation("修改资源应用领域表")
    @RequestMapping("/update")
    @PreAuthorize("hasAuthority('rms:requirementapparea:update')")
    public Object update(@RequestBody RequirementAppArea entity){
		try {
			if (requirementAppAreaService.updateById(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "删除资源应用领域表")
    @ApiOperation("删除资源应用领域表")
    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('rms:requirementapparea:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id){
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("帮助表id");
			}
			if (requirementAppAreaService.removeById(id)) {
				return new CommonResult().success();
			}
		} catch (Exception e) {
			log.error("删除帮助表：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
		return new CommonResult().failed();
    }

	@SysLog(MODULE = "cms", REMARK = "查询资源应用领域表明细")
	@ApiOperation("查询资源应用领域表明细")
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('cms:cmsarticle:read')")
	public Object getRequirementAppAreaById(@ApiParam("新闻表id") @PathVariable Long id) {
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("资源应用领域表id");
			}
			RequirementAppArea object = requirementAppAreaService.getById(id);
			return new CommonResult().success(object);
		} catch (Exception e) {
			log.error("查询资源应用领域表明细：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
	}
}
