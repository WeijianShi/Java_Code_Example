import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Test1 {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private ArrayList<String> getOutputToArrayList() {
    	ArrayList<String> result = new ArrayList<String> (Arrays.asList(testOut.toString().split("\n")));
        return result;
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }
    
    @Test
    public void testCaseA() throws FileNotFoundException {
        provideInput("testCase1.txt");
        TAChecker.main(null);
        assertTrue(getOutputToArrayList().size()==1);
    }
    @Test
    public void testCaseB() throws FileNotFoundException {
        provideInput("testCase2.txt");
        TAChecker.main(null);
        assertTrue(getOutputToArrayList().contains("5;Den;UNSTARTED_JOB"));
        assertTrue(getOutputToArrayList().size()==2);
    }
    @Test
    public void testCaseC() throws FileNotFoundException {
        provideInput("testCase3.txt");
        TAChecker.main(null);
        assertTrue(getOutputToArrayList().contains("3;Evil;SHORTENED_JOB"));
        assertTrue(getOutputToArrayList().size()==2);
    }
    
    @Test
    public void testCaseD() throws FileNotFoundException {
        provideInput("testCase4.txt");
        TAChecker.main(null);
        assertTrue(getOutputToArrayList().contains("3;Jen;SHORTENED_JOB") && getOutputToArrayList().contains( "4;Jen;SHORTENED_JOB"));
        assertTrue(getOutputToArrayList().size()==3);
    }

}