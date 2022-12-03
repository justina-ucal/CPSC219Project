package application;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculatePL {
	
	private String[] dailyArray;
	private int wasCalculated;
	private double allExpenses;
	private double takeHome;

	void reset() {
		this.dailyArray = null;
		this.wasCalculated = 0;
		System.out.println("All entries cleared and not saved");
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

	
	void fillDailyArray(TextField monthTextField, TextField dayTextField, Label dateErrorLabel,
			TextField incomeSourceTextField, Label enterErrorLabel) {
		if(this.wasCalculated != 1) {enterErrorLabel.setText(" Er: (!) "
				+ "Must first calculate earnings above to write data to file.");
		}
		else if(this.wasCalculated == 1) {
			try{enterErrorLabel.setText(" ");
				UserInput dailyInput = new UserInput(monthTextField, dayTextField);	
				dateErrorLabel.setText(" ");
				this.dailyArray[0] = dailyInput.getFormattedDate();
				
				if(incomeSourceTextField == null) {this.dailyArray[2] = "Unspecified";}
				else {this.dailyArray[2] = incomeSourceTextField.getText();}
				
				//calculate non-taxable expenses = this.dailyArray[13]
				//subtract non-taxable expenses from this.allExpenses = this.dailyArray[10]
				
				//calculate income tax (see 2 commented out classes below)
				
				//net daily earnings this.dailyArray[22]
				//= takeHome - this.dailyArray[19] (taxes) - allExpenses
				
				//append file with dailyArray
				
				//reset
				
			} catch(CodeFormatException cfe) {dateErrorLabel.setText(" Er: (!) "
					+ "Invalid date entered. Date must be a real calendar date to be valid. \n"
					+ " Only include 1-2 digits per text field. Do not include spaces or symbols.\n");
				
			}
		}
	}
/*
	void calcIncomeTaxInitial() {
		/**
		 * NOTE: the method assumes user will not jump more than one tax bracket in one day
		 * if user does, the code does not account for this and will calculate the tax on a portion of user's
		 * daily income at a higher rate than they will actually be taxed at
		 */
		/*double incomeTax = 0.0;
		double EIcontribution = 0.0;
		double CPPcontribution = 0.0;
		
		//pull Total take-home (taxable) income (after tip-out) YTD [17] from file = incomeYTD
		double taxableIncome = incomeYTD + this.takeHome - 13808;
		if(taxableIncome < 0) {taxableIncome = 0.0;}
		if(taxableIncome < 50197.01){
			incomeTax = (this.takeHome)*(0.25);
			EIcontribution = calcIncomeTaxSecondary(60300, 0.0158, 0, incomeYTD, taxableIncome);
			CPPcontribution = calcIncomeTaxSecondary(61400, 0.114, 0, incomeYTD, taxableIncome);
		}
		else if(taxableIncome < 100392.01){
			calcIncomeTaxSecondary(50197, 0.25, 30.50, incomeYTD, taxableIncome);
			if(taxableIncome < 61400) {
				CPPcontribution = calcIncomeTaxSecondary(61400, 0.114, 0, incomeYTD, taxableIncome);
				if(taxableIncome < 60300) {EIcontribution = 
						calcIncomeTaxSecondary(60300, 0.0158, 0, incomeYTD, taxableIncome);
				}
			}
		}
		else if(taxableIncome < 131220.01){
			calcIncomeTaxSecondary(100392, 0.3050, 0.36, incomeYTD, taxableIncome);
		}
		else if(taxableIncome < 155625.01){
			calcIncomeTaxSecondary(131220, 0.36, 0.38, incomeYTD, taxableIncome);
		}
		else if(taxableIncome < 157464.01){
			calcIncomeTaxSecondary(155625, 0.38, 0.4138, incomeYTD, taxableIncome);
		}
		else if(taxableIncome < 209952.01){
			calcIncomeTaxSecondary(157464, 0.4138, 0.4238, incomeYTD, taxableIncome);
		}
		else if(taxableIncome < 221708.01){
			calcIncomeTaxSecondary(209952, 0.4238, 0.4338, incomeYTD, taxableIncome);
		}
		else if(taxableIncome < 314928.01){
			calcIncomeTaxSecondary(221708, 0.4338, 0.47, incomeYTD, taxableIncome);
		}
		else{
			calcIncomeTaxSecondary(314928, 0.47, 0.48, incomeYTD, taxableIncome);
		}
		
		double taxesOnEarnings = incomeTax + EIcontribution + CPPcontribution;
		//taxesOnEarnings = this.dailyArray[19]
		
		
		
	}

	void calcIncomeTaxSecondary(int lowBound, double lowRate, double highRate, double incomeYTD, 
			double taxableIncome) {
		if(incomeYTD < (lowBound + 0.01)) {
			double lowerBracket = lowBound - incomeYTD;
			double upperBracket = taxableIncome - lowerBracket;
			incomeTax = (lowerBracket * (lowRate)) + (upperBracket * (highRate));
		} else incomeTax = (this.takeHome)*(highRate);
		return incomeTax;
	}*/
		

}
