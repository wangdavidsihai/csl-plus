package com.csl.plus.portal.util;


import com.csl.plus.portal.vo.UmsMemberUserDetails;
import com.csl.plus.ums.entity.UmsMember;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Date: 2019/4/5 19:48
 * @Description:
 */
public class UserUtils {
    public static UmsMember getCurrentMember() {
        try {
            SecurityContext ctx = SecurityContextHolder.getContext();
            Authentication auth = ctx.getAuthentication();
            UmsMemberUserDetails memberDetails = (UmsMemberUserDetails) auth.getPrincipal();
            return memberDetails.getUmsMember();
        } catch (Exception e) {
            return new UmsMember();
        }
    }
}
