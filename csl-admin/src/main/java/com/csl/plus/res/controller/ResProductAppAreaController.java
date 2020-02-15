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

import com.csl.plus.res.entity.ResProductAppArea;
import com.csl.plus.res.service.IResProductAppAreaService;
import org.springframework.web.bind.annotation.*;

/**
 * 资源应用领域表
 *
 * @author David
 * @email 
 * @date 2020-02-15 22:12:07
 */
@Slf4j
@RestController
@Api(tags = "/api/ResProductAppAreaController", description = "资源应用领域表管理")
@RequestMapping("res/resproductapparea")
public class ResProductAppAreaController {
    @Autowired
    private IResProductAppAreaService resProductAppAreaService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('res:resproductapparea:list')")
    public Object getResProductAppAreaByPage(ResProductAppArea entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        try {
			return new CommonResult()
					.success(resProductAppAreaService.page(new Page<ResProductAppArea>(pageNum, pageSize), new QueryWrapper<>(entity)));
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
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('res:resproductapparea:info')")
    public R info(@PathVariable("id") Long id){
		ResProductAppAreaEntity resProductAppArea = resProductAppAreaService.getById(id);

        return R.ok().put("resProductAppArea", resProductAppArea);
    }
	*/
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存资源应用领域表")
    @ApiOperation("保存资源应用领域表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('res:resproductapparea:save')")
    public Object save(@RequestBody ResProductAppArea entity){
		try {
			if (resProductAppAreaService.saves(entity)) {
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
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('res:resproductapparea:update')")
    public Object update(@RequestBody ResProductAppArea entity){
		try {
			if (resProductAppAreaService.updateById(entity)) {
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
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('res:resproductapparea:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id){
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("帮助表id");
			}
			if (resProductAppAreaService.removeById(id)) {
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
	public Object getResProductAppAreaById(@ApiParam("新闻表id") @PathVariable Long id) {
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("资源应用领域表id");
			}
			ResProductAppArea object = resProductAppAreaService.getById(id);
			return new CommonResult().success(object);
		} catch (Exception e) {
			log.error("查询资源应用领域表明细：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
	}
}
