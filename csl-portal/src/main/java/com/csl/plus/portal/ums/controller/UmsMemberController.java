package com.csl.plus.portal.ums.controller;

import com.csl.plus.portal.annotation.IgnoreAuth;
import com.csl.plus.portal.single.ApiBaseAction;
import com.csl.plus.portal.ums.service.IUmsMemberPermissionService;
import com.csl.plus.portal.ums.service.IUmsMemberRoleService;
import com.csl.plus.portal.ums.service.IUmsMemberService;
import com.csl.plus.portal.vo.UmsMemberVO;
import com.csl.plus.ums.entity.UmsMember;
import com.csl.plus.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * 会员管理Controller
 */
@Controller
@Api(tags = "UmsMemberController", description = "会员自助管理")
@RequestMapping("/api/member")
public class UmsMemberController extends ApiBaseAction {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Resource
    private IUmsMemberService memberService;
    @Resource
    private IUmsMemberRoleService umsMemberRoleService;
    @Resource
    private IUmsMemberPermissionService umsMemberPermissionService;


    @ApiOperation(value = "修改密码")
    @PostMapping(value = "/updatepassword")
    @ResponseBody
    @PreAuthorize("hasAuthority('ums:umsmember:updatepass')")
    public Object updatePassword(@RequestBody UmsMemberVO umsMemberVO) {
        if (!umsMemberVO.getPassword().equals(umsMemberVO.getConfirmpassword())) {
            return new CommonResult().failed("密码不匹配");
        }
        if (StringUtils.isEmpty(umsMemberVO.getPhone())) {
            return new CommonResult().failed("手机号码不能为空");
        }
        return memberService.updatePassword(umsMemberVO.getPhone(), umsMemberVO.getPassword(), umsMemberVO.getAuthCode());
    }

    @IgnoreAuth
    @ApiOperation("会员详细信息接口")
    @GetMapping("/info")
    @ResponseBody
    @PreAuthorize("hasAuthority('ums:umsmember:info')")
    public Object info(Principal principal) {
        Map<String, Object> data = new HashMap<>();
        UmsMember umsMember = memberService.getCurrentMember();
        data.put("umsMember", umsMember);
        data.put("permissionList", umsMemberPermissionService.getMemberPermListByUserId(umsMember.getId(), umsMember.getUsername()));
        data.put("roleList", umsMemberRoleService.getRoleListByUserId(umsMember.getId(), umsMember.getUsername()));
        if (umsMember != null && umsMember.getId() != null) {
            return new CommonResult().success(data);
        }
        return new CommonResult().failed();
    }

    @IgnoreAuth
    @ApiOperation("更新会员信息接口")
    @PostMapping("/updateprofile")
    @ResponseBody
    @PreAuthorize("hasAuthority('ums:umsmember:updateprofile')")
    public Object updateProfile(@RequestBody UmsMember entity) {
        if (entity == null) {
            return new CommonResult().failed("用户信息不能为空");
        }
        entity = memberService.updateProfile(entity);
        if (entity != null && entity.getId() != null) {
            return new CommonResult().success(entity);
        }
        return new CommonResult().failed();
    }


    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/token/refresh", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('ums:umsmember:refreshtoken')")
    public Object refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = memberService.refreshToken(token);
        if (refreshToken == null) {
            return new CommonResult().failed();
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return new CommonResult().success(tokenMap);
    }


    /**
     * 提交小程序推送formid
     *
     * @param request
     * @param response
     * @param formId   小程序推送formId
     * @param source   @see
     *                 com.fittime.health.market.model.PushUserFormIdRecord.source
     * @return
    //     */
//    @ApiIgnore
//    @PostMapping(value = "submitFormId")
//    @ApiOperation(value = "提交小程序推送formid")
//    @ResponseBody
//    @PreAuthorize("hasAuthority('ums:umsmember:updateprofile')")
//    public Object submitFormId(HttpServletRequest request, HttpServletResponse response, String formId,
//                               Integer source) {
//
//        UserFormId entity = new UserFormId();
//
//        if (ValidatorUtils.empty(formId)) {
//            return new CommonResult().validateFailed("前置参数错误，formId不能为空");
//        }
//
//        if (ValidatorUtils.empty(source)) {
//            return new CommonResult().validateFailed("前置参数错误，source不能为空");
//        }
//
//        // 校验formId是否已经存在
//        /*
//         * if(memberService.exists(formId)) { return new
//         * CommonResult().validateFailed("前置参数错误，formId已经存在 formId：" + formId); }
//         *
//         * entity.setUserId(this.getCurrentMember().getId()); entity.setFormId(formId);
//         * entity.setSource(source); entity.setStatus(1);
//         *
//         * memberService.insert(entity);
//         */
//
//        return new CommonResult().success("添加成功");
//    }
}
