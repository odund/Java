package controllers;

/**
 * This SerAndDeserBankRec class converts an instance of a class into a series of bytes.
 * First, it stores serialized data in "bankrecords.ser" file, sleeps for 5 seconds
 * and then get data from "bankrecords.ser" file, deserializes and prints outcome
 * on console window.
 * 
 * Lab 04
 * File Name: SerAndDeserObj.java
 * @author Oleg Grigoryan
 * @since 2019-04-11
 * 
 */

import java.util.HashMap;						// To provide all of the optional map operations, permits null values and the null key
import java.util.Map;							// To access the Map interface
import java.util.Map.Entry;						// To return a collection-view of the map
import java.io.IOException;						// Needed for IOException class
import java.io.FileInputStream;					// To read data from a file in the form of sequence of bytes
import java.io.FileOutputStream;				// To write data to a file in the form of sequence of bytes
import java.io.ObjectInputStream;				// To read an object from the ObjectInputStream
import java.io.ObjectOutputStream;				// To write an object to the ObjectOutputStream
import java.time.format.DateTimeFormatter;		// To format ZonedDateTime, LocalDateTime, LocalDate and LocalTime to string
import java.time.LocalDateTime;					// To format date-time without a time-zone

import records.BankRecords;

public class SerAndDeserBankRec {
	
	static Map<Long, BankRecords> serBankRecords = new HashMap<Long, BankRecords>();
	static Map<Long, BankRecords> deserBankRecords = new HashMap<Long, BankRecords>();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	SerAndDeserObj obj = null;
	long serTime, deserTime;
	
	@SuppressWarnings("unchecked")
	
	/**
	 * The SerAndDeserBankRecord method serializes and deserializes BankRecords
	 * objects using Map class. It stores in and reads from "bankrecords.ser" file
	 * and at the end prints reading from "bankrecords.ser" file on the console window.
	 * @param robjs The array of BankRecords objects
	 * @exception IOException if FileOutputStream or FileInputStream fails
	 * @exception InterruptedException if thread is interrupted
	 * @exception ClassNotFoundException if load in a class through its string fails
	 */
	public void SerAndDeserBankRecord(BankRecords[] robjs) {
				
		// Store BankRecords in Map object
		long i = 0;
	    for(BankRecords value : robjs) {
	    	serBankRecords.put(++i,value);
	    }
				 
	    obj = new SerAndDeserObj(serBankRecords);
	    
	    // Serialize BankRecords objects
	    try {
			FileOutputStream fileOutputStream = new FileOutputStream("bankrecords.ser");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			
			// Save start time
			serTime = System.currentTimeMillis();
			System.out.println("Starting serializing BankRecords objects, current time " + LocalDateTime.now());
			
			objectOutputStream.writeObject(obj.bankRecords);
			
			objectOutputStream.flush();
			objectOutputStream.close();
			fileOutputStream.flush();
			fileOutputStream.close();
			
	    } catch (IOException e) {
			e.printStackTrace();
	    } finally {
	    	System.out.println("Completed serializing BankRecords objects, current time " + LocalDateTime.now());
	    }
	    
		try {
			System.out.println("Start Thread...");
			new Thread();
			System.out.println("Thread going to sleep for 5 secs...");
			Thread.sleep(5000);		// Sleep for 5 seconds
		
		} catch (InterruptedException e) {
			e.printStackTrace();
	 	} finally {
			System.out.println("Thread run completed...");
		}
		
		// Deserialize BankRecords objects
		try {
			FileInputStream fileInputStream = new FileInputStream("bankrecords.ser");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			
			System.out.println("Starting deserializing BankRecords objects, current time " + LocalDateTime.now());
			
			deserBankRecords = (Map<Long, BankRecords>)objectInputStream.readObject();
			deserTime = System.currentTimeMillis();
			
			// Print deserialized data on console window
			Long[] keys = new Long[serBankRecords.size()];
			BankRecords[] values = new BankRecords[serBankRecords.size()];
			int index = 0;
			System.out.println("Printing deserialized data...");
			for (Entry<Long, BankRecords> mapEntry : serBankRecords.entrySet()) {
				keys[index] = (Long) mapEntry.getKey();
				values[index] = (BankRecords) mapEntry.getValue();
				System.out.println("Data=> " + "Key val: " + keys[index] + " id val: " + values[index].getId());
				index++;
			}
			
			objectInputStream.close();
			fileInputStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
	    	System.out.println("Completed deserializing BankRecords objects, current time " + LocalDateTime.now());
	    }
		
		// Print time difference between serTime and deserTime
		System.out.println("Time difference between serializing & deserializing objects is " + (Math.abs(deserTime-serTime-5000.0)/1000.0) + " sec");
	}
}
