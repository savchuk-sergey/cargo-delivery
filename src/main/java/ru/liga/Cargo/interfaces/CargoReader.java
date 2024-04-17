package ru.liga.Cargo.interfaces;

import ru.liga.Cargo.model.Cargo;

import java.util.List;

public interface CargoReader {
    List<Cargo> read(String filepath);
}
