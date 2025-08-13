package zoogame.factories;

import zoogame.InputReader;
import zoogame.animals.*;
import zoogame.exceptions.InvalidAnimalParameterException;

import java.util.Scanner;


public class AnimalFactory {

    public static Animal createNewAnimal(Scanner scanner, String name, double price, SizeClass sizeClass, int maxAmountInDomain, int timesToFeedPerDay, double fullIncome, AnimalType animalType) throws InvalidAnimalParameterException {
        switch (animalType) {
            case AnimalType.BIRD -> {
                return new Bird(name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, fullIncome);
            }
            case AnimalType.INSECT -> {
                return new Insect(name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, fullIncome);
            }
            case AnimalType.REPTILE -> {
                return new Reptile(name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, fullIncome);
            }
            case AnimalType.MAMMAL -> {
                while(true) {
                    try {
                        boolean isAggressive = InputReader.readBoolean(scanner, "aggressiveness");
                        return new Mammal(name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, isAggressive, fullIncome);
                    }
                    catch(InvalidAnimalParameterException e) {
                        System.out.println(e.getMessage());
                        while(true) {
                            System.out.print("Do you want to retry?[y/n]: ");
                            String input = scanner.nextLine();
                            if (input.equals("y")) continue;
                            else if (input.equals("n")) break;
                            else System.out.println("Input incorrect, try again.");
                        }
                    }
                }

            }
            default -> {
                return null;
            }
        }
    }
}
