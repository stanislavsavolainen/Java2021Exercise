package com.mycompany.app;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.mycompany.app.task2.FindSmalestNumberDistance;
import com.mycompany.app.task1.PersonServices;
import com.mycompany.app.task1.OutputDataService;

/**
 * Unit test for potential job task.
 */
public class AppTest 
{
    
    public void linebreaks(){
        for( int linebreak = 0; linebreak < 3; linebreak++)System.out.println();
    }
    
	//====================== task 1 ===========================
	
	@Test
    public void DisplayAllPersonsData(){
		
		linebreaks();
		System.out.println("task1: Test -> display all persons ");

        PersonServices personsData = new PersonServices();
        String jsonResponse = personsData.displayAllPersons();

        OutputDataService displayAllPersons = new OutputDataService();
        displayAllPersons.generatePersonsListPage(jsonResponse);

    }

    @Test
    public void ConfirmPersonIsDead(){
		
		linebreaks();
		System.out.println("task1: Test -> confirm person is death ");
		
        PersonServices personsData = new PersonServices();	
        personsData.PersonDeathProcedure("091178-5604");
    }

    @Test
    public void TakeLoan(){
		
		linebreaks();
		System.out.println("task1: Test -> get loan and pay loan ");
		
        PersonServices personsData = new PersonServices();
        personsData.PersonTakeLoan("251081-268K" , 500.80, 24, "Nordea");	
        personsData.PersonPayLoan("251081-268K", 100);
    }
	
	
    //====================== task 2 =========================== 	
    @Test
    public void testCorrectAnswer(){

        linebreaks();
        System.out.println("task2: Test \"CorrectAnswer\": function give result what expected");

        boolean canUseNegativeValuesInArray = false;
        int myNumbers[] = { 1, 4, 10 };

        FindSmalestNumberDistance searchClass = new FindSmalestNumberDistance();
        int findMinDistance = searchClass.find(myNumbers, canUseNegativeValuesInArray);
        System.out.print("Minimal distance between numbers is : " + findMinDistance);

        assertSame(findMinDistance, 2);

    }

    @Test
    public void testWithNegativeNumbers(){

        linebreaks();
        System.out.println("task2: Test \"WithNegativeNumbers\": function use abs-values for count distance between positive and negative numbers");

        boolean canUseNegativeValuesInArray = true;
        int myNumbers[] = { -13, 7, 10, 6, 25, 14, -27, 23 , -5 , -3 };

        FindSmalestNumberDistance searchClass = new FindSmalestNumberDistance();
        int findMinDistance = searchClass.find(myNumbers, canUseNegativeValuesInArray);
        System.out.print("Minimal distance between numbers is : " + findMinDistance);

        assertTrue( ! ( findMinDistance < 0 || findMinDistance > ( searchClass.maxValue +  Math.abs(searchClass.minValue)  ) ) );
    }

    @Test
    public void checkMaxMinValue(){

        linebreaks();
        System.out.println("task2: Test \"checkMinMaxValue\": function check if array elements values are "
            + "between min and max range, otherwise test not pass  ");

        boolean canUseNegativeValuesInArray = true;
        FindSmalestNumberDistance searchClass = new FindSmalestNumberDistance();
        searchClass.enableConsoleLog(false);
        int myNumbers[] = { searchClass.maxValue , searchClass.minValue };
        int findMinDistance = searchClass.find(myNumbers, canUseNegativeValuesInArray);
        System.out.print("Minimal distance between numbers is : " + findMinDistance);

        //assertTrue( ( findMinDistance >= 0 || findMinDistance < ( searchClass.maxValue +  Math.abs(searchClass.minValue) ) ) );
        assertTrue( ! ( findMinDistance <= 0 || findMinDistance > ( searchClass.maxValue +  Math.abs(searchClass.minValue)  ) ) );
    }

    @Test
    public void checkZeroValue(){

        linebreaks();
        System.out.println("task2: Test \"ZeroValue\": array can now have zero-value element in array without bug in appication ");

        boolean canUseNegativeValuesInArray = true;
        FindSmalestNumberDistance searchClass = new FindSmalestNumberDistance();
        int myNumbers[] = { -3 , -10, 0, 5, 8 , 0 };
        int findMinDistance = searchClass.find(myNumbers, canUseNegativeValuesInArray);
        System.out.print("Minimal distance between numbers is : " + findMinDistance);

        //assertTrue( ( findMinDistance >= 0 || findMinDistance < ( searchClass.maxValue +  Math.abs(searchClass.minValue) ) ) );
        assertTrue( ! ( findMinDistance <= 0 || findMinDistance > ( searchClass.maxValue +  Math.abs(searchClass.minValue)  ) ) );

    }

    @Test
    public void checkOnlyOneElement(){

        linebreaks();
        System.out.println("task2: Test \"OnlyOneElement\": if array have only one element ");

        boolean canUseNegativeValuesInArray = false;
        // this also present same case as int myNumbers[] = { 34, 34, 34 };
        int myNumbers[] = { 34 }; 

        FindSmalestNumberDistance searchClass = new FindSmalestNumberDistance();
        int findMinDistance = searchClass.find(myNumbers, canUseNegativeValuesInArray);
        System.out.print("Minimal distance between numbers is : " + findMinDistance);

        assertSame(findMinDistance, 0);

    }
	
    @Test
    public void checkNullElement(){

        linebreaks();
        System.out.println("task2: Test \"NullElement\": if array don't have any element or not initialized ");

        boolean canUseNegativeValuesInArray = false;

        int myNumbers[] = null; 

        FindSmalestNumberDistance searchClass = new FindSmalestNumberDistance();
        int findMinDistance = searchClass.find(myNumbers, canUseNegativeValuesInArray);
        System.out.print("Minimal distance between numbers is : " + findMinDistance);

        assertSame(findMinDistance, -2);

    }
	
    @Test
    public void sameDistance(){

        linebreaks();
        System.out.println("task2: Test \"sameDistance\": if array elements have same distance ");

        boolean canUseNegativeValuesInArray = true;

        int myNumbers[] = { 1, 3 , 5, -1 }; 

        FindSmalestNumberDistance searchClass = new FindSmalestNumberDistance();
        int findMinDistance = searchClass.find(myNumbers, canUseNegativeValuesInArray);
        System.out.print("Minimal distance between numbers is : " + findMinDistance);

        assertSame(findMinDistance, 1);
		
		System.out.println();

    }

}
