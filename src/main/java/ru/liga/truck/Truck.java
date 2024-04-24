package ru.liga.truck;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Getter
@Log4j2
public class Truck {
    private final List<List<Integer>> fullness;
    private final int height;
    private final int width;

    /**
     * Конструктор создает грузовик с заданными размерами и инициализирует пустые места.
     *
     * @param height высота грузовика
     * @param width  ширина грузовика
     * @throws IllegalArgumentException если высота или ширина меньше, или равна нулю
     */
    public Truck(int height, int width) {
        this(new ArrayList<>(), height, width);
    }

    public Truck(List<List<Integer>> fullness, int height, int width) {
        log.debug("new Truck(fullness: {})", fullness);
        String errorMessage = "";

        if (height <= 0) {
            errorMessage += "Неверное значение высоты: " + height + ". Высота должна быть больше нуля.\n";
        }
        if (width <= 0) {
            errorMessage += "Неверное значение ширины: " + width + ". Ширина должна быть больше нуля.\n";
        }
        if (fullness == null) {
            errorMessage = "Неверное значение заполненности кузова: " + fullness + ". Заполненность не может быть пустой.";
        }

        if (!errorMessage.isEmpty()) {
            log.error("Truck initialization error: {}", errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        if (fullness.isEmpty()) {
            for (int i = 0; i < height; i++) {
                fullness.add(i, new ArrayList<>());
            }
        }

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
        long freeHeight = height - fullness.stream().filter(f -> !f.isEmpty()).count();
        log.trace("getFreeHeight: {}", freeHeight);
        return (int) freeHeight;
    }

    /**
     * Возвращает строковое представление грузовика с размещенными грузами.
     *
     * @return строковое представление грузовика
     */
    @Override
    public String toString() {
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
        return stringBuilder.toString();
    }

    /**
     * Занимает указанное место в грузовике заданным объемом груза.
     *
     * @param vertical    вертикальная координата места
     * @param horizontal  горизонтальная координата места
     * @param cargoVolume объем груза
     */
    public void occupyPlace(int vertical, int horizontal, int cargoVolume) {
        int invertedVertical = (height - 1 - vertical) % (height);
        log.trace("occupyPlace - vertical: {}, horizontal: {}, cargoVolume: {}, invertedVertical: {}, height: {}", vertical, horizontal, cargoVolume, invertedVertical, height);
        if (vertical >= height || horizontal >= width) {
            throw new IllegalArgumentException("Невозможно занять это место. Координата по вертикали: " + vertical + ", по горизонтали: " + horizontal);
        }

        if (fullness.size() <= invertedVertical) {
            fullness.add(invertedVertical, new ArrayList<>());
        }
        fullness.get(invertedVertical).add(horizontal, cargoVolume);
    }
}