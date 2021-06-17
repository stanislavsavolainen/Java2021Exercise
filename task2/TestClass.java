package task2;



import static org.junit.Assert.*;


// https://repo1.maven.org/maven2/junit/junit/4.8.2/junit-4.8.2.jar


public class TestClass {

	
	public void linebreaks(){
		for( int linebreak = 0; linebreak < 3; linebreak++)System.out.println();
	}
	
		
	@org.junit.Test
	public void testCorrectAnswer(){
		
		linebreaks();
		
		System.out.println("Test \"CorrectAnswer\": function give result what expected");
		
		boolean canUseNegativeValuesInArray = false;
		int myNumbers[] = { 1, 4, 10 };
		
		FindSmalestNumberDistance searchClass = new FindSmalestNumberDistance();
		int findMinDistance = searchClass.find(myNumbers, canUseNegativeValuesInArray);
		System.out.print("Minimal distance between numbers is : " + findMinDistance);
		
		assertSame(findMinDistance, 2);
		
	}
	
	
	@org.junit.Test
	public void testWithNegativeNumbers(){
		
		linebreaks();
		
		System.out.println("Test \"WithNegativeNumbers\": function use abs-values for count distance between positive and negative numbers");
		
		boolean canUseNegativeValuesInArray = true;
		int myNumbers[] = { -12, 5, 8, 2, 25, 14, -27, 23 };
		
		FindSmalestNumberDistance searchClass = new FindSmalestNumberDistance();
		int findMinDistance = searchClass.find(myNumbers, canUseNegativeValuesInArray);
		System.out.print("Minimal distance between numbers is : " + findMinDistance);
		
		assertTrue( ! ( findMinDistance <= 0 || findMinDistance > ( searchClass.maxValue +  Math.abs(searchClass.minValue)  ) ) );
		
	}
	
}
