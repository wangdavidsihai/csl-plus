package com.csl.plus.sys.service;


import javax.servlet.http.HttpServletRequest;

import com.csl.plus.sys.vo.OssCallbackResult;
import com.csl.plus.sys.vo.OssPolicyResult;

/**
 * oss上传管理Service
 */
public interface OssService {
    OssPolicyResult policy();

    OssCallbackResult callback(HttpServletRequest request);
}
