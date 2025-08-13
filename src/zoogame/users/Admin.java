package zoogame.users;

import zoogame.Shop;

import java.util.InputMismatchException;
import java.util.Scanner;
import zoogame.Zoo;

import zoogame.InputReader;
import zoogame.animals.Animal;
import zoogame.animals.AnimalType;
import zoogame.animals.SizeClass;
import zoogame.exceptions.InvalidAnimalParameterException;
import zoogame.factories.AnimalFactory;

public class Admin {
    private Zoo admin_zoo = new Zoo();
    private Shop admin_shop = new Shop();

    public void manageGame() {
        System.out.println("Welcome as admin");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Enter command to execute. To get explanation of each command, type \"h\"");
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
                    //adds a food pack

                    break;
                    //then edit a food pack and so on...


            }
        }

    }

    public void addNewAnimal(Scanner scanner) {
        Animal animal;
        while(true) {
            try {
                String name = InputReader.readName(scanner);
                double price = InputReader.readPrice(scanner);
                double fullIncome = InputReader.readIncome(scanner, price);
                int timesToFeedPerDay = InputReader.readPositiveInteger(scanner, "feed");
                int maxAmountInDomain = InputReader.readPositiveInteger(scanner, "in_domain");
                AnimalType animalType = InputReader.readAnimalType(scanner);
                SizeClass sizeClass = InputReader.readSizeClass(scanner);

                animal = AnimalFactory.createNewAnimal(scanner, name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, fullIncome, animalType);
                break;
            }
            catch(InvalidAnimalParameterException | InputMismatchException e) {
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
        if (animal != null) {
            admin_shop.addAnimal(animal);
            System.out.println("Animal added successfully");
        }
    }

}
