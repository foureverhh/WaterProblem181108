package JavaStepByStep;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static JavaStepByStep.Main.bubbleSort;
import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void main() {

        //Test bubble sort
        List<String> bubbleSortTest = new ArrayList<>();
        bubbleSortTest.add("aa");
        bubbleSortTest.add("aaa");
        bubbleSortTest.add("aaaa");
        bubbleSortTest.add("a");
        bubbleSort(bubbleSortTest);
        List<String> bubbleSorted = new ArrayList<>();
        bubbleSorted.add("a");
        bubbleSorted.add("aa");
        bubbleSorted.add("aaa");
        bubbleSorted.add("aaaa");
        assertEquals(bubbleSortTest,bubbleSorted);

        //
        //Bottle bigBottle =
    }
}