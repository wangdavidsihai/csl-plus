package com.csl.plus.portal.controller;

import com.csl.plus.marking.entity.UserFormId;
import com.csl.plus.portal.annotation.IgnoreAuth;
import com.csl.plus.portal.single.ApiBaseAction;
import com.csl.plus.portal.ums.service.IUmsMemberService;
import com.csl.plus.ums.entity.UmsMember;
import com.csl.plus.utils.CommonResult;
import com.csl.plus.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 会员登录注册管理Controller
 */
@Controller
@Api(tags = "UmsMemberController", description = "会员自助管理")
@RequestMapping("/api/member")
public class UmsMemberController extends ApiBaseAction {
    @Autowired
    private IUmsMemberService memberService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public Object updatePassword(@RequestParam String telephone, @RequestParam String password,
                                 @RequestParam String authCode) {
        return memberService.updatePassword(telephone, password, authCode);
    }

    @IgnoreAuth
    @ApiOperation("会员详细信息接口")
    @GetMapping("/info")
    @ResponseBody
    public Object user() {
        UmsMember umsMember = memberService.getCurrentMember();
        if (umsMember != null && umsMember.getId() != null) {
            return new CommonResult().success(umsMember);
        }
        return new CommonResult().failed();
    }


    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/token/refresh", method = RequestMethod.GET)
    @ResponseBody
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
     */
    @ApiIgnore
    @PostMapping(value = "submitFormId")
    @ApiOperation(value = "提交小程序推送formid")
    @ResponseBody
    public Object submitFormId(HttpServletRequest request, HttpServletResponse response, String formId,
                               Integer source) {

        UserFormId entity = new UserFormId();

        if (ValidatorUtils.empty(formId)) {
            return new CommonResult().validateFailed("前置参数错误，formId不能为空");
        }

        if (ValidatorUtils.empty(source)) {
            return new CommonResult().validateFailed("前置参数错误，source不能为空");
        }

        // 校验formId是否已经存在
        /*
         * if(memberService.exists(formId)) { return new
         * CommonResult().validateFailed("前置参数错误，formId已经存在 formId：" + formId); }
         *
         * entity.setUserId(this.getCurrentMember().getId()); entity.setFormId(formId);
         * entity.setSource(source); entity.setStatus(1);
         *
         * memberService.insert(entity);
         */

        return new CommonResult().success("添加成功");
    }
}
