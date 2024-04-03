package org.example.interfaces;

import org.example.model.Cargo;

import java.util.List;

public interface CargoStore {
    void addCargo(Cargo cargo);
    List<Cargo> getCargoes();
}