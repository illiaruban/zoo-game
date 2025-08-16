package zoogame;

import zoogame.animals.Animal;
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
            new Domain(), new Domain(), new Domain(), new Domain(),
            new InsectDomain(), new ReptileDomain()
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

    public int getAnimalsAmount() { return availableAnimals.size(); }

    public void deleteAnimal(int index) {
        availableAnimals.remove(index);
    }


}
