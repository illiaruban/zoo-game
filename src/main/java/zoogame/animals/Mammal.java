package zoogame.animals;

import zoogame.Zoo;

public class Mammal extends Animal{

    boolean isAggressive = false;
    private int aggressiveCount = 0;
    private int counterToSubtract = 0;

    private AnimalType animalType = AnimalType.MAMMAL;
    public Mammal(String name, double price) {
        super(name, price);
    }

    public Mammal(String name, double price, SizeClass sizeClass, int maxAmountInDomain, int timesToFeedPerDay, boolean isAggressive, double fullIncome) {
        super(name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, fullIncome);
        this.isAggressive = isAggressive;
        lowerQuality = timesToFeedPerDay == 3 ? 2 : 1;
    }

    public Mammal(Mammal other) {
        super(other);
        if (other instanceof Mammal) {
            this.isAggressive = other.isAggressive;
        }
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
    // for every unfed animal and income is too by 20%
    //if mammal is non-aggressive - the income will decrease by 40%

    //get a way to decrease the amount of visitors
    //return 0.7 and 0.8
    public double getIncome(){
        double coefficient = 1.0;
        if (todayEatCounter <= lowerQuality) {
            if (isAggressive) aggressiveCount++;
            counterToSubtract++;
        }
        if (aggressiveCount == 2) {
            Zoo.incrDecreaseVisitorsCounter();
            coefficient = 0.8;
            aggressiveCount--;
        }
        else if (counterToSubtract == 2) {
            Zoo.incrDecreaseVisitorsCounter();
            coefficient = 0.6;
            counterToSubtract--;
        }
        else if (todayEatCounter == timesToFeedPerDay) {
            aggressiveCount = 0;
            counterToSubtract = 0;
        }
        return coefficient;
    }
}
