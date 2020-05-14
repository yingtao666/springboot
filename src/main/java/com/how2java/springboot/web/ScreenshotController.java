package com.how2java.springboot.web;

import com.how2java.springboot.dto.ResultDTO;
import com.how2java.springboot.util.GetPicByPhantomjs;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping
public class ScreenshotController {
    @RequestMapping("/screenshot")
    @ResponseBody
    public ResultDTO getPic(String url){
        HashMap<String, String> map = new HashMap<>();
        map.put("url", "http://mp.weixin.qq.com/s/2tSOxdRHBOzIUTvkEiZXag?/");
        String picName = GetPicByPhantomjs.getPicByPhantomjs(map);
        return ResultDTO.putSuccess(picName);
    }
}
