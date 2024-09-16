package com.brixton.sodimac_v2.controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenericNotFoundException extends RuntimeException{

    public GenericNotFoundException(String message){
        super(message);
        log.info("GenericNotFoundException: " + message);
    }

}
