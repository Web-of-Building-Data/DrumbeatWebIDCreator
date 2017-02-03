package fi.ni.concepts;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.utils.URIBuilder;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.util.FileManager;

import fi.ni.DRUMBEATAuthorizationConstants;
import fi.ni.Fetchable;
import fi.ni.Internet;

public class DataStore extends Fetchable {
    private  URI rootURI;
	private  String uri="";
	private Model rdf_model = ModelFactory.createDefaultModel();
	AuthorizationRules ar = new AuthorizationRules();
	
	
	public DataStore(String uri_str, String name)
	{
		 try {
				URI uri=new URI(uri_str);
				this.uri=uri.getHost();
		 }
		 catch (Exception e) {
			e.printStackTrace();
		}
		 try {
			rootURI=new URIBuilder()
				        .setScheme("http")
				        .setHost(uri)
				        .setPath("/"+name)
				        .build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		
		InputStream in = FileManager.get().open( DRUMBEATAuthorizationConstants.RDF_filePath+"datastore_"+name+".n3" );
		if (in == null) {
			writeDemoData(name);
		   return;  // can be nonexistent!
		}

		// read the RDF/XML file
		RDFDataMgr.read(rdf_model, in,  Lang.TURTLE) ;

		
		// write it to standard out
		rdf_model.write(System.out);
	}

	private void writeDemoData(String name)
	{
		Resource root = rdf_model.getResource(rootURI.toString());
		
		//TODO miten kiinnitet‰‰n tietty collection/dataset!  propert path ei rajaa... (tulisiko rajata?)
		Property property_maincontracor = rdf_model.getProperty(DRUMBEATAuthorizationConstants.property_base+"maincontractor");
		
		Resource maincontractor = rdf_model.getResource("http://company1/");
		
		
		
		
		root.addProperty(property_maincontracor, maincontractor);
		try {
			FileOutputStream fout = new FileOutputStream(DRUMBEATAuthorizationConstants.RDF_filePath+"datastore_"+name+".n3" );
			RDFDataMgr.write(fout, rdf_model,  Lang.TURTLE) ;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean connect(WebIDCertificate wc, String request_uri) {
		System.out.println("WebID oli:"+wc.getWebid_uri().toString());
		Organization o = (Organization) Internet.get(wc.getWebid_uri().toString());
		WebIDProfile wp=(WebIDProfile)o.get(wc.getWebid_uri().getPath());
		if (wc.getPublic_key().equals(wp.getPublic_key())) {

			List<AuthenticationRule> matched_rules = ar.match(request_uri);
			for (AuthenticationRule r : matched_rules) {
				
				System.out.println("match: " + r.getRule_uri() + " access: " + r.getPermission());
				System.out.println("match next step: " + r.getRulePath().getHead());
			}
			return true;
		} else
			return false;
	}

}
