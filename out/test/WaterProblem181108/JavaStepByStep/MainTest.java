import JavaStepByStep.Bottle;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static JavaStepByStep.Main.bubbleSort;
import static JavaStepByStep.Main.getValidNumberInput;
import static JavaStepByStep.Main.searchSolutions;
import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void main() {

        //Test the function bubble sort
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

        //Test the function searchSolutions

        //Test the function tryAllPossibilities

        //Test the getValidNumberInput

        assertEquals(getValidNumberInput(),1);

    }
}