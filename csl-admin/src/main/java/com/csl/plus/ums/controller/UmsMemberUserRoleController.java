package com.csl.plus.ums.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.ums.entity.UmsMemberUserRole;
import com.csl.plus.ums.service.IUmsMemberUserRoleService;
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
 * 后台用户和角色关系表
 *
 * @author David
 * @email
 * @date 2020-02-17 17:03:22
 */
@Slf4j
@RestController
@Api(tags = "/api/UmsMemberUserRoleController", description = "后台用户和角色关系表管理")
@RequestMapping("ums/umsmemberuserrole")
public class UmsMemberUserRoleController {
    @Autowired
    private IUmsMemberUserRoleService umsMemberUserRoleService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ums:umsmemberuserrole:list')")
    public Object getUmsMemberUserRoleByPage(UmsMemberUserRole entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(umsMemberUserRoleService.page(new Page<UmsMemberUserRole>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有后台用户和角色关系表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "cms", REMARK = "根据条件查询后台用户和角色关系表列表")
     @ApiOperation("根据条件查询后台用户和角色关系表列表")
     @GetMapping("/info/{id}")
     @PreAuthorize("hasAuthority('ums:umsmemberuserrole:info')") public R info(@PathVariable("id") Long id){
     UmsMemberUserRoleEntity umsMemberUserRole = umsMemberUserRoleService.getById(id);

     return R.ok().put("umsMemberUserRole", umsMemberUserRole);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存后台用户和角色关系表")
    @ApiOperation("保存后台用户和角色关系表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ums:umsmemberuserrole:save')")
    public Object save(@RequestBody UmsMemberUserRole entity) {
        try {
            if (umsMemberUserRoleService.saves(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "修改后台用户和角色关系表")
    @ApiOperation("修改后台用户和角色关系表")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ums:umsmemberuserrole:update')")
    public Object update(@RequestBody UmsMemberUserRole entity) {
        try {
            if (umsMemberUserRoleService.updateById(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "删除后台用户和角色关系表")
    @ApiOperation("删除后台用户和角色关系表")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('ums:umsmemberuserrole:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (umsMemberUserRoleService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "查询后台用户和角色关系表明细")
    @ApiOperation("查询后台用户和角色关系表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ums:umsmemberuserrole:read')")
    public Object getUmsMemberUserRoleById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("后台用户和角色关系表id");
            }
            UmsMemberUserRole object = umsMemberUserRoleService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询后台用户和角色关系表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
