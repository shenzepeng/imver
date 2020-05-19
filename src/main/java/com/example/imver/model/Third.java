package com.example.imver.model;

/**
 * 要写注释呀
 */
public class Third {
    private Boolean end;

    public static void getThird(){
        Third third=new Third();
        int end=(int)(Math.random()*100);
        if (end>50){
            System.out.println("third 验证结果 "+true);
        }else {
            System.out.println("third 验证结果 "+false);
        }
    }
}
