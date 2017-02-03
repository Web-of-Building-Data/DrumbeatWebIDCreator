package fi.ni;

import fi.ni.concepts.Organization;
import fi.ni.concepts.DataStore;
import fi.ni.concepts.WebIDCertificate;

public class Client {
	
	public Client()
	{
		try {
			Organization o = (Organization) Internet.get("http://company1/");
			WebIDCertificate wc=o.getWebID("mats");
			DataStore ds1=new DataStore("http://datastore/","store1");
			if(ds1.connect(wc,"collection1/datastore1/object1")==true)
			  System.out.println("Connected...");	
				
		} catch (Exception e) {
			System.out.println("Result:" +Internet.get("http://company1/"));
			e.printStackTrace();
		}
		
		
	
	}

	public static void main(String[] args) {
		new Client();

	}
}
