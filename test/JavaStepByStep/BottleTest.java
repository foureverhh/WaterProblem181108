package JavaStepByStep;

import org.junit.Test;

import static org.junit.Assert.*;

public class BottleTest {

    private int currentVolume;
    private int fullVolume = 5;
    private int exceedVolume;
    private boolean justFilled;
    private boolean justEmptied;
    private boolean justTransferred;
    private boolean justGotTransferred;

    @Test
    //Test transfer water between two bottles
    public void transCase() {
        Bottle big = new Bottle(5);
        Bottle small = new Bottle(3);

        //Fill big bottle and then transfer water to small twice
        //Fill big bottle
        big.fillBottle();
        assertTrue(big.isJustFilled());
        assertEquals(big.getCurrentVolume(),5);
        //Transfer water from big to small for the first time
        big.transferVolume(small);
        assertEquals(big.getCurrentVolume(),2);
        assertTrue(big.isJustTransferred());
        assertFalse(big.isJustGotTransferred());
        assertFalse(big.isJustFilled());
        assertEquals(small.getCurrentVolume(),3);
        assertEquals(small.getExceedVolume(),0);
        assertTrue(small.isJustGotTransferred());
        assertFalse(small.isJustFilled());
        assertFalse(small.isJustEmptied());
        assertFalse(small.isJustTransferred());
        //Transfer water from big to small for the second time
        big.transferVolume(small);
        assertEquals(big.getCurrentVolume(),0);
        assertTrue(big.isJustTransferred());
        assertFalse(big.isJustGotTransferred());
        assertFalse(big.isJustFilled());
        assertFalse(big.isJustEmptied());
        assertEquals(small.getCurrentVolume(),3);
        assertEquals(small.getExceedVolume(),0);
        assertTrue(small.isJustGotTransferred());
        assertFalse(small.isJustFilled());
        assertFalse(small.isJustEmptied());
        assertFalse(small.isJustTransferred());


        big.resetBottle();
        small.resetBottle();
        assertEquals(big.getCurrentVolume(),0);
        assertEquals(small.getCurrentVolume(),0);
        //Fill big bottle and then transfer water to small, and then get water transferred back from small to big
        big.fillBottle();
        big.transferVolume(small);
        small.transferVolume(big);
        assertEquals(big.getCurrentVolume(),5);
        assertFalse(big.isJustTransferred());
        assertTrue(big.isJustGotTransferred());
        assertFalse(big.isJustFilled());
        assertFalse(big.isJustEmptied());
        assertEquals(small.getCurrentVolume(),0);
        assertFalse(small.isJustGotTransferred());
        assertFalse(small.isJustFilled());
        assertFalse(small.isJustEmptied());
        assertTrue(small.isJustTransferred());

        big.resetBottle();
        small.resetBottle();
        assertEquals(big.getCurrentVolume(),0);
        assertEquals(small.getCurrentVolume(),0);
        //Fill small bottle first and transfer water back to big bottle twice
        small.fillBottle();
        assertEquals(small.getCurrentVolume(),3);
        //Transfer water first time
        small.transferVolume(big);
        assertEquals(big.getCurrentVolume(),3);
        assertFalse(big.isJustTransferred());
        assertTrue(big.isJustGotTransferred());
        assertFalse(big.isJustFilled());
        assertFalse(big.isJustEmptied());
        assertEquals(small.getCurrentVolume(),0);
        assertFalse(small.isJustGotTransferred());
        assertFalse(small.isJustFilled());
        assertFalse(small.isJustEmptied());
        assertTrue(small.isJustTransferred());
        //Transfer water again
        small.transferVolume(big);
        assertEquals(big.getCurrentVolume(),3);
        assertFalse(big.isJustTransferred());
        assertTrue(big.isJustGotTransferred());
        assertFalse(big.isJustFilled());
        assertFalse(big.isJustEmptied());
        assertEquals(small.getCurrentVolume(),0);
        assertFalse(small.isJustGotTransferred());
        assertFalse(small.isJustFilled());
        assertFalse(small.isJustEmptied());
        assertTrue(small.isJustTransferred());

        big.resetBottle();
        small.resetBottle();
        assertEquals(big.getCurrentVolume(),0);
        assertEquals(small.getCurrentVolume(),0);
        //Fill small bottle and then transfer water to big, and then get water transferred back from big to small
        small.fillBottle();
        assertEquals(small.getCurrentVolume(),3);
        //Transfer water first time
        small.transferVolume(big);
        assertEquals(big.getCurrentVolume(),3);
        assertFalse(big.isJustTransferred());
        assertTrue(big.isJustGotTransferred());
        assertFalse(big.isJustFilled());
        assertFalse(big.isJustEmptied());
        assertEquals(small.getCurrentVolume(),0);
        assertFalse(small.isJustGotTransferred());
        assertFalse(small.isJustFilled());
        assertFalse(small.isJustEmptied());
        assertTrue(small.isJustTransferred());
        //Get water back from big to small
        big.transferVolume(small);
        assertEquals(big.getCurrentVolume(),0);
        assertTrue(big.isJustTransferred());
        assertFalse(big.isJustGotTransferred());
        assertFalse(big.isJustFilled());
        assertFalse(big.isJustEmptied());
        assertEquals(small.getCurrentVolume(),3);
        assertTrue(small.isJustGotTransferred());
        assertFalse(small.isJustFilled());
        assertFalse(small.isJustEmptied());
        assertFalse(small.isJustTransferred());
    }
    @Test
    public void isJustFilled() {
        assertFalse(justFilled);
    }

    @Test
    public void isJustEmptied() {
        assertFalse(justEmptied);
    }

    @Test
    public void isJustTransferred() {
        assertFalse(justTransferred);
    }

    @Test
    public void resetBottle() {
        assertFalse(justFilled);
        assertFalse(justGotTransferred);
        assertFalse(justTransferred);
        assertFalse(justEmptied);
        assertEquals(currentVolume,0);
    }

    @Test
    public void isJustGotTransferred() {
        assertFalse(justGotTransferred);
    }

    @Test
    public void getExceedVolume() {
        assertEquals(exceedVolume,0);
    }

    @Test
    public void setExceedVolume() {
        exceedVolume = 2;
        assertEquals(exceedVolume,2);
    }

    @Test
    public void getFullVolume() {
        assertEquals(fullVolume,5);
    }


    @Test
    public void fillBottle() {
        assertEquals(fullVolume,5);
    }

    @Test
    public void emptyBottle() {
        assertEquals(currentVolume,0);
    }
}