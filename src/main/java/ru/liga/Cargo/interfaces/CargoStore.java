package ru.liga.Cargo.interfaces;

import ru.liga.Cargo.model.Cargo;
import java.util.List;

public interface CargoStore {
    void add(Cargo cargo);
    void addAll(List<Cargo> cargos);
    List<Cargo> getAll();
}