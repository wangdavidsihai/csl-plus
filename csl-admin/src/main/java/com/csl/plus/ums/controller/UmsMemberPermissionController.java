package com.csl.plus.ums.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.ums.entity.UmsMemberPermission;
import com.csl.plus.ums.entity.UmsMemberPermissionNode;
import com.csl.plus.ums.service.IUmsMemberPermissionService;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 后台用户权限表
 *
 * @author David
 * @email
 * @date 2020-02-16 21:48:43
 */
@ApiIgnore
@Slf4j
@RestController
@Api(tags = "/api/UmsMemberPermissionController", description = "后台用户权限表管理")
@RequestMapping("/ums/UmsMemberPermission")
public class UmsMemberPermissionController {
    @Autowired
    private IUmsMemberPermissionService umsMemberPermissionService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ums:umsmemberpermission:list')")
    public Object getUmsMemberPermissionByPage(UmsMemberPermission entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(umsMemberPermissionService.page(new Page<UmsMemberPermission>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有后台用户权限表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "cms", REMARK = "根据条件查询后台用户权限表列表")
     @ApiOperation("根据条件查询后台用户权限表列表")
     @GetMapping("/info/{id}")
     @PreAuthorize("hasAuthority('ums:umsmemberpermission:info')") public R info(@PathVariable("id") Long id){
     UmsMemberPermissionEntity umsMemberPermission = umsMemberPermissionService.getById(id);

     return R.ok().put("umsMemberPermission", umsMemberPermission);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存后台用户权限表")
    @ApiOperation("保存后台用户权限表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ums:umsmemberpermission:save')")
    public Object save(@RequestBody UmsMemberPermission entity) {
        try {
            if (umsMemberPermissionService.saves(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "修改后台用户权限表")
    @ApiOperation("修改后台用户权限表")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ums:umsmemberpermission:update')")
    public Object update(@RequestBody UmsMemberPermission entity) {
        try {
            if (umsMemberPermissionService.updateById(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "删除后台用户权限表")
    @ApiOperation("删除后台用户权限表")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('ums:umsmemberpermission:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (umsMemberPermissionService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "查询后台用户权限表明细")
    @ApiOperation("查询后台用户权限表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ums:umsmemberpermission:read')")
    public Object getUmsMemberPermissionById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("后台用户权限表id");
            }
            UmsMemberPermission object = umsMemberPermissionService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询后台用户权限表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }

    @SysLog(MODULE = "sys", REMARK = "以层级结构返回所有权限")
    @ApiOperation("以层级结构返回所有权限")
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    @ResponseBody
    public Object treeList() {
        List<UmsMemberPermissionNode> permissionNodeList = umsMemberPermissionService.treeList();
        return new CommonResult().success(permissionNodeList);
    }
}
