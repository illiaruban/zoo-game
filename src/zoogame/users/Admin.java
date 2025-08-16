package zoogame.users;

import zoogame.Shop;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import zoogame.InputReader;
import zoogame.animals.Animal;
import zoogame.animals.AnimalType;
import zoogame.animals.SizeClass;
import zoogame.exceptions.InvalidAnimalParameterException;
import zoogame.factories.AnimalFactory;

public class Admin {
    private Shop admin_shop = new Shop();

    public void manageGame() {
        System.out.println("Welcome as admin");
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        while(true) {
            System.out.println("Enter command to execute");
            System.out.print("> ");
            int command = scanner.nextInt();
            switch(command) {
                case 1:
                    //adds an animal
                    addNewAnimal(scanner);
                    break;
                case 2:
                    //delete an animal from the shop

                    break;
                case 3:
                    //prints the animals list of the shop
                    System.out.println("Animals added in the shop:");
                    printAnimalList();
                    break;
                    //then edit a food pack and so on...

            }
        }

    }

    public void addNewAnimal(Scanner scanner) {
        Animal animal;
        while(true) {
            try {
                System.out.print("Enter name: ");
                String name = InputReader.readName(scanner);
                System.out.print("Enter price: ");
                double price = InputReader.readPrice(scanner);
                System.out.print("Enter full income: ");
                double fullIncome = InputReader.readIncome(scanner, price);
                System.out.print("Enter times animal must be fed per pay: ");
                int timesToFeedPerDay = InputReader.readPositiveInteger(scanner, "feed");
                System.out.print("Enter maximum amount of these animals in one domain: ");
                int maxAmountInDomain = InputReader.readPositiveInteger(scanner, "in_domain");
                System.out.print("Enter animal type: ");
                AnimalType animalType = InputReader.readAnimalType(scanner);
                System.out.print("Enter size class: ");
                SizeClass sizeClass = InputReader.readSizeClass(scanner);

                animal = AnimalFactory.createNewAnimal(scanner, name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, fullIncome, animalType);
                break;
            }
            catch(InvalidAnimalParameterException | InputMismatchException e) {
                System.out.println(e.getMessage());
                while(true) {
                    System.out.print("Do you want to retry?[y/n]: ");
                    String input = scanner.nextLine();
                    if (input.equals("y")) break;
                    else if (input.equals("n")) return;
                    else System.out.println("Input incorrect, try again.");
                }
            }
        }
        if (animal != null) {
            admin_shop.addAnimal(animal);
            System.out.println("Animal added successfully");
        }
    }

    public void deleteAnimal(Scanner scanner) {
        printAnimalList();
        int animalsAmount = admin_shop.getAnimalsAmount();
        if (animalsAmount == 0) {
            System.out.println("There are no animals in the shop yet.\n");
            return;
        }
        System.out.print("Enter number of animal in the list to delete it: ");
        while (true) {
            int index = scanner.nextInt() - 1;
            scanner.nextLine();
            if (index < 0 || index >= animalsAmount) {
                while(true) {
                    System.out.println("There is no animal under this number.");
                    System.out.print("Do you want to retry?[y/n]: ");
                    String input = scanner.nextLine();
                    if (input.equals("y")) break;
                    else if (input.equals("n")) return;
                    else System.out.println("Input incorrect, try again.");
                }
            }
            admin_shop.deleteAnimal(index);
            System.out.println("Animal deleted successfully.\n");
            break;
        }
    }

    public void printAnimalList() {
        admin_shop.printAnimals();
    }

}
