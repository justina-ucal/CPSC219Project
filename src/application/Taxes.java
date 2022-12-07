package application;

public class Taxes {
	private double incomeYTD;

	Taxes(){
		/**
		 * constructor pull Total take-home (taxable) income (after tip-out) YTD [17] from file = incomeYTD
		 */
	}
	
/*	void calcIncomeTax(double takeHomeEarnings) {
		/**
		 * NOTE: the method assumes user will not jump more than one tax bracket in one day
		 * if user does, the code does not account for this and will calculate the tax on a portion of user's
		 * daily income at a higher rate than they will actually be taxed at
		 */
		
	/*	double incomeTax = 0.0;
		double EIcontribution = 0.0;
		double CPPcontribution = 0.0;
		
		//pull Total take-home (taxable) income (after tip-out) YTD [17] from file = incomeYTD
		double taxableIncome = incomeYTD + takeHomeEarnings - 13808;
		if(taxableIncome < 0) {taxableIncome = 0.0;}
		if(taxableIncome < 50197.01){
			incomeTax = (takeHomeEarnings)*(0.25);
			EIcontribution = calcIncomeTaxSecondary(60300, 0.0158, 0, incomeYTD, taxableIncome);
			CPPcontribution = calcIncomeTaxSecondary(61400, 0.114, 0, incomeYTD, taxableIncome);
		}
		else if(taxableIncome < 100392.01){
			calcIncomeTaxSecondary(50197, 0.25, 30.50, incomeYTD, taxableIncome);
			if(taxableIncome < 61400) {
				CPPcontribution = calcIncomeTaxSecondary(61400, 0.114, 0, incomeYTD, taxableIncome);
				if(taxableIncome < 60300) {EIcontribution = 
						calcIncomeTaxSecondary(60300, 0.0158, 0, incomeYTD, taxableIncome);
				}
			}
		}
		else if(taxableIncome < 131220.01){
			calcIncomeTaxSecondary(100392, 0.3050, 0.36, incomeYTD, taxableIncome);
		}
		else if(taxableIncome < 155625.01){
			calcIncomeTaxSecondary(131220, 0.36, 0.38, incomeYTD, taxableIncome);
		}
		else if(taxableIncome < 157464.01){
			calcIncomeTaxSecondary(155625, 0.38, 0.4138, incomeYTD, taxableIncome);
		}
		else if(taxableIncome < 209952.01){
			calcIncomeTaxSecondary(157464, 0.4138, 0.4238, incomeYTD, taxableIncome);
		}
		else if(taxableIncome < 221708.01){
			calcIncomeTaxSecondary(209952, 0.4238, 0.4338, incomeYTD, taxableIncome);
		}
		else if(taxableIncome < 314928.01){
			calcIncomeTaxSecondary(221708, 0.4338, 0.47, incomeYTD, taxableIncome);
		}
		else{
			calcIncomeTaxSecondary(314928, 0.47, 0.48, incomeYTD, taxableIncome);
		}
		
		double taxesOnEarnings = incomeTax + EIcontribution + CPPcontribution;
		//taxesOnEarnings = this.dailyArray[19]
		
		
		
	}

	void calcIncomeTaxSecondary(int lowBound, double lowRate, double highRate, double incomeYTD, 
			double taxableIncome) {
		if(incomeYTD < (lowBound + 0.01)) {
			double lowerBracket = lowBound - incomeYTD;
			double upperBracket = taxableIncome - lowerBracket;
			incomeTax = (lowerBracket * (lowRate)) + (upperBracket * (highRate));
		} else incomeTax = (takeHomeEarnings)*(highRate);
		return incomeTax;
	}*/
		

}
