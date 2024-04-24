package ru.liga.shell;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.liga.cargo.service.CargoService;
import ru.liga.truck.exception.TruckNumberExceededException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Этот класс представляет собой обработчик грузов,
 * который обрабатывает данные о грузах из текстовых и JSON файлов.
 */
@Log4j2
@ShellComponent
public class ShellController {
    private final CargoService cargoService;

    @Autowired
    public ShellController(CargoService cargoService) {
        this.cargoService = cargoService;
        log.trace("initialization");
    }

    @ShellMethod(value = "Обрабатывает данные о грузах из текстового файла.", key = "process-txt")
    public String processTxt(@ShellOption(value = {"-f", "--filepath"}, help = "Path to file with cargos") String filepath,
                             @ShellOption(value = {"-c", "--max-truck-number"}, help = "Max number of trucks that can be used", defaultValue = "-1") int maxTruckNumber,
                             @ShellOption(value = {"-s", "--sort"}, help = "Place cargos in a truck using sort algorithm") boolean isSortAlgorithm) throws IOException {

        log.trace("run");
        log.debug("filepath: {}", filepath);

        try {
            return cargoService.processTxtToString(Files.readString(Paths.get(filepath)), maxTruckNumber, isSortAlgorithm);
        } catch (TruckNumberExceededException e) {
            log.error(e.getMessage());
        }

        return "";
    }


    @ShellMethod(value = "Обрабатывает данные о грузах из JSON файла.", key = "process-json")
    public void processJson(String filepath) throws IOException {
    }
}
