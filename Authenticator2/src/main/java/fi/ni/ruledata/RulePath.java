package fi.ni.ruledata;

import java.util.ArrayList;
import java.util.List;

import com.viceversatech.rdfbeans.annotations.RDF;
import com.viceversatech.rdfbeans.annotations.RDFBean;

@RDFBean("http://drumbeat.cs.hut.fi/owl/DrumbeatSecurity.ttl#RulePath")
public class RulePath extends AbstractData {
	private String[] rulepath;

	public RulePath() {
		super();
	}

	public RulePath(String rulepath) {
		super();
		this.rulepath = rulepath.split("/");
	}

	public RulePath(String[] rulepath) {
		this.rulepath = rulepath;
	}

	@RDF("http://drumbeat.cs.hut.fi/owl/DrumbeatUserAdmin.ttl#path")
	public String getRulepath() {
		StringBuffer sb = new StringBuffer();
		for(String s:this.rulepath)
			sb.append(s+"/");
		return sb.toString();
	}

	public void setRulepath(String rulepath) {
		this.rulepath = rulepath.split("/");
	}

	public String getHead() {
		if (rulepath.length > 0)
			return rulepath[0];
		else
			return null;
	}

	public RulePath getTail() {
		if (rulepath.length > 0) {
			List<String> steps = new ArrayList<String>();
			for (int n = 1; n < rulepath.length; n++)
				steps.add(rulepath[n]);
			String list[] = new String[steps.size()];
			list = steps.toArray(list);
			return new RulePath(list);

		}
		return null; // Stecial case.. last step
	}
}
