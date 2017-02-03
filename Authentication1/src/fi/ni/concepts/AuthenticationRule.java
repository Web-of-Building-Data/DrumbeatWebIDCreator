package fi.ni.concepts;

public class AuthenticationRule {
	final private String rule_baseuri;
	
	
	public enum PERMISSION {
	    CREATE, READ, UPDATE, DELETE
	} 
	
	final private String rule_uri;
	final private PERMISSION permission;
	final private RulePath rulePath;

	public AuthenticationRule(String rule_baseuri, String rule_uri, PERMISSION permission,RulePath rulePath) {
		super();
		this.rule_baseuri=rule_baseuri;
		this.rule_uri = rule_uri;
		this.permission = permission;
		this.rulePath=rulePath;
	}

	public String getRule_uri() {
		return rule_uri;
	}

	public PERMISSION getPermission() {
		return permission;
	}

	public RulePath getRulePath() {
		return rulePath;
	}
	
	

}
