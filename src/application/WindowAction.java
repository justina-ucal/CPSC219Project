package application;

import java.util.ArrayList;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WindowAction {

	void cancel(Stage applicationStage, Scene mainScene, CalculatePL unsavedEntry, int reasonCodeCleared) {
		/**
		 * returns user to home menu without running any calculations nor saving any changes to the CSV file
		 * this method will also reset changes made to the CalculatePL's instance variables
		 * @param mainScene	is the Scene that sets the home menu 
		 * @param unsavedEntry is the Object/instance of CalculatePL created by the method now calling the cancel method
		 */
		unsavedEntry.reset(reasonCodeCleared);
		applicationStage.setScene(mainScene);
		if(reasonCodeCleared == 0) applicationStage.setTitle("Manage Spreadsheets");
		if(reasonCodeCleared == 1) applicationStage.setTitle("IOFile Error Occurred: Previous Data Lost");
		if(reasonCodeCleared == 2) applicationStage.setTitle("Entry Successful!");
		
	}

	
	void addExpenseTextField (VBox expensesRow, ArrayList<TextField> expensesArrayList, ArrayList<CheckBox> 
			taxCheckArrayList) {
		/**
		 * adds another TextField for the user to enter an expense value under a pre-existing TextField serving
		 * the same purpose (within the VBox passed to this method)
		 * @param expensesRow	is the VBox that contains the pre-existing TextField to enter an expense
		 * @param expensesArrayList	is the ArrayList of all the TextFields for entering expenses
		 */
		HBox expensesHBox = new HBox();
    	TextField expensesTextField = new TextField("0.00");
    	expensesTextField.setPrefWidth(60);
    	expensesTextField.setStyle("-fx-text-fill: red;");
    	Label writeOffLabel = new Label(" Expense can be written off ");
    	CheckBox taxableCheckBox = new CheckBox();
    	
    	expensesArrayList.add(expensesTextField);
    	taxCheckArrayList.add(taxableCheckBox);
    	
    	expensesHBox.getChildren().addAll(expensesTextField,writeOffLabel,taxableCheckBox);
    	expensesRow.getChildren().addAll(expensesHBox);
	}	
	
}
