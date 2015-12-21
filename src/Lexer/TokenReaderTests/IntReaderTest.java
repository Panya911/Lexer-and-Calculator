package Lexer.TokenReaderTests;

import Lexer.TokenReaders.IntReader;
import Lexer.TokenReaders.Token;
import junit.framework.TestCase;
//TODO junit.framework � ��� ��������� ����������, ������� ����� �������� � ������ �������.
//Eclipse ����� ��� � ���� ������ � ��������� ������ �� ������������ �����, ������� Ctrl+1 � �������� ����� Fix project setup

public class IntReaderTest extends TestCase {
	// ������ ������:
	// ���������� ������ Alt+Shift+X, T
	// ��� ������� ������������ ���� (�� ������ ������) -> Run As -> JUnit Test
	private IntReader reader = new IntReader();

	public void testNotANumbers() {
		assertNull(reader.tryReadToken(""));
		assertNull(reader.tryReadToken("_15"));
		assertNull(reader.tryReadToken("qwerty"));
	}

	public void testDecimal() {
		checkInt("0", "0", 0);
		checkInt("1", "1", 1);
		checkInt("15__15", "15__15", 1515);
		checkInt("1515_", "1515", 1515);
		checkInt("1515", "1515", 1515);
		checkInt("15__15", "15__15", 1515);
	}

	public void testHex() {
		checkInt("0xA", "0xA", 10);
		checkInt("0xa", "0xa", 10);
		checkInt("0xAC", "0xAC", 172);
		checkInt("0x2_3", "0x2_3", 35);
	}

	public void testBinary() {
		checkInt("0b0", "0b0", 0);
		checkInt("0b1", "0b1", 1);
		checkInt("0b101", "0b101", 5);
		checkInt("0b10_1", "0b10_1", 5);
	}

	public void testOct() {
		checkInt("012", "012", 10);
		checkInt("072", "072", 58);
		checkInt("078", "07", 7);
	}

	private void checkInt(String input, String expectedToken, double expectedValue) {
		Token token = reader.tryReadToken(input);
		assertEquals("i", token.getType());
		assertEquals(expectedToken, token.getText());
		assertEquals(expectedValue, token.getValue());
	}
}
