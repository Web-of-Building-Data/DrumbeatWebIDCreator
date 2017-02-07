package fi.ni.concepts;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

import org.apache.http.client.utils.URIBuilder;

import com.hp.hpl.jena.rdf.model.RDFNode;

import fi.ni.Fetchable;
import fi.ni.Internet;
import fi.ni.RDFDataStore;
import fi.ni.ruledata.AuthenticationRule;
import fi.ni.ruledata.ProctedtedPath;
import fi.ni.ruledata.SecurityData;
import fi.ni.webid.WebIDCertificate;
import fi.ni.webid.WebIDProfile;

public class DataStore extends Fetchable {
	private URI rootURI;
	private String datastore_uri = "";
	
	private SecurityData security_data=null;  // read from a file, or created at the time
    private  RDFDataStore rdf_datastore=null;
    
	public DataStore(String host_uri, String name) {
		try {
			URI uri = new URI(host_uri);
			this.datastore_uri = uri.getHost();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			rootURI = new URIBuilder().setScheme("https").setHost(datastore_uri).setPath("/" + name).build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		rdf_datastore=new RDFDataStore(rootURI, name);
        security_data=rdf_datastore.readSecurity(rootURI.toString());		
        rdf_datastore.saveSecurity(security_data);
	}


	public String getRootURI() {
		return rootURI.toString();
	}

	
	public boolean connect(WebIDCertificate wc, String request_uri) {
		System.out.println("WebID oli:" + wc.getWebid_uri().toString());
		Organization o = (Organization) Internet.get(wc.getWebid_uri().toString());
		WebIDProfile wp = (WebIDProfile) o.get(wc.getWebid_uri().getPath());
		if (wc.getPublic_key().equals(wp.getPublic_key())) {

			List<ProctedtedPath> matched_paths = security_data.match(request_uri);
			for (ProctedtedPath r : matched_paths) {

				System.out.println("match: " + r.getProtected_path());
				// System.out.println("match next step: " +
				// r.getRulePath().getHead());
				Set<AuthenticationRule> rules=r.getRules();
				for(AuthenticationRule rule:rules)
				{
				   RDFNode result=rdf_datastore.getData(rule.getRulePath().getHead());
				   System.out.println("Rule:"+rule.getId());
				   System.out.println("Rule head is:"+rule.getRulePath().getHead());
				   System.out.println("Rule 1. result:"+result);
				   
				   if(result!=null && result.isResource())
				   {
					   
					   Organization org = (Organization) Internet.get(result.asResource().toString());
					   if(org!=null)
					   {
						   System.out.println("name is:"+org.getName());
					   }
				   }
				}
			}
			return true;
		} else
			return false;
	}



}
