package zoogame.factories;

import zoogame.animals.*;

import java.util.Scanner;

import zoogame.factories.InputReader.*;

import static zoogame.factories.InputReader.*;


public class AnimalFactory {

    public static Animal createNewAnimal(Scanner scanner) {
        String name = readString(scanner, "Enter name: ");
        double price = readPositiveDouble(scanner, "Price: ");
        SizeClass sizeClass = readSizeClass(scanner);
        int maxAmountInDomain = readPositiveInt(scanner, "Max amount of these animals in domain: ");
        int timesToFeedPerDay = readPositiveInt(scanner, "How many times per day animal must be fed: ");
        double fullIncome = readPositiveDouble(scanner, "Full income this animal brings: ");

        String animalType = readAnimalType(scanner);

        switch (animalType) {
            case "bird":
                return new Bird(name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, fullIncome);
            case "insect":
                return new Insect(name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, fullIncome);
            case "mammal":
                boolean isAggressive = readBoolean(scanner, "Is animal aggressive?[yes/no]: ");
                return new Mammal(name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, isAggressive, fullIncome);
            case "reptile":
                return new Reptile(name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, fullIncome);
            default:
                throw new IllegalArgumentException("Unknown animal type");
        }
    }
}
