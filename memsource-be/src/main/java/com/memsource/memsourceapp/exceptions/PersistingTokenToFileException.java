package com.memsource.memsourceapp.exceptions;


public class PersistingTokenToFileException extends RuntimeException{
    public PersistingTokenToFileException(String errorMessage){
        super(errorMessage);
    }
}
