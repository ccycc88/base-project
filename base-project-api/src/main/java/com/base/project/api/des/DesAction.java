package com.base.project.api.des;

import com.base.project.commcon.annotation.des.DecryptRequestBody;
import com.base.project.commcon.annotation.des.EncryptResponBody;
import com.base.project.commcon.vo.req.DecryptReq;
import com.base.project.commcon.vo.rsp.BaseRespone;
import org.springframework.web.bind.annotation.*;

@RestController
public class DesAction {

    @EncryptResponBody
    @GetMapping(value = "/encrypt", produces = "application/json")
    @ResponseBody public BaseRespone encrypt(){

        BaseRespone respone = BaseRespone.success("成功");

        return respone;
    }
    @DecryptRequestBody
    @PostMapping(value = "/decrypt",produces = "application/json")
    @ResponseBody public BaseRespone decrypt(@RequestBody DecryptReq req){

        BaseRespone respone = BaseRespone.success(req.getJson());

        return respone;
    }
}
