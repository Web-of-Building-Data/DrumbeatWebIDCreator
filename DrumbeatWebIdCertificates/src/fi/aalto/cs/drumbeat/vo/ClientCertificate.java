package fi.aalto.cs.drumbeat.vo;

import fi.aalto.cs.drumbeat.Country;
import fi.aalto.cs.drumbeat.WebIDController;

public class ClientCertificate extends Certificate {

	private String modulus=null;
	private String exponent=null;
	
	public ClientCertificate()
	{
		super();
		this.common_name ="Jyrki Oraskari";
	}
	public void updateFrom(WebIDController p) {
		this.common_name = p.client_common_name_field.getText();
		this.organization = p.client_organization_field.getText();
		this.city = p.client_city_field.getText();
		this.country = p.client_country_field.getSelectionModel().getSelectedItem();
	}

	public void updateTo(WebIDController p) {
		p.client_common_name_field.setText(this.common_name);
		p.client_organization_field.setText(this.organization);
		p.client_city_field.setText(this.city);
		for (Country c : p.countries) {
			if (c.getCountry_Code().equals(this.country.getCountry_Code()))
				p.client_country_field.getSelectionModel().select(c);
		}

	}
	public String getModulus() {
		return modulus;
	}
	public void setModulus(String modulus) {
		this.modulus = modulus;
	}
	public String getExponent() {
		return exponent;
	}
	public void setExponent(String exponent) {
		this.exponent = exponent;
	}

	
	
}
