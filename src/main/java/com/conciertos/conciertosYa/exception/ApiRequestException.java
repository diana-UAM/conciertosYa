package com.conciertos.conciertosYa.exception;

public class ApiRequestException  extends RuntimeException{

    public ApiRequestException(String message){
        super(message);
    }
}