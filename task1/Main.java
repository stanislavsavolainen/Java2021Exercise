package task1;
import java.util.ArrayList;


public class Main {

	
	static ArrayList <Person> personlist = new ArrayList<Person>();
	
	
	public static void main( String args [] ){
		
		Person tmpPerson = new Person();
		tmpPerson.setFullname("Pekka Mäkinen");
		tmpPerson.setSocialSecurityNumber("251081-268K");
		tmpPerson.setNationality("Finnish");
		tmpPerson.setAddress("Bulevardi");
		personlist.add(tmpPerson);
		
		Person tmpPerson2 = new Person();
		tmpPerson2.setFullname("Marja-Leena Kirvesterä");
		tmpPerson2.setSocialSecurityNumber("040394-560U");
		tmpPerson2.setNationality("Finnish");
		tmpPerson2.setAddress("Runeberginkatu");
		personlist.add(tmpPerson2);
		
		for( Person element : personlist  ){
			
			System.out.println("Henkilön koko nimi :" + element.getFullname() + " ja henkilötunnus :"+element.getSocialSecurityNumber()  ) ;
			
		}
		
		SimulateBackendData usersPage = new SimulateBackendData();
		usersPage.copyArrayListUsers(personlist);
		
		String usersPageString = usersPage.displayAllUsers();
		
		System.out.println("-------------- all users -----------------------");
		
		System.out.println( usersPageString );
		
		GenerateHtmlFromTemplate generateFronEnd = new GenerateHtmlFromTemplate();
		generateFronEnd.generateUsersListPage(usersPageString);
		
		System.out.println("-------------------------------------------------");
			
	}
	
}
