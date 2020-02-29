package com.csl.plus.res.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.res.entity.ResFinanceCompany;
import com.csl.plus.res.service.IResFinanceCompanyService;
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
@Api(tags = "/api/ResFinanceCompanyController", description = "管理")
@RequestMapping("res/resfinancecompany")
public class ResFinanceCompanyController {
    @Autowired
    private IResFinanceCompanyService resFinanceCompanyService;

    /**
     * 列表
     */
    @SysLog(MODULE = "res", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('res:resfinancecompany:list')")
    public Object getResFinanceCompanyByPage(ResFinanceCompany entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(resFinanceCompanyService.page(new Page<ResFinanceCompany>(pageNum, pageSize), new QueryWrapper<>(entity)));
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
     @PreAuthorize("hasAuthority('res:resfinancecompany:info')") public R info(@PathVariable("id") Integer id){
     ResFinanceCompanyEntity resFinanceCompany = resFinanceCompanyService.getById(id);

     return R.ok().put("resFinanceCompany", resFinanceCompany);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "res", REMARK = "保存")
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('res:resfinancecompany:save')")
    public Object save(@RequestBody ResFinanceCompany entity) {
        try {
            if (resFinanceCompanyService.saves(entity)) {
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
    @PreAuthorize("hasAuthority('res:resfinancecompany:update')")
    public Object update(@RequestBody ResFinanceCompany entity) {
        try {
            if (resFinanceCompanyService.updateById(entity)) {
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
    @PreAuthorize("hasAuthority('res:resfinancecompany:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (resFinanceCompanyService.removeById(id)) {
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
    @PreAuthorize("hasAuthority('res:resfinancecompany:read')")
    public Object getResFinanceCompanyById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("id");
            }
            ResFinanceCompany object = resFinanceCompanyService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
