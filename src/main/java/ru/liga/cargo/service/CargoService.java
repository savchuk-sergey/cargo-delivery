package ru.liga.cargo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.liga.cargo.Cargo;
import ru.liga.truck.Truck;
import ru.liga.truck.exception.TruckNumberExceededException;
import ru.liga.truck.service.TruckLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.exit;
import static ru.liga.constants.Constants.*;

@Log4j2
@Component
public class CargoService {
    private final TruckLoader simpleTruckLoader;
    private final TruckLoader sortTruckLoader;
    private final CargoReader txtCargoReader;


    @Autowired
    public CargoService(TruckLoader simpleTruckLoader, TruckLoader sortTruckLoader, CargoReader txtCargoReader) {
        log.trace("initialization");
        this.simpleTruckLoader = simpleTruckLoader;
        this.sortTruckLoader = sortTruckLoader;
        this.txtCargoReader = txtCargoReader;
    }

    public List<Truck> processTxtToList(String rawCargos, int maxTruckNumber, boolean isSortAlgorithm) throws TruckNumberExceededException {
        log.trace("processTxtToList");

        List<Cargo> cargos = txtCargoReader.read(rawCargos);
        List<Truck> trucks = new ArrayList<>();

        if (isSortAlgorithm) {
            System.out.println("if");
            trucks.addAll(sortTruckLoader.placeCargos(cargos, TRUCK_HEIGHT, TRUCK_WIDTH, maxTruckNumber));
        } else {
            System.out.println("else");
            trucks.addAll(simpleTruckLoader.placeCargos(cargos, TRUCK_HEIGHT, TRUCK_WIDTH, maxTruckNumber));
        }

        System.out.println(trucks);


        log.info("Данные грузов обработаны. Количество груза: {}, количество машин: {}", cargos.size(), trucks.size());
        log.debug("trucks {}", trucks);

        return trucks;
    }

    public List<Truck> processTxtToList(String rawCargos) throws TruckNumberExceededException {
        return processTxtToList(rawCargos, INFINITE_MAX_TRUCKS, false);
    }

    public String processTxtToString(String rawCargos, int maxTruckNumber, boolean isSortAlgorithm) throws TruckNumberExceededException {
        log.trace("processTxtToString");
        return processTxtToList(rawCargos, maxTruckNumber, isSortAlgorithm).stream()
                .map(Truck::toString)
                .collect(Collectors.joining("\n\n"));
    }

    public String processTxtToString(String rawCargos) throws TruckNumberExceededException {
        return processTxtToString(rawCargos, INFINITE_MAX_TRUCKS, false);
    }

    public String processTxtToJson(String rawCargos) throws TruckNumberExceededException {
        return processTxtToString(rawCargos, INFINITE_MAX_TRUCKS, false);
    }
}
