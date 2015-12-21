package Lexer.TokenReaderTests;

import Lexer.TokenReaders.EOLCommentReader;
import Lexer.TokenReaders.Token;
import junit.framework.TestCase;;

public class EOLComentReaderTest extends TestCase {

	private final EOLCommentReader reader;
	
	public EOLComentReaderTest() {
		reader=new EOLCommentReader();
	}
	
	public void testComment()
	{
		String str= "//comment\n";
		Token result=reader.tryReadToken(str);
		assertEquals(new Token("eolc","//comment\n"),result);
	}
	
	public void testThatGetOnlyNeeded()
	{
		String str= "//comment\nEnd";
		Token result=reader.tryReadToken(str);
		assertEquals(new Token("eolc","//comment\n"),result);
	}
}
