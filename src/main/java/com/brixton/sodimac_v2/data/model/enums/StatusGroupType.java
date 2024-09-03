package com.brixton.sodimac_v2.data.model.enums;

public enum StatusGroupType {

    DETAIL("DETAIL"),
    PROFORMA("PROFORMA");

    private final String value;

    StatusGroupType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static StatusGroupType fromString(String value){
        for(StatusGroupType type: StatusGroupType.values()){
            if(type.getValue().equalsIgnoreCase(value)){
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }
}
