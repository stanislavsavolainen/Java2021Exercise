package task2;
import java.util.Arrays;
import java.util.HashSet;

public class FindSmalestNumberDistance {

  final int absoluteMaxElements = 1000;
  final int maxValue = 500;
  final int minValue = -500;
	
  boolean enableLog;
	
  public FindSmalestNumberDistance(){
    enableLog = true;
  }

  public void enableConsoleLog( boolean paramEnableLog ){ enableLog = paramEnableLog; }

  public int find( int numberArray[] , boolean allowNegativeNumbers ){

    int distance = -1;
    int result = -1;

    if(numberArray != null) {

      // means that we don't continue code under this condition, if we reach allowed elements limit in array
      if( numberArray.length > absoluteMaxElements ) return -3; 

      HashSet<Integer> removeDuplicate = new HashSet<Integer>();
      for ( int elementNumber : numberArray ) removeDuplicate.add( elementNumber);

      int [] handledNumberArray =  new int[ removeDuplicate.size() ];  

      int collectionLoopIndex = 0;
      for( Integer collectionElement : removeDuplicate  ) handledNumberArray[collectionLoopIndex++] = collectionElement;

      Arrays.sort( handledNumberArray);

      for( int index = 0; index <  handledNumberArray.length; index++ ){

        //very big or very small numbers is not allowed
        if(  maxValue < handledNumberArray[index] || minValue > handledNumberArray[index]  ) return -4;

          if(index <  handledNumberArray.length - 1 ) {

            if ( ! allowNegativeNumbers &&  ( handledNumberArray[index] < 0 || handledNumberArray[index + 1] < 0) ) continue;

            if ( ! allowNegativeNumbers ) {
              //ignore negative numbers in array	
              if(  handledNumberArray[index + 1] != handledNumberArray[index] ) 
                distance =  handledNumberArray[index + 1] - handledNumberArray[index]  - 1;
                //else if (  handledNumberArray[index + 1] ==  handledNumberArray[index] ) distance = 0; ( never used anymore, because duplicates removed )
              }

              else {
                //allow negative number in array
                //if (  handledNumberArray[index + 1] ==  handledNumberArray[index] ) distance = 0; //same reason, no duplicates anymore

                if( handledNumberArray[index] == 0   ){
                   //Zero left -> reach from positive side
                   
                	distance = handledNumberArray[index + 1] - 1;
                }  
            	
                else if( handledNumberArray[index + 1] == 0 ){
            	  //Zero right -> reach from negative side   
                
                	distance = Math.abs( handledNumberArray[index] ) -1;
            	}
            	  
            	else if( ( handledNumberArray[index] < 0 && handledNumberArray[index + 1] < 0 ) ){
                  distance = Math.abs( handledNumberArray[index + 1] -  handledNumberArray[index]) -1;
                }

                else if( ( handledNumberArray[index] > 0 && handledNumberArray[index + 1] < 0 ) || 
                  ( handledNumberArray[index] < 0 &&  handledNumberArray[index + 1] > 0 ) ) {
                  //distance =  Math.abs(numberArray[index + 1]) + Math.abs(numberArray[index])  - 1;
                  int first = -1;
                  int second = -1;

                  first = Math.abs(handledNumberArray[index]); 
                  second = Math.abs(handledNumberArray[index + 1]);

                  //distance =  Math.abs(numberArray[index + 1] + numberArray[index] )  - 1;
                  distance = second + first - 1;

               }

                else if ( handledNumberArray[index] > 0 && handledNumberArray[index + 1] > 0 ){

                  if( handledNumberArray[index + 1] != handledNumberArray[index] ) 
                    distance = handledNumberArray[index + 1] - handledNumberArray[index]  - 1;
                    else if ( handledNumberArray[index + 1] == handledNumberArray[index] ) distance = 0; 
                  }

                //} //else

              } //if - allowNegative

              if(distance < result || result == -1 ) result = distance;

              if(enableLog){
                System.out.println( "First number value is : " + handledNumberArray[index] + ", second number value is : " + 
                  handledNumberArray[index + 1] + " and thouse number distance is : " + distance  +" , because count amount of following numbers :  ");
              }


                //prevent console log spam, if number is very big
                if( ( maxValue > handledNumberArray[index] && minValue < handledNumberArray[index]) ){

                  if(enableLog){
                    for( int indx = ( handledNumberArray[index] + 1 ) ; indx < ( handledNumberArray[index + 1] ) ; indx++){
                      System.out.print( " " + indx + "," );
                    } 
                    System.out.println("");
                  }

                }

          }

      } //for

    } //null
    else result = -2; 
    return result;
  }


}
