package zoogame.domains;

import zoogame.animals.*;
import zoogame.factories.AnimalFactory;

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
        animals.add(animal);
    }

    public void takeAnimal(Animal animal) {
        animals.remove(animal);
        if (animals.size() == 0) {
            nameOfDomain = "Empty domain";
        }
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

}
