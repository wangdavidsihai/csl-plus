package com.csl.plus.res.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.res.entity.ResTechnical;
import com.csl.plus.res.service.IResTechnicalService;
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
@Api(tags = "/api/ResTechnicalController", description = "需求表管理")
@RequestMapping("res/technical")
public class ResTechnicalController {
    @Autowired
    private IResTechnicalService resTechnicalService;

    /**
     * 列表
     */
    @SysLog(MODULE = "res", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('res:restechnical:list')")
    public Object getResTechnicalByPage(ResTechnical entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            IPage<ResTechnical> page = new Page<ResTechnical>(pageNum, pageSize);
            page.setRecords(resTechnicalService.getList());
            return new CommonResult()
                    .success(page);
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
     @PreAuthorize("hasAuthority('res:restechnical:info')") public R info(@PathVariable("id") Long id){
     ResTechnicalEntity resTechnical = resTechnicalService.getById(id);

     return R.ok().put("resTechnical", resTechnical);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "res", REMARK = "保存需求表")
    @ApiOperation("保存需求表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('res:restechnical:save')")
    public Object save(@RequestBody ResTechnical entity) {
        try {
            if (resTechnicalService.saves(entity)) {
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
    @PreAuthorize("hasAuthority('res:restechnical:update')")
    public Object update(@RequestBody ResTechnical entity) {
        try {
            if (resTechnicalService.updateById(entity)) {
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
    @PreAuthorize("hasAuthority('res:restechnical:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (resTechnicalService.removeById(id)) {
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
    @PreAuthorize("hasAuthority('res:restechnical:read')")
    public Object getResTechnicalById(@ApiParam("新闻表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("需求表id");
            }
            ResTechnical object = resTechnicalService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询需求表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
