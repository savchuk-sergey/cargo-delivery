package ru.liga.Truck.model;

import lombok.extern.log4j.Log4j2;
import ru.liga.Cargo.model.PlacedCargo;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class Truck2 {
    private final int width;
    private final int height;
    private final List<PlacedCargo> cargos;
    public Truck2(int height, int width) {
        this.height = height;
        this.width = width;
        this.cargos = new ArrayList<>();
    }

    public boolean add(PlacedCargo placedCargo) {
        if (cargos.isEmpty())
        cargos.add(placedCargo);
        return true;
    }
}
