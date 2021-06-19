package task1;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

//https://mvnrepository.com/artifact/org.json/json/20140107
public class PersonServices {

    final String projectDirectory = "C:/project_workspace/task1/";
    ArrayList <Person> personslist = new ArrayList<Person>();

    public PersonServices(){
        loadData();
    }
 
    public void loadData(){

        String path = projectDirectory;
        String filename ="persons.json";
        String content = "";
 
        try {
            FileReader myReader = new FileReader(path+filename);
            int i;    
            while((i = myReader.read())!=-1) content += (char) i;  
            myReader.close();

            try{
                System.out.println("++++++++++++++++++++++++++++++++++");
                System.out.println( content );
                System.out.println("++++++++++++++++++++++++++++++++++");

                JSONObject jsonContent = new JSONObject(content);
                JSONArray usersJSON = jsonContent.getJSONArray("persons");

                System.out.println("Amount of persons :" +usersJSON.length() );
                for( int jsonIndex = 0; jsonIndex < usersJSON.length(); jsonIndex++ ){

                    JSONObject userElement = usersJSON.getJSONObject(jsonIndex);
                    String fullname = userElement.getString("fullname");
                    String socialSecurityNumber = userElement.getString("socialsecuritynumber");
                    String nationality = userElement.getString("nationality"); 
                    String birthday = userElement.getString("birthdate");  
                    String address = userElement.getString("address");
                    
                    Person tempPerson = new Person();
                    tempPerson.setFullname(fullname);
                    tempPerson.setSocialSecurityNumber(socialSecurityNumber);
                    tempPerson.setNationality(nationality);
                    tempPerson.setBirthDate(birthday);
                    tempPerson.setAddress(address);

                    personslist.add(tempPerson);

                }

            } catch(JSONException failToLoadPersonJSON){
                System.out.println("Fail to parse data ! error code : " + failToLoadPersonJSON);
            }

            System.out.println("Successfully read file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }	
 
    }

    public void loadLanguages(){
        String path = projectDirectory;
        String filename ="language.json";
        String content = "";
    }
	 
    public String displayAllPersons( ){

        String allUserBasicDetailsJSONArrayStr="";

        allUserBasicDetailsJSONArrayStr += "{ 'persons': [ ";
			
        for( Person userElement : personslist ) {
            allUserBasicDetailsJSONArrayStr += "{ 'fullname' : '"+userElement.getFullname()+"' , 'address': '"+
            userElement.getAddress()+"' , 'nationality': '"+userElement.getNationality()+"' , 'socialsecuritynumber': 'hidden from public view'},";
            }

        allUserBasicDetailsJSONArrayStr += "]}";

        return allUserBasicDetailsJSONArrayStr;
    }
 
    public void PersonDeathProcedure( String paramSocialSecurityNumber  ){

        //lock bank account
        //disable socialSecuriteNumber ( for taking loan, begin new contract, etc ...)
        //Find family relationship ( for heritage and funeral payment )
        //print report ( include death date , time and reason )
        //allow litigation process for death persons ( because sometimes required )

        int index=0;
        for ( Person findUser : personslist ) {

            String personNumber = findUser.getSocialSecurityNumber();

            if( personNumber.equals(paramSocialSecurityNumber) ) {

                if( findUser.getAllive() ) {

                    String deathDate = new Date().toGMTString();
                    Date death = new Date(); 

                    String [] dateStr = deathDate.split(" ");
                    String deathDay = dateStr[0];
                    String deathdaystr = deathDay +"." + ( death.getMonth() + 1 )  + "."+ ( 1900 + death.getYear() );
                    String deathReason = "accident";

                    System.out.println( deathDate );
                    System.out.println("custom date :" + deathDay +"." + ( death.getMonth() + 1 )  + "."+ ( 1900 + death.getYear() ) );

                    personslist.get(index).setAllive(false);
                    personslist.get(index).setDeathDate(deathdaystr);

                    GenerateHtmlFromTemplate deathReport = new GenerateHtmlFromTemplate();
                    String jsonData="";
                    jsonData +="{ \"datatype\": \"death\" ,";
                    jsonData +="\"fullname\": "+"\""+findUser.getFullname()+"\" , ";
                    jsonData +="\"socialsecuritynumber\": "+"\""+findUser.getSocialSecurityNumber()+"\" , ";
                    jsonData +="\"nationality\": "+"\""+findUser.getNationality()+"\" , ";
                    jsonData +="\"birthdate\": "+"\""+findUser.getBirthDate()+"\" , ";
                    jsonData +="\"deathdate\": "+"\""+deathdaystr+"\" , ";
                    jsonData +="\"deathreason\": "+"\""+deathReason+"\"  ";
                    jsonData +="}";
                    deathReport.generateServiceReport( jsonData,  "death");

                } else {
                    // ALLERT , person died twice
                }
            }
            index++;
        }
    }
	
    public void PersonTakeLoan( String paramSocialSecurityNumber , double amountOfMoney, int loanLenghtInMonth , String bankName ){

        //check person is allowed to take loan
        //loan size and loan reason
        //plan to repay the loan
        //plan if problem and "payment default" happen ( is bank allowed to seize property ? )
        //print report

        double universalLoanPercentagePerYear = 0.05;

        int index=0;
        for ( Person findUser : personslist ) {

            String personNumber = findUser.getSocialSecurityNumber();

            if( personNumber.equals(paramSocialSecurityNumber) ) {
                if( findUser.getAllive() ) {	
                    //userlist.get(index).setAllive(false);
                    GenerateHtmlFromTemplate deathReport = new GenerateHtmlFromTemplate();
                    String jsonData="";
                    jsonData +="{ \"datatype\": \"loan\" ,";
                    jsonData +="\"fullname\": "+"\""+findUser.getFullname()+"\" , ";
                    jsonData +="\"socialsecuritynumber\": "+"\""+findUser.getSocialSecurityNumber()+"\" , ";
                    jsonData +="\"loansize\": "+"\""+amountOfMoney+"\" , ";
                    jsonData +="\"loanlenght\": "+"\""+loanLenghtInMonth+"\" , ";
                    jsonData +="\"bankname\": "+"\""+bankName+"\" , ";
                    jsonData +="\"loanpercentageperyear\": "+"\""+universalLoanPercentagePerYear+"\" , ";
                    jsonData +="}";
                    deathReport.generateServiceReport( jsonData,  "loan");
                } else {
                    // ALLERT , person died ( how dead person can take loan ? )
                }
            }
            index++;
        }
    }

    //oikeudenkäyntiprosessi / Rikosilmoitus ????? ( mahdollisesti tulevissa versioissa, toteutustapa miettimisvaiheessa )
    public void PersonLitigationProcess(String victimPersonSocialSecurityNumber, String suspectPersonSocialSecurityNumber  ) {
        //what law suspect person broke ?
        //what kind of demands victim have ?
        // is litigation process accepted , canceled or pending
        //print report
        //litigation process can still happen when victim or suspect is dead
    }

    public void searchModule(){}

    public ArrayList <Person> transferCurrentPersonData(){ return personslist; } 

    public void addMorePersons( ArrayList <Person> newPersonsList ){
        for( Person userElement : newPersonsList ){
            personslist.add(userElement);
        }
 
    }

}
