package com.example.retailer.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Entity
@Component
@Table(name = "user_info")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String username;

    @OneToMany(mappedBy = "user")
    private Set<UserCart> carts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<UserCart> getCarts() {
        return carts;
    }

    public void setCarts(Set<UserCart> carts) {
        this.carts = carts;
    }
}
