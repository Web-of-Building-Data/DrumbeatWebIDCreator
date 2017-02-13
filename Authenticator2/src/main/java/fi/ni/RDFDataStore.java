package fi.ni;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.ontoware.aifbcommons.collection.ClosableIterator;
import org.ontoware.rdf2go.ModelFactory;
import org.ontoware.rdf2go.impl.jena.ModelFactoryImpl;
import org.ontoware.rdf2go.model.Model;
import org.ontoware.rdf2go.model.Statement;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;
import com.viceversatech.rdfbeans.RDFBeanManager;
import com.viceversatech.rdfbeans.exceptions.RDFBeanException;

import fi.ni.ruledata.SecurityData;

public class RDFDataStore {
	private final URI rootURI;
	private final String name;
	private final String rdf_filename;

	final private Model model;
	final private com.hp.hpl.jena.rdf.model.Model jena_model;

	public RDFDataStore(URI rootURI, String name) {
		super();
		rdf_filename=Constants.RDF_filePath + name+"_securitydata.ttl";
		this.rootURI = rootURI;
		this.name = name;
		ModelFactory modelFactory = new ModelFactoryImpl();
		model = modelFactory.createModel();
		model.open();
		jena_model = (com.hp.hpl.jena.rdf.model.Model) model.getUnderlyingModelImplementation();
	}

	private Resource getRoot() {
		return jena_model.getResource(rootURI.toString());
	}

	//TODO Muuta, ettö vastauksia monta
	public RDFNode getDataORG(String property) {
		Property p = jena_model.getProperty(Constants.property_base+property);
		System.out.println("root:"+getRoot().getURI());
		System.out.println("property:"+p.getURI());
		if (getRoot().hasProperty(p))
			return getRoot().getProperty(p).getObject();
		else
			return null;
	}
	
	public List<RDFNode> getData(String property) {
		List<RDFNode> ret=new ArrayList<RDFNode>();
		Property p = jena_model.getProperty(Constants.property_base+property);
		System.out.println("root:"+getRoot().getURI());
		System.out.println("property:"+p.getURI());
		
		StmtIterator it = getRoot().listProperties( p );
	    while( it.hasNext() ) {
	      com.hp.hpl.jena.rdf.model.Statement stmt = it.nextStatement();
	      ret.add(stmt.getObject() );
	    }
		return ret;
	}

	public RDFNode getData(RDFNode node, String property) {
		Property p = jena_model.getProperty(Constants.property_base+property);
		if (node.isResource()) {
			return node.asResource().getProperty(p).getObject();
		} else
			return null;
	}

	public void saveSecurity(SecurityData security) {

		RDFBeanManager manager = new RDFBeanManager(model);
		// jena_model.removeAll(); // clear old

		try {
			manager.add(security);
			// System.out.println("----------------------------");
			// listRDFContent();
			// System.out.println("----------------------------");
		} catch (RDFBeanException ex) {
			ex.printStackTrace();
		}
		saveRDFData();
	}

	public void saveRDFData() {
		try {
			FileOutputStream fout = new FileOutputStream(this.rdf_filename);
			RDFDataMgr.write(fout, jena_model, Lang.TURTLE);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void readRDFData() {

		SecurityData ret = null;
		InputStream in = FileManager.get().open(this.rdf_filename);
		if (in == null) {
			createDemoData();
			return;
		}
		RDFDataMgr.read(jena_model, in, Lang.TURTLE);
	}

	public SecurityData readSecurity(String datastore_uri) {

		SecurityData ret = null;
		InputStream in = FileManager.get().open(this.rdf_filename);
		if (in == null) {
			createDemoData();
			return new SecurityData(datastore_uri + "data_security/"); // can be
																		// nonexistent!
		}

		RDFDataMgr.read(jena_model, in, Lang.TURTLE);
		RDFBeanManager manager = new RDFBeanManager(model);
		try {
			ret = manager.get(datastore_uri + "data_security/", SecurityData.class);
		} catch (RDFBeanException e) {
			e.printStackTrace();
		}
		if (ret == null)
			return new SecurityData(datastore_uri + "data_security/");
		return ret;
	}

	private void createDemoData() {
		//TODO main_constructor suhteessa... protected pathiin...
		Resource root = jena_model.getResource(rootURI.toString());
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

	private void listRDFContent() {
		ClosableIterator<Statement> iterator = model.iterator();
		StringBuffer sb = new StringBuffer();
		while (iterator.hasNext()) {
			Statement stmt = iterator.next();
			// if (!stmt.getPredicate().toString().endsWith("bindingClass"))
			sb.append(stmt.getSubject() + " " + stmt.getPredicate() + " " + stmt.getObject());
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
