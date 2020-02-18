package com.csl.plus.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.inbox.entity.MtMessageText;
import com.csl.plus.portal.inbox.service.IMtMessageTextService;
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
 * 消息内容表
 *
 * @author David
 * @email
 * @date 2020-01-31 17:46:11
 */
@Slf4j
@RestController
@Api(tags = "/api/MtMessageTextController", description = "消息内容表管理")
@RequestMapping("/api/inbox/mtmessagetext")
public class MtMessageTextController {
    @Autowired
    private IMtMessageTextService mtMessageTextService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('rms:mtmessagetext:list')")
    public Object getMtMessageTextByPage(MtMessageText entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(mtMessageTextService.page(new Page<MtMessageText>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有消息内容表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "cms", REMARK = "根据条件查询消息内容表列表")
     @ApiOperation("根据条件查询消息内容表列表")
     @RequestMapping("/info/{id}")
     @PreAuthorize("hasAuthority('rms:mtmessagetext:info')") public R info(@PathVariable("id") String id){
     MtMessageTextEntity mtMessageText = mtMessageTextService.getById(id);

     return R.ok().put("mtMessageText", mtMessageText);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存消息内容表")
    @ApiOperation("保存消息内容表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('inbox:mtmessagetext:save')")
    public Object save(@RequestBody MtMessageText entity) {
        try {
            if (mtMessageTextService.saves(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "修改消息内容表")
    @ApiOperation("修改消息内容表")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('inbox:mtmessagetext:update')")
    public Object update(@RequestBody MtMessageText entity) {
        try {
            if (mtMessageTextService.updateById(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "删除消息内容表")
    @ApiOperation("删除消息内容表")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('inbox:mtmessagetext:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (mtMessageTextService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "查询消息内容表明细")
    @ApiOperation("查询消息内容表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('inbox:mtmessagetext:read')")
    public Object getMtMessageTextById(@ApiParam("新闻表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("消息内容表id");
            }
            MtMessageText object = mtMessageTextService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询消息内容表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
