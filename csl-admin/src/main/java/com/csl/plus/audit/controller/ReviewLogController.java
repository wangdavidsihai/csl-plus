package com.csl.plus.audit.controller;

import com.csl.plus.annotation.SysLog;
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

import com.csl.plus.audit.entity.ReviewLog;
import com.csl.plus.audit.service.IReviewLogService;
import org.springframework.web.bind.annotation.*;

/**
 * 商品审核记录
 *
 * @author David
 * @email 
 * @date 2020-03-21 20:29:15
 */
@Slf4j
@RestController
@Api(tags = "/api/ReviewLogController", description = "商品审核记录管理")
@RequestMapping("audit/reviewlog")
public class ReviewLogController {
    @Autowired
    private IReviewLogService reviewLogService;

    /**
     * 列表
     */
    @SysLog(MODULE = "audit", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('audit:reviewlog:list')")
    public Object getReviewLogByPage(ReviewLog entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        try {
			return new CommonResult()
					.success(reviewLogService.page(new Page<ReviewLog>(pageNum, pageSize), new QueryWrapper<>(entity)));
		} catch (Exception e) {
			log.error("根据条件查询所有商品审核记录列表：%s", e.getMessage(), e);
		}
		return new CommonResult().failed();
    }


    /**
     * 信息
     */
     /**
    @SysLog(MODULE = "audit", REMARK = "根据条件查询商品审核记录列表")
    @ApiOperation("根据条件查询商品审核记录列表") 
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('audit:reviewlog:info')")
    public R info(@PathVariable("id") Long id){
		ReviewLogEntity reviewLog = reviewLogService.getById(id);

        return R.ok().put("reviewLog", reviewLog);
    }
	*/
    /**
     * 保存
     */
    @SysLog(MODULE = "audit", REMARK = "保存商品审核记录")
    @ApiOperation("保存商品审核记录")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('audit:reviewlog:save')")
    public Object save(@RequestBody ReviewLog entity){
		try {
			if (reviewLogService.saves(entity)) {
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
    @SysLog(MODULE = "audit", REMARK = "修改商品审核记录")
    @ApiOperation("修改商品审核记录")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('audit:reviewlog:update')")
    public Object update(@RequestBody ReviewLog entity){
		try {
			if (reviewLogService.updateById(entity)) {
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
    @SysLog(MODULE = "audit", REMARK = "删除商品审核记录")
    @ApiOperation("删除商品审核记录")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('audit:reviewlog:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id){
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("帮助表id");
			}
			if (reviewLogService.removeById(id)) {
				return new CommonResult().success();
			}
		} catch (Exception e) {
			log.error("删除帮助表：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
		return new CommonResult().failed();
    }

	@SysLog(MODULE = "audit", REMARK = "查询商品审核记录明细")
	@ApiOperation("查询商品审核记录明细")
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('audit:reviewlog:read')")
	public Object getReviewLogById(@ApiParam("id") @PathVariable Long id) {
		try {
			if (ValidatorUtils.empty(id)) {
				return new CommonResult().paramFailed("商品审核记录id");
			}
			ReviewLog object = reviewLogService.getById(id);
			return new CommonResult().success(object);
		} catch (Exception e) {
			log.error("查询商品审核记录明细：%s", e.getMessage(), e);
			return new CommonResult().failed();
		}
	}
}
