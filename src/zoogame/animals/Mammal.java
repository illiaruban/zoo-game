package zoogame.animals;

import zoogame.Zoo;

public class Mammal extends Animal{

    boolean isAggressive = false;
    private final double fullIncome = super.price / 1.5;
    private int aggressiveCount = 0;
    private int counterToSubtract = 0;
    public Mammal(String name, double price) {
        super(name, price);
    }

    public Mammal(String name, double price, AnimalType animalType, SizeClass sizeClass, int maxAmountInDomain, int timesToFeedPerDay, boolean isAggressive) {
        super(name, price, animalType, sizeClass, maxAmountInDomain, timesToFeedPerDay);
        this.isAggressive = isAggressive;
        lowerQuality = timesToFeedPerDay == 3 ? 2 : 1;
    }

    public Mammal(Mammal other) {
        super(other);
        this.isAggressive = other.isAggressive;
    }

    @Override
    public String toString() {
        return super.toString() + "Aggressive: " + (isAggressive ? "yes" : "no" + "\n");
    }

    @Override
    public int getCounter() {
        return counterToSubtract;
    }

    //if mammal is aggressive and is not fed for 2 days - numbers of customers will decrease
    // for every unfed animal and income is too by 10%
    //if mammal is non-aggressive - the income will decrease by 30%
    public double getIncome(){
        double subtractIncome = 0.0;
        if (todayEatCounter <= lowerQuality) {
            if (isAggressive) aggressiveCount++;
            counterToSubtract++;
        }
        if (aggressiveCount == 2 && !Zoo.isDecreaseVisitors()) {
            Zoo.setNumberOfVisitors(Zoo.getNumberOfVisitors() > 50 ? Zoo.getNumberOfVisitors() / 8 : Zoo.getNumberOfVisitors() / 5);
            Zoo.setDecreaseVisitors(true);
            subtractIncome = fullIncome * 0.1;
        }
        else if (counterToSubtract == 2) {
            subtractIncome = fullIncome * 0.3;
        }
        aggressiveCount = 0;
        counterToSubtract = 0;
        return fullIncome - subtractIncome;
    }
}
