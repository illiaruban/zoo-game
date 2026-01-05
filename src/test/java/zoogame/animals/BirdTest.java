package zoogame.animals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zoogame.Zoo;
import zoogame.domains.Domain;
import zoogame.exceptions.LowBalanceException;
import zoogame.exceptions.NoDomainFoundForAnimalException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BirdTest {

    Zoo zoo = new Zoo();
    Bird bird = new Bird("Crain", 1200.0, SizeClass.AVERAGE, 14, 3, 3);
    Domain domain = new Domain(5000.0, SizeClass.AVERAGE);

    @BeforeEach
    void init() {
        zoo.setAmountVisitors(30);
    }

    @Test
    void perfectFedCondition() {

        bird.todayEatCounter = 3;
        bird.setCounterToAdd(3);
        try{
            zoo.buyDomain(domain);
            zoo.buyAnimal(bird);
        }
        catch (LowBalanceException | NoDomainFoundForAnimalException ignored) {

        }
        double oldBalance = zoo.getBalance();
        zoo.closeDay();

        assertTrue(zoo.getAmountVisitors() >= 40  && zoo.getAmountVisitors() <= 60);
        assertTrue(zoo.getBalance() == oldBalance + 20 * 1.25 * bird.getFullIncome());

    }

    @Test
    void unfedCondition() {
        bird.todayEatCounter = 2;
        bird.setCounterToSubtract(1);
        zoo.setAmountVisitors(30);
        try{
            zoo.buyDomain(domain);
            zoo.buyAnimal(bird);
        }
        catch (LowBalanceException | NoDomainFoundForAnimalException ignored) {

        }
        double oldBalance = zoo.getBalance();
        zoo.closeDay();

        assertTrue(zoo.getAmountVisitors() >= 40  && zoo.getAmountVisitors() <= 60);
        assertTrue(zoo.getBalance() == oldBalance + 30 * 0.5 * bird.getFullIncome());
    }

}