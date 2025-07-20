package zoogame;

import zoogame.animals.Animal;
import zoogame.animals.AnimalType;
import zoogame.animals.SizeClass;

import java.util.ArrayList;
import java.util.HashMap;

public class Zoo {
    private ArrayList<Domain> domains;
    private boolean isOpened = false;
    private double balance;
    private HashMap<AnimalType, Integer> foodStorage;


    public Zoo() {
        balance = 1000;
        foodStorage = new HashMap<>();
    };

    public void setOpened(boolean isOpened) {
        this.isOpened = isOpened;
    }
    public boolean isOpened() {
        return isOpened;
    }

    public void setBalance(double balance){
        if (balance > 0) {
            this.balance = balance;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void openDomain(Domain domain) {
        domains.add(domain);
    }

    public void closeDomain(Domain domain) { domains.remove(domain); }

    public ArrayList<Domain> getAllDomains() {
        return domains;
    }

    public HashMap<AnimalType, Integer> getFoodStorage() {return foodStorage;}


    //TODO: make conditions in the functions, define exceptions
    public void buyFoodPack(AnimalFoodPack foodPack) {
        balance -= foodPack.getPrice();
        for (AnimalType type: foodPack.content.keySet()) {
            int currentFoodAmount = foodStorage.get(type);
            foodStorage.put(type, currentFoodAmount + foodPack.content.get(type));
        }
    }
    public void buyDomain(Domain domain) {
        balance -= domain.getPrice();
        Domain addedDomain = new Domain(domain);
        domains.add(addedDomain);
    }

    public void sellDomain(Domain domain) {
        balance += domain.getSizeClass() == SizeClass.MASSIVE ? domain.getPrice() / 2 : domain.getPrice() / 1.5;
        domains.remove(domain);
    }

    public void buyAnimal(Animal animal) {
        balance -= animal.getPrice();
        for (Domain domain: domains) {
            if (domain.getNameOfDomain().contains(animal.getName()) || domain.getNameOfDomain().equals("Empty domain")) {
                domain.addAnimal(animal);
                break;
            }
        }
    }

    public void sellAnimal(Animal animal) {
        balance += animal.getAnimalType() == AnimalType.INSECT ? animal.getPrice() / 2 : animal.getAnimalType() == AnimalType.MAMMAL ? animal.getPrice() / 1.75 : animal.getPrice() / 1.5;
        for(Domain domain: domains) {
            if (domain.getNameOfDomain().contains(animal.getName())) {
                domain.takeAnimal(animal);
                break;
            }
        }
    }

}
