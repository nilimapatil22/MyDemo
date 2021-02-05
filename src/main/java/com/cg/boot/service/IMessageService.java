package com.cg.boot.service;

import java.util.List;

import com.cg.boot.model.Message;

public interface IMessageService {

	Message addMessage(Message message);

	Message getMessage(int id);

	List<Message> getAllMessages();

	List<Message> getMessagesByStudentId(int id);

	Message updateMessage(Message message);

	List<Message> deleteMessage(int messageId, int userId);

}
