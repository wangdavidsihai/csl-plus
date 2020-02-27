package com.csl.plus.portal.vo;

import com.csl.plus.common.utils.CommonCodes;
import com.csl.plus.ums.entity.UmsMember;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

/**
 * 会员详情封装
 */
public class MemberDetails implements UserDetails {
    private UmsMember umsMember;
    private String role;

    public MemberDetails(UmsMember umsMember, String role) {
        this.umsMember = umsMember;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 返回当前用户的权限
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return umsMember.getPassword();
    }

    @Override
    public String getUsername() {
        return umsMember.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return umsMember.getStatus().equals(CommonCodes.STATUS_ACTIVE);
    }

    public UmsMember getUmsMember() {
        return umsMember;
    }
}
