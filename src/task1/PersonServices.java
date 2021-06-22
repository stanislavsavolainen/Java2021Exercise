package task1;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

//https://mvnrepository.com/artifact/org.json/json/20140107
public class PersonServices {

    final String projectDirectory = "C:/eclipse_workspace/Java2021Exercise/src/task1/";
    
    ArrayList <Person> personslist = new ArrayList<Person>();
    HashMap <String , BankAccount> bankAccountList = new HashMap<String, BankAccount> ();

    public PersonServices(){
        loadPersonData();
        loadBankAccounts();
    }
 
    public void loadPersonData(){

        String path = projectDirectory;
        String filename ="persons.json";
        String content = "";
 
        try {
            FileReader myReader = new FileReader(path+filename);
            int i;    
            while((i = myReader.read())!=-1) content += (char) i;  
            myReader.close();

            try{
              //  System.out.println("++++++++++++Person++++++++++++++++");
              //  System.out.println( content );
              //  System.out.println("++++++++++++++++++++++++++++++++++");

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
                    String deathdate = userElement.getString("deathdate");
                    
                    Person tempPerson = new Person();
                    tempPerson.setFullname(fullname);
                    tempPerson.setSocialSecurityNumber(socialSecurityNumber);
                    tempPerson.setNationality(nationality);
                    tempPerson.setBirthDate(birthday);
                    tempPerson.setAddress(address);
                    tempPerson.setDeathDate(deathdate);
                    
                    personslist.add(tempPerson);

                }

            } catch(JSONException failToLoadPersonJSON){
                System.out.println("Fail to parse data ! error code : " + failToLoadPersonJSON);
            }

            System.out.println("Successfully read file (Person).");
        } catch (IOException e) {
            System.out.println("An error occurred (Person).");
            e.printStackTrace();
        }	
 
    }
    
    public void loadBankAccounts() {
    	
    	String path = projectDirectory;
        String filename ="bankaccounts.json";
        String content = "";
 
        try {
            FileReader myReader = new FileReader(path+filename);
            int i;    
            while((i = myReader.read())!=-1) content += (char) i;  
            myReader.close();

          //  System.out.println("++++++++++++BankAccount+++++++++++");
          //  System.out.println( content );
          //  System.out.println("++++++++++++++++++++++++++++++++++");

            JSONObject jsonContent = new JSONObject(content);
            JSONArray bankJSON = jsonContent.getJSONArray("bankaccounts");

            System.out.println("Amount of bank records :" +bankJSON.length() );
            for( int jsonIndex = 0; jsonIndex < bankJSON.length(); jsonIndex++ ){
            
                String accountNumber = bankJSON.getJSONObject(jsonIndex).getString("accountnumber");
                String bankName = bankJSON.getJSONObject(jsonIndex).getString("bankname");
                String socialSecurityNumber = bankJSON.getJSONObject(jsonIndex).getString("socialsecuritynumber");
                double balance = 0.0 , loansize = 0.0;
            	
                try{
                    balance = Double.parseDouble( bankJSON.getJSONObject(jsonIndex).getString("balance") );
                    loansize = Double.parseDouble( bankJSON.getJSONObject(jsonIndex).getString("loansize") );
                    boolean isLocked = false;
                    String isLockedStr = "";
                    isLockedStr =  bankJSON.getJSONObject(jsonIndex).getString("islocked");
                    if( isLockedStr.equals("true")){
                        isLocked = true;
                    }
                
                    else if( isLockedStr.equals("false")){
                        isLocked = false;
                    }
                
                    BankAccount tempAccount = new BankAccount();
                    tempAccount.setBalance( ( (double) Math.round(balance * 100) / 100 ) ); //parse-double value
                    tempAccount.setLoanSize( ( (double) Math.round(loansize * 100) / 100 ) ); //parse-double value
                    tempAccount.setBankName(bankName);
                    tempAccount.setAccountNumber(accountNumber);
                    tempAccount.setIsLocked(isLocked);
                    bankAccountList.put( socialSecurityNumber ,  tempAccount );

                } catch(Exception parseDoubleFail ){
                    System.out.println("Unnable to read bankaccount.json balance or loansize values");
                }
                

            }
            
            System.out.println("Successfully read file (BankAccount).");
        } catch (IOException e) {
            System.out.println("An error occurred (BankAccount).");
            e.printStackTrace();
        }
    	
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
            String modelDeathDate = findUser.getDeathDate();
            
            if( personNumber.equals(paramSocialSecurityNumber) ) {

                if( modelDeathDate.equals("null") ||  modelDeathDate.length() == 0  ) {

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
                    
                    //generate report ( html file )
                    OutputDataService deathReport = new OutputDataService();
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
                    
                    //lock death person bank accout
                    bankAccountList.get(findUser.getSocialSecurityNumber()).setIsLocked(true);
                    
                    updateData("persons");
                    updateData("bank");
                    
                    OutputDataService systemlog = new OutputDataService();
                    String content = "";
                    content += "\nSystem=Ok ,  "
                     + "\nperson :" + findUser.getSocialSecurityNumber()+ 
                     "is now dead at : "+( new Date().toGMTString());
                    systemlog.appendToFile(content, projectDirectory + "systemlog.txt");

                } else {
                    // ALERT , person died twice
                	System.out.println("ALERT, person died twice ");
                	
                	OutputDataService systemlog = new OutputDataService();
                     String content = "";
                     content += "\nSystem=Alert ,  "
                      + "\nperson :" + findUser.getSocialSecurityNumber()+ 
                      "die twice : "+( new Date().toGMTString());
                     systemlog.appendToFile(content, projectDirectory + "systemlog.txt");
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
                    
                    //======================= loan report ==============================
                    OutputDataService deathReport = new OutputDataService();
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
               
                    //======================= put money on bank account ================

                        if ( ! bankAccountList.get( findUser.getSocialSecurityNumber()).getIsLocked() ){
                            double currentBalance = bankAccountList.get(findUser.getSocialSecurityNumber()).getBalance();
                            double currentLoanSize = bankAccountList.get(findUser.getSocialSecurityNumber()).getLoanSize();
                            currentBalance += amountOfMoney;
                            currentLoanSize += amountOfMoney;
                            bankAccountList.get( findUser.getSocialSecurityNumber()).setLoanSize(currentLoanSize);
                            bankAccountList.get( findUser.getSocialSecurityNumber()).setBalance(currentBalance);
                            System.out.println("Loan aggrement done !");
                            updateData("bank");
                            
                            OutputDataService systemlog = new OutputDataService();
                            String content = "";
                            content += "\nSystem=Ok ,  "
                             + "\nloan aggrement done with person :" + findUser.getSocialSecurityNumber()+ 
                             " at : "+( new Date().toGMTString());
                            systemlog.appendToFile(content, projectDirectory + "systemlog.txt");
                        } else {
                            // ALERT , someone try to use locked bank account
                              OutputDataService systemlog = new OutputDataService();
                              String content = "";
                              content += "\nSystem=Alert ,  "
                               + "\nsomeone try to use locked bank account "+ findUser.getSocialSecurityNumber() + 
                               " at : "+( new Date().toGMTString());
                              systemlog.appendToFile(content, projectDirectory + "systemlog.txt");
                    	}
                    	
                    //}
                
                
                } else {
                    // ALERT , person died ( how dead person can take loan ? )
                    OutputDataService systemlog = new OutputDataService();
                    String content = "";
                    content += "\nSystem=Alert ,  "
                    + "\nDead person : "+ findUser.getSocialSecurityNumber() + 
                    " trying take loan at : "+( new Date().toGMTString());
                    systemlog.appendToFile(content, projectDirectory + "systemlog.txt");
                }
            }
            index++;
        }
    }

    public void PersonPayLoan( String socialSecurityNumber, double paymentAmount ) {
   	
        double currentBalance = bankAccountList.get( socialSecurityNumber).getBalance();
            System.out.println("Balance :" + currentBalance + "loan pay :" + paymentAmount );
            if( ! bankAccountList.get( socialSecurityNumber).getIsLocked() ) {
                if( paymentAmount <= currentBalance ){
                    double currentLoanSize = bankAccountList.get( socialSecurityNumber).getLoanSize();
                    currentLoanSize -= paymentAmount;
                    currentBalance -= paymentAmount;
                    System.out.println("Loan size : " + currentLoanSize);
                    if( currentLoanSize >= 0 ){ 
                        bankAccountList.get(socialSecurityNumber).setLoanSize(currentLoanSize);
                        bankAccountList.get(socialSecurityNumber).setBalance(currentBalance);
                        //update bank account
                        System.out.println("Payment success. Your bank balance is :"+ 
                        bankAccountList.get( socialSecurityNumber).getBalance() + "and loan left :" + 
                        bankAccountList.get( socialSecurityNumber).getLoanSize() 
                        );
                        updateData("bank");
                    
                        OutputDataService systemlog = new OutputDataService();
                        String content = "";
                        content += "\nSystem=Ok ,  "
                        + "\nperson :" + socialSecurityNumber+ 
                        " pay loan at : "+( new Date().toGMTString());
                        systemlog.appendToFile(content, projectDirectory + "systemlog.txt");
                    }
                }
           } else {
               OutputDataService systemlog = new OutputDataService();
               String content = "";
               content += "\nSystem=Alert ,  "
               + "\nperson :" + socialSecurityNumber+ 
                " attempt pay loan at locked bank account : "+( new Date().toGMTString());
               systemlog.appendToFile(content, projectDirectory + "systemlog.txt");
           }
    }
    
    public void updateData( String dataType){
    
        if(dataType.equals("persons")){
            
        	 String jsonStr = "";
             jsonStr+= "{";
             jsonStr+="\n \t \"persons\": [ ";
            
            for( Person personElement : personslist ){
            	jsonStr+= "\n \t\t  {";
                jsonStr+= "\n \t\t\t  \"fullname\": \""+personElement.getFullname()+ "\", ";
                jsonStr+= "\n \t\t\t  \"address\": \""+personElement.getAddress()+ "\", ";
                jsonStr+= "\n \t\t\t  \"nationality\": \""+personElement.getNationality()+ "\", ";
                jsonStr+= "\n \t\t\t  \"deathdate\": \""+personElement.getDeathDate()+ "\", ";
                jsonStr+= "\n \t\t\t  \"birthdate\": \""+personElement.getBirthDate()+ "\", ";
                jsonStr+= "\n \t\t\t  \"socialsecuritynumber\": \""+personElement.getSocialSecurityNumber()+ "\" ";
                jsonStr+= "\n \t\t },";
            }
            
            jsonStr+="\n \t ]";
            jsonStr+= "}";
            
            OutputDataService writeFile = new OutputDataService();
            writeFile.writeToFile(jsonStr, projectDirectory + "persons.json");
        
        }
        
        else if(dataType.equals("bank")){
            
            String jsonStr = "";
            jsonStr+= "{";
            jsonStr+="\n \t \"bankaccounts\": [ ";
            //jsonStr+="\n \t \t { ";
        
            for( Person personElement : personslist  ) {
                BankAccount tempBank = bankAccountList.get(personElement.getSocialSecurityNumber());
                
                String islockedstr="false";
                if( tempBank.getIsLocked() ) islockedstr = "true";
                else islockedstr="false";
                
                //System.out.println( ">>>>> account locked :" + tempBank.getIsLocked() );
                
                jsonStr+= "\n \t\t  {";
                jsonStr+= "\n \t\t\t   \"bankname\": \""+tempBank.getBankName()+"\", ";
                jsonStr+= "\n \t\t\t  \"accountnumber\": \""+tempBank.getAccountNumber()+ "\", ";
                jsonStr+= "\n \t\t\t  \"balance\": \""+ ( (double) Math.round(tempBank.getBalance() *100) / 100 ) + "\", ";
                jsonStr+= "\n \t\t\t  \"loansize\": \""+ ( (double) Math.round( tempBank.getLoanSize() *100) / 100 )+ "\", ";
                jsonStr+= "\n \t\t\t  \"islocked\": \""+islockedstr+ "\", ";
                jsonStr+= "\n \t\t\t  \"socialsecuritynumber\": \""+personElement.getSocialSecurityNumber()+ "\" ";
                jsonStr+= "\n \t\t },";
               
            }
            
            jsonStr+="\n \t ]";
            jsonStr+= "}";
            
            OutputDataService writeFile = new OutputDataService();
            writeFile.writeToFile(jsonStr, projectDirectory + "bankaccounts.json");
        }
    
    }

    public ArrayList <Person> transferCurrentPersonData(){ return personslist; } 

    public void addMorePersons( ArrayList <Person> newPersonsList ){
        for( Person userElement : newPersonsList ){
            personslist.add(userElement);
        }
 
    }

}
