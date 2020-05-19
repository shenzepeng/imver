package com.example.imver;


import com.example.imver.init.Init;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

@SpringBootTest
public class ImverApplicationTests {

    @Test
    public void contextLoads() throws ExecutionException {
        Init.init();
    }

}
