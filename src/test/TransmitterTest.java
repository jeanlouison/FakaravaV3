package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import ecosystem.*;
import control.*;

/**
 * TransmitterTest
 */
public class TransmitterTest {

    int biteFactor = 10,
        maxCurrentStrength = 20,
        maxDensity = 3,
        n = 4,
        predatorCloneTime = 10,
        preyCloneTime = 5;

    Transmitter t1;
    Diver d1;
    Fishway f1;
    Predator p1;
    
	@Before	
	public void setUp() throws Exception 
    {
        Fakarava.init(biteFactor, maxCurrentStrength, maxDensity, n, predatorCloneTime,
        preyCloneTime, null);
        
        p1 = new Predator("juan", 1, 27.0, 2, 3);
        d1 = new Diver("hamza", 1, "nhf");
        
        t1 = new Transmitter(0, p1, d1);
    }
	
    /**
     * @see Transmitter#getNum()
     */
    @Test
    public void testGetNum() 
    {
        assertEquals((int)0, (int)t1.getNum());
    }
    
    /**
     * @see Transmitter#getDiver()
     */
    @Test
    public void testGetDiver() 
    {
        assertEquals(d1, t1.getDiver());
    }
    
    /**
     * @see Transmitter#getFishway()
     */
    @Test
    public void testPredator() 
    {
        assertEquals(p1, t1.getPredator());
    }        

}