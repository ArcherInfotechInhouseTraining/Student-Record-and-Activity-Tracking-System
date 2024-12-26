package com.arc.model;

public class PaymentModel {
	
	private int studentId;
	private int paymentId;
	private int total_fee;
	private int fee_paid;
	private int fee_remaining;
	
	public PaymentModel() {}
	
	public PaymentModel(int paymentId,int studentId, int total_fee, int fee_paid, int fee_remaining) {
		
		this.paymentId = paymentId;
		this.setStudentId(studentId);
		this.total_fee = total_fee;
		this.fee_paid = fee_paid;
		this.fee_remaining = fee_remaining;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public int getFee_paid() {
		return fee_paid;
	}

	public void setFee_paid(int fee_paid) {
		this.fee_paid = fee_paid;
	}

	public int getFee_remaining() {
		return fee_remaining;
	}

	public void setFee_remaining(int fee_remaining) {
		this.fee_remaining = fee_remaining;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
}
	
	
	

