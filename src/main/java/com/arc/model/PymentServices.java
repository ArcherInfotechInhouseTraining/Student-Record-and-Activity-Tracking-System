package com.arc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.arc.dbutil.DBConnect;

public class PymentServices {
	Connection connection = null;
	
	public PymentServices() throws SQLException {
		DBConnect dbconnect = DBConnect.getInstance();
		connection = dbconnect.getConnection();
	}
	
	private static final String INSERT_Payment_SQL = "Insert INTO payment(school_id,student_id,total_fee,fee_paid,fee_remaining) VALUES(?,?,?,?,?)";
    private static final String SELECT_Payment_BY_ID = "SELECT school_id,student_id,total_fee,fee_paid,fee_remaining where payment_id= ?";
    private static final String SELECT_ALL_Payments = "SELECT * FROM payment";
  //  private static final String SELECT_Fileterd_Payments = "SELECT * FROM payment WHERE school_id =?";
    private static final String SELECT_StudentId_add_class = "SELECT student_id FROM add_class WHERE class_name =?";
    

    
    private static final String DELETE_Payment_SQL = "DLETE FROM USER WHERE payment_id =? ";
    private static final String UPDATE_Payment_SQL = "UPDATE Payment SET fee_paid= fee_paid + ?,fee_remaining=fee_remaining - ? WHERE payment_id=?";
	private static final String SELECT_Fileterd_Payments = null;

//	public void insertUser(int school_id,int student_id,int total_fee) throws SQLException {
//    	try (
//    			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Payment_SQL)){
//             preparedStatement.setInt(1,school_id);
//             preparedStatement.setInt(2, student_id);
//             preparedStatement.setInt(3, total_fee);
//             preparedStatement.setInt(4, 0);
//             preparedStatement.setInt(5, total_fee);
//             
//              preparedStatement.executeUpdate();
//    		
//           }
//    	
//    }
	
	public void insertUser(int school_id,int student_id,int total_fee) throws SQLException {
    	try (
    			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Payment_SQL)){
             preparedStatement.setInt(1,school_id);
             preparedStatement.setInt(2, student_id);
             preparedStatement.setInt(3, total_fee);
             preparedStatement.setInt(4, 0);
             preparedStatement.setInt(5, total_fee);
             
             
              preparedStatement.executeUpdate();
    		
    		
           }
    	
    }
    
    public PaymentModel selectPayment(int school_id) throws SQLException {
        PaymentModel paymentModel = null;
        List<PaymentModel> payments = new ArrayList<>();
        
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_Payments)) {
            preparedStatement.setInt(1, school_id);
           
            ResultSet resultSet = preparedStatement.executeQuery();
        }
        return paymentModel;
    }
    
    public List<PaymentModel> selectPaymentDetails() throws SQLException {
    	 List<PaymentModel> payments = new ArrayList<>();
         
         try (
              PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_Payments)) {
            
             ResultSet resultSet = preparedStatement.executeQuery();
           while(resultSet.next()) {
          int paymentId=resultSet.getInt("payment_id");

     	   int studentId=resultSet.getInt("student_id");
     	   
      	  int total_fee=resultSet.getInt("total_fee");
      	  int fee_paid=resultSet.getInt("fee_paid");
      	  
      	  int fee_remaining = resultSet.getInt("fee_remaining");
        
    	  payments.add(new PaymentModel(paymentId,studentId,total_fee, fee_paid, fee_remaining));
         	  
           }
         }
         return payments;
    }
    
    public boolean deletePayment(int payment_id) throws SQLException {
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_Payment_SQL)) {
            preparedStatement.setInt(1, payment_id);
            return preparedStatement.executeUpdate() > 0;
        }
    }
    
    public boolean updatePayment(int sudentId, int fee_paid) throws SQLException {
        
    	
    	
    	try (
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_Payment_SQL)) {
    		preparedStatement.setDouble(1, fee_paid); // Assuming fee_paid is a double
    	    preparedStatement.setDouble(2, fee_paid); // Value to subtract from fee_remaining
    	    preparedStatement.setInt(3, sudentId); 
        	 return preparedStatement.executeUpdate() > 0;
        	 
        }
    }
    
    
	
}
