package com.base.project.biz.service.common.impl;

import com.api.project.util.StringUtil;
import com.base.project.biz.service.common.ICommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CommonServiceImpl implements ICommonService {

    private Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

    /**
     * response.setContentLength(buf.length);
     * response.setContentType("application/zip");
     * response.setContentType("application/vnd.android.package-archive");
     * response.addHeader("Content-Disposition", "attachment; filename=" + req.getName());
     * response.addHeader("Content-Transfer-Encoding", "binary");
     * response.addHeader("Connection", "keep-alive");
     * @param request
     * @param response
     */
    @Override
    public void download(HttpServletRequest request, HttpServletResponse response) {


    }

    /**
     * 上传文件
     * @param request
     */
    @Override
    public void upload(HttpServletRequest request) {

        try{

            CommonsMultipartResolver resolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            if(resolver.isMultipart(request)){

                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                List<MultipartFile> files = multipartRequest.getFiles("file");
                if(files != null && !files.isEmpty()){

                    for(MultipartFile file : files){

                        // file to work
                    }
                }else{
                    logger.error("上传文件空");
                }
            }else{

                logger.error("上传请求非法");
            }
        }catch (Exception e){

            logger.error("上传文件异常[" + StringUtil.createStackTrace(e) + "]");
        }
    }
}
