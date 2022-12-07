package application;

import javafx.scene.control.TextField;

public class UserInput {
	
	private double userInput;
	private String capitalizedCode;
	private String mm;
	private String dd;
	
	UserInput(TextField userEntered) throws NumberFormatException{
		/**
		 * constructor validates that user entered a valid number for an earning and/or expense entry
		 * and sets the instance variable (double userInput) to match the value that the user entered
		 * into the given TextField (passed to this constructor)
		 * if the entry is not valid, the constructor throws a NumberFormatException
		 * @param userEntered
		 */
		if(userEntered == null) {userInput = 0.0;}
		else{if(userEntered.getText().isEmpty()) {userInput = 0.0;}
				else {String userEnteredString = userEntered.getText();
					boolean blankEntry = true;
					for(char c: userEnteredString.toCharArray()) {
						if(c != ' ') {blankEntry = false;}
						}
					if(blankEntry == true) {userInput = 0.0;}
					else try {userInput = Double.parseDouble(userEnteredString);
						} catch(NumberFormatException nfe) {
							System.out.println("INVALID ENTRY - # FORMAT ERROR: " + userEntered.getText());
							throw nfe;
						}
				}
		}
	}
	
	UserInput(String currencyCode) throws CodeFormatException{
		/**
		 * constructor validates that user entered a valid 3 letter code as to indicate currency 
		 * if the entry is not valid, the constructor throws a CodeFormatException
		 * @param currencyCode
		 */
		if(currencyCode.toCharArray().length != 3) {throw new CodeFormatException("INVALID ENTRY -"
				+ " ALPHA CODE FORMAT ERROR: " + currencyCode);}
		else {boolean validEntry = true;
			for(char c: currencyCode.toCharArray()) {
				if(java.lang.Character.isLetter(c) == false) {validEntry = false;}
			}
			if(validEntry == false) {throw new CodeFormatException("INVALID ENTRY - ALPHA CODE FORMAT "
					+ "ERROR: " + currencyCode);}
			if(validEntry == true) {capitalizedCode = currencyCode.toUpperCase();}
		}
	}
	
	UserInput(TextField monthTextField, TextField dateTextField) throws CodeFormatException{
		/**
		 * constructor validates that user entered a valid digit or couple of digits (i.e. two digits)
		 * to represent the month and date, respectively
		 * if the entry is not valid, the constructor throws a CodeFormatException
		 * @param month	should be two digits no more than 12
		 * @param date	should be two digits no more than 31 (or no more than 30 or 29 given the month)
		 */
		boolean validEntry = true;
		
		String month = monthTextField.getText();
		String date = dateTextField.getText();
				
		if(month.toCharArray().length == 1) {month = "0" + month;}
		if(date.toCharArray().length == 1) {date = "0" + date;}
		
		if(month.toCharArray().length != 2 || date.toCharArray().length !=2 ) {throw new 
			CodeFormatException("INVALID ENTRY - MM/DD CODE FORMAT ERROR: " + month + "/" + date);}
		
		else {for(char c: month.toCharArray()) {
				if(java.lang.Character.isDigit(c) == false){validEntry = false;}
			}
			for(char c: date.toCharArray()) {
				if(java.lang.Character.isDigit(c) == false){validEntry = false;}
			}
		}
		
		if (validEntry != false) {
		
			int validDate = Integer.parseInt(date);
			int validMonth = Integer.parseInt(month);
		
			if(validEntry == true) {
				if(validMonth > 12) {validEntry = false;}
				if(validDate > 31) {validEntry = false;}
			}
			
			if(validDate == 31) {
				if(validMonth == 2 || validMonth == 4 || validMonth == 6 || validMonth == 9 || validMonth == 11) {
					validEntry = false;}
			}
		
			if(validDate == 30) {
				if(validMonth == 2) {
					validEntry = false;}
	
			}
		}
		
		if(validEntry == false) {throw new CodeFormatException("INVALID ENTRY - MM/DD CODE FORMAT "
				+ "ERROR: " + month + "/" + date);}
			
		if(validEntry == true) {
			mm = month;
			dd = date;
			System.out.println("Date entered: " + mm + "/" + dd);
		}
	}
	
	public double getUserInput() {
		/**
		 * method returns user input (converted from TextField entry to double) for earning/expense
		 */
		return userInput;
	}
	
	public String getCaptalizedCode() {
		/**
		 * method returns formatted currency code
		 */
		return capitalizedCode;
	}
	
	public String getFormattedDate() {
		/**
		 * method returns formatted date
		 */
		int mmInt = Integer.parseInt(mm);
		
			 if(mmInt==1) {return ("Jan " + dd);}
		else if(mmInt==2) {return ("Feb " + dd);}
		else if(mmInt==3) {return ("Mar " + dd);}
		else if(mmInt==4) {return ("Apr " + dd);}
		else if(mmInt==5) {return ("May " + dd);}
		else if(mmInt==6) {return ("Jun " + dd);}
		else if(mmInt==7) {return ("Jul " + dd);}
		else if(mmInt==8) {return ("Aug " + dd);}
		else if(mmInt==9) {return ("Sep " + dd);}
		else if(mmInt==10) {return ("Oct " + dd);}
		else if(mmInt==11) {return ("Nov " + dd);}
		else {return ("Dec " + dd);}
	}

}

