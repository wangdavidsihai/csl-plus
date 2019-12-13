package com.csl.plus.portal.single;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.csl.plus.marking.entity.SmsHomeAdvertise;
import com.csl.plus.oms.vo.HomeContentResult;
import com.csl.plus.portal.annotation.IgnoreAuth;
import com.csl.plus.portal.annotation.SysLog;
import com.csl.plus.portal.constant.RedisKey;
import com.csl.plus.portal.marking.service.ISmsHomeAdvertiseService;
import com.csl.plus.portal.oms.service.IOmsOrderService;
import com.csl.plus.portal.ums.service.IUmsMemberService;
import com.csl.plus.portal.ums.service.RedisService;
import com.csl.plus.portal.util.JsonUtil;
import com.csl.plus.ums.entity.UmsMember;
import com.csl.plus.utils.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 首页内容管理Controller
 * https://github.com/shenzhuan/mallplus on 2019/1/28.
 */
@RestController
@Api(tags = "HomeController", description = "首页内容管理")
@RequestMapping("/api/single/home")
public class SingelHomeController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private RedisService redisService;
    @Autowired
    private IUmsMemberService memberService;
    @Autowired
    private ISmsHomeAdvertiseService advertiseService;
    @Autowired
    private IOmsOrderService orderService;
    @IgnoreAuth
    @ApiOperation("首页内容页信息展示")
    @SysLog(MODULE = "home", REMARK = "首页内容页信息展示")
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    public Object content() {
        HomeContentResult contentResult = advertiseService.singelContent();
        return new CommonResult().success(contentResult);
    }


    /**
     * banner
     *
     * @return
     */
    @IgnoreAuth
    @SysLog(MODULE = "home", REMARK = "bannerList")
    @GetMapping("/bannerList")
    public Object bannerList(@RequestParam(value = "type", required = false, defaultValue = "10") Integer type) {
        List<SmsHomeAdvertise> bannerList = null;
        String bannerJson = redisService.get(RedisKey.appletBannerKey+type);
        if(bannerJson!=null && bannerJson!="[]"){
            bannerList = JsonUtil.jsonToList(bannerJson,SmsHomeAdvertise.class);
        }else {
            SmsHomeAdvertise advertise = new SmsHomeAdvertise();
            advertise.setType(type);
            bannerList = advertiseService.list(new QueryWrapper<>(advertise));
            redisService.set(RedisKey.appletBannerKey+type,JsonUtil.objectToJson(bannerList));
            redisService.expire(RedisKey.appletBannerKey+type,24*60*60);
        }
      //  List<SmsHomeAdvertise> bannerList = advertiseService.list(null, type, null, 5, 1);
        return new CommonResult().success(bannerList);
    }




    @IgnoreAuth
    @ApiOperation(value = "登录以后返回token")
    @GetMapping(value = "/login")
    @ResponseBody
    public Object login(UmsMember umsMember) {
        if (umsMember==null){
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
    @ApiOperation("注册")
    @RequestMapping(value = "/reg")
    @ResponseBody
    public Object register(UmsMember umsMember) {
        if (umsMember==null){
            return new CommonResult().validateFailed("用户名或密码错误");
        }
        return memberService.register(umsMember);
    }
    @IgnoreAuth
    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public Object getAuthCode(@RequestParam String telephone) {
        return memberService.generateAuthCode(telephone);
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public Object updatePassword(@RequestParam String telephone,
                                 @RequestParam String password,
                                 @RequestParam String authCode) {
        return memberService.updatePassword(telephone, password, authCode);
    }
}
