package github.magyarzoli.junit5;

import github.magyarzoli.NumberConvert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

public class NumberConvertExceptionTest {

    private NumberConvert numberConvert;

    @BeforeEach
    public void setUp() {
        numberConvert = new NumberConvert();
    }

    @Test
    public void numberConvertException() {
        assertThrows(IllegalArgumentException.class, () -> numberConvert.conversionNumberName(7L, "en"));
    }

    @Test
    public void constructorInvokeException() {
        AtomicReference<NumberConvert> numberConvertInvoke = new AtomicReference<>();
        assertThrows(NullPointerException.class, () -> numberConvertInvoke.set(new NumberConvert(null)));
    }

    @Test
    public void splitInvokeException() {
        assertThrows(NullPointerException.class, () -> numberConvert.setSplit(null));
    }

    @Test
    public void languagesVerify() {
        String exception = assertThrows(IllegalArgumentException.class, () ->
                numberConvert.conversionNumberName(222L, "en")
        ).getMessage();
        assertTrue(exception.contains("specified language nothing! choose from the language list:"));
        assertTrue(exception.contains("hu"));
    }
}