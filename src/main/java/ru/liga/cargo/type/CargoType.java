package ru.liga.cargo.type;

import lombok.Getter;

@Getter
public enum CargoType {
    ONE("1"),
    TWO("22"),
    THREE("333"),
    FOUR("4444"),
    FIVE("55555"),
    SIX("666\n666"),
    SEVEN("777\n7777"),
    EIGHT("8888\n8888"),
    NINE("999\n999\n999");

    private final String volume;

    CargoType(String volume) {
        this.volume = volume;
    }

}
