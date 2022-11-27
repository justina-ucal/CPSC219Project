package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


public class ServiceSheetsController {
	
	Stage applicationStage;
	
	@FXML
    void enterEarnings (ActionEvent enterEarningsEvent) {
		Scene mainScene = applicationStage.getScene();
		applicationStage.setTitle("Enter earnings");
		
		VBox earningsBox = new VBox();
    	
    	Label titleLabel = new Label("Enter earnings");
    	
    	Button doneButton = new Button("Done");
    	doneButton.setOnAction(doneEvent -> addEarnings(mainScene));
  
    	earningsBox.getChildren().addAll(titleLabel, doneButton);
    	Scene earningsScene = new Scene(earningsBox,400,400);
    	
    	applicationStage.setScene(earningsScene);
	}
	
	@FXML
	void addEarnings(Scene mainScene) {
		
		applicationStage.setScene(mainScene);
    	applicationStage.setTitle("Earnings Added");
	}
	
	@FXML
    void enterExpenses (ActionEvent enterExpensesEvent) {
		Scene mainScene = applicationStage.getScene();
		applicationStage.setTitle("Enter expenses");
		
		VBox expensesBox = new VBox();
    	
    	Label titleLabel = new Label("Enter expenses");
    	
    	Button doneButton = new Button("Done");
    	doneButton.setOnAction(doneEvent -> addExpenses(mainScene));
  
    	expensesBox.getChildren().addAll(titleLabel, doneButton);
    	Scene expensesScene = new Scene(expensesBox,400,400);
    	
    	applicationStage.setScene(expensesScene);
	}
	
	@FXML
	void addExpenses(Scene mainScene) {
		
		applicationStage.setScene(mainScene);
    	applicationStage.setTitle("Expenses Added");
	}
	
}
