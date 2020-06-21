package controllers;

/**
 * This SerAndDeserObj class implements Serializable interface to store a copy of the object
 * and in order to convert an instance of a class into a series of bytes or when a Serializable 
 * object might reference an instance of your class.
 * 
 * Lab 04
 * File Name: SerAndDeserObj.java
 * @author Oleg Grigoryan
 * @since 2019-04-11
 * 
 */

import java.io.Serializable;		// To serialize and deserialize classes
import java.util.HashMap;			// To provide all of the optional map operations, permits null values and the null key
import java.util.Map;				// To access the Map interface

import records.BankRecords;

public class SerAndDeserObj implements Serializable {
	
	// Used as a version control in a Serializable class
	private static final long serialVersionUID = 1L;
	
	Map<Long, BankRecords> bankRecords = new HashMap<Long, BankRecords>();
	
	public SerAndDeserObj (Map<Long, BankRecords> bankRecords) {
		this.bankRecords = bankRecords;
	}
}
