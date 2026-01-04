package zoogame.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zoogame.animals.Bird;
import zoogame.animals.Mammal;
import zoogame.animals.SizeClass;
import zoogame.exceptions.NoAnimalFoundException;
import zoogame.exceptions.NotEnoughFoodException;
import zoogame.exceptions.NotEnoughPlaceException;

class DomainTest {

    Domain domain = new Domain();
    Mammal lion = new Mammal("Lion", 2000.0, SizeClass.AVERAGE,
            10, 3, true, 250);

    @BeforeEach
    void initialize() throws NotEnoughPlaceException {
        domain.setSizeClass(SizeClass.AVERAGE);
        domain.addAnimal(lion);
    }

    @Test
    void afterAnimalIsAdded() {
        assertEquals("Lion domain", domain.getNameOfDomain());
        assertEquals(1, domain.getCurrentAmountOfAnimals());
    }

    @Test
    void afterAnimalIsDeleted() {
        assertDoesNotThrow(() -> domain.takeAnimal());
        assertEquals("Empty domain", domain.getNameOfDomain());
        assertEquals(0, domain.getCurrentAmountOfAnimals());
    }

    @Test
    void receiveNotEnoughFoodException() throws NotEnoughPlaceException {
        int amountOfFood = 1;
        domain.addAnimal(new Mammal(lion));
        assertThrows(NotEnoughFoodException.class,() -> {
            domain.feedAnimals("morning", amountOfFood);
        });
    }

    @Test
    void feedingAnimals() throws NotEnoughPlaceException {
        int amountOfFood = 2;
        domain.addAnimal(new Mammal(lion));
        assertDoesNotThrow(() -> {
            domain.feedAnimals("morning", amountOfFood);
        });
    }
    //adding animal in domain with different animal will be tested in zoo class
    //adding animal in domain with full animal capacity will be tested in zoo class


}