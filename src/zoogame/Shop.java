package zoogame;

import zoogame.animals.Animal;
import zoogame.animals.SizeClass;
import zoogame.domains.Domain;
import zoogame.domains.InsectDomain;
import zoogame.domains.ReptileDomain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Shop {
    protected ArrayList<Animal> availableAnimals = new ArrayList<>();
    protected ArrayList<AnimalFoodPack> animalFoodPacks = new ArrayList<>();

    protected ArrayList<Domain> availableDomains = new ArrayList<Domain>(List.of(
            new Domain(5000, SizeClass.TINY), new Domain(10000, SizeClass.AVERAGE),
            new Domain(15000, SizeClass.BIG), new Domain(20000, SizeClass.MASSIVE),

            new InsectDomain(2500, SizeClass.TINY), new InsectDomain(4500, SizeClass.AVERAGE),
            new InsectDomain(7000, SizeClass.BIG), new InsectDomain(10000, SizeClass.MASSIVE),

            new ReptileDomain(4000, SizeClass.TINY), new ReptileDomain(7000, SizeClass.AVERAGE),
            new ReptileDomain(10000, SizeClass.BIG), new ReptileDomain(15000, SizeClass.MASSIVE)
    ));

    public void addAnimal(Animal animal) {
        availableAnimals.add(animal);
    }

    public void addAnimalFoodPack(AnimalFoodPack foodPack) {
        animalFoodPacks.add(foodPack);
    }

    public void addDomain(Domain domain) {
        availableDomains.add(domain);
    }

    public void printAnimals() {
        int counter = 1;
        for (Animal animal: availableAnimals) {
            System.out.print(counter + ".");
            System.out.println(animal.toString());
        }
    }

    public void printDomains() {
        int counter = 1;
        for (Domain domain: availableDomains) {
            System.out.print(counter + ".");
            System.out.println(domain.toString("shopview"));
            counter++;
        }
    }

    public int getAnimalsAmount() { return availableAnimals.size(); }

    public int getDomainsAmount() { return availableAnimals.size(); }

    public ArrayList<Animal> getAvailableAnimals() { return availableAnimals; }

    public ArrayList<Domain> getAvailableDomains() { return availableDomains; }

    public void deleteAnimal(Animal animal) {
        availableAnimals.remove(animal);
    }


}
