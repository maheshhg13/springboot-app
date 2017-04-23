package asapp.chat.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "MESSAGE_TYPE")
@ApiModel(subTypes = {TextMessage.class,VideoLinkMessage.class, ImageLinkMessage.class}, discriminator = "mtype")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "mtype", visible=true)
@JsonSubTypes({
    @Type(value = TextMessage.class, name = "Text"),
    @Type(value = VideoLinkMessage.class, name = "VideoLink"),
    @Type(value = ImageLinkMessage.class, name = "ImageLink")
})
public abstract class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MESSAGE_ID")
	private Long id;
	@ApiModelProperty(value="Message content.", required=true)
	private String message;
	@ApiModelProperty(value="Type of Message being posted.", example="Text", allowableValues="Text, VideoLink, ImageLink", dataType="String", required=true)
	private String mtype;

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	public Message(){
		
	}
	
	public Message(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
