package github.magyarzoli.junit4;

import github.magyarzoli.NumberConvert;
import github.magyarzoli.NumberConvertParameters;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.InvocationTargetException;

import static github.magyarzoli.NumberConvertAuxiliary.addNullString;
import static github.magyarzoli.ReflectionHandler.reflectionMethods;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class NumberConvertParameterizedTest
extends NumberConvertParameters {

    private NumberConvert numberConvert;

    @Before
    public void setUp() {
        numberConvert = new NumberConvert();
    }

    @Test
    @Parameters(method = "hunNumberNameResultParameters")
    public void hunNumberNameInvoke(long number, String expected)
    throws InvocationTargetException, IllegalAccessException {
        assertEquals(
                addNullString(expected, number),
                reflectionMethods(NumberConvert.class).get("hunNumberName").invoke(
                        numberConvert, number));
    }

    @Test
    @Parameters(method = "hunNumberNameResultParameters")
    public void hunNumberNameResultInvoke(long number, String expected)
    throws InvocationTargetException, IllegalAccessException {
        numberConvert.conversionNumberName(number, "hu");
        assertEquals(
                addNullString(expected, number),
                reflectionMethods(NumberConvert.class).get("hunNumberNameResult").invoke(numberConvert));
    }

    @Test
    @Parameters(method = "reverseArrayParameters")
    public void reverseArrayInvoke(int[] numberArray, int[] expected)
    throws InvocationTargetException, IllegalAccessException {
        assertArrayEquals(
                expected,
                (int[]) reflectionMethods(NumberConvert.class).get("reverseArray").invoke(
                        numberConvert, (Object) numberArray));
    }

    @Test
    @Parameters(method = "reviewNumberParameters")
    public void reviewNumberInvoke(
            int hundreds, int tens, int ones, int[] array, int index, String extension, String expected)
    throws InvocationTargetException, IllegalAccessException {
        assertEquals(
                expected,
                reflectionMethods(NumberConvert.class).get("reviewNumber").invoke(
                        numberConvert, hundreds, tens, ones, array, index, extension));
    }

    @Test
    @Parameters(method = "hyphenParameters")
    public void hyphenInvoke(int[] array, int index, String expected)
    throws InvocationTargetException, IllegalAccessException {
        assertEquals(
                expected,
                reflectionMethods(NumberConvert.class).get("hyphen").invoke(
                        numberConvert, array, index));
    }

    @Test
    @Parameters(method = "branchesParameters")
    public void branchesInvoke(int ones, int tens, int hundreds, String hyphen, String extension, String expected)
    throws InvocationTargetException, IllegalAccessException {
        assertEquals(
                expected,
                reflectionMethods(NumberConvert.class).get("branches").invoke(
                        numberConvert, ones, tens, hundreds, hyphen, extension));
    }
}