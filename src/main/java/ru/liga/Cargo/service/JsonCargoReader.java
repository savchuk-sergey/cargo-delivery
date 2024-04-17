package ru.liga.Cargo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ru.liga.Cargo.interfaces.CargoReader;
import ru.liga.Cargo.model.Cargo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.exit;

@Component
@Log4j2
public class JsonCargoReader implements CargoReader {
    public List<Cargo> read(String filePath) {
        log.trace("read - filePath: " + filePath);

        List<List<Integer>> cargoSizes = new ArrayList<>();
        List<Cargo> cargos = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine().trim();
                log.trace("read - file line: " + line);

                if (!line.isEmpty()) {
                    cargoSizes.add(getIntegersFromString(line));
                }
                if (line.isEmpty() || !scanner.hasNext()) {
                    cargos.add(getCargoFromList(cargoSizes));
                    cargoSizes = new ArrayList<>();
                }
            }
        } catch (FileNotFoundException e) {
            log.error("Файл не найден: " + e.getMessage());
            log.trace(Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.joining("\n")));
            exit(1);
        }

        return cargos;
    }

    private List<Integer> getIntegersFromString (String string) {
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
