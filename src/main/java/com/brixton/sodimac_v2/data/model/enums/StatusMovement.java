package com.brixton.sodimac_v2.data.model.enums;

public enum StatusMovement {

    INPUT("INPUT"),
    OUTPUT("OUTPUT");
    private final String value;

    StatusMovement(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
    public static StatusMovement fromString(String value){
        for(StatusMovement type: StatusMovement.values()){
            if(type.getValue().equalsIgnoreCase(value)){
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }
}
