package zoogame.animals;

public class Insect extends Animal{
    private boolean vanished = false;
    private int counterToVanish = 0;

    private AnimalType animalType = AnimalType.INSECT;
    public Insect(String name, double price) {
        super(name, price);
    }

    public Insect(String name, double price, SizeClass sizeClass, int maxAmountInDomain, int timesToFeedPerDay, double fullIncome) {
        super(name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, fullIncome);
        lowerQuality = timesToFeedPerDay == 3 ? 2 : 1;
    }

    public Insect(Insect other) {
        super(other);
        if (other instanceof Insect) {
            this.vanished = other.vanished;
            this.counterToVanish = other.counterToVanish;
        }
    }

    @Override
    public int getCounter() {
        return counterToVanish;
    }
    public boolean isVanished() { return vanished; }

    public void setVanished(boolean vanished) {
        this.vanished = vanished;
    }

    //idea: just check the todayEatCounter
    public double getIncome(){
        if (todayEatCounter < lowerQuality) {
            counterToVanish++;
        }
        if (counterToVanish == 2) {
            vanished = true;
            return 0.0;
        }
        return fullIncome;
    }

}
