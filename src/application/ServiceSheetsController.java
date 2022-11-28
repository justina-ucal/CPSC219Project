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
	void calcDailyPL (TextField hoursTextField, TextField wageTextField, TextField commissionTextField,
			TextField tipsEarnedTextField, TextField tipOutTextField, ArrayList<TextField> expensesArrayList,
			Label dailyLabel, TextField currencyTextField, Label codeErrorLabel) {
		/**
		 * PL stands from "Profit and Loss"
		 */
		//+
		double hourlyTotal = 0;
		double commission = 0;
		double tipsTotal = 0;
		
		//-
		double expensesTotal = 0;
		
		/*try {UserInput currencyInput = new UserInput(currencyTextField.getText());
		} catch(AlphaCodeFormatException acfe) {applicationStage.setTitle("Error occurred: Invalid entry");
			codeErrorLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: red;");
			codeErrorLabel.setText(" Er: (!) Invalid value entered. Currency code must be exactly three letters.");}
		*/
		
		try{UserInput currencyInput = new UserInput(currencyTextField.getText());
			
			UserInput hoursInput = new UserInput(hoursTextField);
			UserInput wageInput = new UserInput(wageTextField);
			
			hourlyTotal = hoursInput.getUserInput() * wageInput.getUserInput();
			System.out.println("Total hourly income entered: " + hourlyTotal);
			
			UserInput commissionInput = new UserInput(commissionTextField);
			
			commission = commissionInput.getUserInput();
			System.out.println("Total commission income entered: " + commission);
			
			UserInput tipsEarnedInput = new UserInput(tipsEarnedTextField);
			UserInput tipOutInput = new UserInput(tipOutTextField);
			
			tipsTotal = tipsEarnedInput.getUserInput() - tipOutInput.getUserInput();
			System.out.println("Total tips income entered: " + tipsTotal);
			
			for(TextField textfield: expensesArrayList) {
				UserInput expensesInput = new UserInput(textfield);
				
				expensesTotal += expensesInput.getUserInput();
				}
			System.out.println("Total daily expenses entered: " + expensesTotal);
				
			double dailyEarnings = (hourlyTotal + commission + tipsTotal) - expensesTotal;
			System.out.println("Total daily income: " + dailyEarnings);
			
			applicationStage.setTitle("Calculations completed: earnings must still be entered");
			
			dailyLabel.setText("Based on the information entered above, your total is: " + dailyEarnings + " " +
					currencyTextField.getText());
		} catch(AlphaCodeFormatException acfe) {applicationStage.setTitle("Error occurred: Invalid entry");
			codeErrorLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: red;");
			codeErrorLabel.setText(" Er: (!) Invalid value entered. Currency code must be exactly three letters.");
			}
		} catch(NumberFormatException nfe) {
			applicationStage.setTitle("Error occurred: Invalid entry");
			dailyLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: red;");
			dailyLabel.setText(" Er: (!) Invalid value entered. All earnings and/or expenses entries must"
					+ "\n be numeric, and may include only a single decimal point (optional)."
					+ "\n Do not include any letters or characters (e.g. $ or %).");
		}
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
    	
    	Label codeErrorLabel = new Label("");
    	
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
    	
    	Label expensesLabel = new Label("\nDaily Expenses");
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
    	
    	Label dailyInfoLabel = new Label("\nDaily Total Earnings");
    	dailyInfoLabel.setStyle("-fx-font-weight: bold;");
    	Label dailyLabel = new Label("---Press below to calculate your take-home---");
    	
    	Button earningsButton = new Button("Calculate");
    	earningsButton.setOnAction(calcEvent -> calcDailyPL(hoursTextField, wageTextField, commissionTextField,
    			tipsEarnedTextField, tipOutTextField, expensesArrayList, dailyLabel, currencyTextField, codeErrorLabel));
    	
    	Button enterEarningsButton = new Button("Enter daily earnings");
    	enterEarningsButton.setStyle("-fx-padding: 0.7em 0.7em;");
    	
    	Button abortButton = new Button("Cancel");
    	abortButton.setStyle("-fx-background-radius: 100");
    	abortButton.setOnAction(abortEvent -> cancel(mainScene));
 
    	earningsBox.getChildren().addAll(titleLabel,dateRow,incomeInfoLabel,incomeSourceRow,currencyRow,codeErrorLabel,
    			earningsLabel,hourlyRow,comissionRow,tipsRow,expensesLabel,expensesRow,dailyInfoLabel,
    			dailyLabel,earningsButton,enterEarningsButton,abortButton);
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
    	
    	Label dailyInfoLabel = new Label("\nDaily Total Expenses");
    	dailyInfoLabel.setStyle("-fx-font-weight: bold;");
    	Label dailyLabel = new Label("---Press below to calculate your spendings---");
    	
    	Button expensesButton = new Button("Calculate");
    	TextField currencyTextField = new TextField("CAD");
    	expensesButton.setOnAction(calcEvent -> calcDailyPL(null, null, null, null, null, expensesArrayList,
    			dailyLabel,currencyTextField,null));
    	
    	Button enterExpensesButton = new Button("Enter expenses");
    	enterExpensesButton.setStyle("-fx-padding: 0.7em 0.7em;");
    	
    	Button abortButton = new Button("Cancel");
    	abortButton.setStyle("-fx-background-radius: 100");
    	abortButton.setOnAction(abortEvent -> cancel(mainScene));
  
    	expensesBox.getChildren().addAll(titleLabel,dateRow,expensesLabel,expensesRow,dailyInfoLabel,
    			dailyLabel,expensesButton,enterExpensesButton,abortButton);
    	Scene expensesScene = new Scene(expensesBox,400,500);
    	
    	applicationStage.setScene(expensesScene);
    	
	}
	

	
}
