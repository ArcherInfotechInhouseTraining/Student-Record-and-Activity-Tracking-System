package com.arc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.arc.dbutil.DBConnect;

public class ClassServices {
	
	Connection connection = null;
	
	public ClassServices() throws SQLException
	{
		DBConnect dbconnect = DBConnect.getInstance();
		connection = dbconnect.getConnection();
		System.out.println("Connection SuccessFully......");
	}
	
	private static final String INSERT_CLASS="INSERT INTO add_class(class_name,school_id,class_fee)values(?,?,?)";
	private static final String SELECT_ALL_CLASS="SELECT * FROM add_class";
	private static final String SELECT_CLASS_BY_ID = "SELECT school_id, class_name ,class_fee from add_class Where class_id =?";
	private static final String UPDATE_CLASS ="UPDATE add_class SET class_name=?, class_fee=? WHERE class_id=?";
	private static final String DELETE_CLASS ="DELETE FROM add_class WHERE class_id=?";
	
	public void insertClass(ClassModel classmodel) throws SQLException
	{
		try(PreparedStatement ptmt = connection.prepareStatement(INSERT_CLASS)){
			ptmt.setString(1, classmodel.getClassName());
			ptmt.setInt(2, classmodel.getSchoolId());
			ptmt.setInt(3, classmodel.getClassFees());
			ptmt.executeUpdate();
		}
//		System.out.println("Data Inserted Successfully...");
	}
	
	public List<ClassModel> selectAllClass() throws SQLException
	{
		List<ClassModel> classModelList = new ArrayList<>();
		try(PreparedStatement ptmt = connection.prepareStatement(SELECT_ALL_CLASS))
		{
			ResultSet resultSet = ptmt.executeQuery();
			while(resultSet.next())
			{
				int classId = resultSet.getInt("class_id");
				int schoolId=resultSet.getInt("school_id");
				String className = resultSet.getString("class_name");  
				int classFees = resultSet.getInt("class_fee");

				classModelList.add(new ClassModel(classId,schoolId,className,classFees));
			}
		}
		return classModelList;
	}
	
	public ClassModel selectClassByID(int classId) throws SQLException
	{
		ClassModel classmodel = null;
		try(PreparedStatement ptmt = connection.prepareStatement(SELECT_CLASS_BY_ID))
		{
			ptmt.setInt(1, classId);
			ResultSet resultSet = ptmt.executeQuery();
			if(resultSet.next())
			{
				  int schoolId = resultSet.getInt("school_id");
				  String className = resultSet.getString("class_name");
	              int classFees =resultSet.getInt("class_fee");
	              classmodel = new ClassModel(classId,schoolId,className,classFees);
			}
		}
		return classmodel;
	}
	
	public boolean updateClassForm(ClassModel classmodel) throws SQLException
	{
		try(PreparedStatement ptmt = connection.prepareStatement(UPDATE_CLASS)){
			ptmt.setString(1, classmodel.getClassName());
			ptmt.setInt(2, classmodel.getClassFees());
			ptmt.setInt(3, classmodel.getClassId());
			return ptmt.executeUpdate()>0;
		}
	}
	
	public boolean deleteClassRecord(int classId) throws SQLException
	{
		try(PreparedStatement ptmt = connection.prepareStatement(DELETE_CLASS))
		{
			ptmt.setInt(1, classId);
			return ptmt.executeUpdate()>0;
		}
	}
}