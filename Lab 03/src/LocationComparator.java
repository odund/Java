/**
 * The LocationComparator class compares two BankRecords objects based on client's location data
 * 
 * Lab 03
 * File Name: LocationComparator.java
 * @author Oleg Grigoryan
 * @since 2020-06-11
 * 
 */

import java.util.Comparator;				// Need for Comparator class

public class LocationComparator implements Comparator<BankRecords> {
	
	@Override
	public int compare(BankRecords o1, BankRecords o2)
	{
		int result;		// Store result from comparing two object's regions
		// Get two BankRecords locations
		String region1 = o1.getRegion();
		String region2 = o2.getRegion();
		
		// Compare the location and return the result of comparison
		result = region1.compareTo(region2);
		return result;
	}

}
