package controllers;

/**
 * This program uses a database to store and then present the loan analysis information
 * based on data from BankRecords objects
 * 
 * Lab 04
 * File Name: LoanProcessing.java
 * @author Oleg Grigoryan
 * @since 2019-04-11
 * 
 */

import records.BankRecords;				// Records package contains BankRecords data
import models.DaoModel;					// Model package
import views.LoanView;					// View package
import java.sql.ResultSet;				// A table of data representing a database result set
import java.text.SimpleDateFormat;		// Package for SimpleDateFormat class object
import java.util.Calendar;				// Package for Calendar class object

public class LoanProcessing extends BankRecords{
	
	// Used as a version control in a Serializable class
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
	
	
	BankRecords br = new BankRecords();							// Instantiate BankRecords object
	br.readData();												// Call readData method to invoke programs to generate client records from file
	SerAndDeserBankRec serNdeserBR = new SerAndDeserBankRec();	// Initiate SerAndDeserBankRec object
	serNdeserBR.SerAndDeserBankRecord(robjs);					// Call SerAndDeserBankRecord method
	DaoModel dao = new DaoModel();								// Instantiate DaoModel object
	dao.createTable();											// Call createTable method to create a table
	dao.insertRecords(robjs);									// Call insertRecords method to insert records into table
	ResultSet rs;												// Instantiate ResultSet object
	rs = dao.retrieveRecords();									// Call retrieveRecords method to retrieve records from the database and store in ResultSet object
	new LoanView().runView(rs);									// Instantiate LoanView object and call its runView method to display the records
	
	// Display the current date and the programmer name
	String timeStamp = new SimpleDateFormat("yyy/MM/dd "
			+ "HH:mm:ss").format(Calendar.getInstance().getTime());
	System.out.println("\nCur dt=" + timeStamp + "\nProgrammed by Oleg Grigoryan\n");
	
	}

}
