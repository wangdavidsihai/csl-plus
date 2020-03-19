package com.csl.plus.res.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.res.entity.ResProjProductionData;
import com.csl.plus.res.service.IResProjProductionDataService;
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
 * 项目表详细信息
 *
 * @author David
 * @email
 * @date 2020-02-15 22:12:07
 */
@Slf4j
@RestController
@Api(tags = "/api/ResProjProductionDataController", description = "项目表详细信息管理")
@RequestMapping("res/projectData")
public class ResProjProductionDataController {
    @Autowired
    private IResProjProductionDataService resProjProductionDataService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('res:resprojproductiondata:list')")
    public Object getResProjProductionDataByPage(ResProjProductionData entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(resProjProductionDataService.page(new Page<ResProjProductionData>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有项目表详细信息列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "cms", REMARK = "根据条件查询项目表详细信息列表")
     @ApiOperation("根据条件查询项目表详细信息列表")
     @GetMapping("/info/{id}")
     @PreAuthorize("hasAuthority('res:resprojproductiondata:info')") public R info(@PathVariable("id") Long id){
     ResProjProductionDataEntity resProjProductionData = resProjProductionDataService.getById(id);

     return R.ok().put("resProjProductionData", resProjProductionData);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存项目表详细信息")
    @ApiOperation("保存项目表详细信息")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('res:resprojproductiondata:save')")
    public Object save(@RequestBody ResProjProductionData entity) {
        try {
            if (resProjProductionDataService.saves(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "修改项目表详细信息")
    @ApiOperation("修改项目表详细信息")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('res:resprojproductiondata:update')")
    public Object update(@RequestBody ResProjProductionData entity) {
        try {
            if (resProjProductionDataService.updateById(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "删除项目表详细信息")
    @ApiOperation("删除项目表详细信息")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('res:resprojproductiondata:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (resProjProductionDataService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "查询项目表详细信息明细")
    @ApiOperation("查询项目表详细信息明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('cms:cmsarticle:read')")
    public Object getResProjProductionDataById(@ApiParam("新闻表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("项目表详细信息id");
            }
            ResProjProductionData object = resProjProductionDataService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询项目表详细信息明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
