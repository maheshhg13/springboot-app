package asapp.chat.store;

import java.util.List;

import asapp.chat.exceptions.AlreadyExistsException;
import asapp.chat.exceptions.DataNotFoundException;
import asapp.chat.models.Conversation;
import asapp.chat.models.Message;
import asapp.chat.models.User;;

public interface ChatStore {
	public void addUser(String userName, String password) throws AlreadyExistsException;
	public void addConversation(String user1, String user2, Message message) throws DataNotFoundException;
	public List<User> listAllUsers();
	public List<Conversation> listAllConversations();
	public List<Conversation> listAllConversations(String user1, String user2, Integer size, Integer page) throws DataNotFoundException;
}
