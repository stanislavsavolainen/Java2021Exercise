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
		int myNumbers[] = { -13, 7, 10, 6, 25, 14, -27, 23 , -5 , -3 };
		
		FindSmalestNumberDistance searchClass = new FindSmalestNumberDistance();
		int findMinDistance = searchClass.find(myNumbers, canUseNegativeValuesInArray);
		System.out.print("Minimal distance between numbers is : " + findMinDistance);
		
		assertTrue( ! ( findMinDistance < 0 || findMinDistance > ( searchClass.maxValue +  Math.abs(searchClass.minValue)  ) ) );
	}
	
	
	@org.junit.Test
	public void checkMaxMinValue(){
		
		linebreaks();
		System.out.println("Test \"checkMinMaxValue\": function check if array elements values are "
				+ "between min and max range, otherwise test not pass  ");
		
		boolean canUseNegativeValuesInArray = true;
		FindSmalestNumberDistance searchClass = new FindSmalestNumberDistance();
		searchClass.enableConsoleLog(false);
		int myNumbers[] = { searchClass.maxValue -1  , searchClass.minValue + 1 };
		int findMinDistance = searchClass.find(myNumbers, canUseNegativeValuesInArray);
		System.out.print("Minimal distance between numbers is : " + findMinDistance);
		
		//assertTrue( ( findMinDistance >= 0 || findMinDistance < ( searchClass.maxValue +  Math.abs(searchClass.minValue) ) ) );
		assertTrue( ! ( findMinDistance < 0 || findMinDistance > ( searchClass.maxValue +  Math.abs(searchClass.minValue)  ) ) );
	}
	
	@org.junit.Test
	public void checkZeroValue(){
		
		linebreaks();
		System.out.println("Test \"ZeroValue\": array can now have zero-value element in array without bug in appication ");
		
		
		boolean canUseNegativeValuesInArray = true;
		FindSmalestNumberDistance searchClass = new FindSmalestNumberDistance();
		int myNumbers[] = { -3 , -10, 0, 5, 8 , 0 };
		int findMinDistance = searchClass.find(myNumbers, canUseNegativeValuesInArray);
		System.out.print("Minimal distance between numbers is : " + findMinDistance);
		
		//assertTrue( ( findMinDistance >= 0 || findMinDistance < ( searchClass.maxValue +  Math.abs(searchClass.minValue) ) ) );
		assertTrue( ! ( findMinDistance <= 0 || findMinDistance > ( searchClass.maxValue +  Math.abs(searchClass.minValue)  ) ) );
		
	}
	
}
