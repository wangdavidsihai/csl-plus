package com.csl.plus.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.portal.res.service.IResProductService;
import com.csl.plus.res.entity.ResProduct;
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
 * @date 2020-02-15 22:12:07
 */
@Slf4j
@RestController
@Api(tags = "/api/ResProductController", description = "需求表管理")
@RequestMapping("res/resproduct")
public class ResProductController {
    @Autowired
    private IResProductService resProductService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('res:resproduct:list')")
    public Object getResProductByPage(ResProduct entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(resProductService.page(new Page<ResProduct>(pageNum, pageSize), new QueryWrapper<>(entity)));
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
     @GetMapping("/info/{id}")
     @PreAuthorize("hasAuthority('res:resproduct:info')") public R info(@PathVariable("id") Long id){
     ResProductEntity resProduct = resProductService.getById(id);

     return R.ok().put("resProduct", resProduct);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存需求表")
    @ApiOperation("保存需求表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('res:resproduct:save')")
    public Object save(@RequestBody ResProduct entity) {
        try {
            if (resProductService.saves(entity)) {
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
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('res:resproduct:update')")
    public Object update(@RequestBody ResProduct entity) {
        try {
            if (resProductService.updateById(entity)) {
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
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('res:resproduct:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (resProductService.removeById(id)) {
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
//    @PreAuthorize("hasAuthority('cms:cmsarticle:read')")
    public Object getResProductById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("需求表id");
            }
            ResProduct object = resProductService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询需求表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
