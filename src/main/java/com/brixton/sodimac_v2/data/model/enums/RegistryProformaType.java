package com.brixton.sodimac_v2.data.model.enums;

public enum RegistryProformaType {
    UNUSED((byte) 0),
    USED((byte) 1);

    private final byte value;
    RegistryProformaType(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    public static RegistryProformaType fromValue(byte value) {
        for (RegistryProformaType state : values()) {
            if (state.getValue() == value) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid value for RegistryProformaType: " + value);
    }
}
