package controllers;

import parsers.CSVArticuloFileParse;
import parsers.IParser;

public class ParserBuilder {

	public static IParser builderParser(String fileName) {
		IParser parse = ParserEnum.valueOf(getExt(fileName)).parser;
		
		return null;
	}
	
	public static String getExt(String fileName) {
		String[] aux = fileName.split("\\.");
		//[bla,doc]
		return aux[aux.length-1].toUpperCase();
	}
	
	private enum ParserEnum {
		CSV(new CSVArticuloFileParse()),
		XLS(new CSVArticuloFileParse()),
		XSLX(new CSVArticuloFileParse())
		;
		
		private IParser parser;		
		
		private ParserEnum(IParser parser) {
			this.parser = parser;
		}		
	

	}
}
