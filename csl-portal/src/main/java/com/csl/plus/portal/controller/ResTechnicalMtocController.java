package com.csl.plus.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.portal.res.service.IResTechnicalMtocService;
import com.csl.plus.res.entity.ResTechnicalMtoc;
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
 * @email
 * @date 2020-02-29 11:32:27
 */
@Slf4j
@RestController
@Api(tags = "/api/ResTechnicalMtocController", description = "军转民技术管理")
@RequestMapping("/api/res/restechmtoc")
public class ResTechnicalMtocController {
    @Autowired
    private IResTechnicalMtocService resTechnicalMtocService;

    /**
     * 列表
     */
    @SysLog(MODULE = "res", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('res:restechnicalmtoc:list')")
    public Object getResTechnicalMtocByPage(ResTechnicalMtoc entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(resTechnicalMtocService.page(new Page<ResTechnicalMtoc>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有需求表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "res", REMARK = "根据条件查询需求表列表")
     @ApiOperation("根据条件查询需求表列表")
     @GetMapping("/info/{id}")
     @PreAuthorize("hasAuthority('res:restechnicalmtoc:info')") public R info(@PathVariable("id") Long id){
     ResTechnicalMtocEntity resTechnicalMtoc = resTechnicalMtocService.getById(id);

     return R.ok().put("resTechnicalMtoc", resTechnicalMtoc);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "res", REMARK = "保存需求表")
    @ApiOperation("保存需求表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('res:restechnicalmtoc:save')")
    public Object save(@RequestBody ResTechnicalMtoc entity) {
        try {
            if (resTechnicalMtocService.saves(entity)) {
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
    @SysLog(MODULE = "res", REMARK = "修改需求表")
    @ApiOperation("修改需求表")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('res:restechnicalmtoc:update')")
    public Object update(@RequestBody ResTechnicalMtoc entity) {
        try {
            if (resTechnicalMtocService.updateById(entity)) {
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
    @SysLog(MODULE = "res", REMARK = "删除需求表")
    @ApiOperation("删除需求表")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('res:restechnicalmtoc:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (resTechnicalMtocService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "res", REMARK = "查询需求表明细")
    @ApiOperation("查询需求表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('res:restechnicalmtoc:read')")
    public Object getResTechnicalMtocById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("需求表id");
            }
            ResTechnicalMtoc object = resTechnicalMtocService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询需求表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
