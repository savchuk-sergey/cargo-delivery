package ru.liga.Truck.model;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import ru.liga.Cargo.model.Cargo;
import ru.liga.Cargo.model.PlacedCargo;

import java.util.*;
import java.util.stream.Collectors;

@Log4j2
public class Truck {
    @Getter
    private final List<List<Integer>> fullness;
    private final List<Cargo> cargos;
    private final int height;
    private final int width;
//    private final PlacedCargo
    /**
     * Конструктор создает грузовик с заданными размерами и инициализирует пустые места.
     *
     * @param height высота грузовика
     * @param width  ширина грузовика
     * @throws IllegalArgumentException если высота или ширина меньше или равна нулю
     */
    public Truck(int height, int width) {
        this(new ArrayList<>(), new ArrayList<>(), height, width);
    }

    public Truck(List<Cargo> cargos, List<List<Integer>> fullness, int height, int width) {
        log.debug("new Truck(fullness: {})", fullness);
        String errorMessage = "";

        if (height <= 0) {
            errorMessage += "Неверное значение высоты: " + height + ". Высота должна быть больше нуля.\n";
        }
        if (width <= 0) {
            errorMessage += "Неверное значение ширины: " + width + ". Ширина должна быть больше нуля.\n";
        }
        if (cargos == null) {
            errorMessage = "Неверное значение посылок: " + cargos + ". Посылки не могут быть пустыми.\n";
        }
        if (fullness == null) {
            errorMessage = "Неверное значение заполненности кузова: " + fullness + ". Заполненность не может быть пустой.";
        }

        if (!errorMessage.isEmpty()) {
            log.error("Truck initialization error: {}", errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        this.cargos = cargos;
        this.fullness = fullness;
        this.height = height;
        this.width = width;
    }

    /**
     * Возвращает количество свободного места в грузовике.
     *
     * @return количество свободного места
     */
    public int getFreeHeight() {
        log.trace("getFreeHeight");
        return height - fullness.size();
    }

    /**
     * Возвращает строковое представление грузовика с размещенными грузами.
     *
     * @return строковое представление грузовика
     */
    @Override
    public String toString() {
        log.trace("toString");
        StringBuilder truck = new StringBuilder();
        for (int i = fullness.size() - 1; i >= 0; i--) {
            String line = fullness.get(i).stream()
                    .map(p -> p != null ? p.toString() : "")
                    .collect(Collectors.joining());
            truck.append(line);
            truck.append("|");
        }
        return truck.deleteCharAt(truck.length() - 1).toString();
    }

    /**
     * Выводит на экран текущее состояние грузовика с размещенными грузами.
     */
    public void print() {
        log.trace("print");
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < height; i++) {
            stringBuilder.append("+");
            for (int j = 0; j < width; j++) {
                if (i >= fullness.size() || fullness.get((fullness.size() + i) % fullness.size()).isEmpty()) {
                    stringBuilder.append(" ".repeat(width));
                    break;
                }

                if (j >= fullness.get(i).size() || fullness.get(i).get(j) == null) {
                    stringBuilder.append(" ");
                } else {
                    stringBuilder.append(fullness.get(i).get(j));
                }

            }
            stringBuilder.append("+\n");
        }
        stringBuilder.append("+").append("+".repeat(width)).append("+\n");
        System.out.println(stringBuilder);
    }

    /**
     * Занимает указанное место в грузовике заданным объемом груза.
     *
     * @param vertical    вертикальная координата места
     * @param horizontal  горизонтальная координата места
     * @param cargoVolume объем груза
     */
    public void occupyPlace(int vertical, int horizontal, int cargoVolume) {
        int invertedVertical = ((height - 1) - horizontal) % height;
        log.trace("occupyPlace - vertical: {}, horizontal: {}, cargoVolume: {}, invertedVertical: {}", vertical, horizontal, cargoVolume, invertedVertical);
        if(vertical >= height || horizontal >= width) {
            throw new IllegalArgumentException("Невозможно занять это место. Координата по вертикали: " + vertical + ", по горизонтали: " + horizontal);
        }

        if(fullness.size() <= invertedVertical) {
            fullness.add(invertedVertical, new ArrayList<>());
        }
        log.debug("occupyPlace - vertical: {}, horizontal: {}, cargoVolume: {}, invertedVertical: {}", vertical, horizontal, cargoVolume, invertedVertical);
        fullness.get(invertedVertical).add(horizontal, cargoVolume);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<Cargo> getCargoList() {
        Map<Integer, List<List<Integer>>> cargoMap = new HashMap<>();
        Map<Integer, List<List<Integer>>> test = new HashMap<>();

        for (List<Integer> fullnessLine : fullness) {
            for (Integer fullnessVolume : fullnessLine) {
                if (!cargoMap.containsKey(fullnessVolume)) {
                    cargoMap.put(fullnessVolume, new ArrayList<>());
                }

                if (fullnessVolume == 1) {
                    cargoMap.get(1).add(List.of(1));
                    continue;
                }

                long area = cargoMap.get(fullnessVolume).stream()
                        .mapToLong(Collection::size)
                        .sum();

                if (area < fullnessVolume) {
                    List<List<Integer>> cargoLine = cargoMap.get(fullnessVolume);

                    cargoMap.get(fullnessVolume).add(fullnessLine.stream()
                            .filter(line -> Objects.equals(line, fullnessVolume))
                            .toList());
                }

            }
        }

        if (!test.containsKey(0)) {
            test.put(0, new ArrayList<>());
        }

        List<List<Integer>> cargoLine = fullness.stream()
                .map(line -> line.stream().filter(place -> place == 3).toList()).toList();


        test.put(0, cargoLine);
        log.debug("test: {}", test);
        List<Cargo> cargoList = new ArrayList<>();

        // Создаем объекты Cargo для каждого набора данных
        for (Map.Entry<Integer, List<List<Integer>>> entry : cargoMap.entrySet()) {
            int key = entry.getKey();
            if (key == 1) {
                for(int i = 0; i < entry.getValue().size(); i++) {
                    cargoList.add(new Cargo(key, List.of(List.of(1))));
                }
                continue;
            }

            cargoList.add(new Cargo(key, entry.getValue()));
        }

        return cargoList;
    }


    public List<Cargo> getCargoListTest() {
        Map<Integer, List<List<Integer>>> cargoMap = new HashMap<>();
        List<Cargo> cargoList = new ArrayList<>();

        for (List<Integer> fullnessLine : fullness) {
            for (Integer fullnessVolume : fullnessLine) {
                if (!cargoMap.containsKey(fullnessVolume)) {
                    cargoMap.put(fullnessVolume, new ArrayList<>());
                }

                if (fullnessVolume == 1) {
                    cargoMap.get(1).add(List.of(1));
                    continue;
                }

                long area = cargoMap.get(fullnessVolume).stream()
                        .mapToLong(Collection::size)
                        .sum();

                if (area < fullnessVolume) {
                    List<List<Integer>> cargoLine = cargoMap.get(fullnessVolume);

                    cargoMap.get(fullnessVolume).add(fullnessLine.stream()
                            .filter(line -> Objects.equals(line, fullnessVolume))
                            .toList());
                }

            }
        }

        // Создаем объекты Cargo для каждого набора данных
        for (Map.Entry<Integer, List<List<Integer>>> entry : cargoMap.entrySet()) {
            int key = entry.getKey();
            if (key == 1) {
                for(int i = 0; i < entry.getValue().size(); i++) {
                    cargoList.add(new Cargo(key, List.of(List.of(1))));
                }
                continue;
            }

            cargoList.add(new Cargo(key, entry.getValue()));
        }

        return cargoList;
    }
}