package com.base.project.commcon.vo.code.image;

import com.base.project.commcon.vo.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageValidateCode extends ValidateCode {

    private BufferedImage  image = null;

    public ImageValidateCode(String code, int expireIn, BufferedImage image) {
        super(code, expireIn);
        this.image = image;
    }

    public ImageValidateCode(String code, LocalDateTime expireTime, BufferedImage image) {
        super(code, expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
