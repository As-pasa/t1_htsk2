package com.as_pasa.htsk_2.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    @OneToMany(mappedBy = "user")
    private List<Order> orders;
    protected User(){

    }

    @Override
    public String toString() {
        return String.format("USER(%s, %s)",name,email);
    }

    public User(String name, String email, List<Order> orders) {
        this.name = name;
        this.email = email;
        this.orders = orders;
    }

    public void setState(User state){
        this.setName(state.getName());
        this.setEmail(state.getEmail());
        this.setOrders(state.getOrders());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }
}
