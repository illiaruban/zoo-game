package zoogame.domains;

import zoogame.animals.Animal;
import zoogame.animals.Reptile;
import zoogame.animals.SizeClass;
import zoogame.domains.Domain;
import zoogame.exceptions.InvalidAnimalAddedException;


public class ReptileDomain extends Domain {

    private boolean isRumbleTime = false;

    public ReptileDomain() {
        super();
    }

    public ReptileDomain(double price, SizeClass sizeClass){
        super(price, sizeClass);
    }

    public void setStartRumble(boolean isRumbleTime) {
        this.isRumbleTime = isRumbleTime;
    }

    public boolean getStartRumble() {
        return isRumbleTime;
    }

    @Override
    public void addAnimal(Animal animal) throws InvalidAnimalAddedException {
        if (animals.isEmpty()) {
            nameOfDomain = animal.getName() + " domain";
        }
        if (!(animal instanceof Reptile)) {
            throw new InvalidAnimalAddedException("[EXCEPTION!]Other animals cannot be added to reptile domains.\n");
        }
        if (animals.get(0).getMaxAmountInDomain() > animals.size())
        {
            animals.add(animal);
        }
        else {
            System.out.println("Domain cannot contain more animals\n");
        }
    }

    //TODO: implement observer pattern for reptile domain
    public void startRumble() {
        if (isRumbleTime) {
            int newSize = (int) (super.animals.size() / 1.5);
            if (newSize < animals.size()) {
                animals.subList(newSize, animals.size()).clear();
            }
        }
    }



}
