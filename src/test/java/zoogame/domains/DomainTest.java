package zoogame.domains;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import zoogame.animals.Mammal;
import zoogame.animals.SizeClass;
import zoogame.exceptions.NoAnimalFoundException;

class DomainTest {

    Domain domain = new Domain();
    @Test
    void defaultConstructorValues() {
        assertEquals("Empty domain", domain.getNameOfDomain());
        assertEquals(0, domain.getCurrentAmountOfAnimals());
    }

    @Test
    void afterAnimalIsAdded() {
        Mammal lion = new Mammal("Lion", 2000.0, SizeClass.AVERAGE,
                10, 3, true, 250);
        domain.setSizeClass(SizeClass.AVERAGE);
        domain.addAnimal(lion);
        assertEquals("Lion domain", domain.getNameOfDomain());
        assertEquals(1, domain.getCurrentAmountOfAnimals());
    }

    @Test
    void afterAnimalIsDeleted() {
        Mammal lion = new Mammal("Lion", 2000.0, SizeClass.AVERAGE,
                10, 3, true, 250);
        domain.setSizeClass(SizeClass.AVERAGE);
        domain.addAnimal(lion);
        assertDoesNotThrow(() -> domain.takeAnimal());
        assertEquals("Empty domain", domain.getNameOfDomain());
        assertEquals(0, domain.getCurrentAmountOfAnimals());
    }




}