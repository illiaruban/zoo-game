package zoogame;
import zoogame.animals.AnimalType;

import java.util.HashMap;

public class AnimalFoodPack {
    private String nameOfPack;
    protected HashMap<AnimalType, Integer> content = new HashMap<>();

    private double price = 100.0;

    public AnimalFoodPack() {
        content = new HashMap<>();
    }
    public AnimalFoodPack(String nameOfPack) {
        this();
        this.nameOfPack = nameOfPack;
    }
    public AnimalFoodPack(String nameOfPack, HashMap<AnimalType, Integer> content, double price){
        this.nameOfPack = nameOfPack;
        this.content.putAll(content);
        this.price = price;
    }

    public AnimalFoodPack(AnimalFoodPack other) {
        this.nameOfPack = other.nameOfPack;
        this.content = new HashMap<>(other.content);
        this.price = other.price;
    }

    public void addFood(AnimalType type, int amount){
        content.put(type, amount);
    }
    public void deleteFood(AnimalType type) {
        content.remove(type);
    }

    public String getNameOfPack() {
        return nameOfPack;
    }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String toString() {
        String string = getNameOfPack() + " contains:\n";
        for (AnimalType type: content.keySet()) {
            switch(type){
                case AnimalType.BIRD -> {
                    string += "Bird food: " + content.get(type) + "\n";
                }
                case AnimalType.INSECT -> {
                    string += "Insect food: " + content.get(type) + "\n";
                }
                case AnimalType.MAMMAL -> {
                    string += "Mammal food: " + content.get(type) + "\n";
                }
                case AnimalType.REPTILE -> {
                    string += "Reptile food: " + content.get(type) + "\n";
                }
                default -> {
                    string += "Not food\n";
                }

            }
        }
        string += "Price: " + price;
        return string;
    }

}
