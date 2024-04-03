package org.example;

import org.example.interfaces.CargoStore;
import org.example.model.Cargo;
import org.example.service.BaseCargoStore;
import org.example.service.CargoReader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class CargoReaderTest {
    static CargoStore cargoStore;

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    @BeforeAll
    static void init() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        cargoStore = new BaseCargoStore();

        // Путь к файлу с данными о грузах
        String filePath = "testData/cargoData.txt";

        CargoReader.readCargoFromFile(cargoStore, filePath);
    }

    @Test
    void testReadCargoFromFile_givenValidInput_thenStoreIsNotEmpty() {
        assertThat(cargoStore.getCargoes()).isNotEmpty();
    }

    @Test
    void testReadCargoFromFile_givenValidInput_thenCorrectValue() {
        //  Проверить, что первый груз имеет правильный размер
        Cargo firstCargo = cargoStore.getCargoes().get(0);
        assertThat(firstCargo.getVolume()).isEqualTo(9);
    }

    @Test
    void testReadCargoFromFile_givenValidInput_thenCorrectSize() {
        //  Проверить, что первый груз имеет правильный объем
        Cargo firstCargo = cargoStore.getCargoes().get(0);
        assertThat(firstCargo.getSize()).isEqualTo(List.of(
                List.of(9, 9, 9),
                List.of(9, 9, 9),
                List.of(9, 9, 9)
        ));
    }

    @Test
    void testReadCargoFromFile_givenThreeCargos_thenThreeCargosInStore() {
        assertThat(cargoStore.getCargoes().size()).isEqualTo(3);
    }



    @Test
    void testReadCargoFromFile_givenWrongFilepath_thenPrintFileNotFound() {
        CargoStore cargoStore = new BaseCargoStore();

        // Путь к несуществующему файлу
        String filePath = "non-existent-file.txt";
        CargoReader.readCargoFromFile(cargoStore, filePath);

        // Попытаться вызвать метод readCargoFromFile для чтения данных из несуществующего файла
        assertThat(outContent.toString()).isEqualTo("Файл не найден!\r\n");
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
