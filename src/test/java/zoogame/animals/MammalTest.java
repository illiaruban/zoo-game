package zoogame.animals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zoogame.Zoo;
import zoogame.domains.Domain;
import zoogame.exceptions.LowBalanceException;
import zoogame.exceptions.NoDomainFoundForAnimalException;
import zoogame.exceptions.NotEnoughPlaceException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MammalTest {
    Zoo zoo = new Zoo();
    Domain aggressiveDomain = new Domain(500.0, SizeClass.AVERAGE);
    Domain nonAggressiveDomain = new Domain(500.0, SizeClass.TINY);

    ArrayList<Mammal> list = new ArrayList<>();
    Mammal aggressiveMammal = new Mammal("Lion", 2000.0, SizeClass.AVERAGE,
            10, 3, true, 25.0);
    Mammal nonAggressiveMammal = new Mammal("Rabbit", 2000.0, SizeClass.TINY,
            20, 3, false, 25.0);

    @BeforeEach
    void init() {
        zoo.setAmountVisitors(30);
    }

    @Test
    void unfedConditionAggressive() {
        try{
            zoo.buyDomain(aggressiveDomain);
            zoo.buyAnimal(aggressiveMammal);
        }
        catch (LowBalanceException e) {

        }
        catch (NoDomainFoundForAnimalException e){

        }
        double oldBalance = zoo.getBalance();
        aggressiveMammal.todayEatCounter = 0;
        aggressiveMammal.setAggressiveCount(1);

        zoo.closeDay();

        assertTrue(zoo.getAmountVisitors() >= 20  && zoo.getAmountVisitors() <= 25);
        assertTrue(zoo.getBalance() == oldBalance + 30 * 0.8 * aggressiveMammal.getFullIncome());

    }

    @Test
    void unfedConditionNonaggressive() {
        try{
            zoo.buyDomain(nonAggressiveDomain);
            zoo.buyAnimal(nonAggressiveMammal);
        }
        catch (LowBalanceException e) {

        }
        catch (NoDomainFoundForAnimalException e){

        }
        double oldBalance = zoo.getBalance();
        nonAggressiveMammal.todayEatCounter = 0;
        nonAggressiveMammal.setCounter(1);

        zoo.closeDay();

        assertTrue(zoo.getAmountVisitors() >= 20  && zoo.getAmountVisitors() <= 25);
        assertTrue(zoo.getBalance() == oldBalance + 30 * 0.6 * aggressiveMammal.getFullIncome());
    }

}