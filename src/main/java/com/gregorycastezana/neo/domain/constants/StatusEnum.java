package com.gregorycastezana.neo.domain.constants;

public enum StatusEnum {
    ALIVE,
    DEAD;

    public String getStatus() {
        switch (this) {
            case ALIVE -> {
                return "Alive";
            }
            case DEAD -> {
                return "Dead";
            }
            default -> {
                return null;
            }
        }
    }
}
