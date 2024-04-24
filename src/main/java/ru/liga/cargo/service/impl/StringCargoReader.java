package ru.liga.cargo.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ru.liga.cargo.Cargo;
import ru.liga.cargo.service.CargoReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Log4j2
public class StringCargoReader implements CargoReader {
    public List<Cargo> read(String rawCargos) {
        log.trace("read - rawCargos: " + rawCargos);
        String[] rawCargosSplit = rawCargos.split("\n");
        List<List<Integer>> cargoSizes = new ArrayList<>();
        List<Cargo> cargos = new ArrayList<>();

        for (int i = 0; i < rawCargosSplit.length; i++) {
            String line = rawCargosSplit[i].trim();
            log.trace("readCargoFromFile - file line: " + line);

            if (!line.isEmpty()) {
                cargoSizes.add(getIntegersFromString(line));
            }
            if (line.isEmpty() || i == rawCargosSplit.length - 1) {
                cargos.add(getCargoFromList(cargoSizes));
                cargoSizes = new ArrayList<>();
            }
        }

        return cargos;
    }

    private List<Integer> getIntegersFromString(String string) {
        log.trace("getIntegersFromString - string: " + string);
        return Arrays.stream(string.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private Cargo getCargoFromList(List<List<Integer>> sizes) {
        log.trace("getCargoFromList - sizes: " + sizes);
        int volume = sizes.get(0).get(0);

        return new Cargo(volume, sizes);
    }
}
