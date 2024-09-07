package com.brixton.sodimac_v2.data.model.enums;

public enum TypeDocumentBusiness {

    TICKET("TICKET"),
    BILL("BILL"),
    ORDER_BUY("ORDER_BUY");
    private final String value;

    TypeDocumentBusiness(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
    public static TypeDocumentBusiness fromString(String value){
        for(TypeDocumentBusiness type: TypeDocumentBusiness.values()){
            if(type.getValue().equalsIgnoreCase(value)){
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }
}
