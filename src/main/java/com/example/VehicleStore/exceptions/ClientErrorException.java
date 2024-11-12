package com.example.VehicleStore.exceptions;

public class ClientErrorException extends RuntimeException{

    public ClientErrorException(String message){
        super(message);
    }

}
