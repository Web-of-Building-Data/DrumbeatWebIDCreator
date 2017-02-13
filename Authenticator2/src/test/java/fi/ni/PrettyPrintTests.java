package fi.ni;

import java.io.StringWriter;

import org.ontoware.rdf2go.ModelFactory;
import org.ontoware.rdf2go.impl.jena.ModelFactoryImpl;
import org.ontoware.rdf2go.model.Model;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class PrettyPrintTests {

	Model model;
	com.hp.hpl.jena.rdf.model.Model jena_model;

	private void createDemoData() {
		Resource root = jena_model.getResource("http://root/");
		Property property_maincontracor = jena_model.getProperty(Constants.property_base + "main_contractor");
		Resource maincontractor = jena_model.getResource("http://company1/");
		root.addProperty(property_maincontracor, maincontractor);

		Property property_contracor = jena_model.getProperty(Constants.property_base + "contractor");
		Resource contractor1 = jena_model.getResource("http://company2/");
		root.addProperty(property_contracor, contractor1);

		Property property_known_person = jena_model.getProperty(Constants.property_base + "known_person");
		Resource person1 = jena_model.getResource("http://company1/mats");
		root.addProperty(property_known_person, person1);
	}

	public void testPrettyPrint() {
		ModelFactory modelFactory = new ModelFactoryImpl();
		model = modelFactory.createModel();
		model.open();
		jena_model = (com.hp.hpl.jena.rdf.model.Model) model.getUnderlyingModelImplementation();
		jena_model.setNsPrefix("root", "http://root/");
		jena_model.setNsPrefix("ds", "https://drumbeat.cs.hut.fi/"); // drumbeat
																		// security
																		// ontology
		createDemoData();

		StringWriter out = new StringWriter();
		
		String syntax = "TURTLE"; // also try "N-TRIPLE" and "TURTLE"
		jena_model.write(out, syntax);
		String result = out.toString();
		System.out.println("RESULT: "+result);
	
	}

	public static void main(String[] args) {
		PrettyPrintTests p = new PrettyPrintTests();
		p.testPrettyPrint();
	}

}
