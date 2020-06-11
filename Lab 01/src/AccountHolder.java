/**
	This class contains field members and data methods for AccountHolder object
	Lab 01
	File Name: AccountHolder.java
	Programmer: Oleg Grigoryan
	Date: 6/11/20
 */

public class AccountHolder
{
	private double balance;
	private double annualInterestRate = 0.04;
	
	/*
		Constructor:
		Creates new account with the specified balance
	*/
	public AccountHolder(double balance)
	{
		// Assign local variable to class member
		this.balance = balance;
	}
	
	/**
		The deposit method calculates the new balance in account after
		user's deposit and stores the new value in the balance field
		@param deposit The value to deposit to account
	 */
	public void deposit(double deposit)
	{
		balance += deposit;
	}
	
	/**
		The withdrawal method calculates the remaining balance in account after
		user's withdrawal and stores the new value in the balance field
	 	@param withdrawal The value to withdraw from account
	 */
	public void withdrawal(double withdrawal)
	{
		if (withdrawal > balance)
		{
			System.out.printf("Your balance goes below $0. A $50.00 overdraft fee is deducted from your balance");
			balance = balance - withdrawal - 50.00;
		}
		else
		{
			balance -= withdrawal;
		}
	}
	
	/**
		The monthlyInterest method calculates monthly balance based on rate
		store in annualInterestRate field and stores value in the balance field
	 */
	public void monthlyInterest()
	{
		balance += balance*(annualInterestRate/12.0);
	}
	
	/**
		The modifyInterest method stores a value in the annualInterestRate field
		@param rateUpdate The value to store in annualInterestUpdate
	 */
	public void modifyInterest(double rateUpdate)
	{
		annualInterestRate = rateUpdate;
	}
	
	/**
		The getAnnualInterestRate method returns an AccountHolder object's annualInterestRate.
		@return The value in annualInterestRate field.
	 */
	public double getAnnualInterestRate()
	{
		return annualInterestRate;
	}
	
	/**
		The getBalance method returns an AccountHolder object's balance.
		@return The value stored in balance field.
	 */
	public double getBalance()
	{
		return balance;
	}
}
