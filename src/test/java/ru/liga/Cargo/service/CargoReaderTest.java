package ru.liga.Cargo.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CargoReaderTest {

    private CargoReader cargoReader;

//    @Mockjhnmm
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        cargoReader = new CargoReader(fileService);
//    }

    @Test
    void testReadFromFile_Successful() {
//        List<Cargo> expectedCargos = new ArrayList<>();
//        expectedCargos.add(new Cargo(9, List.of(
//                List.of(9, 9, 9),
//                List.of(9, 9, 9),
//                List.of(9, 9, 9))));
//
//        expectedCargos.add(new Cargo(6, List.of(
//                List.of(6, 6, 6),
//                List.of(6, 6, 6))));
//
//        expectedCargos.add(new Cargo(5, List.of(
//                List.of(5, 5, 5, 5, 5))));
//
//        // Мокирование вызова метода fileService.readFile()
//        when(fileService.readFile("testFile.txt")).thenReturn("1 2 3 4");
//
//        // Вызов тестируемого метода
//        cargoReader.readFromTxtFile("testFile.txt");
//
//        // Проверка результатов
//        assertThat(cargoReader.getCargos()).isEqualTo(expectedCargos);
    }

    @Test
    void testGetListCargoFromString_ValidInput() {
        // Вызов тестируемого метода
        List<Integer> result = cargoReader.getIntegersFromString("1 2 3");

        // Проверка результатов
        assertThat(result).containsExactly(1, 2, 3);
    }

    @Test
    void testGetCargoFromList_ValidInput() {
        // Подготовка тестовых данных
//        List<List<Integer>> sizes = new ArrayList<>();
//        sizes.add(Arrays.asList(2, 3, 4));
//
//        // Вызов тестируемого метода
//        Cargo cargo = cargoReader.getCargoFromList(sizes);
//
//        // Проверка результатов
//        assertThat(cargo.getVolume()).isEqualTo(1);
//        assertThat(cargo.getSizes()).containsExactly(2, 3, 4);
    }
}