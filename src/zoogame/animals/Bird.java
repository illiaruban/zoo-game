package zoogame.animals;
public class Bird extends Animal{
    private final double fullIncome = super.price / 4;
    private int counterToSubtract = 0;
    private int counterToAdd = 1;
    public Bird(String name, double price) {
        super(name, price);
    }

    public Bird(String name, double price, AnimalType animalType, SizeClass weightClass, int maxAmountInDomain, int timesToFeedPerDay) {
        super(name, price, animalType, weightClass, maxAmountInDomain, timesToFeedPerDay);
        lowerQuality = timesToFeedPerDay == 3 ? 2 : 1;
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
        double income;
        if (counterToAdd == 4) {
            income = fullIncome + fullIncome / 8;
        } else if (counterToSubtract == 2) {
            income = fullIncome - fullIncome / 4;
        } else {
            income = fullIncome;
        }
        counterToAdd = 1;
        counterToSubtract = 0;
        return Math.round(income * 100.0) / 100.0;
    }
}
