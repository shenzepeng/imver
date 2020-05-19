package com.example.imver.model;

import com.example.imver.utils.RsaTool;
import lombok.Data;

import java.util.Map;

/**
 * 要写注释呀
 */
@Data
public class First {
    private String publicKey;
    private String privateKey;

    public static void  getFirst() {
        First first = new First();
        Map<String, Object> init = RsaTool.init();
        first.privateKey = RsaTool.getPrivateKey(init);
        first.publicKey = RsaTool.getPublicKey(init);
        System.out.println("first    " + first.toString());
    }
}
