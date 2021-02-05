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
import com.cg.boot.model.Payment;
import com.cg.boot.service.IPaymentService;

@RestController
@RequestMapping("/api")
public class StudentPaymentController {

	@Autowired
	IPaymentService service;

	/*
	 * get Payment Details by id
	 */
	@GetMapping("/getStudentPayment/{paymentId}")
	public ResponseEntity<Payment> getPayment(@PathVariable("paymentId") int paymentId) throws DataNotFoundException {
		Payment payment = service.getPayment(paymentId);
		if (payment == null) {
			throw new DataNotFoundException("Payment Transaction Not Found By these ID");
		}
		return new ResponseEntity<Payment>(payment, HttpStatus.OK);
	}
	/*
	 * Get all Payment Details
	 */

	@GetMapping("/getAllStudentPayment")
	public ResponseEntity<List<Payment>> getPayment() throws DataNotFoundException {
		List<Payment> payment = service.getAllPayment();
		if (payment.isEmpty()) {
			throw new DataNotFoundException("Payment Transaction Not Found");
		}
		return new ResponseEntity<List<Payment>>(payment, HttpStatus.OK);
	}

	/*
	 * 
	 * Add Payment Details
	 */
	@PostMapping("/addPaymentByStudent")
	public ResponseEntity<Integer> addPayment(@Valid @RequestBody Payment payment) throws DataNotFoundException {
		Payment paymentInfo = service.addPayment(payment);

		return new ResponseEntity<Integer>(paymentInfo.getPaymentId(), HttpStatus.OK);
	}

	
}
