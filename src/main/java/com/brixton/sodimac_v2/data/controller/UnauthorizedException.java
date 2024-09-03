package com.brixton.sodimac_v2.data.controller;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException (String mesaage){
        super(mesaage);
    }
}
