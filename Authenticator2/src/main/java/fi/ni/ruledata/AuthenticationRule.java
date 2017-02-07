package fi.ni.ruledata;

import com.viceversatech.rdfbeans.annotations.RDF;
import com.viceversatech.rdfbeans.annotations.RDFBean;

@RDFBean("http://drumbeat.cs.hut.fi/owl/DrumbeatSecurity.ttl#AuthenticationRule")
public class AuthenticationRule extends AbstractData {
	public enum PERMISSION {
		CREATE, READ, UPDATE, DELETE
	}

	private PERMISSION permission;
	private RulePath rulePath;

	public AuthenticationRule() {
		super();
	}

	public AuthenticationRule(PERMISSION permission, RulePath rulePath) {
		super();
		this.permission = permission;
		this.rulePath = rulePath;
	}

	@RDF("http://drumbeat.cs.hut.fi/owl/DrumbeatUserAdmin.ttl#permission")
	public String getPermissionString() {
		switch (permission) {
		case CREATE:
			return "CREATE";
		case READ:
			return "READ";
		case UPDATE:
			return "UPDATE";
		case DELETE:
			return "DELETE";

		}
		return null;
	}
	
	public void setPermissionString(String txt) {
		if(txt.equals("CREATE"))
		    this.permission= PERMISSION.CREATE;
		if(txt.equals("READ"))
		    this.permission= PERMISSION.READ;
		if(txt.equals("UPDATE"))
		    this.permission= PERMISSION.UPDATE;
		if(txt.equals("DELETE"))
		    this.permission= PERMISSION.DELETE;
	}

	public PERMISSION getPermission() {
		return permission;
	}

	public void setPermission(PERMISSION permission) {
		this.permission = permission;
	}

	@RDF("http://drumbeat.cs.hut.fi/owl/DrumbeatUserAdmin.ttl#rulepath")
	public RulePath getRulePath() {
		return rulePath;
	}

	public void setRulePath(RulePath rulePath) {
		this.rulePath = rulePath;
	}

}
