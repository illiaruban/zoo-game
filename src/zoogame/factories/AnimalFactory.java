package zoogame.factories;

import zoogame.animals.*;

import java.util.Scanner;

import zoogame.factories.InputReader.*;

import static zoogame.factories.InputReader.*;


public class AnimalFactory {

    public static Animal createNewAnimal(Scanner scanner, String name, double price, SizeClass sizeClass, int maxAmountInDomain, int timesToFeedPerDay, double fullIncome, AnimalType animalType) {

        switch (animalType) {
            case AnimalType.BIRD:
                return new Bird(name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, fullIncome);
            case AnimalType.INSECT:
                return new Insect(name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, fullIncome);
            case AnimalType.MAMMAL:
                boolean isAggressive = readBoolean(scanner);
                return new Mammal(name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, isAggressive, fullIncome);
            case AnimalType.REPTILE:
                return new Reptile(name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, fullIncome);
            default:
                throw new IllegalArgumentException("Unknown animal type");
        }
    }
}
