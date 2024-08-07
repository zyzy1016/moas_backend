package com.moas.backend.test;

import cn.hutool.core.util.IdUtil;
import com.moas.backend.toolkit.SseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@RestController
@RequestMapping("/sse")
public class SseController{
    @Autowired
    private SseClient sseClient;
    @GetMapping("/")
    public String index(ModelMap model) {
        String uid = IdUtil.fastUUID();
        model.put("uid",uid);
        return "index";
    }

    @CrossOrigin
    @GetMapping("/createSse")
    public SseEmitter createConnect(String uid) {
        return sseClient.createSse(uid);
    }
    @CrossOrigin
    @GetMapping("/sendMsg")
    @ResponseBody
    public String sseChat(String uid) {
        for (int i = 0; i < 10; i++) {
            sseClient.sendMessage("1016", "no"+i,IdUtil.fastUUID());
        }
        return "ok";
    }

    /**
     * 关闭连接
     */
    @CrossOrigin
    @GetMapping("/closeSse")
    public void closeConnect(String uid ){

        sseClient.closeSse(uid);
    }
}