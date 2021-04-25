package com.miamato.valueobjects;

import com.miamato.PropertyManager;

public class Customer {
    public String signInName;
    public String signInEmail;
    public String signInPassword;
    public String email;
    public String password;

    public Customer(){
        this.signInEmail = PropertyManager.getProperty("customer.sign.in.email");
        this.signInPassword = PropertyManager.getProperty("customer.sign.in.password");
        this.signInName = PropertyManager.getProperty("customer.sign.in.name");
        this.email = PropertyManager.getProperty("customer.email");
        this.password = PropertyManager.getProperty("customer.password");
    }

}
