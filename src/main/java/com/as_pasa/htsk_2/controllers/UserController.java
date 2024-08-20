package com.as_pasa.htsk_2.controllers;

import com.as_pasa.htsk_2.annotations.LogCall;
import com.as_pasa.htsk_2.exceptions.ApplicationException;
import com.as_pasa.htsk_2.models.User;
import com.as_pasa.htsk_2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.as_pasa.htsk_2.models.Order;

@Controller
@Component
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService users) {
        this.userService = users;
    }


    @GetMapping("/users/name")
    @LogCall
    @ResponseBody
    public String getUserName(@RequestParam(name = "id") String id) {
        Long parsed = Long.parseLong(id);
        User user = userService.findById(parsed);
        return user.getName();
    }

    @ResponseBody
    @LogCall
    @GetMapping("/users/mail")
    public String getUserMail(@RequestParam(name = "id") String id) {
        Long parsed = Long.parseLong(id);
        User user = userService.findById(parsed);
        return user.getEmail();
    }

    @GetMapping("/users/orders")
    @LogCall
    @ResponseBody
    public String getUserOrders(@RequestParam(name = "id") String id) {
        Long parsed = Long.parseLong(id);
        User user = userService.findById(parsed);
        return (user.getOrders().stream()
                .map(Order::getId)
                .map(Object::toString)
                .reduce((k, b) -> k + "\n" + b)).orElseThrow(() -> new ApplicationException("No orders Found"));
    }

    @GetMapping("/users/all")
    @ResponseBody
    public String getUsers() {
        return userService.listUsers().stream().map(User::toString).reduce((acc, x) -> acc + "\n" + x).orElseThrow(() -> new ApplicationException("No users found"));
    }

    @GetMapping("/users/create")

    @ResponseBody
    public String postUser(@RequestParam(name = "name") String name, @RequestParam(name = "mail") String mail) {
        User user = userService.initUser(name, mail);
        userService.persistUser(user);
        return user.getId().toString();
    }


}
