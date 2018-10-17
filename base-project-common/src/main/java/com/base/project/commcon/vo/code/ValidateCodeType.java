package com.base.project.commcon.vo.code;

import com.base.project.commcon.constant.Constant;

public enum ValidateCodeType {

    IMAGE{
        @Override
        public String getValidateType() {
            return Constant.DEFAULT_IMAGE_CODE;
        }
    },
    SMS{

        @Override
        public String getValidateType() { return Constant.DEFAULT_SMS_CODE; }
    };
    public abstract String getValidateType();
}
