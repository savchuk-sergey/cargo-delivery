package ru.liga.Cargo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ru.liga.Cargo.model.Cargo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


/**
 * Класс для чтения информации о грузах из файла и преобразования её в объекты {@link Cargo}.
 */
@Log4j2
@Component
public class CargoReader {
    /**
     * Читает информацию о грузах из txt файла и возвращает список объектов {@link Cargo}.
     *
     * @param filePath путь к файлу с информацией о грузах
     * @return список объектов {@link Cargo}, представляющих информацию о грузах из файла
     */
    public List<Cargo> readFromTxtFile(String filePath) {
        log.trace("readCargoFromFile - filePath: " + filePath);

        List<List<Integer>> cargoSizes = new ArrayList<>();
        List<Cargo> cargos = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine().trim();
                log.trace("readCargoFromFile - file line: " + line);

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
        }

        return cargos;
    }

    public List<Cargo> readFromJsonFile(String filePath) {
        log.trace("readCargoFromFile - filePath: " + filePath);

        List<List<Integer>> cargoSizes = new ArrayList<>();
        List<Cargo> cargos = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine().trim();
                log.trace("readCargoFromFile - file line: " + line);

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
        }

        return cargos;
    }

    /**
     * Преобразует строку в список целых чисел.
     *
     * @param string строка с целыми числами
     * @return список целых чисел
     */
    public List<Integer> getIntegersFromString (String string) {
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