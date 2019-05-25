package complete;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class RestAPIController {
    
    @RequestMapping(value="/bookings",method = RequestMethod.GET)
    public String getBooking() throws JsonProcessingException {
    	return Database.getRecords();
    }
    
    @RequestMapping(value="/deleteBookings",method = RequestMethod.DELETE)
    public String deleteBooking(@RequestParam String username, @RequestParam String password, @RequestParam String id) throws Exception {
    	if("admin".equals(username) && "changeme".equals(password)) {
    		return Database.deleteRecords(id);
    	}else {
    		throw new Exception("Invalid credentials");
    	}
    }
    
}
