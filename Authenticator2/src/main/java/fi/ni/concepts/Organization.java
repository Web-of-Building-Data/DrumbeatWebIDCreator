package fi.ni.concepts;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.utils.URIBuilder;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.FileManager;

import fi.ni.Constants;
import fi.ni.Fetchable;
import fi.ni.RDFDataStore;
import fi.ni.ruledata.RulePath;
import fi.ni.webid.WebIDCertificate;
import fi.ni.webid.WebIDProfile;

public class Organization extends Fetchable {
	private URI rootURI;
	private String uri = "";


	private final String name;
	private Map<String, WebIDProfile> webid_profiles = new HashMap<String, WebIDProfile>();

	 private  RDFDataStore rdf_datastore=null;
	
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
		
		rdf_datastore=new RDFDataStore(rootURI, name);
        rdf_datastore.readRDFData();		
        rdf_datastore.saveRDFData();
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
