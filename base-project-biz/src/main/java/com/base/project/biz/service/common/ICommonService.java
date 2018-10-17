package com.base.project.biz.service.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommonService {

    void upload(HttpServletRequest request);

    void download(HttpServletRequest request, HttpServletResponse response);
}
