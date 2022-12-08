package application;

import java.io.BufferedWriter;
//import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
//import java.io.FileReader;
import java.io.IOException;

public class ManageFile {
	
	private String bookkeepingPath = "c:/Temp/serviceSheets.csv";
	
	ManageFile() throws IOException {
		/**
		 * constructor
		 */
		
		File bookkeepingFile = new File(bookkeepingPath);
		
		if(! bookkeepingFile.exists()) {
		BufferedWriter writer = new BufferedWriter(new FileWriter(bookkeepingPath));
		writer.write("Date,Currency,Source of Income,Total daily earnings,YTD,Total daily base pay (hourly) earned,"
				+ "Total daily commission earned,Total daily tips earned,Tip-out ($),Tip-out (%),Daily "
				+ "(taxable) expenses,YTD,,Daily (non-taxabale) expenses *keep recepits*,YTD,,Total take-home"
				+ " (taxable) income (after tip-out),YTD,,Taxes + CPP + EI,YTD,,Net daily earnings (take-home "
				+ "minus daily expenses and taxes),YTD" +
				"\n,,,,,,,,,,,,,,,,,,,,,"
				+ "\nDrag formulas down from bottom right corner,,,,=(D3+E2),,,,,=(I3/D3),,=(K3+L2),,,=(N3+O2),,,"
				+ "=(Q3+R2),,,=(T3+U2),,,=(W3+X2)");
			
		writer.close();}
	}
	
	void appendToCSVFile(String[] completeArray) throws IOException {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(bookkeepingPath, true));
			
			writer.write("\n");
			
			int index = 0;
			while(index < completeArray.length) {
				if(completeArray[index] == null) {completeArray[index] = "";}
				if(index == 0) {writer.write(completeArray[index]);}
				else{writer.write("," + completeArray[index]);}
				index ++;
			}
			
			writer.close();
	}
	
	//FUTURE
	/*double readIncomeYTD() throws IOException, NumberFormatException {
		
		String[] mostRecentPreviousEntry = new String[23];
		double returnValue = 0.0;
		
		BufferedReader reader = new BufferedReader(new FileReader(bookkeepingPath));
		
		//read last line
		String line = reader.readLine();
		while(line != null){line = reader.readLine();
			if(line != null) {mostRecentPreviousEntry = line.split(",");}
			
			System.out.println(mostRecentPreviousEntry.length);
			if(mostRecentPreviousEntry.length > 16) {System.out.println(mostRecentPreviousEntry[17]);}
		
		}
		
		int index = 0;
		while(index < mostRecentPreviousEntry.length) {
			System.out.print(mostRecentPreviousEntry[index]);
			index++;
		}
		
		UserInput previousTakeHomes = new UserInput(mostRecentPreviousEntry);
		returnValue = previousTakeHomes.getFileData();
			
		reader.close();
		
		return returnValue;
		
	}*/
}