package com.cg.boot.student.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.Message;
import com.cg.boot.service.IMessageService;

@RestController
@RequestMapping("/api")
public class StudentMessageController {
	@Autowired
	IMessageService messageService;

	/*
	 * Add Messages
	 */
	@PostMapping("/addStudentMessage")
	public Message addMessage(@Valid @RequestBody Message messageDetails) {
		Message messageInfo = messageService.addMessage(messageDetails);
		return messageInfo;

	}

	/*
	 * Get messages based on message id
	 */
	@GetMapping("/getStudentMessage/{id}")
	public ResponseEntity<Message> getMessage(@PathVariable("id") int id) {
		Message message = messageService.getMessage(id);
		if (message == null) {
			throw new DataNotFoundException("No message present with given id: " + id);
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);

	}

	/*
	 * Get all messages
	 */
	@GetMapping("/getAllStudentMessages")
	public ResponseEntity<List<Message>> getAllMessages() {
		List<Message> messageList = messageService.getAllMessages();
		return new ResponseEntity<List<Message>>(messageList, HttpStatus.OK);

	}

	/*
	 * Get messages based on student id
	 */
	@GetMapping("/getStudentMessagesByStudentId/{id}")
	public ResponseEntity<List<Message>> getMessagesByStudentId(@PathVariable("id") int id) {
		List<Message> message = messageService.getMessagesByStudentId(id);
		return new ResponseEntity<List<Message>>(message, HttpStatus.OK);
	}

}
