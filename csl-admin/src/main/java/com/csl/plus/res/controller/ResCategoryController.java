package com.csl.plus.res.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csl.plus.annotation.SysLog;
import com.csl.plus.res.entity.ResCategory;
import com.csl.plus.res.service.IResCategoryService;
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
 * 资源类别表
 *
 * @author David
 * @email
 * @date 2020-03-20 21:16:19
 */
@Slf4j
@RestController
@Api(tags = "/api/ResCategoryController", description = "资源类别表管理")
@RequestMapping("res/rescategory")
public class ResCategoryController {
    @Autowired
    private IResCategoryService resCategoryService;

    /**
     * 列表
     */
    @SysLog(MODULE = "res", REMARK = "根据条件查询列表")
    @ApiOperation("根据条件查询列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('res:rescategory:list')")
    public Object getResCategoryByPage(ResCategory entity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        try {
            return new CommonResult()
                    .success(resCategoryService.page(new Page<ResCategory>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有资源类别表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }


    /**
     * 信息
     */
    /**
     @SysLog(MODULE = "res", REMARK = "根据条件查询资源类别表列表")
     @ApiOperation("根据条件查询资源类别表列表")
     @GetMapping("/info/{id}")
     @PreAuthorize("hasAuthority('res:rescategory:info')") public R info(@PathVariable("id") Long id){
     ResCategoryEntity resCategory = resCategoryService.getById(id);

     return R.ok().put("resCategory", resCategory);
     }
     */
    /**
     * 保存
     */
    @SysLog(MODULE = "res", REMARK = "保存资源类别表")
    @ApiOperation("保存资源类别表")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('res:rescategory:save')")
    public Object save(@RequestBody ResCategory entity) {
        try {
            if (resCategoryService.saves(entity)) {
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
    @SysLog(MODULE = "res", REMARK = "修改资源类别表")
    @ApiOperation("修改资源类别表")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('res:rescategory:update')")
    public Object update(@RequestBody ResCategory entity) {
        try {
            if (resCategoryService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }


    @ApiOperation("修改显示状态")
    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('res:rescategory:update')")
    public Object updateShowStatus(@RequestParam("id") Long id, @RequestParam("showStatus") Integer showStatus) {
        int count = resCategoryService.updateShowStatus(id, showStatus);
        if (count > 0) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    /**
     * 删除
     */
    @SysLog(MODULE = "res", REMARK = "删除资源类别表")
    @ApiOperation("删除资源类别表")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('res:rescategory:delete')")
    public Object delete(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("帮助表id");
            }
            if (resCategoryService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "res", REMARK = "查询资源类别表明细")
    @ApiOperation("查询资源类别表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('res:rescategory:read')")
    public Object getResCategoryById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("资源类别表id");
            }
            ResCategory object = resCategoryService.getById(id);
            return new CommonResult().success(object);
        } catch (Exception e) {
            log.error("查询资源类别表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }
}
