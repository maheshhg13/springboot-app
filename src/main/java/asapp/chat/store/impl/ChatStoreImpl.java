package asapp.chat.store.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import asapp.chat.exceptions.AlreadyExistsException;
import asapp.chat.exceptions.DataNotFoundException;
import asapp.chat.models.Conversation;
import asapp.chat.models.Message;
import asapp.chat.models.User;
import asapp.chat.repositories.ConversationRepository;
import asapp.chat.repositories.UserRepository;
import asapp.chat.store.ChatStore;

@Component
public class ChatStoreImpl implements ChatStore{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ConversationRepository conversationRepository;
	
	@Override
	public void addUser(String userName, String password) throws AlreadyExistsException {
		if(!userRepository.findByUserName(userName).isEmpty()){
			throw new AlreadyExistsException("User Already Exists.");
		}
		userRepository.save(new User(userName, password));
	}

	@Override
	public void addConversation(String user1, String user2, Message message) throws DataNotFoundException {
		List<User> userOneResult = userRepository.findByUserName(user1);
		List<User> userTwoResult = userRepository.findByUserName(user2);
		if(userOneResult.isEmpty() || userTwoResult.isEmpty()){
			throw new DataNotFoundException("User(s) not found. Please check if users exist.");
		}
		Conversation conversation = new Conversation();
		conversation.setUser1(userOneResult.iterator().next());
		conversation.setUser2(userTwoResult.iterator().next());
		conversation.setMessage(message);
		conversation.setTimestamp(new Date());
		conversationRepository.save(conversation);
	}

	@Override
	public List<User> listAllUsers() {
		List<User> target = new ArrayList<User>();
		userRepository.findAll().forEach(target::add);
		return target;
	}

	@Override
	public List<Conversation> listAllConversations() {
		List<Conversation> target = new ArrayList<Conversation>();
		conversationRepository.findAll().forEach(target::add);
		Collections.sort(target);
		return target;
	}

	@Override
	public List<Conversation> listAllConversations(String user1, String user2, Integer size, Integer page) throws DataNotFoundException {
		List<User> userOneResult = userRepository.findByUserName(user1);
		List<User> userTwoResult = userRepository.findByUserName(user2);
		if(userOneResult.isEmpty() || userTwoResult.isEmpty()){
			throw new DataNotFoundException("User(s) not found. Please check if users exist.");
		}
		List<Conversation> target = new ArrayList<Conversation>();
		PageRequest pageable = null;
		if(size!=null && page!=null){
			pageable = new PageRequest(page-1, size, Sort.Direction.DESC, "timestamp");
		}
		conversationRepository.findByUser1AndUser2(userOneResult.iterator().next(), userTwoResult.iterator().next(), pageable).forEach(target::add);
		Collections.sort(target);
		return target;
	}
}
