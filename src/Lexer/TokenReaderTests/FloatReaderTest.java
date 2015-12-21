package Lexer.TokenReaderTests;

import Lexer.TokenReaders.FloatNumberReader;
import Lexer.TokenReaders.Token;
import junit.framework.TestCase;
import org.junit.Test;

public class FloatReaderTest extends TestCase{
    //************* Invalid ***************

    @Test
    public void testNotFloatAfterPoint() {
        assertNull(getFloatToken(".i"));
    }

    //************* Float ***************

    @Test
    public void testSimpleFloat() {
        Token token = getFloatToken("1e1f");
        assertsToken(token, "1e1f");
    }

    @Test
    public void testFloatWithDotWithoutFloatPart() {
        Token token = getFloatToken("2.f");
        assertsToken(token, "2.f");
    }

    @Test
    public void testFloatWithDotWithoutIntPart() {
        Token token = getFloatToken(".3f");
        assertsToken(token, ".3f");
    }

    @Test
    public void testFloatZero() {
        Token token = getFloatToken("0f");
        assertsToken(token, "0f");
    }

    @Test
    public void testFloatPI() {
        Token token = getFloatToken("3.14f");
        assertsToken(token, "3.14f");
    }

    @Test
    public void testFullFloat() {
        Token token = getFloatToken("6.022137e+23f");
        assertsToken(token, "6.022137e+23f");
    }

    @Test
    public void testFullFloatWithNegativeExponent() {
        Token token = getFloatToken("6.022137e-23f");
        assertsToken(token, "6.022137e-23f");
    }

    //************* DOUBLE ***************

    @Test
    public void testSimpleDouble() {
        Token token = getFloatToken("1e1");
        assertsToken(token, "1e1");
    }

    @Test
    public void testDoubleWithDotWithoutFloatPart() {
        Token token = getFloatToken("2.");
        assertsToken(token, "2.");
    }

    @Test
    public void testDoubleWithDotWithoutIntPart() {
        Token token = getFloatToken(".3");
        assertsToken(token, ".3");
    }

    @Test
    public void testDoubleZero() {
        Token token = getFloatToken("0.0");
        assertsToken(token, "0.0");
    }

    @Test
    public void testDoublePI() {
        Token token = getFloatToken("3.14");
        assertsToken(token, "3.14");
    }

    @Test
    public void testDoubleNegativeExponent() {
        Token token = getFloatToken("1e-9d");
        assertsToken(token, "1e-9d");
    }

    @Test
    public void testDoubleExponentWithoutSign() {
        Token token = getFloatToken("1e137");
        assertsToken(token, "1e137");
    }

    @Test
    public void testDoubleWithEInEnd() {
        Token token = getFloatToken("123e");
        assertsToken(token, "123");
    }

    private Token getFloatToken(String input) {
        FloatNumberReader reader = new FloatNumberReader();
        return reader.tryReadToken(input);
    }

    private void assertsToken(Token token, String expectedComment) {
        assertEquals(expectedComment, token.getText());
        assertEquals(expectedComment, token.getValue());
        assertEquals("f", token.getType());
    }
}