/**
 * This BankRecords class utilizes the Client.java abstract methods and generates
 * ultimately the client records from file. There are three files available:
 * 1. Original bank-Detail.csv file that contain 600 records
 * 2. New 20Records.csv file with 20 records
 * 3. New EmptyFile.csv with no records
 * The user will be asked whether to print entire record or default. The default option
 * will display is up to 25 records with only following columns: ID, AGE, SEX, REGION, INCOME, MORTGAGE.
 * In case of empty file there will be an exception thrown. If provided file doesn't exist, an
 * exception will be thrown.
 * 
 * Lab 02
 * File Name: BankRecords.java
 * @author Oleg Grigoryan
 * @since 2020-06-11
 */

import java.io.*;					// Needed for File I/O classes and IOException
import java.text.SimpleDateFormat;	// Needed for date format
import java.util.*;					// Needed for Array classes and Scanner

public class BankRecords extends Client {
	
	final int MAX_NUM_RECORDS = 600;
	final int DEFAULT_NUM_RECORDS_TO_PRINT = 25;
	private String filename;
	
	// array of BankRecords objects
	BankRecords robjs[] = new BankRecords[MAX_NUM_RECORDS];
	// arraylist to hold spreadsheet rows & columns
	static ArrayList<List<String>> array = new ArrayList<>();
	
	// instance fields
	private String id;				// to hold the client's ID
	private int age;				// to hold the client's age
	private String sex;				// to hold the client's gender {MALE,FEMALE}
	private String region;			// to hold the client's location {INNER_CITY,TOWN,RURAL,SUBURBAN}
	private double income;			// to hold the client's income
	private String married;			// to hold the client's marriage status
	private int children;			// to hold the client's number of children
	private String car;				// to hold the the info whether client owns a car
	private String save_act;		// to hold the info whether the client has a saving account
	private String current_act;		// to hold the info whether the client currently has bank account
	private String mortgage;		// to hold the info whether the client has a mortgage
	private String pep;				// to hold the info whether the client is politically exposed person (PEP)
	
	/**
	 * This method returns the client's ID
	 * @return The value of id field
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * This method sets the client's ID
	 * @param id The client's ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * This method returns the client's age
	 * @return The value of age field
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * This method sets the client's age
	 * @param age The client's age
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * This method returns the client's gender
	 * @return sex The client's gender
	 */
	public String getSex() {
		return sex;
	}
	
	/**
	 * This method sets the client's gender
	 * @param sex The client's gender
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	/**
	 * This method returns the client's region
	 * @return region The client's region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * This method sets the client's region
	 * @param region The client's region
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * This method returns the client's income
	 * @return income The client's income
	 */
	public double getIncome() {
		return income;
	}

	/**
	 * This method sets the client's income
	 * @param income The client's income
	 */
	public void setIncome(double income) {
		this.income = income;
	}

	/**
	 * This method returns the client's marriage status
	 * @return married The client's marriage status
	 */
	public String getMarried() {
		return married;
	}

	/**
	 * This method sets the client's marriage status
	 * @param married The client's marriage status
	 */
	public void setMarried(String married) {
		this.married = married;
	}

	/**
	 * This method returns the client's number of children
	 * @return children The client's number of children
	 */
	public int getChildren() {
		return children;
	}

	/**
	 * This method sets the client's number of children
	 * @param children The client's number of children
	 */
	public void setChildren(int children) {
		this.children = children;
	}

	/**
	 * This method returns info whether the client owns a car
	 * @return car The client's car ownership
	 */
	public String getCar() {
		return car;
	}

	/**
	 * This method sets info whether the client owns a car
	 * @param car The client's car ownership
	 */
	public void setCar(String car) {
		this.car = car;
	}

	/**
	 * This method returns the info whether the client has a saving account
	 * @return save_act The client's saving account
	 */
	public String getSave_act() {
		return save_act;
	}

	/**
	 * This method sets the info whether the client has a saving account
	 * @param save_act The client's saving account
	 */
	public void setSave_act(String save_act) {
		this.save_act = save_act;
	}

	/**
	 * This method returns the info whether the client currently has an account
	 * @return current_act The client's current account
	 */
	public String getCurrent_act() {
		return current_act;
	}

	/**
	 * This method sets the info whether the client currently has an account
	 * @param current_act The client's current account
	 */
	public void setCurrent_act(String current_act) {
		this.current_act = current_act;
	}

	/**
	 * This method returns the info whether the client currently has a mortgage
	 * @return mortgage The client's mortgage info
	 */
	public String getMortgage() {
		return mortgage;
	}

	/**
	 * This method sets the info whether the client currently has a mortgage
	 * @param mortgage The client's mortgage info
	 */
	public void setMortgage(String mortgage) {
		this.mortgage = mortgage;
	}

	/**
	 * This method returns the info whether the client is politically exposed person (PEP)
	 * @return pep The client' s PEP info
	 */
	public String getPep() {
		return pep;
	}

	/**
	 * This method sets the info whether the client is politically exposed person (PEP)
	 * @param pep The client's PEP info
	 */
	public void setPep(String pep) {
		this.pep = pep;
	}

	// Tell Java compiler that the readData method is meant to override a method in the superclass
	@Override
	/**
	 * The readData method reads in all the clients' record data from csv file
	 * in the path into an ArrayList
	 */
	public void readData() 
	{
		// Create a Scanner object for keyboard input
		Scanner keyboard = new Scanner(System.in);
		
		// Get the file name
		System.out.print("Enter the filename: ");
		filename = keyboard.nextLine();
		
		BufferedReader br = null;
		
		try 
		{
			// initialize reader object and set file path to root of project
			br = new BufferedReader(new FileReader(new File(filename)));
			
			String line;
			// read each record in csv file
			while ((line = br.readLine()) != null)
			{
				// parse each record in csv file by a comma ( , )
				// into a list stored in the arraylist-> Arrays
				array.add(Arrays.asList(line.split(",")));
			}
			
			// call function for processing record data
			processData();
			
		} catch (IOException e) 
		{
			System.err.println("An IOException was caught!");
			e.printStackTrace();
		
		} finally 
		{
			try 
			{
				// Close the file
				//inputFile.close();
				br.close();
			}
			catch (IOException e) 
			{
			System.err.println("An IOException was caught!");
			e.printStackTrace();
			}
		}
				
		// Dismiss the Scanner class object
		keyboard.close();
		
	}
	
	// Tell Java compiler that the processData method is meant to override a method in the superclass
	@Override
	/**
	 * The processData method takes all the clients' record data from the ArrayList and
	 * adds the data into each of instance fields via the setters. This method uses an
	 * array of objects to store record data for each instance field.
	 */
	public void processData() 
	{
		// create an index for array while iterating through arraylist
		int indx = 0;
		
		// create for each loop to cycle thru arraylist of values
		// and pass that data into your record objects' setters
		for (List<String> rowData: array) 
		{
			// initialize array of objects
			robjs[indx] = new BankRecords();
			// call setters below and populate them, item by item
			robjs[indx].setId(rowData.get(0));								// get 1st column
			robjs[indx].setAge(Integer.parseInt(rowData.get(1)));			// get 2nd column
			robjs[indx].setSex(rowData.get(2));								// get 3rd column
			robjs[indx].setRegion(rowData.get(3));							// get 4th column
			robjs[indx].setIncome(Double.parseDouble(rowData.get(4)));		// get 5th column
			robjs[indx].setMarried(rowData.get(5));							// get 6th column
			robjs[indx].setChildren(Integer.parseInt(rowData.get(6)));		// get 7th column
			robjs[indx].setCar(rowData.get(7));								// get 8th column
			robjs[indx].setSave_act(rowData.get(8));						// get 9th column
			robjs[indx].setCurrent_act(rowData.get(9));						// get 10th column
			robjs[indx].setMortgage(rowData.get(10));						// get 11th column
			robjs[indx].setPep(rowData.get(11));							// get 12th column
			
			indx++;
		}
		// call function to print objects held in memory
		printData();
	}
	
	// Tell Java compiler that the printData method is meant to override a method in the superclass
	@Override
	/**
	 * The printData method prints the first 25 records for various fields to the console 
	 * via the getters
	 */
	public void printData() {
		
		String input;		// To hold a line of input
		char answer;		// To hold a single character
		int numRecToPrint;	// To hold number of records to print
		
		// Create a Scanner class object for keyboard input
		Scanner sc = new Scanner(System.in);
		// Ask the user if how many records to display
		System.out.print("Would you like to print an entire records in the file (if no, default is up to 25 records)? (Y or y (yes) / N or n (no): )");
		// Get a line of input
		input = sc.next();
		// Get the first character
		answer = input.charAt(0);
		
		if ((answer == 'Y') || (answer == 'y'))
		{
			numRecToPrint = array.size();
		} else {
			if (array.size() < DEFAULT_NUM_RECORDS_TO_PRINT)
			{
				numRecToPrint = array.size();
			} else {
				numRecToPrint = DEFAULT_NUM_RECORDS_TO_PRINT;
			}
		}
		
		// Display a total number of records from the file
		System.out.printf("The total number of records from %s file is %d: \n", filename, array.size());
		
		// Check if entire records needs to print
		if ((answer == 'Y') || (answer == 'y'))
		{
			System.out.println("Client(s) full records");
			System.out.println("ID\t\t\tAGE\t\t\tSEX\t\t\tREGION\t\t\tINCOME\t\t\tMARRIED\t\t\tCHILDREN\t\tOWNCAR\t\tSAVINGACCOUNT\t\tCURENTACCOUNT\t\tMORTGAGE\t\tPEP");
			for (int i = 0; i < numRecToPrint; i++)
			{
				System.out.printf("%-10s\t\t%-10d\t\t%-10s\t\t%-10s\t\t%-10.02f\t\t%-10s\t\t%-10d\t\t%-10s\t\t%-10s\t\t%-10s\t%-10s\t\t%-10s\n", robjs[i].getId(), robjs[i].getAge(), robjs[i].getSex(), robjs[i].getRegion(), robjs[i].getIncome(), robjs[i].getMarried(), robjs[i].getChildren(), robjs[i].getCar(), robjs[i].getSave_act(), robjs[i].getCurrent_act(), robjs[i].getMortgage(), robjs[i].getPep());
			}
		}
		else
		{
			// Display default record details: ID, AGE, SEX, REGION, INCOME, MORTGAGE
			System.out.println("Client(s) default (up to 25) record details");
			System.out.println("ID\t\t\tAGE\t\t\tSEX\t\t\tREGION\t\t\tINCOME\t\t\tMORTGAGE");
			for (int i = 0; i < numRecToPrint; i++)
			{
				System.out.printf("%-10s\t\t%-10d\t\t%-10s\t\t%-10s\t\t%-10.02f\t\t%-10s\n", robjs[i].getId(), robjs[i].getAge(), robjs[i].getSex(), robjs[i].getRegion(), robjs[i].getIncome(), robjs[i].getMortgage());
			}
		}
		
		// Display the current date and the programmer name
		String timeStamp = new SimpleDateFormat("yyy/MM/dd "
				+ "HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println("\nCur dt=" + timeStamp + "\nProgrammed by Oleg Grigoryan\n");
		
		// Dismiss the Scanner class object
		sc.close();
		
	}
}
