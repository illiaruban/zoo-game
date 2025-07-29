package zoogame.animals;

public class Reptile extends Animal{
    private boolean isEaten = false;
    private final double fullIncome = super.price / 1.5;
    private int countToEat = 0;

    public Reptile(String name, double price) {
        super(name, price);
    }
    public Reptile(String name, double price, AnimalType animalType, SizeClass sizeClass, int maxAmountInDomain, int timesToFeedPerDay) {
        super(name, price, animalType, sizeClass, maxAmountInDomain, timesToFeedPerDay);
        lowerQuality = timesToFeedPerDay == 3 ? 2 : 1;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public int getCounter() {
        for (Integer quality: feedForDaysList) {
            if (quality <= lowerQuality) {
                countToEat++;
            }
        }
        int counter = countToEat;
        countToEat = 0;
        return counter;
    }

    //TODO: write getIncome()
    public double getIncome(){

        return 0.0;
    }


}
