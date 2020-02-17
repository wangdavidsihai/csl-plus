package com.csl.plus.portal.vo;

import com.csl.plus.ums.entity.UmsMember;
import com.csl.plus.ums.entity.UmsMemberPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UmsMemberUserDetails implements UserDetails {
    private UmsMember umsMember;
    private List<UmsMemberPermission> permissionList;

    public UmsMemberUserDetails(UmsMember umsMember, List<UmsMemberPermission> permissionList) {
        this.umsMember = umsMember;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return permissionList.stream()
                .filter(permission -> permission.getValue() != null)
                .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
    }

    public UmsMemberUserDetails() {
    }

    @Override
    public String getPassword() {
        return this.umsMember.getPassword();
    }

    @Override
    public String getUsername() {
        return this.umsMember.getUsername();
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
        return true;
    }
}
