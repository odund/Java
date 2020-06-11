/**
 * This Records class uses an existing client bank records to sort the entire data based
 * on client's location, gender, income and print the sorted data on the screen. Then it 
 * processes the sorted data to perform the following analysis:
 * 1. Determines an average income per location
 * 2. Determines max & min income per location
 * 3. Determines number of females with both a mortgage and a saving account per location
 * 4. Determines number of males with both a car and one child per location
 * All above analytic info will be prineted on output screen and will be written in
 * "bankrecords.txt" file
 * 
 * Lab 03
 * File Name: Records.java
 * @author Oleg Grigoryan
 * @since 2020-06-11
 */

import java.io.FileWriter;					// Needed for File I/O class
import java.io.IOException;					// Needed for IOException class
import java.text.SimpleDateFormat;			// To parse and format date
import java.util.Arrays;					// For Array class
import java.util.Calendar;					// For Calendar class to provide current date

public class Records extends BankRecords{
	
	// Create formatted object to write output directly to the console and to a file
	static FileWriter fw = null;
	
	/**
	 * Constructor of class Records creates and opens file
	 * @exception IOException On creating & opening file
	 */
	public Records() {
		
		try
		{
			// Create and open "bankrecords.txt" file
			fw = new FileWriter("bankrecords.txt");
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * The LocationComp method sorts client records based on location, gender, income and
	 * prints sorted data on output screen. The it determines an average income per location,
	 * prints the outcome on the screen and writes to a file
	 * @exception IOException On writing to a file
	 */
	public void LocationComp() {
		
		// Sort Array per location, gender, income
		Arrays.sort(robjs, new LocationComparator()
				.thenComparing(new GenderComparator())
				.thenComparing(new IncomeComparator()));
		
		// Print on the screen entire sorted record
		System.out.println("Printing an entire records sorted by location, gender, income");
		System.out.println("ID\t\t\tAGE\t\t\tSEX\t\t\tREGION\t\t\tINCOME\t\t\tMARRIED\t\t\tCHILDREN\t\tOWNCAR\t\tSAVINGACCOUNT\t\tCURENTACCOUNT\t\tMORTGAGE\t\tPEP");
		for (int i = 0; i < array.size(); i++)
		{
			System.out.printf("%-10s\t\t%-10d\t\t%-10s\t\t%-10s\t\t%-10.02f\t\t%-10s\t\t%-10d\t\t%-10s\t\t%-10s\t\t%-10s\t%-10s\t\t%-10s\n", robjs[i].getId(), robjs[i].getAge(), robjs[i].getSex(), robjs[i].getRegion(), robjs[i].getIncome(), robjs[i].getMarried(), robjs[i].getChildren(), robjs[i].getCar(), robjs[i].getSave_act(), robjs[i].getCurrent_act(), robjs[i].getMortgage(), robjs[i].getPep());
		}

		// Allocate and initialize variables to hold counters and income per location
		double  innerCt = 0, ruralCt = 0, suburbanCt = 0, townCt = 0,
				innerIncSum = 0, ruralIncSum = 0, suburbanIncSum = 0, townIncSum = 0;
		
		// Get sum of income & counter per location
		for (int i = 0; i < robjs.length; i++)
		{
			if (robjs[i].getRegion().equals("INNER_CITY"))
			{
				innerIncSum += robjs[i].getIncome();
				++innerCt;
			}
			else if (robjs[i].getRegion().equals("RURAL"))
			{
				ruralIncSum += robjs[i].getIncome();
				++ruralCt;
			}
			else if (robjs[i].getRegion().equals("SUBURBAN"))
			{
				suburbanIncSum += robjs[i].getIncome();
				++suburbanCt;
			}
			else
			{
				townIncSum += robjs[i].getIncome();
				++townCt;
			}
		}
		
		// Calculate an average income per location
		double innerAvg = (innerIncSum)/(innerCt);
		double ruralAvg = (ruralIncSum)/(ruralCt);
		double suburbanAvg = (suburbanIncSum)/(suburbanCt);
		double townAvg = (townIncSum)/(townCt);
		
		// Print display header on the screen
		System.out.println();
		System.out.println("Displaying Data Analytics:");
		System.out.println("**************************");
		// Print the average income per location on the screen
		System.out.printf("Innercity region average income: %.02f \n", innerAvg);
		System.out.printf("Rural region average income: %.02f \n", ruralAvg);
		System.out.printf("Suburban region average income: %.02f \n", suburbanAvg);
		System.out.printf("Town region average income: %.02f \n", townAvg);
		
		// Write the average income per location to a file
		try {
				fw.write("Average income for innercity region " + innerAvg + " ");
				fw.write("Average income for rural region " + ruralAvg + " ");
				fw.write("Average income for suburban region " + suburbanAvg + " ");
				fw.write("Average income for town region " + townAvg + " ");
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		
		// Call MaxMinComp method
		MaxMinComp();
	}
	
	/**
	 * The MaxMinComp method determines maximum & minimum income per location,
	 * prints the outcome on the screen and writes it to a file
	 * @exception IOException On writing to a file
	 */
	private void MaxMinComp() {
		
		// Allocate & initialize variables to hold max & min income per location
		double innerMaxInc = 0, ruralMaxInc = 0, suburbanMaxInc = 0, townMaxInc = 0,
				innerMinInc = 0, ruralMinInc = 0, suburbanMinInc = 0, townMinInc = 0;
		
		// Allocate and initialize flags per locations
		boolean innerFlg = true, ruralFlg = true, suburbanFlg = true, townFlg = true;
		
		// Get max & min income per location
		for (int i = 0; i < robjs.length; i++)
		{
			if (robjs[i].getRegion().equals("INNER_CITY"))
			{
				if (innerFlg)
				{
					// Store first first values
					innerMaxInc = robjs[i].getIncome();
					innerMinInc = robjs[i].getIncome();
					innerFlg = false;
				}
				else
				{
					if (robjs[i].getIncome() > innerMaxInc)
					{
						innerMaxInc = robjs[i].getIncome();
					}
					else if (robjs[i].getIncome() < innerMinInc)
					{
						innerMinInc = robjs[i].getIncome();
					}
				}
			}
			else if (robjs[i].getRegion().equals("RURAL"))
			{
				if (ruralFlg)
				{
					// Store first first values
					ruralMaxInc = robjs[i].getIncome();
					ruralMinInc = robjs[i].getIncome();
					ruralFlg = false;
				}
				else
				{
					if (robjs[i].getIncome() > ruralMaxInc)
					{
						ruralMaxInc = robjs[i].getIncome();
					}
					else if (robjs[i].getIncome() < ruralMinInc)
					{
						ruralMinInc = robjs[i].getIncome();
					}
				}
			}
			else if (robjs[i].getRegion().equals("SUBURBAN"))
			{
				if (suburbanFlg)
				{
					// Store first first values
					suburbanMaxInc = robjs[i].getIncome();
					suburbanMinInc = robjs[i].getIncome();
					suburbanFlg = false;
				}
				else
				{
					if (robjs[i].getIncome() > suburbanMaxInc)
					{
						suburbanMaxInc = robjs[i].getIncome();
					}
					else if (robjs[i].getIncome() < suburbanMinInc)
					{
						suburbanMinInc = robjs[i].getIncome();
					}
				}
			}
			else
			{
				if (townFlg)
				{
					// Store first first values
					townMaxInc = robjs[i].getIncome();
					townMinInc = robjs[i].getIncome();
					townFlg = false;
				}
				else
				{
					if (robjs[i].getIncome() > townMaxInc)
					{
						townMaxInc = robjs[i].getIncome();
					}
					else if (robjs[i].getIncome() < townMinInc)
					{
						townMinInc = robjs[i].getIncome();
					}
				}
			}
		}
		
		// Print the max income per location on the screen
		System.out.println();
		System.out.printf("Innercity region max income: %.02f \n", innerMaxInc);
		System.out.printf("Rural region max income: %.02f \n", ruralMaxInc);
		System.out.printf("Suburban region max income: %.02f \n", suburbanMaxInc);
		System.out.printf("Town region max income: %.02f \n", townMaxInc);
		
		// Print the min income per location on the screen
		System.out.println();
		System.out.printf("Innercity region min income: %.02f \n", innerMinInc);
		System.out.printf("Rural region min income: %.02f \n", ruralMinInc);
		System.out.printf("Suburban region min income: %.02f \n", suburbanMinInc);
		System.out.printf("Town region min income: %.02f \n", townMinInc);
		
		try {
				// Write the max income per location to a file
				fw.write("Maximum income for innercity region " + innerMaxInc + " ");
				fw.write("Maximum income for rural region " + ruralMaxInc + " ");
				fw.write("Maximum income for suburban region " + suburbanMaxInc + " ");
				fw.write("Maximum income for town region " + townMaxInc + " ");
				
				// Write the min income per location to a file
				fw.write("Minimum income for innercity region " + innerMinInc + " ");
				fw.write("Minimum income for rural region " + ruralMinInc + " ");
				fw.write("Minimum income for suburban region " + suburbanMinInc + " ");
				fw.write("Minimum income for town region " + townMinInc + " ");
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		
		// Call FemaleComp method
		FemaleComp();
	}

	/**
	 * The FemaleComp method determines number of females per location
	 * with both a mortgage and saving accounts, prints the outcome on 
	 * the screen and writes it to a file
	 * @exception IOException On writing to a file
	 */
	private void FemaleComp() {
		
		// Allocate & initialize variables to hold number of female per location
		int innerFemaleCt = 0, ruralFemaleCt = 0, suburbanFemaleCt = 0, townFemaleCt = 0;
		
		// Get number of females per location
		for (int i = 0; i < robjs.length; i++)
		{
			if (robjs[i].getSex().equals("FEMALE") && robjs[i].getMortgage().equals("YES") && robjs[i].getSave_act().equals("YES"))
			{
				if (robjs[i].getRegion().equals("INNER_CITY"))
				{
					++innerFemaleCt;
				}
				else if (robjs[i].getRegion().equals("RURAL"))
				{
					++ruralFemaleCt;
				}
				else if (robjs[i].getRegion().equals("SUBURBAN"))
				{
					++suburbanFemaleCt;
				}
				else
				{
					++townFemaleCt;
				}
			}
		}
		
		// Print number of females per location on the screen
		System.out.println();
		System.out.printf("Innercity region females with mortgage and saving accounts: %d \n", innerFemaleCt);
		System.out.printf("Rural region females with mortgage and saving accounts: %d \n", ruralFemaleCt);
		System.out.printf("Suburban region females with mortgage and saving accounts: %d \n", suburbanFemaleCt);
		System.out.printf("Town region females with mortgage and saving accounts: %d \n", townFemaleCt);
		
		try {
				// Write the number of females per location to a file
				fw.write(" Number of females with both a mortgage and saving accounts for innercity region " + innerFemaleCt + " ");
				fw.write(" Number of females with both a mortgage and saving accounts for rural region " + ruralFemaleCt + " ");
				fw.write(" Number of females with both a mortgage and saving accounts for suburban region " + suburbanFemaleCt + " ");
				fw.write(" Number of females with both a mortgage and saving accounts for town region " + townFemaleCt + " ");
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		
		// Call MaleComp method
		MaleComp();
	}
	
	/**
	 * The MaleComp method determines number of males per location
	 * with both a car and one child, prints the outcome on the screen 
	 * and writes it to a file
	 * @exception IOException On writing to a file
	 */
	private void MaleComp() {
		
		// Allocate & initialize variables to hold number of males per location
		int innerMaleCt = 0, ruralMaleCt = 0, suburbanMaleCt = 0, townMaleCt = 0;
		
		// Get number of males per location
		for (int i = 0; i < robjs.length; i++)
		{
			if (robjs[i].getSex().equals("MALE") && robjs[i].getCar().equals("YES") && robjs[i].getChildren() == 1)
			{
				if (robjs[i].getRegion().equals("INNER_CITY"))
				{
					++innerMaleCt;
				}
				else if (robjs[i].getRegion().equals("RURAL"))
				{
					++ruralMaleCt;
				}
				else if (robjs[i].getRegion().equals("SUBURBAN"))
				{
					++suburbanMaleCt;
				}
				else
				{
					++townMaleCt;
				}
			}
		}
		
		// Print number of males per location on the screen
		System.out.println();
		System.out.printf(" Innercity region males with car and 1 child: %d \n", innerMaleCt);
		System.out.printf(" Rural region males with car and 1 child: %d \n", ruralMaleCt);
		System.out.printf(" Suburban region males with car and 1 child: %d \n", suburbanMaleCt);
		System.out.printf(" Town region males with car and 1 child: %d \n", townMaleCt);
		
		try {
				// Write number of males per location to a file
				fw.write(" Number of males with both a car and 1 child for innercity region " + innerMaleCt + " ");
				fw.write(" Number of males with both a car and 1 child for rural region " + ruralMaleCt + " ");
				fw.write(" Number of males with both a car and 1 child for suburban region " + suburbanMaleCt + " ");
				fw.write(" Number of males with both a car and 1 child for town region " + townMaleCt + " ");
				
				// Store in the file the current date and the programmer name
				String timeStamp = new SimpleDateFormat("yyy/MM/dd "
						+ "HH:mm:ss").format(Calendar.getInstance().getTime());
				fw.write("Cur dt=" + timeStamp + " Programmed by Oleg Grigoryan");
			} catch (IOException e)
			{
				e.printStackTrace();
			} finally 
			{
				try 
				{
						fw.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		
		// Display the current date and the programmer name
		String timeStamp = new SimpleDateFormat("yyy/MM/dd "
				+ "HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println("\nCur dt=" + timeStamp + "\nProgrammed by Oleg Grigoryan\n");
		
	}
}
