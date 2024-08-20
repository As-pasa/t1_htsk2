package com.as_pasa.htsk_2;

import com.as_pasa.htsk_2.controllers.OrderController;
import com.as_pasa.htsk_2.controllers.UserController;
import com.as_pasa.htsk_2.example.ExampleBean;
import com.as_pasa.htsk_2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableAspectJAutoProxy

public class T1Htsk2Application {

    public static void main(String[] args) {
        SpringApplication.run(T1Htsk2Application.class, args);
    }

    private ExampleBean exampleBean;
    private UserService service;
    private UserController userController;
    @Autowired
    public T1Htsk2Application(UserService us, UserController userController, ExampleBean exampleBean){
        this.service=us;
        this.exampleBean = exampleBean;
        this.userController=userController;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createDodzee() {



    }
}