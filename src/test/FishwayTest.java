package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import ecosystem.*;
import control.*;

/**
 * FishwayTest
 */
public class FishwayTest {

    int biteFactor = 10,
    maxCurrentStrength = 20,
    maxDensity = 3,
    n = 4,
    predatorCloneTime = 10,
    preyCloneTime = 5;

    Fishway f1;
    Fishway fOffLimits;

    @Before
    public void SetUp() throws Exception
    {
    	Fakarava.init(biteFactor, maxCurrentStrength, maxDensity, maxCurrentStrength, predatorCloneTime, preyCloneTime, null);
        f1 = new Fishway(1, 2, 3);
    }    
    
    /**
     * @see Fishway#Fishway()
     */
    @Test (expected = IllegalArgumentException.class)
    public void testFishwayPositionOffLimits()
    {
    	fOffLimits = new Fishway(2, 50, -30);
    }

    /**
     * @see Fishway#getNum()
     */
    @Test
    public void testGetNum() {
        assertEquals(1, f1.getNum());
    }

    /**
     * @see Fishway#getPosition()
     */
    @Test
    public void testGetPosition() 
    {
        assertEquals(2, f1.getPosition()[0]);
        assertEquals(3, f1.getPosition()[1]);
    }

    /**
     * @see Fishway#getMaxCurrentStrength()
     */
    @Test
    public void testGetMaxCurrentStrength()
    {
        assertEquals(maxCurrentStrength, Fishway.getMaxCurrentStrength());
    }
    

    /**
     * @see Fishway#setStrength()
     */
    @Test
    public void testSetStrength()
    {
    	double newStrength = 30.0;
    	f1.setStrength(newStrength);
    	assertEquals(newStrength, f1.getStrength(), 0);
    }

    
    /**
     * @see Fishway#setStrength()
     */
    @Test (expected = IllegalArgumentException.class)
    public void testSetNegativeStrength()
    {
    	f1.setStrength(-5.0);
    }

    /**
     * @see Fishway#getPredatorList()
     */
    @Test
    public void testGetPredatorList()
    {
        assertEquals(0, f1.getPredatorList().size());
        Fakarava.createPredator("hugo", 45.7, f1.getPosition()[0], f1.getPosition()[1]);
        assertEquals(1, f1.getPredatorList().size());
    }

    /**
     * @see Fishway#getPreyList()
     */
    @Test
    public void testGetPreyList()
    {
        assertEquals(0, f1.getPreyList().size());
        Fakarava.createPrey("hugo", 45.7, f1.getPosition()[0], f1.getPosition()[1], 50);
        assertEquals(1, f1.getPreyList().size());
    }

    /**
     * @see Fishway#sortPreyListByVivacity()
     */
    @Test
    public void testSortPreyListByVivacity()
    {
        Fakarava.createPrey("moinsvivace", 45.7, f1.getPosition()[0], f1.getPosition()[1], 10);
        Fakarava.createPrey("plusvivace", 45.7, f1.getPosition()[0], f1.getPosition()[1], 80);
        f1.sortPreyListByVivacity();
        assertEquals("plusvivace", f1.getPreyList().get(0).getName());
    }

    /**
     * @see Fishway#sortPredatorListByWeight()
     */
    @Test
    public void testSortPredatorListByWeight()
    {
        Fakarava.createPredator("moinsgros", 10.2, f1.getPosition()[0], f1.getPosition()[1]);
        Fakarava.createPredator("plusgros", 80.2, f1.getPosition()[0], f1.getPosition()[1]);
        f1.sortPredatorListByWeight();
        assertEquals("plusgros", f1.getPredatorList().get(0).getName());
    }

    /**
     * @see Fishway#sortPredatorListByWeight()
     */
    @Test(expected = NullPointerException.class)
    public void testEmptySortPredatorListByWeight()
    {
        f1.sortPredatorListByWeight();
    }

    /**
     * @see Fishway#sortPreyListByVivacity()
     */
    @Test(expected = NullPointerException.class)
    public void testEmptySortPreyListByVivacity()
    {
        f1.sortPreyListByVivacity();
    }

    /**
     * @see Fishway#deletePredator()
     */
    @Test
    public void testDeletePredator()
    {
        int solo = Fakarava.createPredator("solo", 80.2, f1.getPosition()[0], f1.getPosition()[1]);
        f1.deletePredator(solo);
        assertEquals(0, f1.getPredatorList().size());
    }

    /**
     * @see Fishway#deletePredator()
     */
    @Test(expected = NullPointerException.class)
    public void testDeletePredatorNotInList()
    {
        f1.deletePredator(50);
    }

    /**
     * @see Fishway#deletePrey()
     */
    @Test
    public void testDeletePrey()
    {
        int solo = Fakarava.createPrey("solo", 80.2, f1.getPosition()[0], f1.getPosition()[1], 42);
        f1.deletePrey(solo);
        assertEquals(0, f1.getPreyList().size());
    }

    /**
     * @see Fishway#deletePrey()
     */
    @Test(expected = NullPointerException.class)
    public void testDeletePreyNotInList()
    {
        f1.deletePrey(50);
    }
}
