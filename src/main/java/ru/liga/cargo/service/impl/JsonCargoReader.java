package ru.liga.cargo.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ru.liga.cargo.Cargo;
import ru.liga.cargo.service.CargoReader;

import java.util.ArrayList;
import java.util.List;

@Component
@Log4j2
public class JsonCargoReader implements CargoReader {
    public List<Cargo> read(String filePath) {
        return new ArrayList<>();
    }
}
