package zoogame.domains;

import zoogame.animals.*;

import java.util.ArrayList;

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

    public Domain(Domain other) {
        this();
        this.price = other.price;
        this.sizeClass = other.sizeClass;
    }

    public int getCurrentAmountOfAnimals() {
        return animals.size();
    }

    //might write all the checks in zoo function as its zoo's job to check all conditions to add animal in domains
    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void takeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public String getNameOfDomain() {
        return nameOfDomain;
    }

    public void setSizeClass(SizeClass sizeClass){ this.sizeClass = sizeClass; }
    public SizeClass getSizeClass() { return sizeClass; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    //toString method for the shop
    //toString method for the zoo
    public String toString(String command) {
        String string = "";
        if (command.equals("zooview")) {
            string += nameOfDomain + "contains " + animals.size() + " " + animals.get(0).getName() + "\n";
            string += "The size class of domain is " + sizeClass + "\n";
            string += "Domain can contain " + (animals.get(0).getMaxAmountInDomain() - animals.size()) + "animals more\n";
        }
        else if (command.equals("shopview")) {
            string += String.format("%s domain\nPrice: %f\n", sizeClass, price);
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

}
