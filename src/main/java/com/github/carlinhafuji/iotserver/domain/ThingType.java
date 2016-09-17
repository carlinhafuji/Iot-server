package com.github.carlinhafuji.iotserver.domain;

public enum ThingType {
    PLANTA(1),
    BALANCA(2),
    CARTEIRA_CHAVE_CARTEIRA(3);

    private final int type;
    ThingType(int type) {
        this.type = type;
    }
}
