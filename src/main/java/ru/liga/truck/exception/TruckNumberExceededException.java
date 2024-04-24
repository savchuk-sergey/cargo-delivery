package ru.liga.truck.exception;

public class TruckNumberExceededException extends Exception {

    public TruckNumberExceededException(int maxTruckNumber, int currentNumber) {
        super("The maximum truck number has been exceeded. Max number is: " + maxTruckNumber + ", current number: " + currentNumber);
    }

    public TruckNumberExceededException(String message) {
        super(message);
    }
}