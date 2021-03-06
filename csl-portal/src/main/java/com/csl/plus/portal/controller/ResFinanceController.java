package com.csl.plus.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.portal.res.service.IResFinanceService;
import com.csl.plus.res.entity.ResFinance;
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
 * 金融需求表
 *
 * @author David
 * @email
 * @date 2020-02-15 22:12:07
 */
@Slf4j
@RestController
@Api(tags = "/api/ResFinanceController", description = "金融资源表管理")
@RequestMapping("/api/res/resfinance")
public class ResFinanceController {
    @Autowired
    private IResFinanceService resFinanceService;

    /**
     * 列表
     */
    @SysLog(MODULE = "res", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('res:resfinance:list')")
    public Object getResFinanceByPage(ResFinance entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(resFinanceService.page(new Page<ResFinance>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有金融资源表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "cms", REMARK = "根据条件查询金融需求表列表")
     @ApiOperation("根据条件查询金融需求表列表")
     @GetMapping("/info/{id}")
     @PreAuthorize("hasAuthority('res:resfinance:info')") public R info(@PathVariable("id") Long id){
     ResFinanceEntity resFinance = resFinanceService.getById(id);

     return R.ok().put("resFinance", resFinance);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "res", REMARK = "保存金融资源表")
    @ApiOperation("保存金融资源表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('res:resfinance:save')")
    public Object save(@RequestBody ResFinance entity) {
        try {
            if (resFinanceService.saves(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存金融资源：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    /**
     * 修改
     */
    @SysLog(MODULE = "res", REMARK = "修改金融资源表")
    @ApiOperation("修改金融资源表")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('res:resfinance:update')")
    public Object update(@RequestBody ResFinance entity) {
        try {
            if (resFinanceService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新金融资源表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    /**
     * 删除
     */
    @SysLog(MODULE = "res", REMARK = "删除金融资源表")
    @ApiOperation("删除金融资源表")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('res:resfinance:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("金融资源表id");
            }
            if (resFinanceService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除金融资源表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "res", REMARK = "查询金融资源表明细")
    @ApiOperation("查询金融资源表明细")
    @GetMapping(value = "/{id}")
//    @PreAuthorize("hasAuthority('cms:cmsarticle:read')")
    public Object getResFinanceById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("金融资源表id");
            }
            ResFinance object = resFinanceService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询金融资源表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }


    /**
     * 列表
     */
    @SysLog(MODULE = "res", REMARK = "根据条件查询列表")
    @ApiOperation("根据CatId查询列表")
    @GetMapping("/{catid}/list")
    public Object getResFinanceByCatId(ResFinance entity, @ApiParam("category Id") @PathVariable Long catid, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            entity = new ResFinance();
            entity.setCategoryId(catid);
            return new CommonResult()
                    .success(resFinanceService.page(new Page<ResFinance>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有需求表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }
}
