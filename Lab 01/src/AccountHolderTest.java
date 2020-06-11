/**
	This program creates bank account information and executes various transaction 
	details for their clients. This program will prompt users for options such as
	creating an initial balance, entering deposits and withdrawals. Also, will allow
	for the printing of account information including interest at various interest rates
	
	Lab 01
	File Name: AccountHolderTest.java
	Programmer: Oleg Grigoryan
	Date: 06/11/2020
 */

// Package for Scanner class object
import java.util.Scanner;
// Package for SimpleDateFormat class object
import java.text.SimpleDateFormat;
// Package for Calendar class object
import java.util.Calendar;

public class AccountHolderTest
{

	public static void main(String[] args)
	{
		double balance;			// To hold the user's balance
		double deposit;			// To hold the user's deposit
		double withdrawal;		// To hold the user's withdraw
		String input;			// To hold a line of input
		char answer;			// To hold a single character
		double rateUpdate;		// To hold new annual interest rate 
		
		// Create a Scanner class object for keyboard input
		Scanner sc = new Scanner(System.in);
		
		// Get the user's an initial account balance
		System.out.print("Enter your initial balance: ");
		balance = sc.nextDouble();
		
		// Do not accept a negative value. Ask the user to reenter new valid value
		while(true)
		{
			if (balance < 0)
			{
				System.out.println("The initial balance can not be a negative value. "
						+ "Please re-enter a positive balance: ");
				balance = sc.nextDouble();
			}
			else
				break;
		}
		
		// Create AccountHolder object
		AccountHolder account = new AccountHolder(balance);
				
		// Get the user's a deposit amount
		System.out.print("Enter your deposit amount: ");
		deposit = sc.nextDouble();
		// Call object's deposit method
		account.deposit(deposit);
				
		// Get the user's withdraw amount
		System.out.print("Enter your withdraw amount: ");
		withdrawal = sc.nextDouble();
		account.withdrawal(withdrawal);
		
		// Print current base balance
		System.out.printf("Your current account balance is $%.02f: \n", account.getBalance());
		// Ask the user if the
		System.out.print("Would you like to print a report of your balance with monthly interest? (Y or y (yes) / N or n (no): )");
		// Get a line of input
		input = sc.next();
		// Get the first character
		answer = input.charAt(0);
		
		if ((answer == 'Y') || (answer == 'y'))
		{
			// Before print a report check first if the balance is not less than zero
			if (account.getBalance() >= 0)
			{
				// Ask user if the annual interest rate need to be changed
				System.out.print("Would you like to change the annual interest rate (the default rate is 4.00%)? (Y or y (yes) / N or n (no): )");
				// Get a line of input
				input = sc.next();
				// Get the first character
				answer = input.charAt(0);
				
				if ((answer == 'Y') || (answer == 'y'))
				{
					// Get the user's annual interest rate
					System.out.print("Enter a new annual interest rate from 0 to 100 (%): ");
					rateUpdate = sc.nextDouble();
					// Accept value between 0 and 100. Otherwise, ask the user to reenter valid value
					while(true)
					{
						if ((rateUpdate < 0) || (rateUpdate > 100))
						{
							// Get the user's annual interest rate
							System.out.print("The value entered is not valid Please reenter value between 0 to 100 (%): ");
							rateUpdate = sc.nextDouble();
						}
						else
							break;
					}
					// Call object's modifyInterest method and pass the value in decimal format
					account.modifyInterest(rateUpdate / 100);
				}
				// Display new balances for 12 month period calculated based on rate
				System.out.printf("\nYour monthly balance for one year at %.02f (%.02f%%) annual interest\n", account.getAnnualInterestRate(), (account.getAnnualInterestRate() * 100));
				System.out.println("Balances:");
				System.out.println("Account\t\tBalance with Interest");
				System.out.printf("Base\t\t\t$%.02f\n", account.getBalance());
				for (int i = 1; i <= 12; i++)
				{
					account.monthlyInterest();
					System.out.printf("Month %02d\t\t$%.02f\n", i, account.getBalance());
				}
			}
			else
			{
				System.out.printf("Your account has a negative balance: $%.02f. No monthly report will be printed\n", account.getBalance());
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
