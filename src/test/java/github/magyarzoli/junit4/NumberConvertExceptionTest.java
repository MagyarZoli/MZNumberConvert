package github.magyarzoli.junit4;

import github.magyarzoli.NumberConvert;
import org.junit.Before;
import org.junit.Test;

import static github.magyarzoli.NumberConvertAuxiliary.addNullString;
import static org.junit.Assert.assertEquals;

public class NumberConvertExceptionTest {

    private NumberConvert numberConvert;

    @Before
    public void setUp() {
        numberConvert = new NumberConvert();
    }

    @Test(expected = IllegalArgumentException.class)
    public void numberConvertException() {
        assertEquals(
                addNullString("hét", 7L),
                numberConvert.conversionNumberName(7L, "en"));
    }

    @Test(expected = NullPointerException.class)
    public void constructorInvokeException() {
        NumberConvert numberConvertInvoke = new NumberConvert(null);
    }

    @Test(expected = NullPointerException.class)
    public void splitInvokeException() {
        numberConvert.setSplit(null);
        assertEquals(";", numberConvert.getSplit());
    }
}