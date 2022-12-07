package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FileArray extends CalculatePL{
	
	private double nonTaxableExpenses = super.nonTaxableExpenses;
	
	void fillDailyArray(TextField monthTextField, TextField dayTextField, Label dateErrorLabel,
			TextField incomeSourceTextField, ArrayList<TextField> expensesArrayList, ArrayList<CheckBox>
			taxCheckArrayList, Label enterErrorLabel) {
		if(super.wasCalculated != 1) {enterErrorLabel.setText(" Er: (!) "
				+ "Must first calculate earnings above to write data to file.");
		}
		else if(super.wasCalculated == 1) {
			try{enterErrorLabel.setText(" ");
				UserInput dailyInput = new UserInput(monthTextField, dayTextField);	
				dateErrorLabel.setText(" ");
				super.dailyArray[0] = dailyInput.getFormattedDate();
				
				if(incomeSourceTextField == null) {super.dailyArray[2] = "Unspecified";}
				else {super.dailyArray[2] = incomeSourceTextField.getText();}
				
				//calculate non-taxable expenses
				ArrayList<Integer> taxableExpensesArrayList = new ArrayList<Integer>();
				
				for(CheckBox checkbox: taxCheckArrayList) {
					if(checkbox.isSelected()) {taxableExpensesArrayList.add(1);}
					else {taxableExpensesArrayList.add(0);}
					}
				
				int index = 0;
				while (index < taxableExpensesArrayList.size()) {
					if(taxableExpensesArrayList.get(index) == 1) {
						this.nonTaxableExpenses = this.nonTaxableExpenses + 
								Double.parseDouble(expensesArrayList.get(index).getText());
					}
					index ++;
				}
				
				System.out.println("Total taxable expenses of total expenses entered: " + this.nonTaxableExpenses);
				this.dailyArray[13] = String.valueOf(this.nonTaxableExpenses);
				
				//calculate taxable expenses
				String taxableExpenses = String.valueOf(super.allExpenses - this.nonTaxableExpenses);
				System.out.println("Total non-taxable expenses of daily expenses entered: " + taxableExpenses);
				this.dailyArray[10] = taxableExpenses;
				
				
				try{ManageFile newEntry = new ManageFile();
					//calculate income tax
					Taxes incomeTax = new Taxes(newEntry);
					incomeTax.calcIncomeTax(super.takeHome);
				
					//append file with dailyArray
					try{newEntry.appendToCSVFile(super.dailyArray);
					
					} catch(IOException ioe) {System.out.println("INPUT/OUTPUT ISSUE - ERROR OCCURED WHILE"
							+ " APPENDING DATA for: " + super.dailyArray[0] + " to: serviceSheets.csv");}
					
				} catch(IOException ioe) {System.out.println("INPUT/OUTPUT ISSUE - ERROR OCCURED WHILE"
						+ " CREATING OR ACCESSING: serviceSheets.csv");}
				
				
				
				//net daily earnings this.dailyArray[22]
				//= takeHome - this.dailyArray[19] (taxes) - allExpenses
				
				
				//reset
				
			} catch(CodeFormatException cfe) {dateErrorLabel.setText(" Er: (!) "
					+ "Invalid date entered. Date must be a real calendar date to be valid. \n"
					+ " Only include 1-2 digits per text field. Do not include spaces or symbols.\n");
				
			}
		}
	}
}
