package fi.ni.ruledata;

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

	
	public List<ProctedtedPath> match(String uri) {
		List<ProctedtedPath> ret = new ArrayList<ProctedtedPath>();
		for (ProctedtedPath p : protected_paths) {
			if (uri.contains(p.getProtected_path()))
				ret.add(p);
		}
		return ret;
	}


	public void demoData() {
		ProctedtedPath.path_base_uri = "http://this_host/";
		ProctedtedPath p;
		p = new ProctedtedPath("collection1/datastore1/");
		protected_paths.add(p);
		p.addRule(AuthenticationRule.PERMISSION.READ, new RulePath("contractor/persons"));

		p = new ProctedtedPath("collection1/datastore2/");
		protected_paths.add(p);
		p.addRule(AuthenticationRule.PERMISSION.UPDATE, new RulePath("main_contractor/persons"));

		p = new ProctedtedPath("collection1/");
		protected_paths.add(p);
		p.addRule(AuthenticationRule.PERMISSION.READ, new RulePath("main_contractor/persons"));

		p = new ProctedtedPath("collection2/datastore1/");
		protected_paths.add(p);
		p.addRule(AuthenticationRule.PERMISSION.READ, new RulePath("contractor/persons"));

		p = new ProctedtedPath("collection2/datastore2/");
		protected_paths.add(p);
		p.addRule(AuthenticationRule.PERMISSION.READ, new RulePath("main_contractor/persons"));
	}
}
