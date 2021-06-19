package task1;

public class Person {

/*

    nimi = name
    henkilötunnus = social security number
    osoitetiedot = address
    kansalaisuus = natinality
    äidinkieli = native language
    perhesuhdetiedot = family relationship information 
    syntymä- ja kuolintiedot = birth date and death date

*/
	
    int userId;
    String fullname;
    String socialSecurityNumber;
    String address;
    String nationality;
    String nativeLanguage;
    String familyRelationshipInformation;
    String birthDate;
    String deathDate;

    //System data
    boolean isAllowedToTakeLoan;
    boolean isAlive;

    public Person(){
        isAllowedToTakeLoan = true;
        isAlive = true;
    }

    public void setUserId( int id){
        this.userId = id;
    }

    public int getUserId() { return userId; }

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

    public void setAllive( boolean isAlive ){ 
        this.isAlive = isAlive;
    }
	
    public boolean getAllive () {
        return isAlive;
    }

    public void setBirthDate( String birthdate ){ 
        this.birthDate = birthdate; 
    }

    public String getBirthDate(){ 
        return birthDate; 
    }
	
    public void setDeathDate( String deathdate ){ 
        this.deathDate = deathdate; 
    }
	
    public String getDeathDate(){ 
        return deathDate; 
    }

}
