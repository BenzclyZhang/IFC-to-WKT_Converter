package nl.tue.ddss.convert;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import nl.tue.ddss.bimsparql.geometry.convert.GeometryConverter;
import nl.tue.ddss.bimsparql.geometry.ewkt.WktWriteException;

public class Ifc2WktConverter {	
	
	private static String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

	/**
	 * Default namespace for the output RDF file.
	 */
	public static final String DEFAULT_PATH = "http://linkedbuildingdata.net/ifc/resources" + timeLog + "/";
	
	public static void main(String[] args) throws IOException, IfcVersionException, WktWriteException {
		Options options = new Options();
		Option baseuri = Option.builder("b").longOpt("baseuri").argName("uri").hasArg(true).required(false)
				.desc("set base uri for converted RDF instances").build();
		Option version = Option.builder("v").longOpt("version").argName("schema_version").hasArg(true).required(false)
				.desc("manually set used schema version (available values are \"IFC2X3_TC1\",\"IFC2X3_FINAL\",\"IFC4\",\"IFC4X1_RC3\",\"IFC4_ADD1\",\"IFC4_ADD2\")").build();	
		options.addOption(baseuri);
		options.addOption(version);

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd;
		try {

			cmd = parser.parse(options, args);
			if (cmd.getArgs() == null || cmd.getArgs().length != 2) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("java -jar IfcSTEP2WKT.jar <input.ifc> <output.xxx> [options]", options);
			} else {
				String baseURI = cmd.getOptionValue("baseuri");
				String ifcVersion=cmd.getOptionValue("version");
				IfcVersion ifcv=null;
				if (ifcVersion==null) {
					FileInputStream input2=new FileInputStream(args[0]);
					Header header = HeaderParser.parseHeader(input2);
					ifcv=IfcVersion.getIfcVersion(header);
				} else {
					ifcv=IfcVersion.getIfcVersion(ifcVersion);
				}
				if (baseURI != null) {
					convertToWkt(args[0], args[1], baseURI, ifcv);
				} else {
					convertToWkt(args[0], args[1], DEFAULT_PATH, ifcv);
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.err.println("Parsing command line failed.  Reason: " + e.getMessage());
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("java -jar IfcSTEP2IfcOWL.jar <input.ifc> <output.xxx> [options]", options);
		}
	}
	
	public static void convertToWkt(String in,String out,String baseUri,IfcVersion version) throws IOException, WktWriteException {
		long start = System.currentTimeMillis();
		InputStream input=new FileInputStream(in);
		OutputStream output=new FileOutputStream(out);
		GeometryConverter converter=new GeometryConverter(baseUri);
		converter.parseModel2GeometryStream(input, output, version, false);		
		long end = System.currentTimeMillis();
		System.out.println("Total conversion time: " + ((float) (end - start)) / 1000 + " s");
	}
	
	public static void convertToWkt(String in,String out,String baseUri) throws IOException, WktWriteException, IfcVersionException {
		IfcVersion version=null;
		FileInputStream input2=new FileInputStream(in);
		Header header = HeaderParser.parseHeader(input2);
		version=IfcVersion.getIfcVersion(header);
		convertToWkt(in,out,baseUri,version);
	}
	
	public static void convertToWkt(String in,String out) throws IOException, WktWriteException, IfcVersionException {
		convertToWkt(in,out,DEFAULT_PATH);
	}

}
