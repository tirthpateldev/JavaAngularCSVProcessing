package complete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Database {

	static Map<String,CSVRecord> transactionData = new HashMap<String,CSVRecord>();
	
	static void addRecords(String bookingId,CSVRecord csvRecord) {
			transactionData.put(bookingId, csvRecord);
	}
	
	static String getRecords() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		CsvDatas csvDatas = new  CsvDatas();
		for(String transactionId:transactionData.keySet()) {
			CsvData csvData = new CsvData();
			csvData.setBookingId(transactionData.get(transactionId).get("Booking ID"));
			csvData.setCardNumber(transactionData.get(transactionId).get("Guest credit card number"));
			csvDatas.getData().add(csvData);
		}
		return objectMapper.writeValueAsString(csvDatas);
	}
	
	static String deleteRecords(String bookingId) {
		if(transactionData.containsKey(bookingId)) {
			transactionData.remove(bookingId);
			return "record has been deleted for now, it may added when scheduler again run";
		}else {
			return "provided booking Id does not exists";
		}
	}
}
