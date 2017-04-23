package asapp.chat.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Conversation implements Comparable<Conversation>{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="ASSOCIATED_USER1_ID")
	@ApiModelProperty(value="User who initiated the conversation.", required=true)
	private User user1;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="ASSOCIATED_USER2_ID")
	@ApiModelProperty(value="User who is subject of the conversation.", required=true)
	private User user2;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="ASSOCIATED_MESSAGE_ID")
	@ApiModelProperty(value="Message shared between two users.", required=true)
	private Message message;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:MM:SS")
	@ApiModelProperty(value="Timestamp of the conversation.", required=true)
	private Date timestamp;
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public User getUser1() {
		return user1;
	}
	public void setUser1(User user1) {
		this.user1 = user1;
	}
	public User getUser2() {
		return user2;
	}
	public void setUser2(User user2) {
		this.user2 = user2;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	@Override
	public int compareTo(Conversation o) {
		if(this.getTimestamp()==null || o.getTimestamp()==null)
			return 1;
		return o.getTimestamp().compareTo(this.getTimestamp());
	}

}
