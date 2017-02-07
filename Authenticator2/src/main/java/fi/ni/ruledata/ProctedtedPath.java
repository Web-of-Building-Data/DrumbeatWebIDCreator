package fi.ni.ruledata;

import java.util.HashSet;
import java.util.Set;

import com.viceversatech.rdfbeans.annotations.RDF;
import com.viceversatech.rdfbeans.annotations.RDFBean;
import com.viceversatech.rdfbeans.annotations.RDFSubject;

import fi.ni.ruledata.AuthenticationRule.PERMISSION;

@RDFBean("http://drumbeat.cs.hut.fi/owl/DrumbeatSecurity.ttl#ProctedtedPath")
public class ProctedtedPath {
	public static String path_base_uri = null;
	private String uri;
	private  String protected_path;
	private Set<AuthenticationRule> rules = new HashSet<AuthenticationRule>();

	public ProctedtedPath() {
	}
	
	public ProctedtedPath(String protected_path) {
		super();
		this.protected_path = protected_path;
		
		
		if (path_base_uri != null)
			this.uri=path_base_uri+protected_path;
		else
			uri=protected_path;
	}

	@RDFSubject
    public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	@RDF("http://drumbeat.cs.hut.fi/owl/DrumbeatUserAdmin.ttl#path") 	
	public String getProtected_path() {
		return protected_path;
	}

	public void setProtected_path(String protected_path) {
		this.protected_path = protected_path;
	}

	@RDF("http://drumbeat.cs.hut.fi/owl/DrumbeatUserAdmin.ttl#authentication_rule") 	
	public Set<AuthenticationRule> getRules() {
		return rules;
	}

	public void setRules(Set<AuthenticationRule> rules) {
		this.rules = rules;
	}
	
	
	
	public void addRule(PERMISSION permission,RulePath rulePath)
    {
    	rules.add(new AuthenticationRule(permission,rulePath));
    }

	private String addEndSlash(String txt)
	{
		if(txt.charAt(txt.length()-1)!='/')
			return txt+'/';
		else
			return txt;
	}


	
}
