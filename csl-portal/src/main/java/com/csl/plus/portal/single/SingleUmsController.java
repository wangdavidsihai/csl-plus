package com.csl.plus.portal.single;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.csl.plus.portal.annotation.IgnoreAuth;
import com.csl.plus.portal.annotation.SysLog;
import com.csl.plus.portal.cms.service.ISysAreaService;
import com.csl.plus.portal.cms.service.ISysSchoolService;
import com.csl.plus.portal.ums.service.IUmsMemberMemberTagRelationService;
import com.csl.plus.portal.ums.service.IUmsMemberService;
import com.csl.plus.portal.util.UserUtils;
import com.csl.plus.ums.entity.UmsMember;
import com.csl.plus.ums.entity.UmsMemberMemberTagRelation;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Date: 2019/4/2 15:02
 * @Description:
 */
@RestController
@Api(tags = "SingleUmsController", description = "通用会员管理")
@RequestMapping("/api/single/user")
public class SingleUmsController extends ApiBaseAction {

    @Resource
    private ISysSchoolService schoolService;
    @Resource
    private IUmsMemberService memberService;
    @Resource
    private ISysAreaService areaService;
    @Resource
    private IUmsMemberMemberTagRelationService memberTagService;

    @ApiOperation(value = "会员绑定区域")
    @PostMapping(value = "/bindArea")
    @SysLog(MODULE = "ums", REMARK = "会员绑定区域")
    public Object bindArea(@RequestParam(value = "areaIds", required = true) String areaIds) {
        try {
            if (ValidatorUtils.empty(areaIds)) {
                return new CommonResult().failed("请选择区域");
            }
            UmsMember member = UserUtils.getCurrentMember();
            String[] areIdList = areaIds.split(",");
            List<UmsMemberMemberTagRelation> list = new ArrayList<>();
            for (String id : areIdList) {
                UmsMemberMemberTagRelation tag = new UmsMemberMemberTagRelation();
                tag.setMemberId(member.getId());
                tag.setTagId(Long.valueOf(id));
                list.add(tag);
            }
            if (list != null && list.size() > 0) {
                memberTagService.saveBatch(list);
            }
            return new CommonResult().success("绑定区域成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult().failed("绑定区域失败");
        }
    }

    @IgnoreAuth
    @ApiOperation(value = "注册")
    @PostMapping(value = "/reg")
    public Object register(@RequestBody UmsMember umsMember) {
        if (umsMember == null) {
            return new CommonResult().validateFailed("用户名或密码错误");
        }
        return memberService.register(umsMember);
    }

    @IgnoreAuth
    @ApiOperation(value = "登录以后返回token")
    @PostMapping(value = "/login")
    public Object login(@RequestBody UmsMember umsMember) {
        if (umsMember == null || StringUtils.isEmpty(umsMember.getUsername())
                || StringUtils.isEmpty(umsMember.getPassword())) {
            return new CommonResult().validateFailed("用户名或密码错误");
        }
        try {
            Map<String, Object> token = memberService.login(umsMember.getUsername(), umsMember.getPassword());
            if (token.get("token") == null) {
                return new CommonResult().validateFailed("用户名或密码错误");
            }
            return new CommonResult().success(token);
        } catch (AuthenticationException e) {
            return new CommonResult().validateFailed("用户名或密码错误");
        }

    }

    @IgnoreAuth
    @ApiOperation(value = "获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    public Object getAuthCode(@RequestParam String telephone) {
        return memberService.generateAuthCode(telephone);
    }

    @IgnoreAuth
    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Object logout() {

        return new CommonResult().success(null);
    }

    @ApiOperation("激活账户接口")
    @PostMapping("/activate")
    @ResponseBody
    public Object activateUser(@RequestBody UmsMember umsMember) {

        if (umsMember == null) {
            return new CommonResult().validateFailed("激活信息错误");
        }
        return memberService.activate(umsMember);
    }
}
