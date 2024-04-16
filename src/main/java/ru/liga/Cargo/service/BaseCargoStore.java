package ru.liga.Cargo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ru.liga.Cargo.interfaces.CargoStore;
import ru.liga.Cargo.model.Cargo;

import java.util.ArrayList;
import java.util.List;

@Component
@Log4j2
public class BaseCargoStore implements CargoStore {
    private final List<Cargo> cargos;

    public BaseCargoStore() {
        log.trace("initialization");
        cargos = new ArrayList<>();
    }

    @Override
    public void add(Cargo cargo) {
        log.trace("add: {}", cargo);
        cargos.add(cargo);
    }

    @Override
    public void addAll(List<Cargo> cargos) {
        log.trace("addAll: {}", cargos);
        this.cargos.addAll(cargos);
    }

    @Override
    public List<Cargo> getAll() {
        log.trace("getAll: {}", cargos);
        return cargos;
    }

}