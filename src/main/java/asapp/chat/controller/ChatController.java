package asapp.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import asapp.chat.exceptions.AlreadyExistsException;
import asapp.chat.exceptions.DataNotFoundException;
import asapp.chat.models.Conversation;
import asapp.chat.models.Message;
import asapp.chat.models.User;
import asapp.chat.store.ChatStore;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
public class ChatController {
	
	@Autowired
	private ChatStore chatStore;
	
	@ApiOperation(value = "View the list of users.", response=User.class)
	@RequestMapping(value="/users", method=RequestMethod.GET, produces = "application/json")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully completed the request."),
			@ApiResponse(code = 500, message = "Service issue.")
			})
	public List<User> getAllUsers(){
		return chatStore.listAllUsers();
	}
	
	@RequestMapping(value="/users/{userName}", method=RequestMethod.POST)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully added a user."),
			@ApiResponse(code = 400, message = "User already exists."),
			@ApiResponse(code = 500, message = "Service issue.")
			})
	@ApiOperation(value = "Add a new user.")
	public void addUser(@PathVariable String userName, @RequestBody String password) throws AlreadyExistsException{
		chatStore.addUser(userName.toLowerCase(), password);
	}
	
	@ApiResponses(value = { 
			@ApiResponse(code = 400, message = "Invalid message type provided."),
			@ApiResponse(code = 404, message = "Either/Both of user doesn't exists."),
			@ApiResponse(code = 500, message = "Service issue.")
			})
	@RequestMapping(value="/conversations/{user1}/{user2}", method=RequestMethod.POST)
	@ApiOperation(value = "Add a new conversation.")
	public void addConversation(@PathVariable("user1") String user1, @PathVariable("user2") String user2, @RequestBody Message message) throws DataNotFoundException{
		chatStore.addConversation(user1.toLowerCase(), user2.toLowerCase(), message);
	}
	
	@ApiResponses(value = { 
			@ApiResponse(code = 404, message = "Either/Both of user doesn't exists."),
			@ApiResponse(code = 500, message = "Service issue.")
			})
	@ApiOperation(value = "View the list of conversation between two users.", response=Conversation.class)
	@RequestMapping(value="/conversations/{user1}/{user2}", method=RequestMethod.GET, produces = "application/json")
	public List<Conversation> getAllConversations(@PathVariable String user1, @PathVariable String user2, @RequestParam(required=false, value = "size") Integer size, @RequestParam(required=false, value = "page") Integer page) throws DataNotFoundException{
		return chatStore.listAllConversations(user1.toLowerCase(), user2.toLowerCase(), size, page);
	}
	
}
