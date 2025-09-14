package zoogame.domains;

import zoogame.AnimalFoodPack;
import zoogame.animals.*;
import zoogame.exceptions.NoAnimalFoundException;
import zoogame.exceptions.NotEnoughFoodException;
import zoogame.factories.AnimalFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class Domain {
    protected String nameOfDomain;
    protected ArrayList<Animal> animals = new ArrayList<>();
    protected SizeClass sizeClass = SizeClass.TINY;
    protected double price = 1000.0;

    public Domain() {
        nameOfDomain = "Empty domain";
    }

    public Domain(double price, SizeClass sizeClass){
        this();
        this.price = price;
        this.sizeClass = sizeClass;
    }

    public Domain(Domain otherDomain) {
        this();
        this.price = otherDomain.price;
        this.sizeClass = otherDomain.sizeClass;
        for (Animal otherAnimal: otherDomain.animals) {
            Animal newAnimal = AnimalFactory.createNewAnimalWithCopy(otherAnimal);
            animals.add(newAnimal);
        }
    }

    public int getCurrentAmountOfAnimals() {
        return animals.size();
    }

    //might write all the checks in zoo function as its zoo's job to check all conditions to add animal in domains
    public void addAnimal(Animal animal) {
        if (animals.size() == 0) nameOfDomain = animal.getName() + " domain";
        animals.add(animal);
    }

    public double takeAnimal() throws NoAnimalFoundException{
        if (animals.size() == 0) {
            throw new NoAnimalFoundException("Domain is empty");
        }
        Animal animal = animals.getLast();
        double price = animal.getPrice();
        animals.remove(animals.getLast());
        if (animals.size() == 0) {
            nameOfDomain = "Empty domain";
        }
        return switch(animal.getAnimalType()) {
            case BIRD -> price / 1.75;
            case INSECT -> price / 2;
            case MAMMAL, REPTILE -> price / 1.5;
        };
    }

    public String getNameOfDomain() {
        return nameOfDomain;
    }

    public void setSizeClass(SizeClass sizeClass){ this.sizeClass = sizeClass; }
    public SizeClass getSizeClass() { return sizeClass; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public AnimalType getTypeOfDomain() { return animals.size() != 0 ? animals.getFirst().getAnimalType() : null;}

    //toString method for the shop
    //toString method for the zoo

    public String toString(String command) {

        String string = this instanceof InsectDomain ? "[Insect Domain]" : this instanceof ReptileDomain ? "[Reptile Domain]" : "[Regular Domain]";
        string += "\n";
        if (command.equals("zooview")) {
            string += String.format("Size class: %s\n", sizeClass);
            if (animals.size() == 0) {
                string += "Contains no animals\n";
            }
            else {
                string += String.format("%s contains %d %s\n", nameOfDomain, animals.size(), animals.get(0).getName());
                string += String.format("Domain can contain %d animals more\n", (animals.get(0).getMaxAmountInDomain() - animals.size()));
            }
        }
        else if (command.equals("shopview")) {
            string += String.format("Size class: %s\nPrice: %f\n", sizeClass, price);
        }
        else {
            string += "Invalid command\n";
        }
        return string;
    }

    public boolean containsAnimalsLike(Animal animal) {
        if (animals.isEmpty()) return false;
        return animals.getFirst().getAnimalType() == animal.getAnimalType() &&
                animals.getFirst().getName().equals(animal.getName());
    }

    public double closeDay() {
        if (animals.isEmpty()) return 0.0;
        double sumCoefficients = 0.0;
        for (Animal animal: animals) {
            sumCoefficients += animal.getIncome();
            animal.endDay();
        }
        return sumCoefficients;
    }

    //we must learn how many animals of each type have to be fed during morning, day and evening
    //
    public int getListUnfedAnimals(String dayTime) {
        if (animals.size() == 0) {
            return 0;
        }
        int unfedAnimalCounter = 0;
        if (dayTime.equals("morning")) {
            for (Animal animal: animals) {
                if (animal.getFedPerDay().get("morning") == false) {
                    unfedAnimalCounter++;
                }
            }
        }
        else if (dayTime.equals("day") && animals.getFirst().getTimesToFeedPerDay() == 3) {
            for (Animal animal: animals) {
                if (animal.getFedPerDay().get("day") == false) {
                    unfedAnimalCounter++;
                }
            }
        }
        else if (dayTime.equals("evening")) {
            for (Animal animal: animals) {
                if (animal.getFedPerDay().get("evening") == false) {
                    unfedAnimalCounter++;
                }
            }
        }
        else return 0;

        return unfedAnimalCounter;
    }

    public AnimalType getAnimalType() {
        return animals.size() != 0 ? animals.getFirst().getAnimalType() : null;
    }

    //feed animals in every domain
    //function also should take food packs as parameter
    public void feedAnimals(String timeOfDay, int amountOfFood) throws NotEnoughFoodException{
        int timesToFeed = animals.getFirst().getTimesToFeedPerDay();
        if (timesToFeed == 2 && timeOfDay.equals("day")) {
            return;
        }
        for (Animal animal:animals) {
            if (amountOfFood == 0) throw new NotEnoughFoodException(nameOfDomain + " had not enough food.");
            animal.feed(timeOfDay);
            amountOfFood--;
        }
    }

}
