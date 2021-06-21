
import java.util.ArrayList;
import java.util.Scanner;

import task1.Person;
import task1.OutputDataService;
import task1.PersonServices;
import task2.FindSmalestNumberDistance;

public class Main {

    static boolean personNumberInList( String input, String data ) {

        String compareNumber [] = data.split(",");

        for( String compareElement : compareNumber ){
            if( compareElement.equals(input) && ! input.equals("") ) return true;
        }

        return false;
    }

    public static void main( String args [] ){

        System.out.println();
        System.out.println("======== First task ================");
	
        PersonServices service = new PersonServices();
        ArrayList <Person> persondata = service.transferCurrentPersonData();

        System.out.println("Service application");
        System.out.println("Press 1 to generate all persons");
        System.out.println("Press 2 to take loan");
        System.out.println("Press 3 to pay loan");
        System.out.println("Press 4 to confirm death persons ");
        System.out.println("Other input will skip task1 program ");
        
        String personNumbers = "";

        for( Person element : persondata ){
            personNumbers += element.getSocialSecurityNumber() +",";
        }

        Scanner input = new Scanner(System.in);
        String option1 = input.next();
		//input.close();

        if(option1.equals("1") ) {

            String jsonResponse = service.displayAllPersons();

            OutputDataService displayAllPersons = new OutputDataService();
            displayAllPersons.generatePersonsListPage(jsonResponse);
        }

        else if(option1.equals("2") ) {

            System.out.println("You selected take loan option ");
            System.out.println("View following person numbers :" + personNumbers );
            System.out.println("Input one of following number : ");
            String selectedPersonNumber = input.next();
            if( personNumberInList(selectedPersonNumber, personNumbers) ) {
                System.out.println("Input loan size in decimal : ");
                String loanSize = input.next();
                try{
                    double loanSizeValue = Double.parseDouble(loanSize);
                    int loanLenghtInMonths = 24;
                    service.PersonTakeLoan( selectedPersonNumber , loanSizeValue, loanLenghtInMonths , "Nordea");
                } catch ( Exception e) {
                    System.out.println("Not valid decimal number for loan size");
                }
            } else {
                System.out.println("This person number not exsist in system");
            }
        }

        else if(option1.equals("3") ) {

            System.out.println("You selected pay loan option ");
            System.out.println("View following person numbers :" + personNumbers );
            System.out.println("Input one of following number : ");
            String selectedPersonNumber = input.next();

            if( personNumberInList(selectedPersonNumber, personNumbers) ) {
               
            	System.out.println("Input payment size in decimal : ");
                String loanSize = input.next();

                try{
                    double loanSizeValue = Double.parseDouble(loanSize);
                    int loanLenghtInMonths = 24;
                    service.PersonPayLoan( selectedPersonNumber , loanSizeValue);
               } catch ( Exception e) {
                   System.out.println("Not valid decimal number for loan size");
               }
            } else {
                System.out.println("This person number not exsist in system");
            }
        }

        else if(option1.equals("4") ) {

            System.out.println("You selected confirm death person option ");
            System.out.println("View following person numbers :" + personNumbers );

            System.out.println("Input one of following number : ");
            String selectedPersonNumber = input.next();

            if( personNumberInList(selectedPersonNumber, personNumbers) ) {
                service.PersonDeathProcedure(selectedPersonNumber);
            } else {
                System.out.println("This person number not exsist in system");
            }
        }

        else{
            System.out.println("This option not match any action in task1");
        }
        System.out.println();
        System.out.println("======== Second task ==============");

        FindSmalestNumberDistance measureNumberDistance = new FindSmalestNumberDistance();

        Integer inputValue = 0;

        ArrayList<Integer> inputNumberArray = new ArrayList<Integer>();

        for(int numberCount = 0; numberCount <  measureNumberDistance.absoluteMaxElements; numberCount++) {

            System.out.println("Add numbers for distance measurement (not valid number stop input-chain )");

            String selectedPersonNumber = input.next();

            try {
                inputValue = Integer.parseInt(selectedPersonNumber);
            } catch ( Exception e) {
                break;
            } finally {

                if( numberCount > measureNumberDistance.absoluteMaxElements ) break;   
                if(  measureNumberDistance.maxValue < inputValue || measureNumberDistance.minValue > inputValue ) break; 

                inputNumberArray.add(inputValue);

            } //finally

        }//for	
        input.close();

        int [] paramNumbers = new int[ inputNumberArray.size() ]; 
        int collectionLoopIndex = 0;
        for( Integer collectionElement : inputNumberArray  ) paramNumbers[collectionLoopIndex++] = collectionElement;

        int answer = measureNumberDistance.find( paramNumbers , true);
        if( answer < 0 ) System.out.println("You have wrong input, if input is right, then distance will never be negative");
        System.out.println("Minimal distance between numbers is : " + answer);
        
    }

}
