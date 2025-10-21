package zoogame.animals;

public class Reptile extends Animal{
    private int countToEat = 0;

    public Reptile(String name, double price) {
        super(name, price);
    }
    public Reptile(String name, double price, SizeClass sizeClass, int maxAmountInDomain, int timesToFeedPerDay, double fullIncome) {
        super(name, price, sizeClass, maxAmountInDomain, timesToFeedPerDay, fullIncome);
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
        return 1.0;
    }


}
