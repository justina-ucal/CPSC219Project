package application;

import javafx.scene.control.TextField;

public class UserInput {
	
	private double userInput;
	
	UserInput(TextField userEntered) throws NumberFormatException{
		/**
		 * constructor validates that user entered a valid number for an earning and/or expense entry
		 * and sets the instance variable (double userInput) to match the value that the user entered
		 * into the given TextField (passed to this constructor)
		 * if the entry is not valid, the constructor throws a NumberFormatException
		 * @param userEntered
		 */
		if(userEntered.getText().isEmpty()) {userInput = 0.0;}
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
	
	UserInput(String currencyCode) throws AlphaCodeFormatException{
		/**
		 * constructor validates that user entered a valid 3 letter code as to indicate currency 
		 * if the entry is not valid, the constructor throws an AlphaCodeFormatException
		 * @param currencyCode
		 */
		if(currencyCode.toCharArray().length != 3) {throw new AlphaCodeFormatException("INVALID ENTRY - CODE FORMAT ERROR: "
				+ currencyCode);}
		else {boolean validEntry = true;
			for(char c: currencyCode.toCharArray()) {
				if(java.lang.Character.isLetter(c) == false) {validEntry = false;}
			}
			if(validEntry = false) {throw new AlphaCodeFormatException("INVALID ENTRY - CODE FORMAT ERROR: "
					+ currencyCode);}
		}
	}
			
	public double getUserInput() {
		return userInput;
	}

}

