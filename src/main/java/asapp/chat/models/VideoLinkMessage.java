package asapp.chat.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "VIDEO_LINK_MESSAGE")
public class VideoLinkMessage extends Message {

	private long length;
	private String source;
	
	public VideoLinkMessage(){
		
	}
	
	public VideoLinkMessage(String message, long length, String source) {
		super(message);
		this.length = length;
		this.source = source;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
