package zoogame.domains;

import zoogame.animals.Animal;
import zoogame.animals.Insect;
import zoogame.animals.SizeClass;
import zoogame.exceptions.InvalidAnimalAddedException;

public class InsectDomain extends Domain{
    public InsectDomain() {
        super();
    }

    public InsectDomain(double price, SizeClass sizeClass){
        super(price, sizeClass);
    }

    @Override
    public void addAnimal(Animal animal, boolean check) throws InvalidAnimalAddedException {
        if (!(animal instanceof Insect)) {
            throw new InvalidAnimalAddedException("[EXCEPTION!]Other animals cannot be added to insect domains.\n" +
                    "Type of given animal: " + animal.getClass() + ".\n");
        }
        super.addAnimal(animal);
    }

    public void startVanish() {
        for (Animal animal: animals) {

        }
    }



}
