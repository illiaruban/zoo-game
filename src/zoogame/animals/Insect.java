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
        return counterToVanish;
    }
    public boolean isVanished() { return vanished; }

    //TODO: improve getIncome() so it does not get into so many loops
    //idea: just check the todayEatCounter
    public double getIncome(){
        if (todayEatCounter < lowerQuality) {
            counterToVanish++;
        }
        if (counterToVanish == 2) {
            vanished = true;
            return 0.0;
        }
        return Math.round(fullIncome * 100.0) / 100.0;
    }

}
