package github.magyarzoli.junit5;

import github.magyarzoli.NumberConvert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static github.magyarzoli.NumberConvertAuxiliary.addNullString;
import static github.magyarzoli.ReflectionHandler.reflectionMethods;
import static org.junit.jupiter.api.Assertions.*;

public class NumberConvertTest {

    private NumberConvert numberConvert;

    @BeforeEach
    public void setUp() {
        numberConvert = new NumberConvert();
    }

    @Test
    public void numberConvert() {
        assertEquals(
                addNullString("hét", 7L),
                numberConvert.conversionNumberName(7L, "hu"));
    }

    @Test
    public void constructorInvoke() {
        NumberConvert numberConvertInvoke = new NumberConvert(":");
        assertEquals(":", numberConvertInvoke.getSplit());
    }

    @Test
    public void splitInvoke() {
        assertNull(numberConvert.getSplit());
        numberConvert.setSplit(";");
        assertEquals(";", numberConvert.getSplit());
    }

    @Test
    public void hunNumberNameInvoke()
    throws InvocationTargetException, IllegalAccessException {
        assertEquals(
                addNullString("négyszáznegyvennégy", 444L),
                reflectionMethods(NumberConvert.class).get("hunNumberName").invoke(
                        numberConvert, 444L));
    }

    @Test
    public void hunNumberNameResultInvoke()
    throws InvocationTargetException, IllegalAccessException {
        numberConvert.conversionNumberName(110L, "hu");
        assertEquals(
                addNullString("száztíz", 110L),
                reflectionMethods(NumberConvert.class).get("hunNumberNameResult").invoke(numberConvert));
    }

    @Test
    public void reverseArrayInvoke()
    throws InvocationTargetException, IllegalAccessException {
        assertArrayEquals(
                new int[]{0, 9 ,1, 8, 2, 7, 3, 6, 0, 0, 0, 0},
                (int[]) reflectionMethods(NumberConvert.class).get("reverseArray").invoke(
                        numberConvert, (Object) new int[]{0, 9 ,1, 8, 2, 7, 3, 6}));
    }

    @Test
    public void reviewNumberInvoke()
    throws InvocationTargetException, IllegalAccessException {
        assertEquals(
                "nullkétszázhuszonkét",
                reflectionMethods(NumberConvert.class).get("reviewNumber").invoke(
                        numberConvert, 1, 1, 1, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2}, 12, ""));
    }

    @Test
    public void hyphenInvoke()
    throws InvocationTargetException, IllegalAccessException {
        assertEquals(
                "",
                reflectionMethods(NumberConvert.class).get("hyphen").invoke(
                        numberConvert, new int[]{1,2,3}, 3));
    }

    @Test
    public void branchesInvoke()
    throws InvocationTargetException, IllegalAccessException {
        assertEquals(
                "nullháromszázhuszonegyezer",
                reflectionMethods(NumberConvert.class).get("branches").invoke(
                        numberConvert, 1, 2, 3, "", "ezer"));
    }

    @Test
    public void languagesInvoke()
    throws InvocationTargetException, IllegalAccessException {
        assertEquals(
                Arrays.stream(new String[]{"hu"}).collect(Collectors.joining(" ")),
                reflectionMethods(NumberConvert.class).get("languages").invoke(numberConvert));
    }
}