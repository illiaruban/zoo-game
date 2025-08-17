package zoogame.users;

import zoogame.Shop;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import zoogame.InputReader;
import zoogame.animals.Animal;
import zoogame.animals.AnimalType;
import zoogame.animals.SizeClass;
import zoogame.domains.Domain;
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
                    deleteAnimal(scanner);
                    break;
                case 3:
                    //prints the animals list of the shop
                    System.out.println("Animals added in the shop:");
                    printAnimalList();
                    break;
                    //then edit a food pack and so on...
                case 4:
                    //print all domains
                    System.out.println("Domains in the shop:");
                    printDomainList();
                    break;
                case 5:
                    //edit price in domain
                    editDomainPrice(scanner);
                    break;
                case 6:
                    //edit animal name, price, fullIncome


            }
        }

    }
    //------------------------------------------------------------------[ADD NEW ANIMAL TO THE SHOP]
    public void addNewAnimal(Scanner scanner) {
        while (true) {
            try {
                Animal animal = collectAnimalData(scanner);
                admin_shop.addAnimal(animal);
                System.out.println("Animal added successfully");
                return;
            } catch (InvalidAnimalParameterException | InputMismatchException e) {
                System.out.println(e.getMessage());
                if (!wantsToRetry(scanner)) {
                    return;
                }
            }
        }
    }

    /**
     * Collects all fields required to create Animal.
     */
    private Animal collectAnimalData(Scanner scanner) throws InvalidAnimalParameterException {
        System.out.print("Enter name: ");
        String name = InputReader.readName(scanner, "animal name");

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

        return AnimalFactory.createNewAnimal(scanner, name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, fullIncome, animalType);
    }

    /**
     * Asks user if they want to retry after exception.
     */
    private boolean wantsToRetry(Scanner scanner) {
        while (true) {
            System.out.print("Do you want to retry?[y/n]: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("y")) return true;
            if (input.equalsIgnoreCase("n")) return false;
            System.out.println("Input incorrect, try again.");
        }
    }

    //------------------------------------------------------------------[DELETE ANIMAL FROM SHOP]
    public void deleteAnimal(Scanner scanner) {
        printAnimalList();
        int amount = admin_shop.getAnimalsAmount();
        if (amount == 0) {
            System.out.println("There are no animals in the shop yet.\n");
            return;
        }
        while (true) {
            System.out.print("Enter number of animal in the list to delete it: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine();

            if (index < 0 || index >= amount) {
                System.out.println("Invalid index.");
                if (!wantsToRetry(scanner)) return;
                continue;
            }
            Animal animal = admin_shop.getAvailableAnimals().get(index);

            boolean success = deleteAnimalByIndex(animal);
            if (success) {
                System.out.println("Animal deleted successfully.\n");
                return;
            } else {
                System.out.println("There is no animal under this number.");
                if (!wantsToRetry(scanner)) {
                    return;
                }
            }
        }
    }

    public boolean deleteAnimalByIndex(Animal animal) {
        if (animal == null) {
            return false;
        }
        admin_shop.deleteAnimal(animal);
        return true;
    }

    //------------------------------------------------------------------[PRINT]
    public void printAnimalList() {
        admin_shop.printAnimals();
    }

    public void printDomainList() {
        admin_shop.printDomains();
    }
    //------------------------------------------------------------------[CHANGE DOMAIN PRICE]
    public void editDomainPrice(Scanner scanner) {
        printDomainList();
        int amount = admin_shop.getDomainsAmount();
        if (amount == 0) {
            System.out.println("Operation is not functioning properly: there is no domains.");
            return;
        }
        while (true) {
            System.out.print("Enter number of domain to change its price: ");
            int selectedIndex = scanner.nextInt() - 1;
            scanner.nextLine();

            if (selectedIndex < 0 || selectedIndex >= amount) {
                System.out.println("Invalid index.");
                if (!wantsToRetry(scanner)) return;
                continue;
            }

            Domain domain = admin_shop.getAvailableDomains().get(selectedIndex);

            System.out.print("Enter new price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            boolean success = changeDomainPrice(domain, price);
            if (success) {
                System.out.println("Price edited successfully!");
                return;
            } else {
                System.out.println("Could not change price.");
            }
        }
    }

    public boolean changeDomainPrice(Domain domain, double price) {
        if (domain == null) {
            return false;
        }
        else {
            domain.setPrice(price);
            return true;
        }
    }


}
