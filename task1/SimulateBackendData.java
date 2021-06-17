package task1;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

//https://mvnrepository.com/artifact/org.json/json/20140107

public class SimulateBackendData {

    ArrayList <Person> userlist = new ArrayList<Person>();

	//generate valid json-string
    public String generateProfileJSON( int userId ){

        String profileJSONObjectstr="";

        profileJSONObjectstr += "";

        try{

            String tmpFullname = userlist.get(userId).getFullname();
            String tmpAddress = userlist.get(userId).getAddress();
            String tmpSocialSecurityAddress= userlist.get(userId).getSocialSecurityNumber();
            String tmpNationality = userlist.get(userId).getNationality();

            profileJSONObjectstr += "{ "+
                "'fullname' : '"+userlist.get(userId).getFullname()+"',"+
                "'address': '"+ userlist.get(userId).getAddress()+"' ,"+
                "'nationality': '"+userlist.get(userId).getNationality()+" ,"+ 
                "'socialsecuritynumber': '"+
                  userlist.get(userId).getSocialSecurityNumber()+
            "'}";

        } catch(Exception e) { 
            System.out.println("Problem to generate user profile, JSON-error :"+e);
        }

    return profileJSONObjectstr;
    }
		
    public String displayAllUsers( ){

        String allUserBasicDetailsJSONArrayStr="";

        allUserBasicDetailsJSONArrayStr += "{ 'users': [ ";
		
        for( Person userElement : userlist ) {
            allUserBasicDetailsJSONArrayStr += "{ 'fullname' : '"+userElement.getFullname()+"' , 'address': '"+
            userElement.getAddress()+"' , 'nationality': '"+userElement.getNationality()+"' , 'socialsecuritynumber': 'hidden from public view'},";
		}

        allUserBasicDetailsJSONArrayStr += "]}";

    return allUserBasicDetailsJSONArrayStr;
    }

    public void addNewUser( JSONObject newUser ){

        try{

            Person addPersonObject = new Person();
            addPersonObject.setFullname( newUser.get("fullname").toString() );
            addPersonObject.setAddress( newUser.get("address").toString() );
            addPersonObject.setNationality( newUser.get("nationality").toString() );
            addPersonObject.setSocialSecurityNumber(newUser.get("socialsecuritynumber").toString() );

            userlist.add(addPersonObject);

        }catch( JSONException userAddFail){
            System.out.println("Problem to add new user, Json-error :"+userAddFail);
        }

    }

    public void copyArrayListUsers( ArrayList<Person> transferUserData ){

        for( Person userElement : transferUserData ){
            userlist.add(userElement);
        }

    }

}

