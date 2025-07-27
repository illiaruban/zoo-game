package zoogame.animals;

public class Reptile extends Animal{
    private boolean isEaten = false;
    private final double fullIncome = super.price / 1.5;
    private int lowerQuality;
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

    //if there are 3+ animals and one of them is not fed well enough, reptiles start eating each other
    //possible idea: create class ReptileDomain with one function that makes it possible - possible idea for scaling it further
    //TODO: decide what to do with reptile domain, how to give the info to it through its habitants
    public double getIncome(){
        for (Integer quality : super.feedForDaysList) {
            if (quality <= lowerQuality) {
                countToEat++;
            }
        }
        return 0.0;
    }
}
