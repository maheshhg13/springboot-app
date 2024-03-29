package asapp.chat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyExistsException extends Exception {
	
	private static final long serialVersionUID = 2069478180932330344L;

	public AlreadyExistsException(String message){
		super(message);
	}
}
