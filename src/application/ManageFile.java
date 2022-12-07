package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
		writer.write("Date,Currency,Source of Income,Total daily earnings,YTD,Total daily base pay earned,"
				+ "Total daily commission earned,Total daily tips earned,Tip-out ($),Tip-out (%),Daily "
				+ "(taxable) expenses,YTD,,Daily (non-taxabale) expenses *keep recepits*,YTD,,Taxes + CPP"
				+ " + EI,YTD,,Net daily earnings (take-home minus daily expenses and taxes),YTD" +
				"\n\n"
				+ "Drag formulas  down from bottom right corner,,,,,,,=(I4/D4),,=(K4+L3),,,=(N4+O3),,,=(Q4+R3),"
				+ ",,=(T4+U3),,,=(W4+X3)");
			
		writer.close();}
		
		else {
			BufferedWriter writer = new BufferedWriter(new FileWriter(bookkeepingPath, true));
			writer.write("\n");
			writer.close();
		}
	}
	
	void appendToCSVFile(String[] completeArray) throws IOException {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(bookkeepingPath, true));
			
			int index = 0;
			while(index < completeArray.length) {
				if(index == 0) {writer.write(completeArray[index]);}
				else{writer.write("," + completeArray[index]);}
				index ++;
			}
			
			writer.close();
	}

}
