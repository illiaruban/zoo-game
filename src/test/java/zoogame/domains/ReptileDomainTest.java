package zoogame.domains;

import org.junit.jupiter.api.Test;
import zoogame.animals.Reptile;
import zoogame.animals.SizeClass;

import static org.junit.jupiter.api.Assertions.*;

class ReptileDomainTest {

    Reptile crocodile = new Reptile("Crocodile", 3500.0, SizeClass.BIG,
            10, 2, 300);
    ReptileDomain domain = new ReptileDomain();

    @Test
    void rumbleHappened()
    {
        domain.addAnimal(crocodile);
        for (int i = 0; i < 2; i++) {
            Reptile copy = new Reptile(crocodile);
            domain.addAnimal(copy);
        }
        domain.setRumbleTime(true);
        domain.startRumble();
        assertEquals(2, domain.getCurrentAmountOfAnimals());
    }

}