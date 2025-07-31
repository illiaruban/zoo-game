package zoogame.domains;

import zoogame.animals.Animal;
import zoogame.animals.SizeClass;


public class ReptileDomain extends Domain {

    private boolean rumbleTime = false;

    public ReptileDomain() {
        super();
    }

    public ReptileDomain(double price, SizeClass sizeClass){
        super(price, sizeClass);
    }

    public void setRumbleTime(boolean isRumbleTime) {
        this.rumbleTime = isRumbleTime;
    }

    public boolean isRumbleTime() {
        return rumbleTime;
    }

    /**
     * checks on every reptile in domain and in case of one starving it starts a rumble
     */
    public void checkOnHabitants() {
        for (Animal animal: animals) {
            if (animal.getCounter() <= 3) {
                rumbleTime = true;
                break;
            }
        }
    }

    /**
     * starts rumble: divides the amount of habitants by 1.5 and deletes the sublist of the required length
     */
    public void startRumble() {
        if (animals == null || animals.size() < 3) return;
        int newSize = (int)(animals.size() / 1.5);
        animals.subList(newSize, animals.size()).clear();
    }


}
