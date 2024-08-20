package com.as_pasa.htsk_2.example;


import com.as_pasa.htsk_2.annotations.LogCall;
import org.springframework.stereotype.Component;

@Component
public class ExampleBean {
    @LogCall
    public void foo() {
        System.out.println(111);
    }

}
