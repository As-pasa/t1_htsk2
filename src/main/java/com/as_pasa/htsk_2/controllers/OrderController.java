package com.as_pasa.htsk_2.controllers;

import com.as_pasa.htsk_2.exceptions.ApplicationException;
import com.as_pasa.htsk_2.models.Order;
import com.as_pasa.htsk_2.models.User;
import com.as_pasa.htsk_2.services.OrderService;
import com.as_pasa.htsk_2.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Controller
public class OrderController {

    private OrderService orderService;
    private UserService userService;

    @Autowired
    public OrderController(OrderService service, UserService userService) {
        this.orderService = service;
        this.userService = userService;
    }

    @GetMapping("/orders/description")
    @ResponseBody
    public String getDescription(@RequestParam(name = "id") String id) {
        Long parsed = Long.parseLong(id);
        return orderService.findById(parsed).getDescription();
    }

    @GetMapping("/orders/owner")
    @ResponseBody
    public String getUserId(@RequestParam(name = "id") String id) {
        return orderService.findById(Long.parseLong(id)).getUser().getId().toString();
    }

    @GetMapping("/orders/status")
    @ResponseBody
    public String getStatus(@RequestParam(name = "id") String id) {

        return orderService.findById(Long.parseLong(id)).getState().toString();
    }

    @GetMapping("/orders/all")
    @ResponseBody
    public String getAll() {
        return orderService.listAll().stream().map(Order::toString).reduce((acc, x)->acc+"\n"+x).orElseThrow(()-> new ApplicationException("Orders Not Found"));
    }
    @GetMapping("/orders/create")
    @ResponseBody
    public String addOrder(@RequestParam(name="user") String userId,@RequestParam(name="description") String description){
        User user = userService.findById(Long.parseLong(userId));
        Order order = orderService.initOrder(user,description);
        orderService.persistOrder(order);
        return order.getId().toString();
    }
}
