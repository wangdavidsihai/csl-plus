package com.csl.plus.oms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.oms.entity.OmsOrderBill;
import com.csl.plus.oms.service.IOmsOrderBillService;
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
 * 发票表
 *
 * @author David
 * @email
 * @date 2020-03-04 10:37:32
 */
@Slf4j
@RestController
@Api(tags = "/api/OmsOrderBillController", description = "发票表管理")
@RequestMapping("oms/omsorderbill")
public class OmsOrderBillController {
    @Autowired
    private IOmsOrderBillService omsOrderBillService;

    /**
     * 列表
     */
    @SysLog(MODULE = "oms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('oms:omsorderbill:list')")
    public Object getOmsOrderBillByPage(OmsOrderBill entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(omsOrderBillService.page(new Page<OmsOrderBill>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有发票表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "oms", REMARK = "根据条件查询发票表列表")
     @ApiOperation("根据条件查询发票表列表")
     @GetMapping("/info/{id}")
     @PreAuthorize("hasAuthority('oms:omsorderbill:info')") public R info(@PathVariable("id") Long id){
     OmsOrderBillEntity omsOrderBill = omsOrderBillService.getById(id);

     return R.ok().put("omsOrderBill", omsOrderBill);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "oms", REMARK = "保存发票表")
    @ApiOperation("保存发票表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('oms:omsorderbill:save')")
    public Object save(@RequestBody OmsOrderBill entity) {
        try {
            if (omsOrderBillService.saves(entity)) {
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
    @SysLog(MODULE = "oms", REMARK = "修改发票表")
    @ApiOperation("修改发票表")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('oms:omsorderbill:update')")
    public Object update(@RequestBody OmsOrderBill entity) {
        try {
            if (omsOrderBillService.updateById(entity)) {
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
    @SysLog(MODULE = "oms", REMARK = "删除发票表")
    @ApiOperation("删除发票表")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('oms:omsorderbill:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (omsOrderBillService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "查询发票表明细")
    @ApiOperation("查询发票表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('oms:omsorderbill:read')")
    public Object getOmsOrderBillById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("发票表id");
            }
            OmsOrderBill object = omsOrderBillService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询发票表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
