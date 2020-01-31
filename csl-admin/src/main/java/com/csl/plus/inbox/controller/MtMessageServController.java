package com.csl.plus.inbox.controller;

import com.csl.plus.annotation.SysLog;
import com.csl.plus.inbox.entity.MtMessageServ;
import com.csl.plus.inbox.service.IMtMessageServService;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

/**
 * 服务端站内信
 *
 * @author David
 * @email 
 * @date 2020-01-31 17:46:11
 */
@Slf4j
@RestController
@Api(tags = "/api/MtMessageServController", description = "服务端站内信管理")
@RequestMapping("rms/mtmessageserv")
public class MtMessageServController {
    @Autowired
    private IMtMessageServService mtMessageServService;

    /**
     * 列表
     */
    @SysLog(MODULE = "cms", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('rms:mtmessageserv:list')")
    public Object getMtMessageServByPage(MtMessageServ entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
										 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        try {
			return new CommonResult()
					.success(mtMessageServService.page(new Page<MtMessageServ>(pageNum, pageSize), new QueryWrapper<>(entity)));
		} catch (Exception e) {
			log.error("根据条件查询所有服务端站内信列表：%s", e.getMessage(), e);
		}
		return new CommonResult().failed();
    }


    /**
     * 信息
     */
     /**
    @SysLog(MODULE = "cms", REMARK = "根据条件查询服务端站内信列表")
    @ApiOperation("根据条件查询服务端站内信列表") 
    @RequestMapping("/info/{id}")
    @PreAuthorize("hasAuthority('rms:mtmessageserv:info')")
    public R info(@PathVariable("id") String id){
		MtMessageServEntity mtMessageServ = mtMessageServService.getById(id);

        return R.ok().put("mtMessageServ", mtMessageServ);
    }
	*/
    /**
     * 保存
     */
    @SysLog(MODULE = "cms", REMARK = "保存服务端站内信")
    @ApiOperation("保存服务端站内信")
    @RequestMapping("/save")
    @PreAuthorize("hasAuthority('rms:mtmessageserv:save')")
    public Object save(@RequestBody MtMessageServ entity){
		try {
			if (mtMessageServService.saves(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "修改服务端站内信")
    @ApiOperation("修改服务端站内信")
    @RequestMapping("/update")
    @PreAuthorize("hasAuthority('rms:mtmessageserv:update')")
    public Object update(@RequestBody MtMessageServ entity){
		try {
			if (mtMessageServService.updateById(entity)) {
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
    @SysLog(MODULE = "cms", REMARK = "删除服务端站内信")
    @ApiOperation("删除服务端站内信")
    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('rms:mtmessageserv:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id){
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("帮助表id");
			}
			if (mtMessageServService.removeById(id)) {
				return new CommonResult().success();
			}
		} catch (Exception e) {
			log.error("删除帮助表：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
		return new CommonResult().failed();
    }

	@SysLog(MODULE = "cms", REMARK = "查询服务端站内信明细")
	@ApiOperation("查询服务端站内信明细")
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('cms:cmsarticle:read')")
	public Object getMtMessageServById(@ApiParam("新闻表id") @PathVariable Long id) {
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("服务端站内信id");
			}
			MtMessageServ object = mtMessageServService.getById(id);
			return new CommonResult().success(object);
		} catch (Exception e) {
			log.error("查询服务端站内信明细：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
	}
}
