package zoogame;

import zoogame.animals.*;
import zoogame.domains.Domain;
import zoogame.domains.InsectDomain;
import zoogame.domains.ReptileDomain;
import zoogame.exceptions.*;

import java.util.*;

public class Zoo {
    private ArrayList<Domain> domains = new ArrayList<>();
    private boolean isOpened = false;
    private double balance;
    private HashMap<AnimalType, Integer> foodStorage = new HashMap<>();

    private static int decreaseVisitorsCounter = 0;
    private int amountVisitors = 15;


    public Zoo() {
        balance = 30000;
        foodStorage.putAll(Map.of(AnimalType.BIRD, 0,
                AnimalType.MAMMAL, 0,
                AnimalType.REPTILE, 0,
                AnimalType.INSECT, 0));
    };

    public Zoo(Zoo other) {
        this.balance = other.balance;
        for (Domain domain: other.domains) {
            Domain newDomain = new Domain(domain);
            domains.add(newDomain);
        }
        this.amountVisitors = other.amountVisitors;
        foodStorage = new HashMap<>(other.foodStorage);
    }

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

    public void printDomains() {
        int counter = 1;
        for (Domain domain: domains) {
            System.out.print(counter + ". ");
            System.out.println(domain.toString("zooview"));
            System.out.println("---");
            counter++;
        }
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
            if (lookForInsectDomain && !(domain instanceof InsectDomain)) {
                continue;
            }
            else if (lookForReptileDomain && !(domain instanceof ReptileDomain)) {
                continue;
            }
            else if (domain.getSizeClass() != animal.getSizeClass()) {
                continue;
            }
            else if (domain.getCurrentAmountOfAnimals() > animal.getMaxAmountInDomain()) {
                continue;
            }
            if (domain.getNameOfDomain().equals("Empty domain")) {
                domainWithNoAnimals = domain;
            }
            if (domain.containsAnimalsLike(animal)) {
                domainWithLikeAnimals = domain;
            }
            if (domainWithNoAnimals != null && domainWithLikeAnimals != null){
                break;
            }
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
    public void sellAnimal(int index) throws NoAnimalFoundException, NoDomainFoundException {
        if (domains.size() < index || index < 0) {
            throw new NoDomainFoundException("There is no domain under such index");
        }
        Domain domain = domains.get(index);
        try {
            balance += domain.takeAnimal();
        }
        catch( NoAnimalFoundException e) {
            throw e;
        }
    }

    /**
     * goes through every domain and checks on every animal in it, gathers coefficients in a sum
     * calculates new balance by multiplying amount of visitors by coefficient
     * then decreases the visitors due to counter
     *
     */
    public void closeDay() {
        double todayCoefficient = 0.0;
        for (Domain domain: domains) {
            todayCoefficient += domain.closeDay();
        }
        balance += amountVisitors * todayCoefficient;
        Random random = new Random();
        int lowerLimit = 0;
        int upperLimit = 0;
        //generate random number of visitors to add
        if (decreaseVisitorsCounter == 0) {
            upperLimit = getUpperLimit("+");
            lowerLimit = getLowerLimit("+");
            amountVisitors += random.nextInt(lowerLimit, upperLimit);
        }
        else {
            lowerLimit = getLowerLimit("-");
            upperLimit = getUpperLimit("-");
            amountVisitors -= random.nextInt(lowerLimit, upperLimit) * decreaseVisitorsCounter;
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

    public void printListUnfedAnimals() {
        final int MORNING = 0, DAY = 1, EVENING = 2;

        // totals[type] -> int[3] = {morning, day, evening}
        EnumMap<AnimalType, int[]> totals = new EnumMap<>(AnimalType.class);
        for (AnimalType t : AnimalType.values()) totals.put(t, new int[3]);

        for (Domain domain : domains) {
            AnimalType type = domain.getTypeOfDomain();
            if (type == null) continue;

            int[] t = totals.get(type);
            t[MORNING] += domain.getListUnfedAnimals("morning");
            t[DAY]     += domain.getListUnfedAnimals("day");
            t[EVENING] += domain.getListUnfedAnimals("evening");
        }

        for (Map.Entry<AnimalType, int[]> e : totals.entrySet()) {
            int[] t = e.getValue();
            System.out.printf(
                    "%s -> morning: %d, day: %d, evening: %d\n",
                    e.getKey(), t[MORNING], t[DAY], t[EVENING]
            );
        }
    }

    public void printFoodStorage() {
        System.out.printf("Current food storage contains:\n" +
                "Birds - %d portions\n" +
                "Insects - %d portions\n" +
                "Mammals - %d portions\n" +
                "Reptiles - %d portions\n", foodStorage.get(AnimalType.BIRD), foodStorage.get(AnimalType.INSECT),
                foodStorage.get(AnimalType.MAMMAL), foodStorage.get(AnimalType.REPTILE));
    }

    public void feedAnimals(String dayTime) {
        for (Domain domain: domains) {
            AnimalType type = domain.getAnimalType();
            if (type == null) continue;
            int amountOfFood = foodStorage.get(type) > domain.getCurrentAmountOfAnimals() ?
                    domain.getCurrentAmountOfAnimals() : foodStorage.get(type);
            foodStorage.put(type, foodStorage.get(type) - amountOfFood);
            try {
                domain.feedAnimals(dayTime, amountOfFood);
            }
            catch (NotEnoughFoodException e) {
                System.out.println("Domain " + domains.indexOf(domain) + ":" + e.getMessage());
            }
        }
    }


}
