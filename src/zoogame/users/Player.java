package zoogame.users;
import zoogame.Shop;
import zoogame.Zoo;

import java.util.Scanner;

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
                //morning phase: buy food/buy domains/sell animals/feed animals

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
                    "5 - see the list of animals to be fed for morning/day/evening\n" +
                    "6 - print the current amount of food for every type of animal\n" +
                    "0 - exit the game");
            System.out.print("> ");
            int input_command = scanner.nextInt();
            switch(input_command) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    //TODO: implement function in zoo that gives names of an animals which are in zoo
                    //TODO: implement delete function by index
                    break;
                case 5:
                    printAnimalsToBeFed();
                    break;
                case 6:
                    printFoodStorage();
                    break;
                default:
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

    //-----------------------------------------------------------------[DAY PHASE]

    public void manageDay(Scanner scanner) {

    }
    //-----------------------------------------------------------------[EVENING PHASE]

    public void manageEvening(Scanner scanner) {

    }
    //-----------------------------------------------------------------[EVENING PHASE]
    //-----------------------------------------------------------------[EVENING PHASE]
    //-----------------------------------------------------------------[EVENING PHASE]
    //-----------------------------------------------------------------[EVENING PHASE]


}
