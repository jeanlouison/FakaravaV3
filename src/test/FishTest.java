package test;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import ecosystem.*;
import control.*;

/**
 * FishTest
 */
public class FishTest {

    int biteFactor = 10,
        maxCurrentStrength = 20,
        maxDensity = 3,
        n = 4,
        predatorCloneTime = 10,
        preyCloneTime = 5;

    //On instancie une Prey car Fish est abstrait. On ne teste ici que les methodes de Fish
    Prey p1;
    Prey pOffLimits;
	
	@Before	
	public void setUp() throws Exception 
    {
        Fakarava.init(biteFactor, maxCurrentStrength, maxDensity, n, predatorCloneTime,
        preyCloneTime, null);
        
        p1 = new Prey("richard", 5, 20.3, 2, 3, 80);
        pOffLimits = new Prey("richard", 5, -15.4, 7, 8, 80);
    }
	
    /**
     * @see Fish#getName()
     */
    @Test
    public void testGetName() 
    {
        assertEquals("richard", p1.getName());
    }
        
    /**
     * @see Fish#getPosition()
     */
    @Test
    public void testGetPosition() 
    {
        assertEquals(2, p1.getPosition()[0]);
        assertEquals(3, p1.getPosition()[1]);

        assertEquals(4, pOffLimits.getPosition()[0]);
        assertEquals(4, pOffLimits.getPosition()[1]);
    }

    /**
     * @see Fish#getWeight()
     */
    @Test
    public void testGetWeight() 
    {
        assertThat(20.3, is(p1.getWeight()));
    }
    
    /**
     * @see Fish#setPosition()
     */
    @Test
    public void testSetPosition()
    {
        p1.setPosition(1, 2);
        assertEquals(1, p1.getPosition()[0]);
        assertEquals(2, p1.getPosition()[1]);

        pOffLimits.setPosition(50, 80);
        assertEquals(Lagoon.getLength(), pOffLimits.getPosition()[0]);
        assertEquals(Lagoon.getLength(), pOffLimits.getPosition()[1]);
    }
    
    /**
     * @see Fish#setPosition()
     */
    @Test (expected = IllegalArgumentException.class)
    public void testSetNegativePosition()
    {
    	pOffLimits.setPosition(-10, 5);
    	pOffLimits.setPosition(5, -10);
    }

    /**
     * @see Fish#setWeight()
     */
    @Test
    public void testSetWeight() 
    {
        p1.setWeight(50.0);
        assertThat(50.0, is(p1.getWeight()));
    }

    /**
     * @see Fish#setWeight()
     */
    @Test (expected = IllegalArgumentException.class)
    public void testSetNegativeWeight()
    {
        pOffLimits.setWeight(-43.7);
    }
}