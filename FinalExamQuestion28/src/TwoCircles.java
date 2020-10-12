/**
 * This program determines whether the second circle inside first one, or overlapping
 * 
 * Final Exam Question 28
 * File Name: TwoCircles.java
 * @author Oleg Grigoryan
 * @since 2019-05-09
 * 
 */

import java.util.Scanner;		// package for Scanner class objects

public class TwoCircles {
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		// Prompt the user to enter the center coordinates and radii of two circles
		System.out.print("Enter Circle1's center x-, y-coordinates, and radius: ");
		double x1 = input.nextDouble();
		double y1 = input.nextDouble();
		double r1 = input.nextDouble();
		System.out.print("Enter Circle2's center x-, y-coordinates, and radius: ");
		double x2 = input.nextDouble();
		double y2 = input.nextDouble();
		double r2 = input.nextDouble();
		
		if (Math.pow(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2), 0.5)
				<= Math.abs(r1 - r2))
			System.out.println("Circle2 is inside Circle1");
		else if (Math.pow(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2), 0.5)
				<= r1 + r2)
			System.out.println("Circle2 overlaps Circle1");
		else
			System.out.println("Circle2 does not overlap Circle1");
		
		input.close();
	}

}
