package com.example.imver.init;

import com.example.imver.model.First;
import com.example.imver.model.Second;
import com.example.imver.model.Third;

/**
 * 要写注释呀
 */
public class Init {
    public static void init(){
        System.out.println("init");
        First.getFirst();
        Second.getSecond();
        Third.getThird();
        System.out.println("end");
    }
}
