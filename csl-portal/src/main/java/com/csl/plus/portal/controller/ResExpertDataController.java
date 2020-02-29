package com.csl.plus.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.portal.res.service.IResExpertDataService;
import com.csl.plus.res.entity.ResExpertData;
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
 * 专家表
 *
 * @author David
 * @email
 * @date 2020-02-15 22:48:23
 */
@Slf4j
@RestController
@Api(tags = "/api/ResExpertDataController", description = "专家详细信息管理")
@RequestMapping("/api/res/resexpertdata")
public class ResExpertDataController {
    @Autowired
    private IResExpertDataService resExpertDataService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('res:resexpertdata:list')")
    public Object getResExpertDataByPage(ResExpertData entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(resExpertDataService.page(new Page<ResExpertData>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有专家表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "cms", REMARK = "根据条件查询专家表列表")
     @ApiOperation("根据条件查询专家表列表")
     @GetMapping("/info/{id}")
     @PreAuthorize("hasAuthority('res:resexpertdata:info')") public R info(@PathVariable("id") Long id){
     ResExpertDataEntity resExpertData = resExpertDataService.getById(id);

     return R.ok().put("resExpertData", resExpertData);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存专家表")
    @ApiOperation("保存专家表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('res:resexpertdata:save')")
    public Object save(@RequestBody ResExpertData entity) {
        try {
            if (resExpertDataService.saves(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "修改专家表")
    @ApiOperation("修改专家表")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('res:resexpertdata:update')")
    public Object update(@RequestBody ResExpertData entity) {
        try {
            if (resExpertDataService.updateById(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "删除专家表")
    @ApiOperation("删除专家表")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('res:resexpertdata:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (resExpertDataService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "查询专家表明细")
    @ApiOperation("查询专家表明细")
    @GetMapping(value = "/{id}")
//    @PreAuthorize("hasAuthority('res:resexpertdata:read')")
    public Object getResExpertDataById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("专家表id");
            }
            ResExpertData object = resExpertDataService.getExpertDataByExpId(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询专家表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
