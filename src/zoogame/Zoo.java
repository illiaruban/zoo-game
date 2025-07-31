package zoogame;

import zoogame.animals.*;
import zoogame.domains.Domain;
import zoogame.domains.InsectDomain;
import zoogame.domains.ReptileDomain;
import zoogame.exceptions.LowBalanceException;
import zoogame.exceptions.NoAnimalFoundException;
import zoogame.exceptions.NoDomainFoundForAnimalException;

import java.util.ArrayList;
import java.util.HashMap;

public class Zoo {
    private ArrayList<Domain> domains = new ArrayList<>();
    private boolean isOpened = false;
    private double balance;
    private HashMap<AnimalType, Integer> foodStorage;

    private static int decreaseVisitorsCounter = 0;
    private int amountVisitors = 15;


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

    public void setNumberOfVisitors(int numberOfVisitors) { amountVisitors = numberOfVisitors; }

    public int getNumberOfVisitors() { return amountVisitors; }

    public static void incrDecreaseVisitorsCounter() {
        decreaseVisitorsCounter++;
    }

    //TODO: make conditions in the functions, define exceptions
    public void buyFoodPack(AnimalFoodPack foodPack) {
        balance -= foodPack.getPrice();
        for (AnimalType type: foodPack.content.keySet()) {
            int currentFoodAmount = foodStorage.get(type);
            foodStorage.put(type, currentFoodAmount + foodPack.content.get(type));
        }
    }
    public void buyDomain(Domain domain) throws LowBalanceException {
        if (balance < domain.getPrice())
        {
            throw new LowBalanceException("The budget cannot allow to purchase the domain");
        }
        balance -= domain.getPrice();
        Domain addedDomain = new Domain(domain);
        domains.add(addedDomain);
    }

    public void sellDomain(Domain domain) {
        balance += domain.getSizeClass() == SizeClass.MASSIVE ? domain.getPrice() / 2 : domain.getPrice() / 1.5;
        domains.remove(domain);
    }


    /**
     * checks if:
     * balance is enough
     * there is a domain for animal with passing size class, type
     * and adds an animal to the first domain in the loop with satisfied conditions
     *
     * @param animal
     */
    public void buyAnimal(Animal animal) throws NoDomainFoundForAnimalException, LowBalanceException {
        if (balance < animal.getPrice()) {
            throw new LowBalanceException("The budget cannot allow the purchase of the animal");
        }

        boolean lookForInsectDomain = false;
        boolean lookForReptileDomain = false;
        Domain domainWithNoAnimals = null;
        Domain domainWithLikeAnimals = null;

        if (animal instanceof Insect) lookForInsectDomain = true;
        if (animal instanceof Reptile) lookForReptileDomain = true;

        for (Domain domain: domains) {
            if (lookForInsectDomain && !(domain instanceof InsectDomain)) continue;
            else if (lookForReptileDomain && !(domain instanceof ReptileDomain)) continue;
            else if (domain.getSizeClass() != animal.getSizeClass()) continue;
            else if (domain.getCurrentAmountOfAnimals() > animal.getMaxAmountInDomain()) continue;
            if (domain.getNameOfDomain().equals("Empty domain")) domainWithNoAnimals = domain;
            if (domain.containsAnimalsLike(animal)) domainWithLikeAnimals = domain;
            if (domainWithNoAnimals != null && domainWithLikeAnimals != null) break;
        }
        try {
            if (domainWithLikeAnimals != null) domainWithLikeAnimals.addAnimal(animal);
            else if (domainWithNoAnimals != null) domainWithNoAnimals.addAnimal(animal);
            else {
                throw new NoDomainFoundForAnimalException("No domain found for this animal");
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sellAnimal(Animal animal) throws NoAnimalFoundException {
        boolean isTaken = false;
        for(Domain domain: domains) {
            if (domain.containsAnimalsLike(animal)) {
                domain.takeAnimal(animal);
                isTaken = true;
                break;
            }
        }
        if (isTaken) {
            balance += animal.getAnimalType() == AnimalType.INSECT ? animal.getPrice() / 2 :
                            animal.getAnimalType() == AnimalType.MAMMAL ? animal.getPrice() / 1.75 :
                            animal.getPrice() / 1.5;
        }
        else {
            throw new NoAnimalFoundException("Zoo does not possess this animal");
        }
    }

    //TODO: work on logic of decreasing or increasing the visitors
    /**
     * goes through every domain and checks on every animal in it
     * then decreases the visitors due to counter
     */
    public void closeDay() {
        for (Domain domain: domains) {
            balance += domain.closeDay();
        }

    }


}
