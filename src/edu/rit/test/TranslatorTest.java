package edu.rit.test;

import org.junit.Test;

import edu.rit.dao.impl.stream.Translator;

public class TranslatorTest {

	@Test
	public void test() {
		Translator.translate("C:\\workspace\\Streams\\src\\edu\\rit\\test\\classDefinition.txt");
	}

}
