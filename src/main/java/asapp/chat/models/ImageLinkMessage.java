package asapp.chat.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "IMAGE_LINK_MESSAGE")
public class ImageLinkMessage extends Message {
	private long width;
	private long height;

	public ImageLinkMessage() {
		super();
	}

	public ImageLinkMessage(String message, long width, long height) {
		super(message);
		this.width = width;
		this.height = height;
	}

	public long getWidth() {
		return width;
	}

	public void setWidth(long width) {
		this.width = width;
	}

	public long getHeight() {
		return height;
	}

	public void setHeight(long height) {
		this.height = height;
	}

}
