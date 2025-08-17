package zoogame;

import zoogame.animals.AnimalType;
import zoogame.animals.SizeClass;
import zoogame.exceptions.InvalidAnimalParameterException;

import java.util.Scanner;

public class InputReader {
    public static double readPrice(Scanner scanner) throws InvalidAnimalParameterException{
        double value = scanner.nextDouble();
        scanner.nextLine();
        if (value < 100.0 || value > 10_000_000) {
            throw new InvalidAnimalParameterException("Invalid parameter: price\n>Only values in range [100, 1,000,000] allowed");
        }
        return value;
    }

    public static double readIncome(Scanner scanner, double price) throws InvalidAnimalParameterException{
        double value = scanner.nextDouble();
        scanner.nextLine();
        if (value < 100.0 || value > 1_000_000 && value > price) {
            throw new InvalidAnimalParameterException("Invalid parameter: income\n>Only values in range [100, 1,000,000] allowed\n" +
                    ">Income of animal cannot be higher that its price");
        }
        return value;
    }

    public static int readPositiveInteger(Scanner scanner, String whatFor) throws InvalidAnimalParameterException{
        int value = scanner.nextInt();
        scanner.nextLine();
        if (whatFor.equals("in_domain") && (value < 0 || value > 100)) {
            throw new InvalidAnimalParameterException("Invalid parameter: amount in domain\n>Only values in range [1, 100] allowed");
        }
        else if (whatFor.equals("feed") && (value < 2 || value > 3)) {
            throw new InvalidAnimalParameterException("Invalid parameter: times to feed\n>Only values in range [2, 3] allowed");
        }
        return value;
    }

    public static AnimalType readAnimalType(Scanner scanner) throws InvalidAnimalParameterException {
        String value = scanner.nextLine().toLowerCase();
        AnimalType animalType;
        switch(value) {
            case "bird":
                animalType = AnimalType.BIRD;
                break;
            case "insect":
                animalType = AnimalType.INSECT;
                break;
            case "mammal":
                animalType = AnimalType.MAMMAL;
                break;
            case "reptile":
                animalType = AnimalType.REPTILE;
                break;
            default:
                throw new InvalidAnimalParameterException("Invalid parameter: animal type\n>Only values ['bird', 'insect', 'mammal', 'reptile'] allowed");
        }
        return animalType;
    }

    public static String readName(Scanner scanner, String whatFor) throws InvalidAnimalParameterException{
        String name = scanner.nextLine();
        if (!name.matches("^[A-Za-z\s]+$")) {
            throw new InvalidAnimalParameterException("Invalid parameter: " + whatFor + "\n>Only letters and spaces allowed");
        }
        return name;
    }

    public static SizeClass readSizeClass(Scanner scanner) throws InvalidAnimalParameterException{
        String value = scanner.nextLine().toLowerCase();
        SizeClass sizeClass;
        switch(value) {
            case "tiny":
                sizeClass = SizeClass.TINY;
                break;
            case "average":
                sizeClass = SizeClass.AVERAGE;
                break;
            case "big":
                sizeClass = SizeClass.BIG;
                break;
            case "massive":
                sizeClass = SizeClass.MASSIVE;
                break;
            default:
                throw new InvalidAnimalParameterException("Invalid parameter: animal type\n>Only values ['tiny', 'average', 'big', 'massive'] allowed");
        }
        return sizeClass;
    }

    public static boolean readBoolean(Scanner scanner, String whatFor) throws InvalidAnimalParameterException {
        String value = scanner.nextLine();
        boolean boolValue;
        if (value.equalsIgnoreCase("yes")){
            boolValue = true;
        }
        else if (value.equalsIgnoreCase("no")) {
            boolValue = false;
        }
        else {
            throw new InvalidAnimalParameterException("Invalid parameter:" + whatFor + "\n>Only values ['yes','no'] allowed");
        }
        return boolValue;
    }

}