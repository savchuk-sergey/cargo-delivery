package ru.liga.Cargo.model;

import ru.liga.util.Coordinate;

public class PlacedCargo {
    private final Cargo cargo;
    private final Coordinate coordinate;

    public PlacedCargo(Cargo cargo, Coordinate coordinate) {
        this.cargo = cargo;
        this.coordinate = coordinate;
    }
}
