package zoogame.animals;

import zoogame.Zoo;

public class Mammal extends Animal{

    boolean isAggressive = false;
    private int aggressiveCount = 0;
    private int counterToSubtract = 0;
    public Mammal(String name, double price) {
        super(name, price);
    }

    public Mammal(String name, double price, AnimalType animalType, SizeClass sizeClass, int maxAmountInDomain, int timesToFeedPerDay, boolean isAggressive, double fullIncome) {
        super(name, price, animalType, sizeClass, maxAmountInDomain, timesToFeedPerDay, fullIncome);
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
    // for every unfed animal and income is too by 10%
    //if mammal is non-aggressive - the income will decrease by 30%

    //get a way to decrease the amount of visitors
    public double getIncome(){
        double subtractIncome = 0.0;
        if (todayEatCounter <= lowerQuality) {
            if (isAggressive) aggressiveCount++;
            counterToSubtract++;
        }
        if (aggressiveCount == 2) {
            Zoo.incrDecreaseVisitorsCounter();
            subtractIncome = fullIncome * 0.1;
            aggressiveCount--;
        }
        else if (counterToSubtract == 2) {
            Zoo.incrDecreaseVisitorsCounter();
            subtractIncome = fullIncome * 0.3;
            counterToSubtract--;
        }
        else if (todayEatCounter == timesToFeedPerDay) {
            aggressiveCount = 0;
            counterToSubtract = 0;
        }
        return fullIncome - subtractIncome;
    }
}
