package ru.liga.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.liga.Cargo.interfaces.CargoStore;
import ru.liga.Cargo.service.CargoReader;
import ru.liga.Truck.exception.TruckNumberExceededException;
import ru.liga.Truck.interfaces.TruckLoader;
import ru.liga.Truck.interfaces.TruckPark;
import ru.liga.Truck.model.Truck;
import ru.liga.Truck.service.TruckParkWriter;
import ru.liga.Truck.util.TruckSerializer;

import java.io.File;
import java.io.IOException;

/**
 * Этот класс представляет собой обработчик грузов,
 * который обрабатывает данные о грузах из текстовых и JSON файлов.
 */
@Log4j2
@ShellComponent
public class CargoProcessor {
    public static final int TRUCK_HEIGHT = 6;
    public static final int TRUCK_WIDTH = 6;

    private final TruckLoader simpleTruckLoader;
    private final TruckLoader sortTruckLoader;
    private final CargoStore cargoStore;
    private final TruckPark truckPark;
    private final CargoReader cargoReader;
    private final TruckParkWriter truckParkWriter;


    @Autowired
    public CargoProcessor(TruckLoader simpleTruckLoader, TruckLoader sortTruckLoader, CargoStore cargoStore, TruckPark truckPark, CargoReader cargoReader, TruckParkWriter truckParkWriter) {
        log.trace("initialization");
        this.simpleTruckLoader = simpleTruckLoader;
        this.sortTruckLoader = sortTruckLoader;
        this.cargoStore = cargoStore;
        this.truckPark = truckPark;
        this.cargoReader = cargoReader;
        this.truckParkWriter = truckParkWriter;
    }

    /**
     * Обрабатывает данные о грузах из текстового файла.
     *
     * @param filepath путь к текстовому файлу с данными о грузах
     */
    @ShellMethod(key = "process-txt")
    public void processTxt(@ShellOption(value = {"-f", "--filepath"}, help = "Path to file with cargos") String filepath,
                           @ShellOption(value = {"-c", "--max-truck-number"}, help = "Max number of trucks that can be used", defaultValue = "-1") int maxTruckNumber,
                           @ShellOption(value = {"-s", "--sort"}, help = "Place cargos in a truck using sort algorithm") boolean isSortAlgorithm,
                           @ShellOption(value = {"-S", "--simple"}, help = "Place cargos in a truck using simple algorithm(one truck - one cargo)") boolean isSimpleAlgorithm) throws IOException {
        log.trace("run");
        log.debug("filepath: {}", filepath);

        cargoStore.addAll(cargoReader.readFromTxtFile(filepath));

        try {
            if (isSimpleAlgorithm) {
                truckPark.addAll(simpleTruckLoader.placeCargos(cargoStore.getAll(), TRUCK_HEIGHT, TRUCK_WIDTH, maxTruckNumber));

            } else if (isSortAlgorithm) {
                truckPark.addAll(sortTruckLoader.placeCargos(cargoStore.getAll(), TRUCK_HEIGHT, TRUCK_WIDTH, maxTruckNumber));
            }
        } catch (TruckNumberExceededException e) {
            log.error(e.getMessage());
        }

        truckPark.print();

        log.info("Файл обработан. Количество груза: {}, количество машин: {}",cargoStore.getAll().size(), truckPark.getAll().size());
        log.debug("trucks {}", truckPark.getAll());
        log.debug("truckPark {}", truckPark);

        truckParkWriter.writeToJson(truckPark, "trucks.json");
    }

    /**
     * Обрабатывает данные о грузах из JSON файла.
     *
     * @param filepath путь к JSON файлу с данными о грузах
     * @throws IOException если произошла ошибка ввода-вывода
     */
    @ShellMethod(key = "process-json")
    public void processJson(String filepath) throws IOException {

    }
}
