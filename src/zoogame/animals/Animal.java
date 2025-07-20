package zoogame.animals;

import java.util.ArrayList;
import java.util.HashMap;

/*
* there will be 4 kinds of animals to implement: mammals, birds, insects and reptiles
* mammals can be aggressive or passive: in case of aggressive mammal lack of food will make them scare customers off("Claws off!")
* insects will die if not fed for some time("Vanished!")
* birds will take 25% of their income if not fed but add 10% if they will be regularly fed("Healthy Feathers")
* reptiles will eat each other if not fed("Wild Nature") - (!)works for 3+ animals in one domain
*
* */
public abstract class Animal {
    protected String name;
    protected double price;
    protected AnimalType animalType = AnimalType.MAMMAL;
    protected SizeClass sizeClass = SizeClass.TINY;
    protected int maxAmountInDomain = 10;
    protected int timesToFeedPerDay = 2;
    protected HashMap<String, Boolean> fedPerDay = new HashMap<>();
    //list of integer percentages that contains how good animals have eaten
    protected ArrayList<Integer> feedForDaysList = new ArrayList<>();


    public Animal(String name, double price){
        this.name = name;
        this.price = price;
    }

    public Animal(String name, double price, AnimalType animalType, SizeClass sizeClass, int maxAmountInDomain, int timesToFeedPerDay) {
        this(name, price);
        this.animalType = animalType;
        this.sizeClass = sizeClass;
        this.maxAmountInDomain = maxAmountInDomain;
        this.timesToFeedPerDay = timesToFeedPerDay;
        fedPerDay.put("day", false);
        fedPerDay.put("evening", false);
        if (timesToFeedPerDay == 3) fedPerDay.put("morning", false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public int getTimesToFeedPerDay() {
        return timesToFeedPerDay;
    }

    public void setTimesToFeedPerDay(int timesToFeedPerDay){
        this.timesToFeedPerDay = timesToFeedPerDay;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public void setSizeClass(SizeClass sizeClass){ this.sizeClass = sizeClass; }
    public SizeClass getSizeClass() { return sizeClass; }

    public void setMaxAmountInDomain(int maxAmountInDomain) { this.maxAmountInDomain = maxAmountInDomain; }
    public int getMaxAmountInDomain() { return maxAmountInDomain;}

    public String toString() {
        return String.format("[%s]\nName: %s\nSize: %s\nPrice: %f\nMax amount in one domain: %d\nTimes to feed per day: %d",
                animalType, name, sizeClass, price, maxAmountInDomain, timesToFeedPerDay);
    }
    //TODO: implement toString() in all subclasses
    public void feed(String timeOfDay) {
        fedPerDay.put(timeOfDay, true);
    }

    public abstract double getIncome();

}
