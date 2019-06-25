package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import ecosystem.*;
import control.*;

/**
 * PredatorTest
 */
public class PredatorTest {

        int biteFactor = 10,
            maxCurrentStrength = 20,
            maxDensity = 3,
            n = 4,
            predatorCloneTime = 10,
            preyCloneTime = 5;

	Predator p1;
	
	@Before	
	public void setUp() throws Exception 
    {
        Fakarava.init(biteFactor, maxCurrentStrength, maxDensity, n, predatorCloneTime,
        preyCloneTime, null);
        
        p1 = new Predator("philipe", 5, 0.5, 2, 3);
    }
        
    /**
     * @see Predator#getBiteFactor()
     */
    @Test
    public void testGetBiteFactor() 
    {
        assertEquals(biteFactor, Predator.getBiteFactor());
    }

    /**
     * @see Predator#getPredatorCloneTime()
     */
    @Test
    public void testGetPredatorCloneTime() 
    {
        assertEquals(predatorCloneTime, Predator.getPredatorCloneTime());
    }
}