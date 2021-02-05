package com.cg.boot.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@SuppressWarnings("serial")
@Entity
@Table(name="Payment_master")
public class Payment implements Serializable{

	@Id
	@GeneratedValue
	@Column(name = "payment_Id")
	private int paymentId;
	
	@NotNull(message="Enter valid Fee Details")
	@Min(value=1,message="Enter valid Fee Details")
	private double fee;
	
	
	@Column(name="payment_Type")
	private String paymentType;
	
	
	@Column(name="account_No")
	private long accountNo;
	
	
	@Column(name="payment_Status")
	private String paymentStatus;
	
	@NotNull(message = "Enter valid student ID")
	@Min(value=1,message = "Enter valid student ID")
	@Column(name="student_Id")
	private int StudentId;
	
	@NotNull(message = "Enter valid Admin ID")
	@Min(value=1,message = "Enter valid Admin ID")
	@Column(name="admin_Id")
	private int adminId;
	
	Payment(){
		
	}
	public Payment(int paymentId, double fee, String paymentType, long accountNo, String paymentStatus, int studentId,
			int adminId) {
		super();
		this.paymentId = paymentId;
		this.fee = fee;
		this.paymentType = paymentType;
		this.accountNo = accountNo;
		this.paymentStatus = paymentStatus;
		StudentId = studentId;
		this.adminId = adminId;
	}
	public Payment(double fee, String paymentType, long accountNo, String paymentStatus, int studentId, int adminId) {
		super();
		this.fee = fee;
		this.paymentType = paymentType;
		this.accountNo = accountNo;
		this.paymentStatus = paymentStatus;
		StudentId = studentId;
		this.adminId = adminId;
	}
	
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(long fee) {
		this.fee = fee;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public int getStudentId() {
		return StudentId;
	}
	public void setStudentId(int studentId) {
		StudentId = studentId;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", fee=" + fee + ", paymentType=" + paymentType + ", accountNo="
				+ accountNo + ", paymentStatus=" + paymentStatus + ", StudentId=" + StudentId + ", adminId=" + adminId
				+ "]";
	}
	
	
}
