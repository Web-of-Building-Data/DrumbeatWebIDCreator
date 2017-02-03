package fi.ni.concepts;

public class AuthenticationRule {
	public enum PERMISSION {
	    CREATE, READ, UPDATE, DELETE
	} 
	
	
	final public String rule_uri;
	final public PERMISSION permission;
	final public RulePath rulePath;

	public AuthenticationRule(String rule_uri, PERMISSION permission,RulePath rulePath) {
		super();
		this.rule_uri = rule_uri;
		this.permission = permission;
		this.rulePath=rulePath;
	}

}
