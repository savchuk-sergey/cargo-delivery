package ru.liga.cargo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.liga.cargo.Cargo;
import ru.liga.truck.Truck;
import ru.liga.truck.exception.TruckNumberExceededException;
import ru.liga.truck.service.TruckLoader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static ru.liga.constants.Constants.TRUCK_HEIGHT;
import static ru.liga.constants.Constants.TRUCK_WIDTH;

@ExtendWith(MockitoExtension.class)
public class CargoServiceTest {

    @Mock
    private CargoReader cargoReader;

    @Mock
    private TruckLoader simpleTruckLoader;

    @Mock
    private TruckLoader sortTruckLoader;

    @Test
    public void testProcessTxtToListMethod_SimpleAlgorithm_CorrectInput_CorrectOutput() throws TruckNumberExceededException {
        CargoService cargoService = new CargoService(simpleTruckLoader, sortTruckLoader, cargoReader);
        String rawCargos = "1\n\n22\n\n333\n\n4444";
        int maxTruckNumber = 5;
        boolean isSortAlgorithm = false;

        List<Cargo> expectedCargos = List.of(
                new Cargo(1, List.of(List.of(1))),
                new Cargo(2, List.of(List.of(2, 2))),
                new Cargo(3, List.of(List.of(3, 3, 3))),
                new Cargo(4, List.of(List.of(4, 4, 4, 4)))
        );

        when(cargoReader.read(rawCargos)).thenReturn(expectedCargos);

        List<Truck> expectedTrucks = List.of(
                new Truck(List.of(List.of(), List.of(), List.of(), List.of(), List.of(), List.of(1)), TRUCK_HEIGHT, TRUCK_WIDTH),
                new Truck(List.of(List.of(), List.of(), List.of(), List.of(), List.of(), List.of(2, 2)), TRUCK_HEIGHT, TRUCK_WIDTH),
                new Truck(List.of(List.of(), List.of(), List.of(), List.of(), List.of(), List.of(3, 3, 3)), TRUCK_HEIGHT, TRUCK_WIDTH),
                new Truck(List.of(List.of(), List.of(), List.of(), List.of(), List.of(), List.of(4, 4, 4, 4)), TRUCK_HEIGHT, TRUCK_WIDTH)
        );

        when(simpleTruckLoader.placeCargos(expectedCargos, TRUCK_HEIGHT, TRUCK_WIDTH, maxTruckNumber)).thenReturn(expectedTrucks);

        List<Truck> trucks = cargoService.processTxtToList(rawCargos, maxTruckNumber, isSortAlgorithm);

        assertThat(trucks).hasSize(4);
    }
}