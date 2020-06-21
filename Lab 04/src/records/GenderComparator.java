package records;
/**
 * The GenderComparator class compares two BankRecords objects based on client's gender data
 * 
 * Lab 03
 * File Name: GenderComparator.java
 * @author Oleg Grigoryan
 * @since 2019-03-24
 * 
 */

import java.util.Comparator;				// Need for Comparator class

public class GenderComparator implements Comparator<BankRecords> {
	
	@Override
	public int compare(BankRecords o1, BankRecords o2)
	{
		int result;		// Store result from comparing two object's regions
		// Get two BankRecords genders
		String gender1 = o1.getSex();
		String gender2 = o2.getSex();
		
		// Compare the gender and return the result of comparison
		result = gender1.compareTo(gender2);
		return result;
	}

}
