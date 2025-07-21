package zoogame.animals;
public class Bird extends Animal{
    private final double fullIncome = super.price / 4;
    private int counterToSubtract = 0;
    private int counterToAdd = 1;
    private int maxQuality;
    private int lowerQuality;
    public Bird(String name, double price) {
        super(name, price);
    }

    public Bird(String name, double price, AnimalType animalType, SizeClass weightClass, int maxAmountInDomain, int timesToFeedPerDay) {
        super(name, price, animalType, weightClass, maxAmountInDomain, timesToFeedPerDay);
        maxQuality = super.timesToFeedPerDay == 3 ? 3 : 2;
        lowerQuality = maxQuality == 3 ? 2 : 1;
    }


    //TODO: create getIncome() function to take income if bird was not regularly fed for 2 days(check if list of feedings is lower than 2)
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

        return Math.round(income * 100.0) / 100.0;
    }
}
