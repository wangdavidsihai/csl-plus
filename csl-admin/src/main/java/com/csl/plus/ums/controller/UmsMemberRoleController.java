package com.csl.plus.ums.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.ums.entity.UmsMemberRole;
import com.csl.plus.ums.entity.UmsMemberRolePermission;
import com.csl.plus.ums.service.IUmsMemberRoleService;
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
 * 后台用户角色表
 *
 * @author David
 * @email
 * @date 2020-02-16 21:48:43
 */
@ApiIgnore
@Slf4j
@RestController
@Api(tags = "/api/UmsMemberRoleController", description = "后台用户角色表管理")
@RequestMapping("/ums/UmsMemberRole")
public class UmsMemberRoleController {
    @Autowired
    private IUmsMemberRoleService umsMemberRoleService;

    /**
     * 列表
     */
    @SysLog(MODULE = "ums", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ums:umsmemberrole:list')")
    public Object getUmsMemberRoleByPage(UmsMemberRole entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(umsMemberRoleService.page(new Page<UmsMemberRole>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有后台用户角色表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "ums", REMARK = "根据条件查询后台用户角色表列表")
     @ApiOperation("根据条件查询后台用户角色表列表")
     @GetMapping("/info/{id}")
     @PreAuthorize("hasAuthority('ums:umsmemberrole:info')") public R info(@PathVariable("id") Long id){
     UmsMemberRoleEntity umsMemberRole = umsMemberRoleService.getById(id);

     return R.ok().put("umsMemberRole", umsMemberRole);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "ums", REMARK = "保存后台用户角色表")
    @ApiOperation("保存后台用户角色表")
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ums:umsmemberrole:save')")
    public Object save(@RequestBody UmsMemberRole entity) {
        try {
            if (umsMemberRoleService.saves(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存后台用户角色：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    /**
     * 修改
     */
    @SysLog(MODULE = "ums", REMARK = "修改后台用户角色表")
    @ApiOperation("修改后台用户角色表")
    @PostMapping("/update/{roleId}")
    @PreAuthorize("hasAuthority('ums:umsmemberrole:update')")
    public Object update(@RequestBody UmsMemberRole entity, @PathVariable Long roleId) {
        try {
            if (entity != null) {
                entity.setId(roleId);
                if (umsMemberRoleService.updatesRole(entity)) {
                    return new CommonResult().success();
                }
            }
        } catch (Exception e) {
            log.error("更新后台用户角色：%s", e.getMessage(), e);
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
    @PreAuthorize("hasAuthority('ums:umsmemberrole:update')")
    public Object updateShowStatus(@RequestParam("id") Long id, @RequestParam("status") Integer status) {
        try {
            UmsMemberRole entity = new UmsMemberRole();
            entity.setId(id);
            entity.setStatus(status);
            if (umsMemberRoleService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新后台用户角色：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    /**
     * 删除
     */
    @SysLog(MODULE = "ums", REMARK = "删除后台用户角色表")
    @ApiOperation("删除后台用户角色表")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ums:umsmemberrole:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("后台用户角色id");
            }
            if (umsMemberRoleService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除后台用户角色：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "查询后台用户角色表明细")
    @ApiOperation("查询后台用户角色表明细")
    @GetMapping(value = "/{roleId}")
    @PreAuthorize("hasAuthority('ums:umsmemberrole:read')")
    public Object getUmsMemberRoleById(@ApiParam("roleId") @PathVariable Long roleId) {
        try {
            if (ValidatorUtils.empty(roleId)) {
                return new CommonResult().paramFailed("后台用户角色表id");
            }
            UmsMemberRole object = umsMemberRoleService.getById(roleId);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询后台用户角色表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }

    @SysLog(MODULE = "ums", REMARK = "获取相应角色权限-单表")
    @ApiOperation("获取相应角色权限-单表")
    @RequestMapping(value = "/permission/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('ums:umsmemberrole:read')")
    public Object rolePermission(@PathVariable Long roleId) {
        List<UmsMemberRolePermission> rolePermission = umsMemberRoleService.getPermissionList(roleId);
        return new CommonResult().success(rolePermission);
    }
}
