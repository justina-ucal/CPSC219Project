package application;

//import java.io.IOException;

public class Taxes {
	//FUTURE
	/*
	
	private double incomeTax;
	private double incomeYTD;

	Taxes(ManageFile bookkeepingCSV, double takeHome) throws NumberFormatException {
		try{incomeYTD = bookkeepingCSV.readIncomeYTD();
						
		} catch(IOException ioe) {System.out.println("INPUT/OUTPUT ISSUE - ERROR OCCURED WHILE"
				+ " READING: serviceSheets.csv");
		} catch(NumberFormatException nfe) {throw nfe;}
	}*/
	
	String calcIncomeTax(double takeHomeEarnings) {
		/**
		 * NOTE: the method assumes user will not jump more than one tax bracket in one day
		 * if user does, the code does not account for this and will calculate the tax on a portion of user's
		 * daily income at a higher rate than they will actually be taxed at
		 */
		double EIcontribution = 0;
		double CPPcontribution = 0;
		double incomeTax = 0;
		
		EIcontribution = (takeHomeEarnings)*(0.01);
		CPPcontribution = (takeHomeEarnings)*(0.114);
		incomeTax = (takeHomeEarnings)*(0.25);
		
		System.out.println("**Calculated new amounts owing**"
				+ "\n Income tax: " + incomeTax
				+ "\n EI: " +  EIcontribution
				+ "\n CPP: " + CPPcontribution
				+ "\n************");
		
		double taxesOnEarnings = incomeTax + EIcontribution + CPPcontribution;
		return String.valueOf(taxesOnEarnings);
		
		
		//FUTURE
		/*System.out.println("************"
				+ "\n Income taxes previously applied to: " + incomeYTD
				+ "\n Apply taxes to this income: " + takeHomeEarnings);
		
		double taxableIncome = incomeYTD + takeHomeEarnings - 13808;
		if(taxableIncome < 0) {taxableIncome = 0.0;}
		
		else if(taxableIncome < 50197.01){
			incomeTax = (takeHomeEarnings)*(0.25);
			EIcontribution = calcIncomeTaxBeyondFirstBracket(60300, 0.0158, 0, incomeYTD, taxableIncome, takeHomeEarnings);
			CPPcontribution = calcIncomeTaxBeyondFirstBracket(61400, 0.114, 0, incomeYTD, taxableIncome, takeHomeEarnings);
		}
		else if(taxableIncome < 100392.01){
			calcIncomeTaxBeyondFirstBracket(50197, 0.25, 30.50, incomeYTD, taxableIncome, takeHomeEarnings);
			if(taxableIncome < 61400) {
				CPPcontribution = calcIncomeTaxBeyondFirstBracket(61400, 0.114, 0, incomeYTD, taxableIncome, takeHomeEarnings);
				if(taxableIncome < 60300) {EIcontribution = 
						calcIncomeTaxBeyondFirstBracket(60300, 0.0158, 0, incomeYTD, taxableIncome, takeHomeEarnings);
				}
			}
		}
		else if(taxableIncome < 131220.01){
			calcIncomeTaxBeyondFirstBracket(100392, 0.3050, 0.36, incomeYTD, taxableIncome, takeHomeEarnings);
		}
		else if(taxableIncome < 155625.01){
			calcIncomeTaxBeyondFirstBracket(131220, 0.36, 0.38, incomeYTD, taxableIncome, takeHomeEarnings);
		}
		else if(taxableIncome < 157464.01){
			calcIncomeTaxBeyondFirstBracket(155625, 0.38, 0.4138, incomeYTD, taxableIncome, takeHomeEarnings);
		}
		else if(taxableIncome < 209952.01){
			calcIncomeTaxBeyondFirstBracket(157464, 0.4138, 0.4238, incomeYTD, taxableIncome, takeHomeEarnings);
		}
		else if(taxableIncome < 221708.01){
			calcIncomeTaxBeyondFirstBracket(209952, 0.4238, 0.4338, incomeYTD, taxableIncome, takeHomeEarnings);
		}
		else if(taxableIncome < 314928.01){
			calcIncomeTaxBeyondFirstBracket(221708, 0.4338, 0.47, incomeYTD, taxableIncome, takeHomeEarnings);
		}
		else{
			calcIncomeTaxBeyondFirstBracket(314928, 0.47, 0.48, incomeYTD, taxableIncome, takeHomeEarnings);
		}
		
		double taxesOnEarnings = incomeTax + EIcontribution + CPPcontribution;
		System.out.println("**Calculated new amounts owing**"
				+ "\n Income tax: " + incomeTax
				+ "\n EI: " +  EIcontribution
				+ "\n CPP: " + CPPcontribution
				+ "\n************");
		return String.valueOf(taxesOnEarnings);
		
	}

	double calcIncomeTaxBeyondFirstBracket(int lowBound, double lowRate, double highRate, double incomeYTD, 
			double taxableIncome, double takeHomeEarnings) {
		
		double amountOwing = 0.0;
		
		if(incomeYTD < (lowBound + 0.01)) {
			double lowerBracket = lowBound - incomeYTD;
			double upperBracket = taxableIncome - lowerBracket;
			amountOwing = (lowerBracket * (lowRate)) + (upperBracket * (highRate));
		} else amountOwing = (takeHomeEarnings)*(highRate);
		return amountOwing;
	}
	
	double getYTD() {
		return incomeYTD;*/
	}
}
