package fi.ni.ruledata;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.viceversatech.rdfbeans.annotations.RDF;
import com.viceversatech.rdfbeans.annotations.RDFBean;
import com.viceversatech.rdfbeans.annotations.RDFSubject;

@RDFBean("http://drumbeat.cs.hut.fi/owl/DrumbeatSecurity.ttl#DataSecurity")
public class SecurityData {
	private String uri = "";

	private Set<ProctedtedPath> protected_paths = new HashSet<ProctedtedPath>();

	public SecurityData() {
		super();
	}

	// First time initialization
	public SecurityData(String uri) {
		super();
		System.out.println("used uri:"+uri);
		this.uri = uri;
		
		demoData();

	}



	@RDFSubject
    public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	@RDF("http://drumbeat.cs.hut.fi/owl/DrumbeatUserAdmin.ttl#protected_path")
	public Set<ProctedtedPath> getProtected_paths() {
		return protected_paths;
	}


	public void setProtected_paths(Set<ProctedtedPath> protected_paths) {
		this.protected_paths = protected_paths;
	}

	
	public List<ProctedtedPath> match(URI requested_uri) {
		String requested_path=requested_uri.getPath().replaceFirst("/security", "");
		System.out.println("search path: "+requested_path);
		List<ProctedtedPath> ret = new ArrayList<ProctedtedPath>();
		for (ProctedtedPath p : protected_paths) {
			if (requested_path.startsWith(p.getProtected_path()))
				ret.add(p);
		}
		return ret;
	}

	
	//TODO matchaa säännöt moneen uri alkuu... kanonisoi path.. ja hae lyhimmän mukaan
	//TODO projektikuvaus... owner
	//TODO property lista 

	public void demoData() {
		ProctedtedPath.path_base_uri = "http://this_host/";
		ProctedtedPath p;
		p = new ProctedtedPath("/smc2/architectural/");
		protected_paths.add(p);
		p.addRule(AuthenticationRule.PERMISSION.READ, new RulePath("contractor/known_person"));

		p = new ProctedtedPath("/smc-arc/architectural/");
		protected_paths.add(p);
		p.addRule(AuthenticationRule.PERMISSION.UPDATE, new RulePath("main_contractor/known_person"));

		p = new ProctedtedPath("/smc-arc/");
		protected_paths.add(p);
		p.addRule(AuthenticationRule.PERMISSION.READ, new RulePath("main_contractor/known_person"));

		p = new ProctedtedPath("/smc-arc/architectural/v1");
		protected_paths.add(p);
		p.addRule(AuthenticationRule.PERMISSION.READ, new RulePath("contractor/known_person"));

		p = new ProctedtedPath("/collection2/datastore2/");
		protected_paths.add(p);
		p.addRule(AuthenticationRule.PERMISSION.READ, new RulePath("main_contractor/known_person"));
	}
}
