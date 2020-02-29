package com.csl.plus.res.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.res.entity.ResTechnicalAppAreaMtoc;
import com.csl.plus.res.service.IResTechnicalAppAreaMtocService;
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
 * 技术应用领域表
 *
 * @author David
 * @email
 * @date 2020-02-29 11:32:27
 */
@Slf4j
@RestController
@Api(tags = "/api/ResTechnicalAppAreaMtocController", description = "技术应用领域表管理")
@RequestMapping("res/restechnicalappareamtoc")
public class ResTechnicalAppAreaMtocController {
    @Autowired
    private IResTechnicalAppAreaMtocService resTechnicalAppAreaMtocService;

    /**
     * 列表
     */
    @SysLog(MODULE = "res", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('res:restechnicalappareamtoc:list')")
    public Object getResTechnicalAppAreaMtocByPage(ResTechnicalAppAreaMtoc entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(resTechnicalAppAreaMtocService.page(new Page<ResTechnicalAppAreaMtoc>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有技术应用领域表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "res", REMARK = "根据条件查询技术应用领域表列表")
     @ApiOperation("根据条件查询技术应用领域表列表")
     @GetMapping("/info/{id}")
     @PreAuthorize("hasAuthority('res:restechnicalappareamtoc:info')") public R info(@PathVariable("id") Long id){
     ResTechnicalAppAreaMtocEntity resTechnicalAppAreaMtoc = resTechnicalAppAreaMtocService.getById(id);

     return R.ok().put("resTechnicalAppAreaMtoc", resTechnicalAppAreaMtoc);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "res", REMARK = "保存技术应用领域表")
    @ApiOperation("保存技术应用领域表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('res:restechnicalappareamtoc:save')")
    public Object save(@RequestBody ResTechnicalAppAreaMtoc entity) {
        try {
            if (resTechnicalAppAreaMtocService.saves(entity)) {
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
    @SysLog(MODULE = "res", REMARK = "修改技术应用领域表")
    @ApiOperation("修改技术应用领域表")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('res:restechnicalappareamtoc:update')")
    public Object update(@RequestBody ResTechnicalAppAreaMtoc entity) {
        try {
            if (resTechnicalAppAreaMtocService.updateById(entity)) {
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
    @SysLog(MODULE = "res", REMARK = "删除技术应用领域表")
    @ApiOperation("删除技术应用领域表")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('res:restechnicalappareamtoc:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (resTechnicalAppAreaMtocService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "res", REMARK = "查询技术应用领域表明细")
    @ApiOperation("查询技术应用领域表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('res:restechnicalappareamtoc:read')")
    public Object getResTechnicalAppAreaMtocById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("技术应用领域表id");
            }
            ResTechnicalAppAreaMtoc object = resTechnicalAppAreaMtocService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询技术应用领域表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
