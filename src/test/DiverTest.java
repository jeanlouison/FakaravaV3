package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import ecosystem.*;
import control.*;

/**
 * DiverTest
 */
public class DiverTest {

    int biteFactor = 10,
        maxCurrentStrength = 20,
        maxDensity = 3,
        n = 4,
        predatorCloneTime = 10,
        preyCloneTime = 5;

    Diver d1;
    Fishway f1;
    int numd1;
    int numf1;
	
	@Before	
	public void setUp() throws Exception 
    {
        Fakarava.init(biteFactor, maxCurrentStrength, maxDensity, n, predatorCloneTime,
        preyCloneTime, null);
        
        numd1 = Lagoon.addDiver("jose", "lab");
        d1 = Lagoon.getDiver(numd1);

        numf1 = Fakarava.createFishway(1, 1);
        f1 = Lagoon.getFishway(numf1);
        
        Fakarava.putCamera(numd1, numf1);
        
        // pOffLimits = new Prey("richard", 5, -15.4f, 7, 8, 80);
    }
	
    /**
     * @see Diver#getName()
     */
    @Test
    public void testGetName() 
    {
        assertEquals("jose", d1.getName());
    }
        
    /**
     * @see Diver#getId()
     */
    @Test
    public void testGetId() 
    {
        assertEquals(1, d1.getId());
    }

    /**
     * @see Diver#putTransmitter()
     */
    @Test
    public void testPutTransmitter() 
    {
        assertEquals(0, d1.getTransmittersList().size());
        d1.putTransmitter(f1);
        assertEquals(1, d1.getTransmittersList().size());
    }

    /**
     * @see Diver#putCamera()
     */
    @Test
    public void testPutCamera() 
    {
        assertEquals(0, d1.getCamerasList().size());
        d1.putCamera(f1);
        assertEquals(1, d1.getCamerasList().size());
    }

    /**
     * @see Diver#removeTransmitter()
     */
    @Test
    public void testRemoveTransmitter() 
    {
        System.out.println("s"+d1.getTransmittersList().size());
        assertEquals(1, d1.getTransmittersList().size());
        d1.removeTransmitter(1);
        assertEquals(0, d1.getTransmittersList().size());
    }

    /**
     * @see Diver#removeTransmitter()
     */
    @Test (expected = NullPointerException.class)
    public void testRemoveUnknownTransmitter() 
    {
        d1.removeTransmitter(23);
    }

     /**
     * @see Diver#removeCamera()
     */
    @Test
    public void testRemoveCamera() 
    {
    	d1.putCamera(f1);
        assertEquals(1, d1.getCamerasList().size());
        d1.removeCamera(0);
        assertEquals(0, d1.getCamerasList().size());
    }

    /**
     * @see Diver#removeCamera()
     */
    @Test (expected = NullPointerException.class)
    public void testRemoveUnknownCamera() 
    {
        d1.removeCamera(23);
    }

    /**
     * @see Diver#toString()
     */
    @Test 
    public void testToString()
    {
        assertEquals("Plongeur 2 : jose (lab)", d1.toString());
    }
}