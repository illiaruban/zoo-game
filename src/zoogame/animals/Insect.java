package zoogame.animals;

public class Insect extends Animal{
    private boolean isVanished = false;
    private final double fullIncome = super.price / 1.5;
    private int counterToVanish = 0;
    private int lowerQuality;
    public Insect(String name, double price) {
        super(name, price);
    }

    public Insect(String name, double price, AnimalType animalType, SizeClass sizeClass, int maxAmountInDomain, int timesToFeedPerDay) {
        super(name, price, animalType, sizeClass, maxAmountInDomain, timesToFeedPerDay);
        lowerQuality = timesToFeedPerDay == 3 ? 2 : 1;
    }

    public double getIncome(){

        for (Integer quality : feedForDaysList) {
            if (quality < lowerQuality) {
                counterToVanish++;
            }
        }
        if (counterToVanish == 2) {
            isVanished = true;
            return 0.0;
        }
        return Math.round(fullIncome * 100.0) / 100.0;
    }
}
