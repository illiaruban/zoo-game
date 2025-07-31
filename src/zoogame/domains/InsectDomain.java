package zoogame.domains;

import zoogame.animals.Animal;
import zoogame.animals.Insect;
import zoogame.animals.SizeClass;

import java.util.ArrayList;

public class InsectDomain extends Domain{
    public InsectDomain() {
        super();
    }

    public InsectDomain(double price, SizeClass sizeClass){
        super(price, sizeClass);
    }

    public void startVanish() {
        ArrayList<Animal> toRemove = new ArrayList<>();
        for (Animal animal: animals) {
            if (animal instanceof Insect && ((Insect) animal).isVanished()){
                toRemove.add(animal);
            }
        }
        for (Animal animal: toRemove) {
            animals.remove(animal);
        }
    }

    public double closeDay() {
        double domainIncome = super.closeDay();
        startVanish();
        return domainIncome;
    }



}
