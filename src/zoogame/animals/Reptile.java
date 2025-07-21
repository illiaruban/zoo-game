package zoogame.animals;

public class Reptile extends Animal{
    private boolean isEaten = false;
    private final double fullIncome = super.price / 1.5;
    private int lowerQuality;
    private int aggressiveCount = 0;
    private int counterToSubtract = 0;

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

    //if there are 3+ animals, reptiles start eating each other
    //possible idea: create class ReptileDomain with one function that makes it possible - possible idea for scaling it further
    public double getIncome(){
        return 0.0;
    }
}
