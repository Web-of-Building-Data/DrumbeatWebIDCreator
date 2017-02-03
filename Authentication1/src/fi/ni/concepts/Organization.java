package fi.ni.concepts;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.utils.URIBuilder;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.util.FileManager;

import fi.ni.DRUMBEATAuthorizationConstants;
import fi.ni.Fetchable;

public class Organization extends Fetchable {
	private URI rootURI;
	private String uri = "";
	private Model rdf_model = ModelFactory.createDefaultModel();

	private final String name;
	private Map<String, WebIDProfile> webid_profiles = new HashMap<String, WebIDProfile>();

	public Organization(String uri_str, String name) {
		super();
		try {
			URI uri = new URI(uri_str);
			this.uri = uri.getHost();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			rootURI = new URIBuilder().setScheme("http").setHost(uri).setPath("/" + name).build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		this.name = name;

		InputStream in = FileManager.get()
				.open(DRUMBEATAuthorizationConstants.RDF_filePath + "organization_" + name + ".n3");
		if (in == null) {
			writeDemoData(name);
		   return;  // can be nonexistent!
		}

		// read the RDF/XML file
		RDFDataMgr.read(rdf_model, in,  Lang.TURTLE) ;

		// write it to standard out
		rdf_model.write(System.out);
	}

	private void writeDemoData(String name) {
		Resource root = rdf_model.getResource(rootURI.toString());
		Property property_maincontracor = rdf_model.getProperty("http://drumbeat/maincontractor");
		Resource maincontractor = rdf_model.getResource("http://company1/");
		root.addProperty(property_maincontracor, maincontractor);
		try {
			FileOutputStream fout = new FileOutputStream(
					DRUMBEATAuthorizationConstants.RDF_filePath + "datastore_" + name + ".n3");
			RDFDataMgr.write(fout, rdf_model, Lang.TURTLE);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public WebIDProfile get(String local_path) {
		return webid_profiles.get(local_path);
	}

	public void connect(RulePath rulepath) {
		System.out.println(rulepath.getHead());
	}

	public String getUri() {
		return uri;
	}

	public String getName() {
		return name;
	}

	public WebIDCertificate getWebID(String local_path) {
		String public_key = "1234";
		URI webid_uri;
		try {
			webid_uri = new URIBuilder().setScheme("http").setHost(uri).setPath("/" + local_path).build();
			WebIDCertificate wc = new WebIDCertificate(webid_uri, public_key);
			webid_profiles.put(webid_uri.getPath(), new WebIDProfile(public_key));
			return wc;

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

}
