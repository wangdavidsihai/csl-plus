package com.csl.plus.res.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.audit.entity.ReviewLog;
import com.csl.plus.res.entity.ResProject;
import com.csl.plus.res.service.IResProjectService;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目表
 *
 * @author David
 * @email
 * @date 2020-02-15 22:12:07
 */
@Slf4j
@RestController
@Api(tags = "/api/ResProjProductionController", description = "项目表管理")
@RequestMapping("res/project")
public class ResProjectController {
    @Autowired
    private IResProjectService resProjectService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('res:resprojproduction:list')")
    public Object getResProjProductionByPage(ResProject entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            IPage<ResProject> page = new Page<ResProject>(pageNum, pageSize);
            page.setRecords(resProjectService.getList());
            return new CommonResult()
                    .success(page);
        } catch (Exception e) {
            log.error("根据条件查询所有项目表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "cms", REMARK = "根据条件查询项目表列表")
     @ApiOperation("根据条件查询项目表列表")
     @GetMapping("/info/{id}")
     @PreAuthorize("hasAuthority('res:resprojproduction:info')") public R info(@PathVariable("id") Long id){
     ResProjProductionEntity resProjProduction = resProjProductionService.getById(id);

     return R.ok().put("resProjProduction", resProjProduction);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存项目表")
    @ApiOperation("保存项目表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('res:resprojproduction:save')")
    public Object save(@RequestBody ResProject entity) {
        try {
            if (resProjectService.saves(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "修改项目表")
    @ApiOperation("修改项目表")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('res:resprojproduction:update')")
    public Object update(@RequestBody ResProject entity) {
        try {
            if (resProjectService.updateById(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "删除项目表")
    @ApiOperation("删除项目表")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('res:resprojproduction:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (resProjectService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "查询项目表明细")
    @ApiOperation("查询项目表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('res:resprojproduction:read')")
    public Object getResProjProductionById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("项目表id");
            }
            ResProject object = resProjectService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询项目表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }

    @ApiOperation("批量修改审核状态")
    @PostMapping(value = "/update/verifyStatus")
    @ResponseBody
    @SysLog(MODULE = "res", REMARK = "批量修改审核状态")
    @PreAuthorize("hasAuthority('res:resprojproduction:update')")
    public Object updateVerifyStatus(@RequestParam("ids") Long ids,
                                     @RequestParam("verifyStatus") Integer verifyStatus,
                                     @RequestParam("detail") String detail) {
        int count = resProjectService.updateVerifyStatus(ids, verifyStatus, detail);
        if (count > 0) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }


    @ApiOperation("根据id获取审核信息")
    @GetMapping(value = "/fetchVList/{id}/{sysGroup}")
    @ResponseBody
    @SysLog(MODULE = "res", REMARK = "据id获取审核信息")
    @PreAuthorize("hasAuthority('res:resprojproduction:read')")
    public Object fetchVList(@PathVariable Long id, @PathVariable String sysGroup) {
        List<ReviewLog> list = resProjectService.getVertifyRecord(id, sysGroup);
        return new CommonResult().success(list);
    }
}
