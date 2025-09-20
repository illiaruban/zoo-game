package zoogame.users;
import zoogame.AnimalFoodPack;
import zoogame.Shop;
import zoogame.Zoo;
import zoogame.domains.Domain;
import zoogame.exceptions.LowBalanceException;
import zoogame.exceptions.NoAnimalFoundException;
import zoogame.exceptions.NoDomainFoundException;

import java.util.Scanner;

/**
 * Describes player's functionality during three phases of game.
 * Planned:
 * - upon entering player account download the admin version of the shop
 * (at first time while launching and if the changes from admin account are detected(update))
 * - upon entering the first time the zoo and shop will be downloaded from database
 */
public class Player {

    Zoo player_zoo = new Zoo();
    Shop player_shop = new Shop();
    public Player(Zoo other_zoo, Shop other_shop) {
        this.player_zoo = new Zoo(other_zoo);
        this.player_shop = new Shop(other_shop);
    }
    Scanner scanner = new Scanner(System.in);
    boolean morningTime = true;
    boolean dayTime = false;
    boolean eveningTime = false;

    public Player() {}

    public void manageGame() {
        System.out.println("Welcome, Player!");
        while (true) {
            System.out.println("Would you like to start a game?\n" +
                    "1 - yes\n" +
                    "0 - no");
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.equals("1")) {
                System.out.println("You started the game!");
                break;
            }
            else if (input.equals("0")) {
                System.out.println("You exit the game. Come back soon!");
                return;
            }
            else {
                System.out.println("Input incorrect. Please enter '1' to start the game OR '0' to stop playing");
            }
        }

        //in the zoo there are 3 phases: morning, day and evening. In the morning you can buy only food and domains and sell animals,
        // on evening you can buy animals,food and domains as well as sell them

        while(true) {
            if (morningTime) {
                System.out.println("----------------------------------------------------------------");
                //morning phase: buy food/buy domains/sell animals/feed animals
                manageMorning(scanner);
            }
            if (dayTime) {
                System.out.println("----------------------------------------------------------------");
                manageDay(scanner);
            }
            if (eveningTime) {
                System.out.println("----------------------------------------------------------------");
                manageEvening(scanner);
            }
            while (true) {
                System.out.print("Do you want to start next day?[y/n]: ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("y")) break;
                if (input.equalsIgnoreCase("n")) return;
                System.out.println("Input incorrect, try again.");
            }
        }


    }
    //-----------------------------------------------------------------[MORNING PHASE]
    public void manageMorning(Scanner scanner) {
        while (true) {
            System.out.println("Please choose an operation to perform:\n" +
                    "1 - buy food\n" +
                    "2 - buy domain\n" +
                    "3 - feed animals\n" +
                    "4 - sell an animal\n" +
                    "5 - see the list of animals to be fed during all phases\n" +
                    "6 - print the current amount of food for every type of animal\n" +
                    "7 - see all domains and animals in it" +
                    "0 - proceed to day phase");
            System.out.print("> ");
            int input_command = scanner.nextInt();
            scanner.nextLine();
            switch(input_command) {
                case 1:
                    buyFood(scanner);
                    break;
                case 2:
                    buyDomain(scanner);
                    break;
                case 3:
                    feedAnimals("morning");
                    break;
                case 4:
                    deleteAnimal(scanner);
                    break;
                case 5:
                    printAnimalsToBeFed();
                    break;
                case 6:
                    printFoodStorage();
                    break;
                case 7:
                    printZooDomains();
                    break;
                case 0:
                    System.out.println("Morning phase is over. Day phase gonna keep workers busy!");
                    morningTime = false;
                    dayTime = true;
                    return;
                default:
                    System.out.println("No command under that number.");
                    break;
            }
        }
    }

    //-----------------------------------------------------------------[DAY PHASE]

    public void manageDay(Scanner scanner) {
        //day function: sell animals, feed animals, print unfed animals
        while(true) {
            System.out.println("Please choose an operation to perform:\n" +
                    "1 - feed animals\n" +
                    "2 - sell an animal\n" +
                    "3 - see the list of animals to be fed during all phases\n" +
                    "4 - print the current amount of food for every type of animal\n" +
                    "5 - see all domains and animals in it\n" +
                    "0 - proceed to evening phase");
            System.out.println("> ");
            int input_command = scanner.nextInt();
            scanner.nextLine();
            switch(input_command) {
                case 1:
                    feedAnimals("day");
                    break;
                case 2:
                    deleteAnimal(scanner);
                    break;
                case 3:
                    printAnimalsToBeFed();
                    break;
                case 4:
                    printFoodStorage();
                    break;
                case 5:
                    printZooDomains();
                    break;
                case 0:
                    System.out.println("Day phase is over. Evening phase is coming!");
                    return;
                default:
                    System.out.println("No command under that number.");
                    break;
            }
        }
    }

    //-----------------------------------------------------------------[EVENING PHASE]

    public void manageEvening(Scanner scanner) {
        while(true) {
            System.out.println("Please choose an operation to perform:\n" +
                    "1 - feed animals\n" +
                    "2 - buy an animal\n" +
                    "3 - buy a domain\n" +
                    "4 - sell an animal\n" +
                    "5 - sell a domain\n" +
                    "6 - buy food\n" +
                    "7 - see the list of animals to be fed during all phases\n" +
                    "8 - print the current amount of food for every type of animal\n" +
                    "9 - see all domains and animals in it\n" +
                    "0 - proceed to evening phase");
            System.out.println("> ");
            int input_command = scanner.nextInt();
            scanner.nextLine();
            switch(input_command) {
                case 1:
                    feedAnimals("evening");
                    break;
                case 2:
                    //TODO: write buy animal as player function
                    break;
                case 3:
                    buyDomain(scanner);
                    break;
                case 4:
                    //TODO: write sell animal function
                    break;
                case 5:
                    //TODO: write sell domain function
                    break;
                case 6:
                    buyFood(scanner);
                    break;
                case 7:
                    printAnimalsToBeFed();
                    break;
                case 8:
                    printFoodStorage();
                    break;
                case 9:
                    printZooDomains();
                    break;
                case 0:
                    System.out.println("Evening phase is over. Good work, boss!");
                    return;
                default:
                    System.out.println("No command under that number.");
                    break;
            }
        }
    }


    //-----------------------------------------------------------------[PRINT SHOP]
    public void printShopFoodPacks() {
        player_shop.printFoodPacks();
    }
    public void printShopAnimals() {
        player_shop.printAnimals();
    }

    public void printShopDomains() {
        player_shop.printDomains();
    }

    //-----------------------------------------------------------------[PRINT ZOO]

    public void printZooDomains() {
        player_zoo.printDomains();
    }

    //-----------------------------------------------------------------[PRINT THE ANIMALS TO BE FED]

    public void printAnimalsToBeFed() {
        player_zoo.printListUnfedAnimals();
    }

    //-----------------------------------------------------------------[PRINT THE FOOD STORAGE]

    public void printFoodStorage() {
        player_zoo.printFoodStorage();
    }

    //-----------------------------------------------------------------[DELETE AN ANIMAL]
    public void deleteAnimal(Scanner scanner) {
        System.out.println("List of your domains: ");
        printZooDomains();
        while(true) {
            System.out.print("Enter the number of domain you want to sell an animal from: ");
            int domainIndex = scanner.nextInt() - 1;
            scanner.nextLine();
            try {
                player_zoo.sellAnimal(domainIndex);
                break;
            }
            catch(NoAnimalFoundException | NoDomainFoundException e) {
                System.out.println(e.getMessage());
                if (!wantsToRetry(scanner)) {
                    return;
                }
            }
        }
    }

    //-----------------------------------------------------------------[BUY FOOD]

    public void buyFood(Scanner scanner) {
        System.out.println("List of the food packs");
        player_shop.printFoodPacks();
        while(true) {
            System.out.print("Enter the number of the pack you want to purchase: ");
            int number = scanner.nextInt();
            scanner.nextLine();
            try {
                AnimalFoodPack foodPack = new AnimalFoodPack(player_shop.getAnimalFoodPacks().get(number));
                player_zoo.buyFoodPack(foodPack);
                break;
            }
            catch(LowBalanceException | IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                if (!wantsToRetry(scanner)) {
                    return;
                }
            }
        }


    }
    //-----------------------------------------------------------------[BUY DOMAIN]

    public void buyDomain(Scanner scanner) {
        System.out.println("List of domains: ");
        player_shop.printDomains();
        while(true) {
            System.out.print("Enter the number of the domain you want to purchase: ");
            int number = scanner.nextInt();
            scanner.nextLine();
            try {
                Domain domain = new Domain(player_shop.getAvailableDomains().get(number));
                player_zoo.buyDomain(domain);
                break;
            }
            catch(LowBalanceException | IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                if (!wantsToRetry(scanner)) {
                    return;
                }
            }
        }
    }
    //-----------------------------------------------------------------[RETRY]
    public void feedAnimals(String dayTime) {
        player_zoo.feedAnimals(dayTime);
    }

    //-----------------------------------------------------------------[RETRY]

    private boolean wantsToRetry(Scanner scanner) {
        while (true) {
            System.out.print("Do you want to retry?[y/n]: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("y")) return true;
            if (input.equalsIgnoreCase("n")) return false;
            System.out.println("Input incorrect, try again.");
        }
    }

}
