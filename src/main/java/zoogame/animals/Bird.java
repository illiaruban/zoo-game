package zoogame.animals;
public class Bird extends Animal{
    private int counterToSubtract = 0;
    private int counterToAdd = 1;

    private AnimalType animalType = AnimalType.BIRD;
    public Bird(String name, double price) {
        super(name, price);
    }

    public Bird(String name, double price, SizeClass weightClass, int maxAmountInDomain, int timesToFeedPerDay, double fullIncome) {
        super(name, price, weightClass, maxAmountInDomain, timesToFeedPerDay, fullIncome);
        lowerQuality = timesToFeedPerDay == 3 ? 2 : 1;
    }

    public Bird(Bird other) {
        super(other);
    }

    @Override
    public int getCounter() {
        return counterToSubtract;
    }

    public double getIncome(){
        if (todayEatCounter == timesToFeedPerDay && counterToAdd > 0) {
            counterToAdd++;
        }
        else counterToAdd = 0;
        if (todayEatCounter <= lowerQuality) {
            counterToSubtract++;
        }
        double coefficient;
        if (counterToAdd == 4) {
            coefficient = 1.25;
        } else if (counterToSubtract == 2) {
            coefficient = 0.5;
        } else {
            coefficient = 1.0;
        }
        counterToAdd = 1;
        counterToSubtract = 0;
        return coefficient * fullIncome;
    }
}
