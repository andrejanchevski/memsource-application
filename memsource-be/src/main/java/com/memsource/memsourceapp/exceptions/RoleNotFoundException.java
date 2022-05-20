package com.memsource.memsourceapp.exceptions;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
