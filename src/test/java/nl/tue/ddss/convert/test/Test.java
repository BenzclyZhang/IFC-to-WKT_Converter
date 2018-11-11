package nl.tue.ddss.convert.test;

import java.io.IOException;

import nl.tue.ddss.bimsparql.geometry.ewkt.WktWriteException;
import nl.tue.ddss.convert.Ifc2WktConverter;
import nl.tue.ddss.convert.IfcVersionException;

public class Test {
	
	public static void main(String[] args) throws IOException, WktWriteException, IfcVersionException {
		Ifc2WktConverter.convertToWkt("C:\\Data\\Ghent\\Duplex_A_20110907.ifc", "C:\\Data\\Ghent\\Duplex_A_20110907_geometry.ttl", "http://www.tue.nl/test/");
	}

}
