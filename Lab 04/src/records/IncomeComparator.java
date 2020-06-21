package records;
/**
 * The IncomeComparator class compares two BankRecords objects based on client's income data
 * 
 * Lab 03
 * File Name: IncomeComparator.java
 * @author Oleg Grigoryan
 * @since 2019-03-24
 * 
 */

import java.util.Comparator;				// Need for Comparator class

public class IncomeComparator implements Comparator<BankRecords> {
	
	@Override
	public int compare(BankRecords o1, BankRecords o2)
	{
		int result;		// Store result from comparing two object's regions
		// Get two BankRecords incomes
		double income1 = o1.getIncome();
		double income2 = o2.getIncome();
		
		// Compare the income and return the result of comparison
		result = Double.compare(income1, income2);
		return result;
	}

}
