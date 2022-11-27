package application;

import javafx.scene.control.TextField;

public class UserInput {
	
	private TextField userInput;
	
	public double convertStringToDouble(TextField userEntered) {
		String userEnteredString = userEntered.getText();
		Double userEnteredDouble = Double.parseDouble(userEnteredString);
		return userEnteredDouble;
	}

}

