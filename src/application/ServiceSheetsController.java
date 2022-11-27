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
	void cancel(Scene mainScene) {
		/**
		 * returns user to home menu without running any calculations nor saving any changes to the CSV file
		 * @param mainScene	is the Scene that sets the home menu 
		 */
		applicationStage.setScene(mainScene);
		applicationStage.setTitle("Manage Spreadsheets");
		
	}
	
	@FXML
	void addExpenseTextField (VBox expensesRow, ArrayList<TextField> expensesArrayList) {
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
    	expensesHBox.getChildren().addAll(expensesTextField,writeOffLabel,taxableCheckBox);
    	expensesRow.getChildren().addAll(expensesHBox);
	}
	
	@FXML
	void calcDailyEarnings (TextField hoursTextField, TextField wageTextField, TextField commissionTextField,
			TextField tipsEarnedTextField, TextField tipOutTextField, ArrayList<TextField> expensesArrayList) {
		//+
		double hourlyTotal = 0;
		double commission = 0;
		double tipsTotal = 0;
		
		//-
		double expensesTotal = 0;
		
		UserInput hoursInput = new UserInput();
		//TO-DO: use constructor to verify input
		UserInput wageInput = new UserInput();
		//TO-DO: use constructor to verify input
		hourlyTotal = hoursInput.convertStringToDouble(hoursTextField)
				* wageInput.convertStringToDouble(wageTextField);
		System.out.println("Total hourly entered: " + hourlyTotal);
		
		UserInput commissionInput = new UserInput();
		//TO-DO: use constructor to verify input
		commission = commissionInput.convertStringToDouble(commissionTextField);
		System.out.println("Total commission entered: " + commission);
		
		UserInput tipsEarnedInput = new UserInput();
		//TO-DO: use constructor to verify input
		UserInput tipOutInput = new UserInput();
		//TO-DO: use constructor to verify input
		tipsTotal = tipsEarnedInput.convertStringToDouble(tipsEarnedTextField)
				- tipOutInput.convertStringToDouble(tipOutTextField);
		System.out.println("Total tips entered: " + tipsTotal);
		
		for(TextField textfield: expensesArrayList) {
			UserInput expensesInput = new UserInput();
			//TO-DO: use constructor to verify input
			expensesTotal += expensesInput.convertStringToDouble(textfield);
			//String expenseEnteredString = textfield.getText();
			//Double expenseEnteredDouble = Double.parseDouble(expenseEnteredString);
			//expensesTotal += expenseEnteredDouble;
			}
		System.out.println("Total expenses entered: " + expensesTotal);
			
		double dailyEarnings = (hourlyTotal + commission + tipsTotal) - expensesTotal;
		System.out.println("Total daily earnings entered: " + dailyEarnings);
	}
	
	@FXML
    void enterEarnings (ActionEvent enterEarningsEvent) {
		/**
		 * sets the Scene for the user to enter their daily earnings information after having pressed the "Enter
		 * earnings" Button on the home menu (i.e. mainScene) resulting in the Action Event (passed to this method)
		 * @param enterEarningsEvent
		 */
		
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
    	TextField commissionTextField = new TextField("0.00");
    	commissionTextField.setPrefWidth(60);
    	commissionTextField.setStyle("-fx-text-fill: green;");
    	Button comissionButton = new Button("Need to calculate");
    	comissionButton.setStyle("-fx-background-radius: 100");
    	comissionRow.getChildren().addAll(comissionLabel,commissionTextField,comissionButton);
    	
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
    	
    	ArrayList<TextField> expensesArrayList = new ArrayList<TextField>();
    	VBox expensesRow = new VBox();
    	HBox expensesHBox = new HBox();
    	TextField expensesTextField = new TextField("0.00");
    	expensesTextField.setPrefWidth(60);
    	expensesTextField.setStyle("-fx-text-fill: red;");
    	Label writeOffLabel = new Label(" Expense can be written off ");
    	CheckBox taxableCheckBox = new CheckBox();
    	expensesArrayList.add(expensesTextField);
    	expensesHBox.getChildren().addAll(expensesTextField,writeOffLabel,taxableCheckBox);
    	Button addButton = new Button("(+) add expense");
    	expensesRow.getChildren().addAll(expensesHBox,addButton);
    	addButton.setOnAction(addEvent -> addExpenseTextField(expensesRow, expensesArrayList));
    	
    	Button earningsButton = new Button("Calculate daily earnings");
    	earningsButton.setOnAction(calcEvent -> calcDailyEarnings(hoursTextField, wageTextField, commissionTextField,
    			tipsEarnedTextField, tipOutTextField, expensesArrayList));
    	
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
		/**
		 * sets the Scene for the user to enter some expenses after having pressed the "Enter expenses" Button
		 * on the home menu (i.e. mainScene) resulting in the Action Event (passed to this method)
		 * @param enterExpensesEvent
		 */
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
    	
    	ArrayList<TextField> expensesArrayList = new ArrayList<TextField>();
    	VBox expensesRow = new VBox();
    	HBox expensesHBox = new HBox();
    	TextField expensesTextField = new TextField("0.00");
    	expensesTextField.setPrefWidth(60);
    	expensesTextField.setStyle("-fx-text-fill: red;");
    	Label writeOffLabel = new Label(" Expense can be written off ");
    	CheckBox taxableCheckBox = new CheckBox();
    	expensesArrayList.add(expensesTextField);
    	expensesHBox.getChildren().addAll(expensesTextField,writeOffLabel,taxableCheckBox);
    	Button addButton = new Button("(+) add expense");
    	expensesRow.getChildren().addAll(expensesHBox,addButton);
    	addButton.setOnAction(addEvent -> addExpenseTextField(expensesRow, expensesArrayList));
    	
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
