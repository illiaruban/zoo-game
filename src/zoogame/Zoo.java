package zoogame;

import zoogame.animals.*;
import zoogame.domains.Domain;
import zoogame.domains.InsectDomain;
import zoogame.domains.ReptileDomain;
import zoogame.exceptions.LowBalanceException;
import zoogame.exceptions.NoAnimalFoundException;
import zoogame.exceptions.NoDomainFoundForAnimalException;
import java.util.Random;

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

    public void buyFoodPack(AnimalFoodPack foodPack) throws LowBalanceException {
        if (balance < foodPack.getPrice())
        {
            throw new LowBalanceException("The budget cannot allow to purchase the domain");
        }
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
        Random random = new Random();
        int lowerLimit = 0;
        int upperLimit = 0;
        //generate random number of visitors to add
        if (decreaseVisitorsCounter == 0) {
            upperLimit = getUpperLimit("+");
            lowerLimit = getLowerLimit("+");
            balance += random.nextInt(lowerLimit, upperLimit);
        }
        else {
            lowerLimit = getLowerLimit("-");
            upperLimit = getUpperLimit("-");
            balance -= decreaseVisitorsCounter * random.nextInt(lowerLimit, upperLimit);
        }

    }

    private int getLowerLimit(String command) {
        if (command.equals("+")) {
            return amountVisitors <= 70 ? 10 : amountVisitors <= 135 ? 22 : amountVisitors <= 225 ? 29 : amountVisitors <= 575 ? 45 : 75;
        }
        else if (command.equals("-")) {
            return amountVisitors <= 50 ? 5 : amountVisitors <= 100 ? 7 : amountVisitors <= 200 ? 12 : amountVisitors <= 250 ? 15 : 20;
        }
        else return -1;
    }

    private int getUpperLimit(String command) {
        if (command.equals("+")) {
            return amountVisitors <= 65 ? 30 : amountVisitors <= 140 ? 50 : amountVisitors <= 250 ? 80 : amountVisitors <= 575 ? 120 : 190;
        }
        else if (command.equals("-")) {
            return amountVisitors <= 65 ? 10 : amountVisitors <= 120 ? 14 : amountVisitors <= 150 ? 20 : amountVisitors <= 300 ? 37 : 50;
        }
        else return -1;
    }


}
