package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import ecosystem.*;
import control.*;

/**
 * CameraTest
 */
public class CameraTest {

    int biteFactor = 10,
        maxCurrentStrength = 20,
        maxDensity = 3,
        n = 4,
        predatorCloneTime = 10,
        preyCloneTime = 5;

    Camera c1;
    Diver d1;
    Fishway f1;
	
	@Before	
	public void setUp() throws Exception 
    {
        Fakarava.init(biteFactor, maxCurrentStrength, maxDensity, n, predatorCloneTime,
        preyCloneTime, null);
        
        f1 = new Fishway(1, 2, 3);
        d1 = new Diver("hamza", 1, "nhf");
        d1.putCamera(f1);
        c1 = d1.getCamera(0);
    }
	
    /**
     * @see Camera#getNum()
     */
    @Test
    public void testGetNum() 
    {
        assertEquals((int)0, (int)c1.getNum());
    }
    
    /**
     * @see Camera#getDiver()
     */
    @Test
    public void testGetDiver() 
    {
        assertEquals(d1, c1.getDiver());
    }
    
    /**
     * @see Camera#getFishway()
     */
    @Test
    public void testGetFishway() 
    {
        assertEquals(f1, c1.getFishway());
    }        

}