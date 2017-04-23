package asapp.chat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends Exception {
	
	private static final long serialVersionUID = 2069478180932330344L;

	public DataNotFoundException(String message){
		super(message);
	}
}
