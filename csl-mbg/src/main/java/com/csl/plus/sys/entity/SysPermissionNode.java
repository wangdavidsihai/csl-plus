package com.csl.plus.sys.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 */
public class SysPermissionNode extends SysPermission {
	@Getter
	@Setter
	private List<SysPermissionNode> children;
}
