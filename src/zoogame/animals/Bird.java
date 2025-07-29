package zoogame.animals;
public class Bird extends Animal{
    private final double fullIncome = super.price / 4;
    private int counterToSubtract = 0;
    private int counterToAdd = 1;
    private int maxQuality;
    public Bird(String name, double price) {
        super(name, price);
    }

    public Bird(String name, double price, AnimalType animalType, SizeClass weightClass, int maxAmountInDomain, int timesToFeedPerDay) {
        super(name, price, animalType, weightClass, maxAmountInDomain, timesToFeedPerDay);
        maxQuality = super.timesToFeedPerDay == 3 ? 3 : 2;
        lowerQuality = maxQuality == 3 ? 2 : 1;
    }

    @Override
    public int getCounter() {
        for (Integer quality : feedForDaysList ) {
            if (quality <= lowerQuality) {
                counterToSubtract++;
            }
        }
        int counter = counterToSubtract;
        counterToSubtract = 0;
        return counter;
    }

    public double getIncome(){
        for (Integer quality : feedForDaysList ) {
            if (quality == maxQuality && counterToAdd > 0) {
                counterToAdd++;
            }
            else counterToAdd = 0;
            if (quality <= lowerQuality) {
                counterToSubtract++;
            }
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
