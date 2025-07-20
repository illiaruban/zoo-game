package zoogame.animals;
public class Bird extends Animal{
    public Bird(String name, double price) {
        super(name, price);
    }

    public Bird(String name, double price, AnimalType animalType, SizeClass weightClass, int maxAmountInDomain, int timesToFeedPerDay) {
        super(name, price, animalType, weightClass, maxAmountInDomain, timesToFeedPerDay);
    }

    @Override
    public String toString() {
        return "";
    }

    public double getIncome(){
        return 0.0;
    }
}
