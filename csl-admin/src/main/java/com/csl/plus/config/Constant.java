package com.csl.plus.config;

public interface Constant {
	// 演示系统账户
	public static String DEMO_ACCOUNT = "test";
	// 自动去除表前缀
	public static String AUTO_REOMVE_PRE = "true";
	// 停止计划任务
	public static String STATUS_RUNNING_STOP = "stop";
	// 开启计划任务
	public static String STATUS_RUNNING_START = "start";
	// 通知公告阅读状态-未读
	public static String OA_NOTIFY_READ_NO = "0";
	// 通知公告阅读状态-已读
	public static int OA_NOTIFY_READ_YES = 1;
	// 部门根节点id
	public static Long DEPT_ROOT_ID = 0l;
	// 缓存方式
	public static String CACHE_TYPE_REDIS = "redis";
	public static String LOG_ERROR = "error";
	// 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
	public static Integer PERMISSION_TYPE_CATALOG = 0;
	public static Integer PERMISSION_TYPE_MENU = 1;
	public static Integer PERMISSION_TYPE_BUTTON = 2;
	// 启用状态；0->禁用；1->启用
	public static Integer PERMISSION_STATUS_TERNINATED = 0;
	public static Integer PERMISSION_STATUS_ACTIVE = 1;

}
