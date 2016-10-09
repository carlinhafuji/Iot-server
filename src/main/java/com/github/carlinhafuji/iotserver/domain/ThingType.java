package com.github.carlinhafuji.iotserver.domain;

public enum ThingType {
    PLANTA(0),
    BALANCA(1),
    CARTEIRA_CHAVE_CARTEIRA(2);

    private final int type;
    ThingType(int type) {
        this.type = type;
    }
}
