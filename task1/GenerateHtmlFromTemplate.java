package task1;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateHtmlFromTemplate {

	// output front-end file path
	final String path = "C:/outputfile/"; 
	final String filename1 = "users.html";
	final String filename2 = "user.html";
	final String filename3 = "death.html";
	final String filename4 = "loan.html";
	final String filename5 = "litigation.html";
	
	//https://character-table.netlify.app/finnish/   ( unicode value )
	
	// Ä = 0x00C4 = &#xC4;
	// ä = 0x00E4 = &#xE4;
	// Ö = 0x00D6 = &#D6;
	// ö = 0x00F6 = &#F6;
	// Å = 0x00C5 = &#C6;
	// å = 0x00E5 = &#E5;
	
	
	public void generateServiceReport( String simulateHttpResponseJSON , String reportType ){
		
		String hardcodedHtmlPageTemplate = "";
		
		if( reportType.equals("death")){
			
			System.out.println("death happen !");
			
			hardcodedHtmlPageTemplate += htmlUpBlock();
			hardcodedHtmlPageTemplate += htmlMidleBlock( reportType , simulateHttpResponseJSON);
			hardcodedHtmlPageTemplate += htmlDownBlock();
			
			writeToFile( hardcodedHtmlPageTemplate , path + filename3 );
		}
		
		else if ( reportType.equals("loan")){ 
			
			
			hardcodedHtmlPageTemplate += htmlUpBlock();
			hardcodedHtmlPageTemplate += htmlMidleBlock(reportType , simulateHttpResponseJSON);
			hardcodedHtmlPageTemplate += htmlDownBlock();
			
			writeToFile( hardcodedHtmlPageTemplate , path + filename4 );
		} 
		
		else if ( reportType.equals("litigation")){ 
			
			hardcodedHtmlPageTemplate += htmlUpBlock();
			hardcodedHtmlPageTemplate += htmlMidleBlock( reportType,  "Litigation report");
			hardcodedHtmlPageTemplate += htmlDownBlock();
			
			writeToFile( hardcodedHtmlPageTemplate , path + filename5 );
		} 
		
		else {
			System.out.println("Unknown report");
			
		}
		
		//hardcodedHtmlPageTemplate +="\n\r </script>";
		
		//hardcodedHtmlPageTemplate +="\n\r <pre id=\"listjson\"></pre>";
		//hardcodedHtmlPageTemplate +="\n\r <table id=\"tableusers\" border=\"4\"></table>";
		hardcodedHtmlPageTemplate +="\n\r </body>";
		hardcodedHtmlPageTemplate +="\n\r </html>";
		
	}
	
	
	public void generateSingleUserProfilePage( String simulateHttpResponseJSON ) {
		
	}
	
	public void generatePersonsListPage( String simulateHttpResponseJSON  ) {
		
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
		hardcodedHtmlPageTemplate +="\n\r \t console.log(Object.keys(personArrayObject.persons[0]));";
		hardcodedHtmlPageTemplate +="\n\r \t let titledata = Object.keys(personArrayObject.persons[0]);";
		//---------- generate html table ----------------
		hardcodedHtmlPageTemplate += "\n\r \t let uiTable = '';";
		hardcodedHtmlPageTemplate +="\n\r \t for( let titleIndex = 0; titleIndex < titledata.length;  titleIndex++)"
				+ "uiTable += '<th id=\"'+ titledata[titleIndex]+'\">'+ titledata[titleIndex]+ '</th>';";    
		hardcodedHtmlPageTemplate +="\n\r \t personArrayObject.persons.forEach( function ( element ) { ";
		hardcodedHtmlPageTemplate +="\n\r \t\t uiTable += '<tr>';";
		hardcodedHtmlPageTemplate +="\n\r \t\t titledata.forEach( function ( subelement ) { ";
		hardcodedHtmlPageTemplate +="\n\r \t\t\t uiTable += '<td>' + element[subelement] + '</td>';";
		hardcodedHtmlPageTemplate +="\n\r \t\t });";	
		hardcodedHtmlPageTemplate +="\n\r \t\t uiTable += '</tr>';";
		hardcodedHtmlPageTemplate +="\n\r \t });";	
		hardcodedHtmlPageTemplate +="\n\r \t document.getElementById(\"tablepersons\").innerHTML = uiTable;";
		//-----------------------------------------------
		hardcodedHtmlPageTemplate +="\n\r }";
		hardcodedHtmlPageTemplate +="\n\r </script>";
		hardcodedHtmlPageTemplate +="\n\r <body onload=\"init()\">";
		hardcodedHtmlPageTemplate +="\n\r <pre id=\"listjson\"></pre>";
		hardcodedHtmlPageTemplate +="\n\r <table id=\"tablepersons\" border=\"4\"></table>";
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
	
	public String htmlUpBlock(){ 
		
		String hardcodedHtmlPageTemplate = "";
		
		hardcodedHtmlPageTemplate +="\n\r <!DOCTYPE html>";
		hardcodedHtmlPageTemplate +="\n\r <html>";
		
		return hardcodedHtmlPageTemplate; 
	}
	
	public String htmlMidleBlock( String reportType , String content ){
		
		String hardcodedHtmlPageTemplate = "";
		hardcodedHtmlPageTemplate +="\n\r <script>";
		hardcodedHtmlPageTemplate +="\n\r function init() {";
		hardcodedHtmlPageTemplate +="\n\r \t let personObject = "+content + ";";
		hardcodedHtmlPageTemplate +="\n\r \t let uiPage = \"\" ;";
		//================= type block =======================
		//hardcodedHtmlPageTemplate +="\n\r \t if( personObject.datatype == \"death\" ) { ";
		// Kuolintodistus template 
		if(reportType.equals("death")){
			hardcodedHtmlPageTemplate +="\n\r \t\t uiPage += '<div> <h1> Kuolintodistus template ( Death report )</h1></div>';";
			hardcodedHtmlPageTemplate +="\n\r \t\t uiPage += '<div> Fullname : ' + personObject.fullname + ' </div>';";
			hardcodedHtmlPageTemplate +="\n\r \t\t uiPage += '<div> Social Security number : ' + personObject.socialsecuritynumber + ' </div>';";
			hardcodedHtmlPageTemplate +="\n\r \t\t uiPage += '<div> Living time : ' + personObject.birthdate + ' - ' + personObject.deathdate + ' </div>';";
		//hardcodedHtmlPageTemplate +="\n\r \t } ";
		}
		// Lainahakemus template 
		//hardcodedHtmlPageTemplate +="\n\r \t else if( personObject.datatype == \"loan\" ) { ";
		else if(reportType.equals("loan")){
			hardcodedHtmlPageTemplate +="\n\r \t\t uiPage += '<div><h1>Lainasopimus template ( Loan report ) </h1></div>';";
			hardcodedHtmlPageTemplate +="\n\r \t\t uiPage += '<div> Fullname : ' + personObject.fullname + ' </div>';";
			hardcodedHtmlPageTemplate +="\n\r \t\t uiPage += '<div> Loan size : ' + personObject.loansize + ' € </div>';";
			hardcodedHtmlPageTemplate +="\n\r \t\t uiPage += '<div> Loan pay back months : ' + personObject.loanlenght + '</div>';";
			hardcodedHtmlPageTemplate +="\n\r \t\t uiPage += '<div> Bank ( who offer loan ) : ' + personObject.bankname + '</div>';";
		//hardcodedHtmlPageTemplate +="\n\r \t } ";
		}
		// Rikosilmoitus template
		else if(reportType.equals("litigation")){
		//hardcodedHtmlPageTemplate +="\n\r \t else if( personObject.datatype == \"litigation\" ) { ";
			hardcodedHtmlPageTemplate +="\n\r \t\t uiPage += '<div>Rikosilmoitus template ( Report of an offence ) <h1></h1></div>';";
			//hardcodedHtmlPageTemplate +="\n\r \t } ";
			//hardcodedHtmlPageTemplate +="\n\r \t document.getElementById(\"contentUi\").innerHTML = uiPage;";
		//hardcodedHtmlPageTemplate +="\n\r }";
		}
		//====================================================
		hardcodedHtmlPageTemplate +="\n\r \t document.getElementById(\"contentUi\").innerHTML = uiPage;";
		hardcodedHtmlPageTemplate +="\n\r \t } ";
		hardcodedHtmlPageTemplate +="\n\r </script>";
		hardcodedHtmlPageTemplate +="\n\r <body onload=\"init()\">";
		hardcodedHtmlPageTemplate +="\n\r <div id=\"contentUi\"></div>";
	
		//return "\n\r <h1>"+content+"</h1>";
		return hardcodedHtmlPageTemplate;
	}
	
	public String htmlDownBlock(){
		
		String hardcodedHtmlPageTemplate = "";
		
		hardcodedHtmlPageTemplate +="\n\r </body>";
		hardcodedHtmlPageTemplate +="\n\r </html>";
		
		return hardcodedHtmlPageTemplate; 
	}
	
}
