package com.csl.plus.ums.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 */
public class UmsMemberPermissionNode extends UmsMemberPermission {
    @Getter
    @Setter
    private List<UmsMemberPermissionNode> children;
}
