package fi.ni.concepts;

import java.util.ArrayList;
import java.util.List;

public class RulePath {
	private final String[] rulepath;

	public RulePath(String rulepath) {
		super();
		this.rulepath = rulepath.split("/");
	}

	public RulePath(String[] rulepath) {
		this.rulepath = rulepath;
	}

	public String[] getRulepath() {
		return rulepath;
	}

	public String getHead() {
		if (rulepath.length > 0)
			return rulepath[0];
		else
			return null;
	}

	public RulePath getTail() {
		if (rulepath.length > 0)
		{
			List<String> steps=new ArrayList<String>();
			for(int n=1;n<rulepath.length;n++)
				steps.add(rulepath[n]);
			 String list[] = new String[steps.size()];
			   list = steps.toArray(list);
			return new RulePath(list);
			
		}
		return null;  // Stecial case.. last step
	}
}
