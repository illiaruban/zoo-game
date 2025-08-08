package zoogame.factories;

import zoogame.animals.*;

import java.util.Scanner;

public class AnimalFactory extends Factory{

    /**
     * creates a new animal and returns it to admin class
     * @param scanner
     * @return
     */
    public static Animal createNewAnimal(Scanner scanner) {
        Animal animal = null;
        String name = readString(scanner, "Enter name: ");
        double price = readPositiveDouble(scanner, "Price: ");
        SizeClass sizeClass = readSizeClass(scanner);
        int maxAmountInDomain = readPositiveInt(scanner, "Max amount of these animals in domain: ");
        int timesToFeedPerDay = readPositiveInt(scanner, "How many times per day animal must be fed: ");
        double fullIncome = readPositiveDouble(scanner, "Full income this animal brings: ");

        String animalType = readAnimalType(scanner);
        switch (animalType) {
            case "bird":
                animal = new Bird(name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, fullIncome);
                break;
            case "insect":
                animal = new Insect(name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, fullIncome);
                break;
            case "mammal":
                boolean isAggressive = readBoolean(scanner, "Is animal aggressive?[yes/no]: ");
                animal = new Mammal(name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, isAggressive, fullIncome);
                break;
            case "reptile":
                animal = new Reptile(name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, fullIncome);
                break;
            default:
                animal = null;
        }
        return animal;
    }


}
