package zoogame;

import zoogame.animals.Animal;
import zoogame.animals.SizeClass;

import java.util.ArrayList;

public class Domain {
    private String nameOfDomain;
    private ArrayList<Animal> animals;
    private SizeClass sizeClass = SizeClass.TINY;
    private double price;

    public Domain() {
        nameOfDomain = "Empty domain";
        animals = new ArrayList<Animal>();
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

    protected void addAnimal(Animal animal) {
        if (animals.isEmpty()) {
            nameOfDomain = animal.getName() + " domain";
        }
        if (animals.get(0).getMaxAmountInDomain() > animals.size())
        {
            animals.add(animal);
        }
        else {
            System.out.println("Domain cannot contain more animals\n");
        }
    }

    protected void takeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public String getNameOfDomain() {
        return nameOfDomain;
    }

    public void setSizetClass(SizeClass sizeClass){ this.sizeClass = sizeClass; }
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
            string += String.format("%s domain\nPrice: %f\n");
        }
        else {
            string += "Invalid command\n";
        }
        return string;
    }



}
