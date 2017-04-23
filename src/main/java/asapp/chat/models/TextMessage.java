package asapp.chat.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "TEXT_MESSAGE")
public class TextMessage extends Message {
	
	public TextMessage(){
		
	}
	
	public TextMessage(String message) {
		super(message);
	}

}
