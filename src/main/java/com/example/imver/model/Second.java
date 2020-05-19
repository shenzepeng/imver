package com.example.imver.model;

import com.example.imver.utils.RsaTool;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

/**
 * 要写注释呀
 */
@Data
public class Second {
    private String msg;
    public static void getSecond(){
        Second second=new Second();
        Map<String, Object> init = RsaTool.init();
        String privateKey = RsaTool.getPrivateKey(init);
        second.msg = RsaTool.encryptByPrivateKey(UUID.randomUUID().toString(), privateKey);
        System.out.println("第二个 "+second);
    }
}
