package zoogame.factories;

import zoogame.animals.SizeClass;

import java.util.Scanner;

public class InputReader {
    public static String readAnimalType(Scanner scanner) {
        String animalType;
        while (true) {
            System.out.print("Type of animal: ");
            animalType = scanner.nextLine().toLowerCase();
            switch (animalType) {
                case "bird":
                case "insect":
                case "mammal":
                case "reptile":
                    return animalType;
                default:
                    System.out.println("Invalid animal type. Please enter one of the following: bird, insect, mammal, or reptile.");
            }
        }
    }
    public static String readString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static double readPositiveDouble(Scanner scanner, String prompt) {
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value > 0) {
                break;
            }
            System.out.println("Please enter a positive value.");
        }
        scanner.nextLine(); // Consume the newline
        return value;
    }

    public static int readPositiveInt(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextInt();
            if (value > 0) {
                break;
            }
            System.out.println("Please enter a positive integer.");
        }
        scanner.nextLine(); // Consume the newline
        return value;
    }

    public static boolean readBoolean(Scanner scanner, String prompt) {
        boolean result = false;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().toLowerCase();
            if ("yes".equals(input)) {
                result = true;
                break;
            } else if ("no".equals(input)) {
                result = false;
                break;
            } else {
                System.out.println("Please enter 'yes' or 'no'.");
            }
        }
        return result;
    }

    public static SizeClass readSizeClass(Scanner scanner) {
        String sizeInput;
        while (true) {
            System.out.print("Size class: ");
            sizeInput = scanner.nextLine().toLowerCase();
            switch (sizeInput) {
                case "tiny":
                    return SizeClass.TINY;
                case "average":
                    return SizeClass.AVERAGE;
                case "big":
                    return SizeClass.BIG;
                case "massive":
                    return SizeClass.MASSIVE;
                default:
                    System.out.println("Incorrect parameter entered: size class. Please enter a valid size class (tiny, average, big, or massive).");
            }
        }
    }
}