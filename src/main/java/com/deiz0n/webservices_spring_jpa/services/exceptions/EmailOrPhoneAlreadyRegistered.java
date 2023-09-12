package com.deiz0n.webservices_spring_jpa.services.exceptions;

public class EmailOrPhoneAlreadyRegistered extends RuntimeException {

    public EmailOrPhoneAlreadyRegistered(String msg) {
        super(msg);
    }

}
