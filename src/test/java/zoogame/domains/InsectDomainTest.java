package zoogame.domains;

import org.junit.jupiter.api.Test;
import zoogame.animals.Insect;
import zoogame.animals.SizeClass;

import static org.junit.jupiter.api.Assertions.*;

class InsectDomainTest {
    Insect blackWidow = new Insect("Black Widow", 750, SizeClass.TINY, 35, 3, 45);
    InsectDomain domain = new InsectDomain();

    @Test
    void vanishHappened() {
        domain.addAnimal(blackWidow);
        for (int i = 0; i < 2; i++) {
            Insect copy = new Insect(blackWidow);
            copy.setVanished(true);
            domain.addAnimal(copy);
        }
        domain.startVanish();
        assertEquals(1, domain.getCurrentAmountOfAnimals());
    }
}