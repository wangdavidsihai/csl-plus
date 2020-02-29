package com.csl.plus.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.portal.res.service.IResFinanceCompanyDataService;
import com.csl.plus.res.entity.ResFinanceCompanyData;
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
 * @author David
 * @email
 * @date 2020-02-29 12:55:20
 */
@Slf4j
@RestController
@Api(tags = "/api/ResFinanceCompanyDataController", description = "金融企业详细信息管理")
@RequestMapping("/api/res/resfincompdata")
public class ResFinanceCompanyDataController {
    @Autowired
    private IResFinanceCompanyDataService resFinanceCompanyDataService;

    /**
     * 列表
     */
    @SysLog(MODULE = "res", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('res:resfinancecompanydata:list')")
    public Object getResFinanceCompanyDataByPage(ResFinanceCompanyData entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(resFinanceCompanyDataService.page(new Page<ResFinanceCompanyData>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "res", REMARK = "根据条件查询列表")
     @ApiOperation("根据条件查询列表")
     @GetMapping("/info/{id}")
     @PreAuthorize("hasAuthority('res:resfinancecompanydata:info')") public R info(@PathVariable("id") Integer id){
     ResFinanceCompanyDataEntity resFinanceCompanyData = resFinanceCompanyDataService.getById(id);

     return R.ok().put("resFinanceCompanyData", resFinanceCompanyData);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "res", REMARK = "保存")
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('res:resfinancecompanydata:save')")
    public Object save(@RequestBody ResFinanceCompanyData entity) {
        try {
            if (resFinanceCompanyDataService.saves(entity)) {
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
    @SysLog(MODULE = "res", REMARK = "修改")
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('res:resfinancecompanydata:update')")
    public Object update(@RequestBody ResFinanceCompanyData entity) {
        try {
            if (resFinanceCompanyDataService.updateById(entity)) {
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
    @SysLog(MODULE = "res", REMARK = "删除")
    @ApiOperation("删除")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('res:resfinancecompanydata:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (resFinanceCompanyDataService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "res", REMARK = "查询明细")
    @ApiOperation("查询明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('res:resfinancecompanydata:read')")
    public Object getResFinanceCompanyDataById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("id");
            }
            ResFinanceCompanyData object = resFinanceCompanyDataService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
