package zoogame.animals;

public class Reptile extends Animal{
    public Reptile(String name, double price) {
        super(name, price);
    }
    public Reptile(String name, double price, AnimalType animalType, SizeClass sizeClass, int maxAmountInDomain, int timesToFeedPerDay) {
        super(name, price, animalType, sizeClass, maxAmountInDomain, timesToFeedPerDay);
    }

    @Override
    public String toString() {
        return "";
    }

    public double getIncome(){
        return 0.0;
    }
}
