package application;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculatePL {
	
	protected String[] dailyArray;
	protected int wasCalculated;
	protected double allExpenses;
	protected double takeHome;
	protected double nonTaxableExpenses;

	void reset(int reasonCodeReset) {
		this.dailyArray = null;
		this.wasCalculated = 0;
		this.allExpenses = 0.0;
		this.takeHome = 0.0;
		this.nonTaxableExpenses = 0.0;
		if(reasonCodeReset == 2) {System.out.println("All entries cleared and saved to serviceSheets.csv file");}
		else {System.out.println("All entries cleared and not saved");}
	}
	
	void calcDailyPL (TextField hoursTextField, TextField wageTextField, TextField commissionTextField,
			TextField tipsEarnedTextField, TextField tipOutTextField, ArrayList<TextField> expensesArrayList,
			Label dailyLabel, TextField currencyTextField, Label codeErrorLabel) {
		/**
		 * PL stands from "Profit and Loss"
		 */
		
		String[] babyArray = new String[23];
		
		//+
		double hourlyTotal = 0;
		double commission = 0;
		double tipsTotal = 0;
		
		//-
		double expensesTotal = 0;
		
		try{UserInput currencyInput = new UserInput(currencyTextField.getText());
			babyArray[1] = currencyInput.getCaptalizedCode();
			
			UserInput hoursInput = new UserInput(hoursTextField);
			UserInput wageInput = new UserInput(wageTextField);
			
			hourlyTotal = hoursInput.getUserInput() * wageInput.getUserInput();
			System.out.println("Total hourly income entered: " + hourlyTotal);
			babyArray[5] = String.valueOf(hourlyTotal);
			
			UserInput commissionInput = new UserInput(commissionTextField);
			
			commission = commissionInput.getUserInput();
			System.out.println("Total commission income entered: " + commission);
			babyArray[6] = String.valueOf(commission);
			
			UserInput tipsEarnedInput = new UserInput(tipsEarnedTextField);
			if(tipsEarnedTextField == null) {babyArray[7] = "0";}
			else {babyArray[7] = tipsEarnedTextField.getText();}
			UserInput tipOutInput = new UserInput(tipOutTextField);
			if(tipOutTextField == null) {babyArray[8] = "0";}
			else {babyArray[8] = tipOutTextField.getText();}
					
			tipsTotal = tipsEarnedInput.getUserInput() - tipOutInput.getUserInput();
			System.out.println("Total tips income entered: " + tipsTotal);
			
			for(TextField textfield: expensesArrayList) {
				UserInput expensesInput = new UserInput(textfield);
				
				expensesTotal += expensesInput.getUserInput();
				}
			System.out.println("Total daily expenses entered: " + expensesTotal);
			this.allExpenses = expensesTotal;
				
			double dailyEarnings = (hourlyTotal + commission + tipsTotal);
			babyArray[16] = String.valueOf(dailyEarnings);
			this.takeHome = dailyEarnings;
			if(tipOutTextField == null){babyArray[3] = String.valueOf(dailyEarnings);}
			else{babyArray[3] = String.valueOf(dailyEarnings + tipOutTextField.getText());}
			dailyEarnings = dailyEarnings - expensesTotal;
			babyArray[22] = String.valueOf(dailyEarnings);
			System.out.println("Total daily income: " + dailyEarnings);
			
			//applicationStage.setTitle("Calculations completed: earnings must still be entered");
			
			codeErrorLabel.setText("");
			dailyLabel.setStyle("-fx-font-weight: normal; -fx-text-fill: black;");
			dailyLabel.setText("Based on the information entered above, your total is: " + dailyEarnings + " " +
					currencyInput.getCaptalizedCode());
			
		} catch(CodeFormatException cfe) {
			//applicationStage.setTitle("Error occurred: Invalid entry");
			dailyLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: red;");
			dailyLabel.setText(" (!) Must resolve currency code error. See above.");
			codeErrorLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: red;");
			codeErrorLabel.setText(" Er: (!) Invalid value entered. Currency code must be exactly three letters."
					+ "\n Do not include any spaces.");
		
		} catch(NumberFormatException nfe) {
			//applicationStage.setTitle("Error occurred: Invalid entry");
			dailyLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: red;");
			dailyLabel.setText(" Er: (!) Invalid value entered. All earnings and/or expenses entries must"
					+ "\n be numeric, and may include only a single decimal point (optional)."
					+ "\n Do not include any letters or special characters (e.g. $ or %).");
			}
		
		this.dailyArray = babyArray;
		this.wasCalculated = 1;
	}	
}