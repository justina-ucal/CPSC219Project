package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ServiceSheetsController {
	
	Stage applicationStage;
	
	@FXML
	void cancel(Scene mainScene) {
		
		applicationStage.setScene(mainScene);
		applicationStage.setTitle("Manage Spreadsheets");
		
	}
	
	@FXML
    void enterEarnings (ActionEvent enterEarningsEvent) {
		Scene mainScene = applicationStage.getScene();
		applicationStage.setTitle("Enter earnings");
		
		VBox earningsBox = new VBox();
		earningsBox.setStyle("-fx-background-color: pink;");
    	
    	Label titleLabel = new Label("Track Earnings \n\nDate");
    	titleLabel.setStyle("-fx-font-weight: bold;");
    	
    	HBox dateRow = new HBox();
    	Label monthLabel = new Label("Month ");
    	TextField monthTextField = new TextField("mm");
    	monthTextField.setPrefWidth(35);
    	Label dayLabel = new Label(" Day ");
    	TextField dayTextField = new TextField("dd");
    	dayTextField.setPrefWidth(35);
    	dateRow.getChildren().addAll(monthLabel,monthTextField,dayLabel,dayTextField);
    	
    	Label incomeInfoLabel = new Label("\nIncome Info.");
    	incomeInfoLabel.setStyle("-fx-font-weight: bold;");
    	
    	HBox incomeSourceRow = new HBox();
    	Label incomeSourceLabel = new Label("Source of Income ");
    	TextField incomeSourceTextField = new TextField();
    	incomeSourceRow.getChildren().addAll(incomeSourceLabel,incomeSourceTextField);
    	
    	HBox currencyRow = new HBox();
    	Label currencyLabel = new Label("Currency Code ");
    	TextField currencyTextField = new TextField("CAD");
    	currencyTextField.setPrefWidth(40);
    	currencyRow.getChildren().addAll(currencyLabel,currencyTextField);
    	
    	Label earningsLabel = new Label("\nEarnings");
    	earningsLabel.setStyle("-fx-font-weight: bold;");
    	
    	HBox hourlyRow = new HBox();
    	TextField hoursTextField = new TextField("0");
    	hoursTextField.setPrefWidth(30);
    	Label hourlyLabel = new Label("hrs at hourly wage of ");
    	TextField wageTextField = new TextField("0.00");
    	wageTextField.setPrefWidth(50);
    	hourlyRow.getChildren().addAll(hoursTextField,hourlyLabel,wageTextField);
    	
    	HBox comissionRow = new HBox();
    	Label comissionLabel = new Label("Comission earned ");
    	TextField comissionTextField = new TextField("0.00");
    	comissionTextField.setPrefWidth(60);
    	comissionTextField.setStyle("-fx-text-fill: green;");
    	Button comissionButton = new Button("Need to calculate");
    	comissionButton.setStyle("-fx-background-radius: 100");
    	comissionRow.getChildren().addAll(comissionLabel,comissionTextField,comissionButton);
    	
    	HBox tipsRow = new HBox();
    	Label tipsEarnedLabel = new Label("Tips earned ");
    	TextField tipsEarnedTextField = new TextField("0.00");
    	tipsEarnedTextField.setPrefWidth(50);
    	tipsEarnedTextField.setStyle("-fx-text-fill: green;");
    	Label tipOutLabel = new Label(" minus Tip out ");
    	TextField tipOutTextField = new TextField("0.00");
    	tipOutTextField.setPrefWidth(50);
    	tipOutTextField.setStyle("-fx-text-fill: red;");
    	Button tipOutButton = new Button("Need to calculate");
    	tipOutButton.setStyle("-fx-background-radius: 100");
    	tipsRow.getChildren().addAll(tipsEarnedLabel,tipsEarnedTextField,tipOutLabel,tipOutTextField,tipOutButton);
    	
    	Label expensesLabel = new Label("\nDaily expenses");
    	expensesLabel.setStyle("-fx-font-weight: bold;");
    	
    	VBox expensesRow = new VBox();
    	HBox expensesHBox = new HBox();
    	TextField expensesTextField = new TextField("0.00");
    	expensesTextField.setPrefWidth(60);
    	expensesTextField.setStyle("-fx-text-fill: red;");
    	Label writeOffLabel = new Label(" Expense can be written off ");
    	CheckBox taxableCheckBox = new CheckBox();
    	expensesHBox.getChildren().addAll(expensesTextField,writeOffLabel,taxableCheckBox);
    	Button addButton = new Button("(+) add expense");
    	expensesRow.getChildren().addAll(expensesHBox,addButton);
    	
    	Button earningsButton = new Button("Calculate daily earnings");
    	
    	Button enterEarningsButton = new Button("Enter daily earnings");
    	enterEarningsButton.setStyle("-fx-padding: 0.7em 0.7em;");
    	
    	Button abortButton = new Button("Cancel");
    	abortButton.setStyle("-fx-background-radius: 100");
    	abortButton.setOnAction(abortEvent -> cancel(mainScene));
 
    	earningsBox.getChildren().addAll(titleLabel,dateRow,incomeInfoLabel,incomeSourceRow,currencyRow,
    			earningsLabel,hourlyRow,comissionRow,tipsRow,expensesLabel,expensesRow,earningsButton,
    			enterEarningsButton,abortButton);
    	Scene earningsScene = new Scene(earningsBox,400,500);
    	
    	applicationStage.setScene(earningsScene);
	}
	
	@FXML
    void enterExpenses (ActionEvent enterExpensesEvent) {
		Scene mainScene = applicationStage.getScene();
		applicationStage.setTitle("Enter expenses");
		
		VBox expensesBox = new VBox();
		expensesBox.setStyle("-fx-background-color: pink;");
		
		Label titleLabel = new Label("Track Expenses \n\nDate");
    	titleLabel.setStyle("-fx-font-weight: bold;");
    	
    	HBox dateRow = new HBox();
    	Label monthLabel = new Label("Month ");
    	TextField monthTextField = new TextField("mm");
    	monthTextField.setPrefWidth(35);
    	Label dayLabel = new Label(" Day ");
    	TextField dayTextField = new TextField("dd");
    	dayTextField.setPrefWidth(35);
    	dateRow.getChildren().addAll(monthLabel,monthTextField,dayLabel,dayTextField);
    	
    	Label expensesLabel = new Label("\nExpenses");
    	expensesLabel.setStyle("-fx-font-weight: bold;");
    	
    	VBox expensesRow = new VBox();
    	HBox expensesHBox = new HBox();
    	TextField expensesTextField = new TextField("0.00");
    	expensesTextField.setPrefWidth(60);
    	expensesTextField.setStyle("-fx-text-fill: red;");
    	Label writeOffLabel = new Label(" Expense can be written off ");
    	CheckBox taxableCheckBox = new CheckBox();
    	expensesHBox.getChildren().addAll(expensesTextField,writeOffLabel,taxableCheckBox);
    	Button addButton = new Button("(+) add expense");
    	expensesRow.getChildren().addAll(expensesHBox,addButton);
    	
    	Button expensesButton = new Button("Click to calculate expenses total");
    	
    	Button enterExpensesButton = new Button("Enter expenses");
    	enterExpensesButton.setStyle("-fx-padding: 0.7em 0.7em;");
    	
    	Button abortButton = new Button("Cancel");
    	abortButton.setStyle("-fx-background-radius: 100");
    	abortButton.setOnAction(abortEvent -> cancel(mainScene));
  
    	expensesBox.getChildren().addAll(titleLabel,dateRow,expensesLabel,expensesRow,expensesButton,
    			enterExpensesButton,abortButton);
    	Scene expensesScene = new Scene(expensesBox,400,500);
    	
    	applicationStage.setScene(expensesScene);
	}
	

	
}
