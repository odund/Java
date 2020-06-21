package views;

/**
 * This LoanView class implements method runView to print out all the records from the record set
 * to a Java window which contains a JTable with heading names of columns and contains  rows of
 * data records
 * 
 * Lab 04
 * File Name: LoanView.java
 * @author Oleg Grigoryan
 * @since 2019-04-11
 * 
 */

import java.sql.ResultSet;					// A table of data representing a database result set
import java.util.Vector;					// To access Vector interface
import javax.swing.JTable;					// To display and edit regular two-dimensional tables of cells
import javax.swing.JFrame;					// For top level window, with border and a title bar
import javax.swing.JScrollPane;				// To provide a scrollable view
import java.sql.ResultSetMetaData;			// An object used to get information about the types and properties of the columns in a ResultSet object
import java.sql.SQLException;				// An exception that provides information on a database access error or other errors

import javax.swing.table.DefaultTableModel;

//import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class LoanView {
	
	/**
	 * The RunView method creates JTable to take in rows and columns
	 * dynamically from database result set , stores each data as a Vector,
	 * then displays the table on the screen
	 * @param rs The table of data representing a database result set
	 * @exception SQLException if displaying database table on the screen fails
	 */
	public void runView(ResultSet rs) {
		
		// instantiate vector objects to hold column/row data for JTable
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		Vector<String> column = new Vector<String>();
		try {
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columns = metaData.getColumnCount();
			
			// Print number of columns count on console window
			System.out.printf("The total number of columns is %d \n", columns);
			long rowCount = 0;		// To hold number of raws count
			
			// get column names from table!
			String cols = "";
			
			for (int i = 1; i <= columns; i++) {
				cols = metaData.getColumnName(i);
				column.add(cols);
			}
			
			// get row data from table!
			while (rs.next()) {
				Vector<Object> row = new Vector<Object>(columns);
				
				for (int i = 1; i <= columns; i++) {
					row.addElement(rs.getObject(i));
				}
				data.addElement(row);
				// For debugging only. Remove after testing
				rowCount++;
			}
			
			// For debugging only. Remove next two lines after testing
			System.out.printf("The total number of rows is %d \n", rowCount);
			
			// Store data and columns info in DefaultTableModel instance
			DefaultTableModel model = new DefaultTableModel(data, column);
			
			JTable table = new JTable(model);							// Instantiate a JTable object to take rows and columns
			JFrame frame = new JFrame("Loan Details");					// Instantiate a JFrame object to display rows and columns
			frame.setSize(700, 200);;
			frame.add(new JScrollPane(table));
			//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// Exit JFrame window on close - will terminate the program
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	// Dispose JFrame window on close - will close just this JFrame
			frame.pack();												// Sizes the frame so that all its contents are at preferred sizes
			frame.setVisible(true);										// Make the GUI component visible to the user 
			
		} catch (SQLException e) { 
			System.err.println("An SQLException was caught in runView method!");
			e.printStackTrace(); 
			} finally {
				try 
				{
					// Close ResultSet instance
					rs.close();
				}
				catch (SQLException e) 
				{
				System.err.println("An SQLException was caught while closing ResultSet instance!");
				e.printStackTrace();
				}
			}
	}

}
