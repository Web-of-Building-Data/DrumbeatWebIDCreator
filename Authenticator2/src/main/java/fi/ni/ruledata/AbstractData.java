package fi.ni.ruledata;

import java.util.UUID;

import com.viceversatech.rdfbeans.annotations.RDFSubject;


public abstract class AbstractData {
	protected  String id = UUID.randomUUID().toString();
    
	public AbstractData() {
		super();
	}

    
	@RDFSubject(prefix="http://drumbeat.cs.hut.fi/DrumbeatSecurity/resource/")
	public String getId() {
		return id.toString();
	}


	public void setId(String id) {
		this.id = id;
	}

	
	
}
