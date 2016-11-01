package fi.aalto.cs.drumbeat.vo;

import fi.aalto.cs.drumbeat.Country;

public abstract class Certificate {
	protected String common_name="DRUMBEAT";
	protected String organization="Aalto University";
	protected  String city="Espoo";
	protected  Country country=new Country("FI","Finland");
	
	public String getCommon_name() {
		return common_name;
	}
	public void setCommon_name(String common_name) {
		this.common_name = common_name;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	
	
	
}
