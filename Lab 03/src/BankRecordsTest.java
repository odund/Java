/**
 * This program reads the client data from the file, processes it and has an option 
 * to print original unsorted records details on the output screen. Then it will sort
 * data based on location, gender, income and entire sorted records on the screen. Then
 * it will process some analytics and display on output screen as well as to write to a file
 * 
 * Lab 03
 * File Name: BankRecordsTest.java
 * @author Oleg Grigoryan
 * @since 2020-06-11
 * 
 */

public class BankRecordsTest {

	public static void main(String[] args) {
		
		// Create Records object
		Records br = new Records();
		// Call readData method to invoke programs to generate client records from file
		br.readData();
		// Call LocationComp method to invoke programs to process data analytics
		br.LocationComp();
						
	}

}
