package com.cg.boot.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.Payment;
import com.cg.boot.service.IPaymentService;

@RestController
@RequestMapping("/api")
public class PaymentController {

	@Autowired
	IPaymentService service;

	/*
	 * get Payment Details by id
	 */
	@GetMapping("/getmyPayment/{paymentId}")
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

	@GetMapping("/getAllPayment")
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
	@PostMapping("/addPayment")
	public ResponseEntity<Integer> addPayment(@Valid @RequestBody Payment payment) throws DataNotFoundException {
		Payment paymentInfo = service.addPayment(payment);

		return new ResponseEntity<Integer>(paymentInfo.getPaymentId(), HttpStatus.OK);
	}

	/*
	 * Update Payment Details
	 */
	@PutMapping("/updatePayment")
	public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment) throws DataNotFoundException {
		Payment paymentInfo = service.updatePayment(payment);
		if (paymentInfo == null) {
			throw new DataNotFoundException("Payment Transaction Data Not Found for update");
		}
		return new ResponseEntity<Payment>(paymentInfo, HttpStatus.OK);
	}

	/*
	 * Delete Payment Details
	 */
	@DeleteMapping("/deletePayment/{id}")
	public ResponseEntity<List<Payment>> deletePayment(@PathVariable int id) {
		List<Payment> payment = service.deletePayment(id);
		return new ResponseEntity<List<Payment>>(payment, HttpStatus.OK);
	}

}
