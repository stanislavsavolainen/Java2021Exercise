package task1;
import java.io.FileWriter;
import java.io.IOException;


public class GenerateHtmlFromTemplate {

	
	final String path = "C:/myprogrammingworkspace/";
	final String filename1 = "users.html";
	
	
	public void generateSingleUserProfilePage( String simulateHttpResponseJSON ) {
		
	}
	
	
	public void generateUsersListPage( String simulateHttpResponseJSON  ) {
		
		String hardcodedHtmlPageTemplate="";
		
		hardcodedHtmlPageTemplate +="\n\r <!DOCTYPE html>";
		hardcodedHtmlPageTemplate +="\n\r <html>";
		hardcodedHtmlPageTemplate +="\n\r <script>";
		hardcodedHtmlPageTemplate +="\n\r function init() {";
		hardcodedHtmlPageTemplate +="\n\r \t console.log('init happen');";
		hardcodedHtmlPageTemplate +="\n\r \t let personArrayObject = ";
		hardcodedHtmlPageTemplate += simulateHttpResponseJSON;
		hardcodedHtmlPageTemplate +=";";
		hardcodedHtmlPageTemplate +="\n\r \t document.getElementById(\"listjson\").innerHTML = JSON.stringify( personArrayObject , undefined, 2 );";
		hardcodedHtmlPageTemplate +="\n\r \t console.log(Object.keys(personArrayObject.users[0]));";
		hardcodedHtmlPageTemplate +="\n\r \t let titledata = Object.keys(personArrayObject.users[0]);";
		//---------- generate html table ----------------
		hardcodedHtmlPageTemplate += "\n\r \t let uiTable = '';";
		hardcodedHtmlPageTemplate +="\n\r \t for( let titleIndex = 0; titleIndex < titledata.length;  titleIndex++)"
				+ "uiTable += '<th id=\"'+ titledata[titleIndex]+'\">'+ titledata[titleIndex]+ '</th>';";    
		hardcodedHtmlPageTemplate +="\n\r \t personArrayObject.users.forEach( function ( element ) { ";
		hardcodedHtmlPageTemplate +="\n\r \t\t uiTable += '<tr>';";
		hardcodedHtmlPageTemplate +="\n\r \t\t titledata.forEach( function ( subelement ) { ";
		hardcodedHtmlPageTemplate +="\n\r \t\t\t uiTable += '<td>' + element[subelement] + '</td>';";
		hardcodedHtmlPageTemplate +="\n\r \t\t });";	
		hardcodedHtmlPageTemplate +="\n\r \t\t uiTable += '</tr>';";
		hardcodedHtmlPageTemplate +="\n\r \t });";	
		hardcodedHtmlPageTemplate +="\n\r \t document.getElementById(\"tableusers\").innerHTML = uiTable;";
		//-----------------------------------------------
		hardcodedHtmlPageTemplate +="\n\r }";
		hardcodedHtmlPageTemplate +="\n\r </script>";
		hardcodedHtmlPageTemplate +="\n\r <body onload=\"init()\">";
		hardcodedHtmlPageTemplate +="\n\r <pre id=\"listjson\"></pre>";
		hardcodedHtmlPageTemplate +="\n\r <table id=\"tableusers\" border=\"4\"></table>";
		hardcodedHtmlPageTemplate +="\n\r </body>";
		hardcodedHtmlPageTemplate +="\n\r </html>";
		
		writeToFile( hardcodedHtmlPageTemplate , path + filename1 );
		
	}
	
	
	public void writeToFile( String content, String filename ) {
		
		try {
		      FileWriter myWriter = new FileWriter(filename);
		      myWriter.write(content);
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}
	
	
	
}
