package zoogame.factories;

import zoogame.animals.AnimalType;
import zoogame.animals.SizeClass;

import java.util.Scanner;

public class InputReader {
    public static AnimalType readAnimalType(Scanner scanner) {
        String animalType;
        System.out.print("Type of animal: ");
        animalType = scanner.nextLine().toLowerCase();
        switch (animalType) {
            case "bird": return AnimalType.BIRD;
            case "insect": return AnimalType.INSECT;
            case "mammal": return AnimalType.MAMMAL;
            case "reptile": return AnimalType.REPTILE;
            default:
                System.out.println("Invalid animal type. Please enter one of the following: bird, insect, mammal, or reptile.");
        }

    }

    //TODO: rework input reader class so it throws exception to admin function
    public static String readString(Scanner scanner) {
        return scanner.nextLine();
    }

    public static double readPositiveDouble(Scanner scanner) {
        double value;
        value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    public static int readPositiveInt(Scanner scanner) {
        int value;
            value = scanner.nextInt();
            System.out.println("Please enter a positive integer.");
        scanner.nextLine();
        return value;
    }

    public static boolean readBoolean(Scanner scanner) {
        boolean result = false;
            String input = scanner.nextLine().toLowerCase();
            if ("yes".equals(input)) {
                result = true;
            } else if ("no".equals(input)) {
                result = false;
            } else {
                System.out.println("Please enter 'yes' or 'no'.");
            }
        return result;
        }


    public static SizeClass readSizeClass(Scanner scanner) {
        String sizeInput;
        sizeInput = scanner.nextLine().toLowerCase();
        switch (sizeInput) {
                case "tiny":
                    return SizeClass.TINY;
                    break;
                case "average":
                    return SizeClass.AVERAGE;
                    break;
                case "big":
                    return SizeClass.BIG;
                    break;
                case "massive":
                    return SizeClass.MASSIVE;
                    break;
                default:
                    System.out.println("Incorrect parameter entered: size class. Please enter a valid size class (tiny, average, big, or massive).");
            }
    }
}