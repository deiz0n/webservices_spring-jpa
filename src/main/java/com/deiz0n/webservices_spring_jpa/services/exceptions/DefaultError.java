package com.deiz0n.webservices_spring_jpa.services.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DefaultError {

    private Integer code;
    private String msg;

}
