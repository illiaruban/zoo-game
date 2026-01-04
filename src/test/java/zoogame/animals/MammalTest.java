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

        zoo.setAmountVisitors(30);
        zoo.closeDay();

        //amountVisitors -= random.nextInt(lowerLimit, upperLimit) * 1
        //lowerlimit 5, upperLimit 10
        //20 <= amountVisitors <= 25
        assertTrue(zoo.getAmountVisitors() >= 20  && zoo.getAmountVisitors() <= 25);
        assertTrue(zoo.getBalance() == oldBalance + 600);

    }



}