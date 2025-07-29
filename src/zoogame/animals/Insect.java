package zoogame.animals;

public class Insect extends Animal{
    private boolean vanished = false;
    private final double fullIncome = super.price / 1.5;
    private int counterToVanish = 0;
    public Insect(String name, double price) {
        super(name, price);
    }

    public Insect(String name, double price, AnimalType animalType, SizeClass sizeClass, int maxAmountInDomain, int timesToFeedPerDay) {
        super(name, price, animalType, sizeClass, maxAmountInDomain, timesToFeedPerDay);
        lowerQuality = timesToFeedPerDay == 3 ? 2 : 1;
    }

    @Override
    public int getCounter() {
        for (Integer quality : feedForDaysList) {
            if (quality < lowerQuality) {
                counterToVanish++;
            }
        }
        int counter = counterToVanish;
        counterToVanish = 0;
        return counter;
    }
    public boolean isVanished() { return vanished; }

    public double getIncome(){

        for (Integer quality : feedForDaysList) {
            if (quality < lowerQuality) {
                counterToVanish++;
            }
        }
        if (counterToVanish == 2) {
            vanished = true;
            return 0.0;
        }
        counterToVanish = 0;
        return Math.round(fullIncome * 100.0) / 100.0;
    }

}
