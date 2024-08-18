package com.brixton.sodimac_v2.data.enums;

public enum RegistryStateType {

    INACTIVE((byte) 0),
    ACTIVE((byte) 1);

    private final byte value;
    RegistryStateType(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    public static RegistryStateType fromValue(byte value) {
        for (RegistryStateType state : values()) {
            if (state.getValue() == value) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid value for RegistryStateType: " + value);
    }

}
