package fi.ni.concepts;

import java.util.ArrayList;
import java.util.List;

public class AuthorizationRules {

	private List<AuthenticationRule> rules = new ArrayList<AuthenticationRule>();

	public AuthorizationRules() {
		rules.add(new AuthenticationRule("collection1/datastore1/","collection1/datastore1/",AuthenticationRule.PERMISSION.READ,new RulePath("contractor/persons")));
		rules.add(new AuthenticationRule("collection1/datastore1/","collection1/datastore2/",AuthenticationRule.PERMISSION.READ,new RulePath("main_contractor/persons")));

		rules.add(new AuthenticationRule("collection1/datastore1/","collection2/datastore1/",AuthenticationRule.PERMISSION.READ,new RulePath("contractor/persons")));
		rules.add(new AuthenticationRule("collection1/datastore1/","collection2/datastore2/",AuthenticationRule.PERMISSION.READ,new RulePath("main_contractor/persons")));
	}

	public List<AuthenticationRule> match(String uri)
	{
		List<AuthenticationRule> ret = new ArrayList<AuthenticationRule>();
		for(AuthenticationRule r:rules)
			if(uri.contains(r.getRule_uri()))
		      ret.add(r);
		return ret;
	}
}
