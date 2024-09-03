package com.brixton.sodimac_v2.data.model.enums;

public enum TypeDocument {
    CARNET_EXTRANJERIA((byte)0),
    DNI((byte)1),
    PASSAPORTE((byte)2);

    private final byte value;
    TypeDocument(byte value) {
        this.value = value;
    }

    public byte getValue(){
        return value;
    }

    public static TypeDocument fromValue(byte value){
        for(TypeDocument document: values()){
            if(document.getValue() == value){
                return document;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeDocument: " + value);
    }
}
