package com.csl.plus.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.common.utils.CommonCodes;
import com.csl.plus.inbox.entity.MtMessage;
import com.csl.plus.inbox.entity.MtMessageText;
import com.csl.plus.portal.inbox.service.IMtMessageService;
import com.csl.plus.portal.inbox.service.IMtMessageTextService;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 消息通知表
 *
 * @author David
 * @email
 * @date 2020-01-31 17:46:11
 */
@Slf4j
@RestController
@Api(tags = "/api/MtMessageController", description = "消息通知表管理")
@RequestMapping("/api/inbox/mtmessage")
public class MtMessageController {
    @Resource
    private IMtMessageService mtMessageService;
    @Resource
    private IMtMessageTextService mtMessageTextService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('rms:mtmessage:list')")
    public Object getMtMessageByPage(MtMessage entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(mtMessageService.page(new Page<MtMessage>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有消息通知表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "cms", REMARK = "根据条件查询消息通知表列表")
     @ApiOperation("根据条件查询消息通知表列表")
     @RequestMapping("/info/{id}")
     @PreAuthorize("hasAuthority('rms:mtmessage:info')") public R info(@PathVariable("id") String id){
     MtMessageEntity mtMessage = mtMessageService.getById(id);

     return R.ok().put("mtMessage", mtMessage);
     }
     */
//    /**
//     * 保存
//     */
//    @SysLog(MODULE = "cms", REMARK = "保存消息通知表")
//    @ApiOperation("保存消息通知表")
//    @PostMapping("/save")
//    @PreAuthorize("hasAuthority('inbox:mtmessage:save')")
//    public Object save(@RequestBody MtMessage entity) {
//        try {
//            if (mtMessageService.saves(entity)) {
//                return new CommonResult().success();
//            }
//        } catch (Exception e) {
//            log.error("保存帮助表：%s", e.getMessage(), e);
//            return new CommonResult().failed();
//        }
//        return new CommonResult().failed();
//    }

//    /**
//     * 修改
//     */
//    @SysLog(MODULE = "cms", REMARK = "修改消息通知表")
//    @ApiOperation("修改消息通知表")
//    @PostMapping("/update")
//    @PreAuthorize("hasAuthority('inbox:mtmessage:update')")
//    public Object update(@RequestBody MtMessage entity) {
//        try {
//            if (mtMessageService.updateById(entity)) {
//                return new CommonResult().success();
//            }
//        } catch (Exception e) {
//            log.error("更新帮助表：%s", e.getMessage(), e);
//            return new CommonResult().failed();
//        }
//        return new CommonResult().failed();
//    }

    /**
     * 删除
     */
    @SysLog(MODULE = "cms", REMARK = "删除消息通知表")
    @ApiOperation("删除消息通知表")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('inbox:mtmessage:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (mtMessageService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "查询消息通知表明细")
    @ApiOperation("查询消息通知表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('inbox:mtmessage:read')")
    public Object getMtMessageById(@ApiParam("站内信id") @PathVariable String id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("消息通知表id");
            }
            MtMessage mtMessage = new MtMessage();
            mtMessage.setStatus(CommonCodes.INBOX_READ);
            mtMessage.setId(id);
            mtMessageService.updateById(mtMessage);
            MtMessageText mtMessageText = new MtMessageText();
            mtMessageText.setRefid(id);
            mtMessageText.setStatus(CommonCodes.INBOX_READ);
            mtMessageTextService.update(mtMessageText, new QueryWrapper<>());
            MtMessageText object = mtMessageTextService.getOne(new QueryWrapper<>(mtMessageText));
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询消息通知表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
