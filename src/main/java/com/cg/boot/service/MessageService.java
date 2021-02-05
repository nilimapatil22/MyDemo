package com.cg.boot.service;

import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.Message;
import com.cg.boot.model.User;
import com.cg.boot.repository.MessageRepository;

@Service
@Transactional
public class MessageService implements IMessageService {
	@Autowired
	MessageRepository repository;

	@Autowired
	UserService userService;

	/*
	 * Add Messages
	 */
	@Override
	public Message addMessage(Message message) {
		if (userService.getUser(message.getCreatedById()) == null) {
			throw new DataNotFoundException("Invalid User Id");
		}
		return repository.save(message);

	}
	/*
	 * Get messages based on message id
	 */

	@Override
	public Message getMessage(int id) {
		return repository.findById(id).orElse(null);
	}
	/*
	 * Get all messages
	 */

	@Override
	public List<Message> getAllMessages() {
		return repository.findAll();
	}
	/*
	 * Get messages based on student id
	 */

	@Override
	public List<Message> getMessagesByStudentId(int id) {
		List<Message> list = repository.findAllByStudentId(id);
		if (list.isEmpty()) {
			throw new DataNotFoundException("No messages are present with given student id: " + id);
		}
		return list;

	}
	/*
	 * Update message
	 */

	@SuppressWarnings("null")
	@Override
	public Message updateMessage(Message message) {
		if (getMessage(message.getMessageId()) == null) {
			throw new DataNotFoundException("No message present to update");
		}
		if (!isValidMessage(message.getMessage())) {
			throw new DataNotFoundException("Message should has min 4 chars");
		}
		if (!isValidDate(message.getCreatedDate())) {
			throw new DataNotFoundException("Date should be in yyyy-MM-dd format");
		}

		User userInfo = userService.getUser(message.getCreatedById());
		if (userInfo == null) {
			throw new DataNotFoundException("Invalid user id");
		}
		if (!userInfo.getRoleType().equals("admin")) {
			throw new DataNotFoundException("You are not authorized");
		}

		return repository.save(message);
	}
	/*
	 * Delete message
	 */

	@Override
	public List<Message> deleteMessage(int messageId, int userId) {
		User userInfo = userService.getUser(userId);
		if (userInfo == null) {
			throw new DataNotFoundException("Invalid user id");
		}
		if (!userInfo.getRoleType().equals("admin")) {
			throw new DataNotFoundException("You are not authorized");
		}

		if (getMessage(messageId) == null) {
			throw new DataNotFoundException("No message present to delete with given id: " + messageId);
		}
		repository.deleteById(messageId);
		return repository.findAll();
	}
	/*
	 * Validate Message
	 */

	public boolean isValidMessage(String message) {
		boolean flag = false;
		if (message.length() > 4) {
			flag = true;
		}
		return flag;
	}

	/*
	 * Validate Date
	 */
	public boolean isValidDate(String date) {
		boolean flag = false;
		String regex = "((?:20)[2-3][1-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(date);
		if (m.matches()) {
			flag = true;
		}
		return flag;

	}

}
