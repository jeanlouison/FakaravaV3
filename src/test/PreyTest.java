package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import ecosystem.*;
import control.*;

/**
 * PreyTest
 */
public class PreyTest {

        int biteFactor = 10,
            maxCurrentStrength = 20,
            maxDensity = 3,
            n = 4,
            predatorCloneTime = 10,
            preyCloneTime = 5;

	Prey p1;
	
	@Before	
	public void setUp() throws Exception 
    {
        Fakarava.init(biteFactor, maxCurrentStrength, maxDensity, n, predatorCloneTime,
        preyCloneTime, null);
        
        p1 = new Prey("richard", 5, 0.5, 2, 3, 80);
    }
        
    /**
     * @see Prey#getVivacity()
     */
    @Test
    public void testGetVivacity() 
    {
        assertEquals(80, p1.getVivacity());
    }

    /**
     * @see Prey#getPreyCloneTime()
     */
    @Test
    public void testGetPreyCloneTime() 
    {
        assertEquals(preyCloneTime, Prey.getPreyCloneTime());
    }

    /**
     * @see Prey#getMaxDensity()
     */
    @Test
    public void testGetMaxDensity() 
    {
        assertEquals(maxDensity, Prey.getMaxDensity());
    }
}