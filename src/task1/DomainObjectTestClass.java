package task1;

import static org.junit.Assert.*;

public class DomainObjectTestClass {


    @org.junit.Test
    public void DisplayAllPersonsData(){

        PersonServices personsData = new PersonServices();
        String jsonResponse = personsData.displayAllPersons();

        OutputDataService displayAllPersons = new OutputDataService();
        displayAllPersons.generatePersonsListPage(jsonResponse);

    }

    @org.junit.Test
    public void ConfirmPersonIsDead(){
        PersonServices personsData = new PersonServices();	
        personsData.PersonDeathProcedure("091178-5604");
    }

    @org.junit.Test
    public void TakeLoan(){
        PersonServices personsData = new PersonServices();
        personsData.PersonTakeLoan("251081-268K" , 500.80, 24, "Nordea");	
        personsData.PersonPayLoan("251081-268K", 100);
    }
		
	
}
