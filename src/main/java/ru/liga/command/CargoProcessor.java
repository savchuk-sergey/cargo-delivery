package ru.liga.command;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.liga.Cargo.interfaces.CargoReader;
import ru.liga.Cargo.interfaces.CargoStore;
import ru.liga.Truck.exception.TruckNumberExceededException;
import ru.liga.Truck.interfaces.TruckLoader;
import ru.liga.Truck.interfaces.TruckPark;
import ru.liga.Truck.interfaces.TruckParkWriter;

import java.io.IOException;

import static java.lang.System.exit;

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
    private final CargoReader txtCargoReader;
    private final TruckParkWriter jsonTruckParkWriter;


    @Autowired
    public CargoProcessor(TruckLoader simpleTruckLoader, TruckLoader sortTruckLoader, CargoStore cargoStore, TruckPark truckPark, CargoReader txtCargoReader, TruckParkWriter jsonTruckParkWriter) {
        log.trace("initialization");
        this.simpleTruckLoader = simpleTruckLoader;
        this.sortTruckLoader = sortTruckLoader;
        this.cargoStore = cargoStore;
        this.truckPark = truckPark;
        this.txtCargoReader = txtCargoReader;
        this.jsonTruckParkWriter = jsonTruckParkWriter;
    }

    @ShellMethod(value = "Обрабатывает данные о грузах из текстового файла.", key = "process-txt")
    public void processTxt(@ShellOption(value = {"-f", "--filepath"}, help = "Path to file with cargos") String filepath,
                           @ShellOption(value = {"-c", "--max-truck-number"}, help = "Max number of trucks that can be used", defaultValue = "-1") int maxTruckNumber,
                           @ShellOption(value = {"-s", "--sort"}, help = "Place cargos in a truck using sort algorithm") boolean isSortAlgorithm) throws IOException {
        log.trace("run");
        log.debug("filepath: {}", filepath);

        cargoStore.addAll(txtCargoReader.read(filepath));

        try {
            if (isSortAlgorithm) {
                truckPark.addAll(sortTruckLoader.placeCargos(cargoStore.getAll(), TRUCK_HEIGHT, TRUCK_WIDTH, maxTruckNumber));
            } else {
                truckPark.addAll(simpleTruckLoader.placeCargos(cargoStore.getAll(), TRUCK_HEIGHT, TRUCK_WIDTH, maxTruckNumber));
            }
        } catch (TruckNumberExceededException e) {
            log.error(e.getMessage());
            exit(1);
        }

        truckPark.print();

        log.info("Файл обработан. Количество груза: {}, количество машин: {}", cargoStore.getAll().size(), truckPark.getTrucks().size());
        log.debug("trucks {}", truckPark.getTrucks());
        log.debug("truckPark {}", truckPark);

        truckPark.print();
        jsonTruckParkWriter.writeToFile(truckPark, "trucks.json");
    }


    @ShellMethod(value = "Обрабатывает данные о грузах из JSON файла.", key = "process-json")
    public void processJson(String filepath) throws IOException {

    }
}
