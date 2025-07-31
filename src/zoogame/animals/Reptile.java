package zoogame.animals;

public class Reptile extends Animal{
    private final double fullIncome = super.price / 1.7;
    private int countToEat = 0;

    public Reptile(String name, double price) {
        super(name, price);
    }
    public Reptile(String name, double price, AnimalType animalType, SizeClass sizeClass, int maxAmountInDomain, int timesToFeedPerDay) {
        super(name, price, animalType, sizeClass, maxAmountInDomain, timesToFeedPerDay);
        lowerQuality = timesToFeedPerDay == 3 ? 2 : 1;
    }

    public Reptile(Reptile other) {
        super(other);
        this.countToEat = other.countToEat;
    }

    @Override
    public int getCounter() {
        return countToEat;
    }

    public double getIncome(){
        if (todayEatCounter < lowerQuality) {
            countToEat++;
        }
        return fullIncome;
    }


}
