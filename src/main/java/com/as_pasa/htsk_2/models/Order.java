package com.as_pasa.htsk_2.models;

import jakarta.persistence.*;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;


    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    private OrderState state;

    @Override
    public String toString() {
        return String.format("Order(%s, %s)", description,state.toString());
    }

    public Order(String product, User user, OrderState state) {
        this.description = product;
        this.user = user;
        this.state = state;
    }
    protected Order(){

    }
    public void setState(Order state){
        this.setState(state.getState());
        this.setDescription(state.getDescription());
        this.setUser(state.getUser());

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }
}
