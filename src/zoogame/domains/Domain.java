package zoogame.domains;

import zoogame.animals.Animal;
import zoogame.animals.Insect;
import zoogame.animals.Reptile;
import zoogame.animals.SizeClass;
import zoogame.exceptions.InvalidAnimalAddedException;
import zoogame.exceptions.InvalidSizeClassAddedException;

import java.util.ArrayList;

public class Domain {
    protected String nameOfDomain;
    protected ArrayList<Animal> animals;
    protected SizeClass sizeClass = SizeClass.TINY;
    protected double price;

    public Domain() {
        nameOfDomain = "Empty domain";
        animals = new ArrayList<>();
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

    //TODO: handle new exceptions in other classes
    //possible idea: just give it one step more

    public void addAnimal(Animal animal) throws InvalidSizeClassAddedException {
        if (animals.isEmpty()) {
            nameOfDomain = animal.getName() + " domain";
        }
        if (animal.getSizeClass() != sizeClass) {
            throw new InvalidSizeClassAddedException("[EXCEPTION!] Size class of domain is not identical with size class of" +
                    "an animal. Size class of an animal: " + animal.getSizeClass() + "\n");
        }
        if (animals.getFirst().getMaxAmountInDomain() > animals.size())
        {
            animals.add(animal);
        }
        else {
            System.out.println("Domain cannot contain more animals\n");
        }
    }

    public void addAnimal(Animal animal, boolean check) throws InvalidAnimalAddedException{
        if (animal instanceof Reptile || animal instanceof Insect) {
            throw new InvalidAnimalAddedException("[EXCEPTION!] Invalid type of animal added to common domain: " +
                    animal.getClass() + "\n");
        }
        addAnimal(animal);
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



}
