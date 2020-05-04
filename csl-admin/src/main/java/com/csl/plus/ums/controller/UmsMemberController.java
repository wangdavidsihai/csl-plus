package com.csl.plus.ums.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.ums.entity.UmsMember;
import com.csl.plus.ums.service.IUmsMemberService;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "UmsMemberController", description = "会员表管理")
@RequestMapping("/ums/UmsMember")
public class UmsMemberController {
    @Resource
    private IUmsMemberService umsMemberService;

    @SysLog(MODULE = "ums", REMARK = "根据条件查询所有会员表列表")
    @ApiOperation("根据条件查询所有会员表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('ums:UmsMember:read')")
    public Object getUmsMemberByPage(UmsMember entity,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        try {
            return new CommonResult().success(umsMemberService.page(new Page<UmsMember>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有会员表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "保存会员表")
    @ApiOperation("保存会员表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('ums:UmsMember:create')")
    public Object saveUmsMember(@RequestBody UmsMember entity) {
        try {
            if (umsMemberService.saves(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存会员表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "更新会员表")
    @ApiOperation("更新会员表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMember:update')")
    public Object updateUmsMember(@RequestBody UmsMember entity) {
        try {
            if (umsMemberService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新会员表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "删除会员表")
    @ApiOperation("删除会员表")
    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMember:delete')")
    public Object deleteUmsMember(@ApiParam("会员表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("会员表id");
            }
            if (umsMemberService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除会员表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "给会员表分配会员表")
    @ApiOperation("查询会员表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMember:read')")
    public Object getUmsMemberById(@ApiParam("会员表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("会员表id");
            }
            UmsMember coupon = umsMemberService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询会员表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }

    @ApiOperation(value = "批量删除会员表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除会员表")
    @PreAuthorize("hasAuthority('ums:UmsMember:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = umsMemberService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    @SysLog(MODULE = "ums", REMARK = "审核会员")
    @ApiOperation("更新会员表")
    @PostMapping(value = "/review/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMember:update')")
    public Object reviewUmsMember(@RequestBody UmsMember entity) {
        try {
            if (umsMemberService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新会员表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    /**
     * 修改
     */
    @SysLog(MODULE = "ums", REMARK = "修改后台用户角色状态")
    @ApiOperation("修改后台用户角色状态")
    @PostMapping("/update/showStatus")
    @PreAuthorize("hasAuthority('ums:UmsMember:update')")
    public Object updateShowStatus(@RequestParam("id") Long id, @RequestParam("status") Integer status) {
        try {
            UmsMember entity = new UmsMember();
            entity.setId(id);
            entity.setStatus(status);
            if (umsMemberService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新后台用户角色：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }
}
