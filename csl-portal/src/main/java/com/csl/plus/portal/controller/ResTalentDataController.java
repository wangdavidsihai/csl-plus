package com.csl.plus.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.portal.res.service.IResTalentDataService;
import com.csl.plus.res.entity.ResTalentData;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 专家表
 *
 * @author David
 * @email
 * @date 2020-02-18 21:43:07
 */
@Slf4j
@RestController
@Api(tags = "/api/ResTalentDataController", description = "人才详细信息管理")
@RequestMapping("/api/res/restalentdata")
public class ResTalentDataController {
    @Autowired
    private IResTalentDataService resTalentDataService;

    /**
     * 列表
     */
    @SysLog(MODULE = "res", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('res:restalentdata:list')")
    public Object getResTalentDataByPage(ResTalentData entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(resTalentDataService.page(new Page<ResTalentData>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有人才数据表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "res", REMARK = "根据条件查询专家表列表")
     @ApiOperation("根据条件查询专家表列表")
     @GetMapping("/info/{id}")
     @PreAuthorize("hasAuthority('res:restalentdata:info')") public R info(@PathVariable("id") Long id){
     ResTalentDataEntity resTalentData = resTalentDataService.getById(id);

     return R.ok().put("resTalentData", resTalentData);
     }
     */
    /**
     * 保存
     */
//    @SysLog(MODULE = "res", REMARK = "保存人才数据表")
//    @ApiOperation("保存人才数据表")
//    @PostMapping("/save")
//    @PreAuthorize("hasAuthority('res:restalentdata:save')")
//    public Object save(@RequestBody ResTalentData entity) {
//        try {
//            if (resTalentDataService.saves(entity)) {
//                return new CommonResult().success();
//            }
//        } catch (Exception e) {
//            log.error("保存人才数据表：%s", e.getMessage(), e);
//            return new CommonResult().failed();
//        }
//        return new CommonResult().failed();
//    }

    /**
     * 修改
     */
//    @SysLog(MODULE = "res", REMARK = "修改人才明细表")
//    @ApiOperation("修改人才明细表")
//    @PostMapping("/update")
//    @PreAuthorize("hasAuthority('res:restalentdata:update')")
//    public Object update(@RequestBody ResTalentData entity) {
//        try {
//            if (resTalentDataService.updateById(entity)) {
//                return new CommonResult().success();
//            }
//        } catch (Exception e) {
//            log.error("更新人才明细表：%s", e.getMessage(), e);
//            return new CommonResult().failed();
//        }
//        return new CommonResult().failed();
//    }

    /**
     * 删除
     */
//    @SysLog(MODULE = "res", REMARK = "删除人才明细表")
//    @ApiOperation("删除人才明细表")
//    @DeleteMapping("/delete")
//    @PreAuthorize("hasAuthority('res:restalentdata:delete')")
//    public Object delete(@ApiParam("id") @PathVariable Long id) {
//        try {
//            if (ValidatorUtils.empty(id)) {
//                return new CommonResult().paramFailed("人才明细表id");
//            }
//            if (resTalentDataService.removeById(id)) {
//                return new CommonResult().success();
//            }
//        } catch (Exception e) {
//            log.error("删除人才明细表：%s", e.getMessage(), e);
//            return new CommonResult().failed();
//        }
//        return new CommonResult().failed();
//    }
    @SysLog(MODULE = "res", REMARK = "查询人才表明细")
    @ApiOperation("查询人才表明细")
    @GetMapping(value = "/{id}")
//    @PreAuthorize("hasAuthority('res:restalentdata:read')")
    public Object getResTalentDataById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("人才明细表id");
            }
            ResTalentData object = resTalentDataService.getTalentDataByTalId(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询人才表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
