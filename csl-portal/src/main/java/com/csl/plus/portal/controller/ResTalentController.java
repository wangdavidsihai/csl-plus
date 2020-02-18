package com.csl.plus.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.portal.res.service.IResTalentService;
import com.csl.plus.res.entity.ResTalent;
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
 * 专家表
 *
 * @author David
 * @email
 * @date 2020-02-18 21:43:07
 */
@Slf4j
@RestController
@Api(tags = "/api/ResTalentController", description = "人才表管理")
@RequestMapping("/api/res/restalent")
public class ResTalentController {
    @Autowired
    private IResTalentService resTalentService;

    /**
     * 列表
     */
    @SysLog(MODULE = "res", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('res:restalent:list')")
    public Object getResTalentByPage(ResTalent entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(resTalentService.page(new Page<ResTalent>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有人才列表：%s", e.getMessage(), e);
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
     @PreAuthorize("hasAuthority('res:restalent:info')") public R info(@PathVariable("id") Long id){
     ResTalentEntity resTalent = resTalentService.getById(id);

     return R.ok().put("resTalent", resTalent);
     }
     */
    /**
     * 保存
     */
//    @SysLog(MODULE = "res", REMARK = "保存人才表")
//    @ApiOperation("保存人才表")
//    @PostMapping("/save")
//    @PreAuthorize("hasAuthority('res:restalent:save')")
//    public Object save(@RequestBody ResTalent entity) {
//        try {
//            if (resTalentService.saves(entity)) {
//                return new CommonResult().success();
//            }
//        } catch (Exception e) {
//            log.error("保存人才表：%s", e.getMessage(), e);
//            return new CommonResult().failed();
//        }
//        return new CommonResult().failed();
//    }

    /**
     * 修改
     */
//    @SysLog(MODULE = "res", REMARK = "修改人才表")
//    @ApiOperation("修改人才表")
//    @PostMapping("/update")
//    @PreAuthorize("hasAuthority('res:restalent:update')")
//    public Object update(@RequestBody ResTalent entity) {
//        try {
//            if (resTalentService.updateById(entity)) {
//                return new CommonResult().success();
//            }
//        } catch (Exception e) {
//            log.error("更新人才表：%s", e.getMessage(), e);
//            return new CommonResult().failed();
//        }
//        return new CommonResult().failed();
//    }

    /**
     * 删除
     */
//    @SysLog(MODULE = "res", REMARK = "删除人才表")
//    @ApiOperation("删除人才表")
//    @DeleteMapping("/delete")
//    @PreAuthorize("hasAuthority('res:restalent:delete')")
//    public Object delete(@ApiParam("id") @PathVariable Long id) {
//        try {
//            if (ValidatorUtils.empty(id)) {
//                return new CommonResult().paramFailed("人才表id");
//            }
//            if (resTalentService.removeById(id)) {
//                return new CommonResult().success();
//            }
//        } catch (Exception e) {
//            log.error("删除人才表：%s", e.getMessage(), e);
//            return new CommonResult().failed();
//        }
//        return new CommonResult().failed();
//    }
    @SysLog(MODULE = "res", REMARK = "查询专人才明细")
    @ApiOperation("查询专人才明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('res:restalent:read')")
    public Object getResTalentById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("专家表id");
            }
            ResTalent object = resTalentService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询人才表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
