package task1;

public class Person {

	/*
	
	
    nimi = name
    henkilötunnus = social security number
    osoitetiedot = address
    kansalaisuus = natinality
    äidinkieli = native language
    perhesuhdetiedot = family relationship information 
    syntymä- ja kuolintiedot = birth date and date of death

	
	
	*/
	
	
	String fullname;
	String socialSecurityNumber;
	String address;
	String nationality;
	String nativeLanguage;
	String familyRelationshipInformation;
	String birthDate;
	String deathDate;
	
	
	public Person(){
		
	}
	
	
	public void setFullname( String name){
		this.fullname = name;
	}
	
	public String getFullname( ) { return fullname; }
	
	
	public void setSocialSecurityNumber( String socialSecurityNumber ){
		this.socialSecurityNumber = socialSecurityNumber;
	}
	
	public String getSocialSecurityNumber() { return socialSecurityNumber; }
	
	
	public void setAddress( String address ){
		this.address = address;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setNationality(String nationality){
		this.nationality = nationality;
	}
	
	public String getNationality() {
		return nationality;
	}

	
}
