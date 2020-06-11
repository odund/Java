/**
 * This Client class is an abstract class that holds three abstract methods (readData, processData, printData).
 * They must be overridden in a subclass.
 * 
 * Lab 03
 * File Name: Client.java
 * @author Oleg Grigoryan
 * @since 2020-06-11
 *
 */
public abstract class Client {
	
	public abstract void readData();		// read file details
	public abstract void processData();		// process file details
	public abstract void printData();		// print file details

}
