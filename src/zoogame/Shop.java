package zoogame;

import zoogame.animals.Animal;
import zoogame.animals.AnimalType;
import zoogame.animals.SizeClass;
import zoogame.domains.Domain;
import zoogame.domains.InsectDomain;
import zoogame.domains.ReptileDomain;
import zoogame.factories.AnimalFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop {
    protected ArrayList<Animal> availableAnimals = new ArrayList<>();

    protected ArrayList<AnimalFoodPack> animalFoodPacks = new ArrayList<>(List.of(
        new AnimalFoodPack("Small Mammal Pack", new HashMap(Map.of(AnimalType.MAMMAL, 10)), 1000),
            new AnimalFoodPack("Great Mammal Pack", new HashMap(Map.of(AnimalType.MAMMAL, 30)), 2900),
            new AnimalFoodPack("Small Bird Pack", new HashMap(Map.of(AnimalType.BIRD, 10)), 850),
            new AnimalFoodPack("Great Bird Pack", new HashMap(Map.of(AnimalType.BIRD, 30)), 2500),
            new AnimalFoodPack("Small Insect Pack", new HashMap(Map.of(AnimalType.INSECT, 10)), 400),
            new AnimalFoodPack("Great Insect Pack", new HashMap(Map.of(AnimalType.INSECT, 30)), 1100),
            new AnimalFoodPack("Small Reptile Pack", new HashMap(Map.of(AnimalType.REPTILE, 10)), 1500),
            new AnimalFoodPack("Great Reptile Pack", new HashMap(Map.of(AnimalType.REPTILE, 10)), 4200),
            new AnimalFoodPack("Small Everything Pack", new HashMap(Map.of(
                    AnimalType.MAMMAL, 10,
                    AnimalType.REPTILE, 10,
                    AnimalType.INSECT, 10,
                    AnimalType.BIRD, 10)), 3700),
            new AnimalFoodPack("Great Everything Pack", new HashMap(Map.of(
                    AnimalType.MAMMAL, 30,
                    AnimalType.BIRD, 30,
                    AnimalType.INSECT, 30,
                    AnimalType.REPTILE, 30)), 10_600)

    ));

    protected ArrayList<Domain> availableDomains = new ArrayList<Domain>(List.of(
            new Domain(5000, SizeClass.TINY), new Domain(10000, SizeClass.AVERAGE),
            new Domain(15000, SizeClass.BIG), new Domain(20000, SizeClass.MASSIVE),

            new InsectDomain(2500, SizeClass.TINY), new InsectDomain(4500, SizeClass.AVERAGE),
            new InsectDomain(7000, SizeClass.BIG), new InsectDomain(10000, SizeClass.MASSIVE),

            new ReptileDomain(4000, SizeClass.TINY), new ReptileDomain(7000, SizeClass.AVERAGE),
            new ReptileDomain(10000, SizeClass.BIG), new ReptileDomain(15000, SizeClass.MASSIVE)
    ));

    public Shop() {}

    public Shop(Shop other) {
        for (Animal animal: other.availableAnimals) {
            Animal newAnimal = AnimalFactory.createNewAnimalWithCopy(animal);
            availableAnimals.add(newAnimal);
        }
        for (AnimalFoodPack pack: other.animalFoodPacks) {
            AnimalFoodPack newPack = new AnimalFoodPack(pack);
            animalFoodPacks.add(newPack);
        }
    }

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
            System.out.println("---");
            counter++;
        }
    }

    public void printDomains() {
        int counter = 1;
        for (Domain domain: availableDomains) {
            System.out.print(counter + ". ");
            System.out.println(domain.toString("shopview"));
            System.out.println("---");
            counter++;
        }
    }

    public void printFoodPacks() {
        int counter = 1;
        for (AnimalFoodPack pack: animalFoodPacks) {
            System.out.print(counter + ". ");
            System.out.println(pack.toString());
            System.out.println("---");
            counter++;
        }
    }

    public int getAnimalsAmount() { return availableAnimals.size(); }

    public int getDomainsAmount() { return availableDomains.size(); }

    public ArrayList<Animal> getAvailableAnimals() { return availableAnimals; }

    public ArrayList<Domain> getAvailableDomains() { return availableDomains; }

    public ArrayList<AnimalFoodPack> getAnimalFoodPacks() {
        return animalFoodPacks;
    }

    public void deleteAnimal(Animal animal) {
        availableAnimals.remove(animal);
    }


}
