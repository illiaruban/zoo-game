package zoogame.users;

import zoogame.Shop;
import java.util.Scanner;
import zoogame.Zoo;
import zoogame.factories.AnimalFactory;
import zoogame.animals.Animal;

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
                    Animal animal = AnimalFactory.createNewAnimal(scanner);
                    admin_shop.addAnimal(animal);
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

}
