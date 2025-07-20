package zoogame.animals;

public class Mammal extends Animal{

    boolean isAggressive = false;
    public Mammal(String name, double price) {
        super(name, price);
    }

    public Mammal(String name, double price, AnimalType animalType, SizeClass sizeClass, int maxAmountInDomain, int timesToFeedPerDay, boolean isAggressive) {
        super(name, price, animalType, sizeClass, maxAmountInDomain, timesToFeedPerDay);
        this.isAggressive = isAggressive;
    }

    @Override
    public String toString() {

        return super.toString() + "Aggressive: " + (isAggressive ? "yes" : "no" + "\n");
    }

    public double getIncome(){
        return 0.0;
    }
}
