package application;

import java.util.ArrayList;
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
    void enterEarnings (ActionEvent enterEarningsEvent) {
		/**
		 * sets the Scene for the user to enter their daily earnings information after having pressed the "Enter
		 * earnings" Button on the home menu (i.e. mainScene) resulting in the Action Event (passed to this method)
		 * @param enterEarningsEvent
		 */
		WindowAction window = new WindowAction();
		
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
    	
    	Label dateErrorLabel = new Label("");
    	dateErrorLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: red");
    	
    	Label incomeInfoLabel = new Label("Income Info.");
    	incomeInfoLabel.setStyle("-fx-font-weight: bold;");
    	
    	HBox incomeSourceRow = new HBox();
    	Label incomeSourceLabel = new Label("Source of Income ");
    	TextField incomeSourceTextField = new TextField();
    	Label optionalLabel = new Label(" (optional)");
    	incomeSourceRow.getChildren().addAll(incomeSourceLabel,incomeSourceTextField,optionalLabel);
    	
    	HBox currencyRow = new HBox();
    	Label currencyLabel = new Label("Currency Code ");
    	TextField currencyTextField = new TextField("CAD");
    	currencyTextField.setPrefWidth(40);
    	currencyRow.getChildren().addAll(currencyLabel,currencyTextField);
    	
    	Label codeErrorLabel = new Label("");
    	
    	Label earningsLabel = new Label("Earnings");
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
    	TextField commissionTextField = new TextField("0.00");
    	commissionTextField.setPrefWidth(60);
    	commissionTextField.setStyle("-fx-text-fill: green;");
    	comissionRow.getChildren().addAll(comissionLabel,commissionTextField);
    	
    	HBox tipsRow = new HBox();
    	Label tipsEarnedLabel = new Label("Tips earned ");
    	TextField tipsEarnedTextField = new TextField("0.00");
    	tipsEarnedTextField.setPrefWidth(50);
    	tipsEarnedTextField.setStyle("-fx-text-fill: green;");
    	Label tipOutLabel = new Label(" minus Tip out ");
    	TextField tipOutTextField = new TextField("0.00");
    	tipOutTextField.setPrefWidth(50);
    	tipOutTextField.setStyle("-fx-text-fill: red;");
    	tipsRow.getChildren().addAll(tipsEarnedLabel,tipsEarnedTextField,tipOutLabel,tipOutTextField);
    	
    	Label expensesLabel = new Label("\nDaily Expenses");
    	expensesLabel.setStyle("-fx-font-weight: bold;");
    	
    	ArrayList<TextField> expensesArrayList = new ArrayList<TextField>();
    	ArrayList<CheckBox> taxCheckArrayList = new ArrayList<CheckBox>();
    	
    	VBox expensesRow = new VBox();
    	HBox expensesHBox = new HBox();
    	TextField expensesTextField = new TextField("0.00");
    	expensesTextField.setPrefWidth(60);
    	expensesTextField.setStyle("-fx-text-fill: red;");
    	Label writeOffLabel = new Label(" Expense can be written off ");
    	CheckBox taxableCheckBox = new CheckBox();
    	
    	expensesArrayList.add(expensesTextField);
    	taxCheckArrayList.add(taxableCheckBox);
    	
    	expensesHBox.getChildren().addAll(expensesTextField,writeOffLabel,taxableCheckBox);
    	Button addButton = new Button("(+) add expense");
    	expensesRow.getChildren().addAll(expensesHBox,addButton);
    	addButton.setOnAction(addEvent -> window.addExpenseTextField(expensesRow,expensesArrayList,taxCheckArrayList));
    	
    	Label dailyInfoLabel = new Label("\nDaily Total Earnings");
    	dailyInfoLabel.setStyle("-fx-font-weight: bold;");
    	Label dailyLabel = new Label("---Press below to calculate your take-home---");
    	
    	Button earningsButton = new Button("Calculate");
    	FileArray dailyEarnings = new FileArray();
    	earningsButton.setOnAction(calcEvent -> dailyEarnings.calcDailyPL(hoursTextField,wageTextField,commissionTextField,
    			tipsEarnedTextField,tipOutTextField,expensesArrayList,dailyLabel,currencyTextField,codeErrorLabel));
    	
    	Label enterLabel = new Label("\nEnter Earnings");
    	enterLabel.setStyle("-fx-font-weight: bold;");
    	Label enterErrorLabel = new Label("\n");
    	enterErrorLabel.setStyle("-fx-font-weight: bold; -fx-text-fill:red;");
    	Button enterEarningsButton = new Button("Save");
    	enterEarningsButton.setStyle("-fx-padding: 0.7em 0.7em;");
    	enterEarningsButton.setOnAction(dataEntryEvent -> dailyEarnings.fillDailyArray(monthTextField,dayTextField,
    			dateErrorLabel,incomeSourceTextField,expensesArrayList,taxCheckArrayList,enterErrorLabel,applicationStage,mainScene,
    			dailyEarnings));
    	
    	Button abortButton = new Button("Cancel");
    	abortButton.setStyle("-fx-background-radius: 100");
    	abortButton.setOnAction(abortEvent -> window.cancel(applicationStage,mainScene,dailyEarnings,0));
 
    	earningsBox.getChildren().addAll(titleLabel,dateRow,dateErrorLabel,incomeInfoLabel,incomeSourceRow,currencyRow,
    			codeErrorLabel,earningsLabel,hourlyRow,comissionRow,tipsRow,expensesLabel,expensesRow,dailyInfoLabel,
    			dailyLabel,earningsButton,enterLabel,enterEarningsButton,enterErrorLabel,abortButton);
    	Scene earningsScene = new Scene(earningsBox,450,650);
    	
    	applicationStage.setScene(earningsScene);
	}
	
	@FXML
    void enterExpenses (ActionEvent enterExpensesEvent) {
		/**
		 * sets the Scene for the user to enter some expenses after having pressed the "Enter expenses" Button
		 * on the home menu (i.e. mainScene) resulting in the Action Event (passed to this method)
		 * @param enterExpensesEvent
		 */
		WindowAction window = new WindowAction();
		
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
    	
    	Label dateErrorLabel = new Label("");
    	dateErrorLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: red");
    	
    	Label expenseInfoLabel = new Label("Expense Info.");
    	expenseInfoLabel.setStyle("-fx-font-weight: bold;");
    	
    	HBox expenseTypeRow = new HBox();
    	Label expenseTypeLabel = new Label("Type of Expense ");
    	TextField expensesTypeTextField = new TextField();
    	Label optionalLabel = new Label(" (optional)");
    	expenseTypeRow.getChildren().addAll(expenseTypeLabel,expensesTypeTextField,optionalLabel);
    	
    	Label expensesLabel = new Label("Expenses");
    	expensesLabel.setStyle("-fx-font-weight: bold;");
    	
    	ArrayList<TextField> expensesArrayList = new ArrayList<TextField>();
    	ArrayList<CheckBox> taxCheckArrayList = new ArrayList<CheckBox>();
    	
    	VBox expensesRow = new VBox();
    	HBox expensesHBox = new HBox();
    	TextField expensesTextField = new TextField("0.00");
    	expensesTextField.setPrefWidth(60);
    	expensesTextField.setStyle("-fx-text-fill: red;");
    	Label writeOffLabel = new Label(" Expense can be written off ");
    	CheckBox taxableCheckBox = new CheckBox();
    	
    	expensesArrayList.add(expensesTextField);
    	taxCheckArrayList.add(taxableCheckBox);
    	
    	expensesHBox.getChildren().addAll(expensesTextField,writeOffLabel,taxableCheckBox);
    	Button addButton = new Button("(+) add expense");
    	expensesRow.getChildren().addAll(expensesHBox,addButton);
    	addButton.setOnAction(addEvent -> window.addExpenseTextField(expensesRow,expensesArrayList,taxCheckArrayList));
    	
    	Label dailyInfoLabel = new Label("\nDaily Total Expenses");
    	dailyInfoLabel.setStyle("-fx-font-weight: bold;");
    	Label dailyLabel = new Label("---Press below to calculate your spendings---");
    	
    	TextField currencyTextField = new TextField("CAD");
    	Label codeErrorLabel = new Label("");
    	Button expensesButton = new Button("Calculate");
    	FileArray expensesEntry = new FileArray();
    	expensesButton.setOnAction(calcEvent -> expensesEntry.calcDailyPL(null, null, null, null, null, expensesArrayList,
    			dailyLabel,currencyTextField,codeErrorLabel));
    	
    	Label enterLabel = new Label("\nEnter Expenses");
    	enterLabel.setStyle("-fx-font-weight: bold;");
    	Label enterErrorLabel = new Label("\n");
    	enterErrorLabel.setStyle("-fx-font-weight: bold; -fx-text-fill:red;");
    	Button enterExpensesButton = new Button("Save");
    	enterExpensesButton.setStyle("-fx-padding: 0.7em 0.7em;");
    	enterExpensesButton.setOnAction(dataEntryEvent -> expensesEntry.fillDailyArray(monthTextField,dayTextField,
    			dateErrorLabel,expensesTypeTextField,expensesArrayList,taxCheckArrayList,enterErrorLabel,applicationStage,
    			mainScene,expensesEntry));
    	
    	Button abortButton = new Button("Cancel");
    	abortButton.setStyle("-fx-background-radius: 100");
    	abortButton.setOnAction(abortEvent -> window.cancel(applicationStage,mainScene,expensesEntry,0));
  
    	expensesBox.getChildren().addAll(titleLabel,dateRow,dateErrorLabel,expenseInfoLabel,expenseTypeRow,
    			expensesLabel,expensesRow,dailyInfoLabel,dailyLabel,expensesButton,enterLabel,enterExpensesButton,
    			enterErrorLabel,abortButton);
    	Scene expensesScene = new Scene(expensesBox,450,650);
    	
    	applicationStage.setScene(expensesScene);
	}
	

	
}
