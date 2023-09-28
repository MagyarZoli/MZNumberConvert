package github.magyarzoli.junit5;

import github.magyarzoli.NumberConvert;
import github.magyarzoli.NumberConvertParameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.InvocationTargetException;

import static github.magyarzoli.NumberConvertAuxiliary.addNullString;
import static github.magyarzoli.ReflectionHandler.reflectionMethods;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberConvertParameterizedTest
extends NumberConvertParameters {

    private NumberConvert numberConvert;

    @BeforeEach
    public void setUp() {
        numberConvert = new NumberConvert();
    }

    @ParameterizedTest
    @MethodSource(value = "hunNumberNameResultParameters")
    public void hunNumberNameInvoke(long number, String expected)
    throws InvocationTargetException, IllegalAccessException {
        assertEquals(
                addNullString(expected, number),
                reflectionMethods(NumberConvert.class).get("hunNumberName").invoke(
                        numberConvert, number));
    }

    @ParameterizedTest
    @MethodSource(value = "hunNumberNameResultParameters")
    public void hunNumberNameResultInvoke(long number, String expected)
    throws InvocationTargetException, IllegalAccessException {
        numberConvert.conversionNumberName(number, "hu");
        assertEquals(
                addNullString(expected, number),
                reflectionMethods(NumberConvert.class).get("hunNumberNameResult").invoke(numberConvert));
    }

    @ParameterizedTest
    @MethodSource(value = "reverseArrayParameters")
    public void reverseArrayInvoke(int[] numberArray, int[] expected)
    throws InvocationTargetException, IllegalAccessException {
        assertArrayEquals(
                expected,
                (int[]) reflectionMethods(NumberConvert.class).get("reverseArray").invoke(
                        numberConvert, (Object) numberArray));
    }

    @ParameterizedTest
    @MethodSource(value = "reviewNumberParameters")
    public void reviewNumberInvoke(
            int hundreds, int tens, int ones, int[] array, int index, String extension, String expected)
    throws InvocationTargetException, IllegalAccessException {
        assertEquals(
                expected,
                reflectionMethods(NumberConvert.class).get("reviewNumber").invoke(
                        numberConvert, hundreds, tens, ones, array, index, extension));
    }

    @ParameterizedTest
    @MethodSource(value = "hyphenParameters")
    public void hyphenInvoke(int[] array, int index, String expected)
    throws InvocationTargetException, IllegalAccessException {
        assertEquals(
                expected,
                reflectionMethods(NumberConvert.class).get("hyphen").invoke(
                        numberConvert, array, index));
    }

    @ParameterizedTest
    @MethodSource(value = "branchesParameters")
    public void branchesInvoke(int ones, int tens, int hundreds, String hyphen, String extension, String expected)
    throws InvocationTargetException, IllegalAccessException {
        assertEquals(
                expected,
                reflectionMethods(NumberConvert.class).get("branches").invoke(
                        numberConvert, ones, tens, hundreds, hyphen, extension));
    }
}