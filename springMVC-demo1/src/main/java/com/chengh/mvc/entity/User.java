package com.chengh.mvc.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class User {
    @NotNull
    private String name;
    @Length(min = 6,max = 16)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
