package org.example.service;

import org.example.interfaces.CargoStore;
import org.example.model.Cargo;

import java.util.ArrayList;
import java.util.List;

public class BaseCargoStore implements CargoStore {
    private final List<Cargo> cargos;

    public BaseCargoStore() {
        cargos = new ArrayList<>();
    }

    @Override
    public void addCargo(Cargo cargo) {
        cargos.add(cargo);
    }

    @Override
    public List<Cargo> getCargoes() {
        return cargos;
    }
}