package nl.tue.ddss.convert;


import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import nl.tue.ddss.bimsparql.geometry.convert.GeometryConverter;

public class Ifc2WktConverter {	
	public static void main(String[] args) {
		Options options = new Options();
		Option baseuri = Option.builder("b").longOpt("baseuri").argName("uri").hasArg(true).required(false)
				.desc("set base uri for converted RDF instances").build();
		Option version = Option.builder("v").longOpt("version").argName("schema_version").hasArg(true).required(false)
				.desc("manually set used schema version (available values are \"IFC2X3_TC1\",\"IFC2X3_FINAL\",\"IFC4\",\"IFC4X1_RC3\",\"IFC4_ADD1\",\"IFC4_ADD2\")").build();
		Option boundingbox = new Option("bb", "bounding_box", false, "set whether to generate bounding box for products");	
		options.addOption(baseuri);
		options.addOption(version);
		options.addOption(boundingbox);

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd;
		GeometryConverter trial = new GeometryConverter("http://www.instance.com/data/");
		trial.convertGeometry("D:\\Final_Test\\IFC\\Duplex_A_20110505.ifc", "D:\\Final_Test\\IFC\\Duplex_A_20110505_geometry_tt.ttl", null,false);
	/*	try {

			cmd = parser.parse(options, args);
			if (cmd.getArgs() == null || cmd.getArgs().length != 2) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("java -jar IfcSTEP2WKT.jar <input.ifc> <output.xxx> [options]", options);
			} else {
				String baseURI = cmd.getOptionValue("baseuri");
				String ifcVersion=cmd.getOptionValue("version");
				boolean bb=cmd.hasOption("bounding_box");
				if (baseURI != null) {
					GeometryConverter gc = new GeometryConverter(baseURI);
					gc.convertGeometry(args[0], args[1], ifcVersion,bb);
				} else {
					GeometryConverter gc = new GeometryConverter("");
					gc=new GeometryConverter(gc.DEFAULT_PATH);
					long start = System.currentTimeMillis();
					gc.convertGeometry(args[0], args[1], ifcVersion,bb);
					long end = System.currentTimeMillis();
					System.out.println("Total conversion time: " + ((float) (end - start)) / 1000 + " s");
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.err.println("Parsing command line failed.  Reason: " + e.getMessage());
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("java -jar IfcSTEP2IfcOWL.jar <input.ifc> <output.xxx> [options]", options);
		}

*/
	}

}
