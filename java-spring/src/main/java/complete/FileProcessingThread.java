package complete;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileProcessingThread{

	Path filePath;
	
	public FileProcessingThread(Path filePath) {
		this.filePath = filePath;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		try {
	            Reader reader = Files.newBufferedReader(filePath);
	            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
	                    .withFirstRecordAsHeader()
	                    .withIgnoreHeaderCase()
	                    .withTrim());
	        
	            for (CSVRecord csvRecord : csvParser) {
	                // Accessing values by Header names
	                String bookingId = csvRecord.get("Booking ID");
	                String propertyId = csvRecord.get("Property ID");
	                String bookingAmount = csvRecord.get("Booking amount");
	                String creditCardNumber = csvRecord.get("Guest credit card number");
	                if(creditCardNumber.endsWith("4242")) {
	                	System.out.println("Booking ID: " +bookingId + " for Property: " +propertyId+ "and amount "+bookingAmount+" was processed SUCCESSFULLY!");
	                }else {
	                	System.out.println("Booking ID: " +bookingId + " for Property: " +propertyId+ "and amount "+bookingAmount+" was processed FAILED!");
	                }
	                Database.addRecords(bookingId, csvRecord);
	            }
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
