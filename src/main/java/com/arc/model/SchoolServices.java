package com.arc.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.arc.dbutil.DBConnect;

public class SchoolServices {

    Connection con = null;

    // Constructor: Establish Database Connection
    public SchoolServices() throws SQLException {
        DBConnect dbconnect = DBConnect.getInstance();
        con = dbconnect.getConnection();
    }

    // SQL Queries
    private static final String INSERT_SCHOOL_SQL = "INSERT INTO add_school (school_name) VALUES (?)";
    private static final String SELECT_SCHOOL_BY_ID = "SELECT school_id, school_name FROM add_school WHERE school_id = ?";
    private static final String SELECT_ALL_SCHOOLS = "SELECT * FROM add_school";
    private static final String DELETE_SCHOOL_SQL = "DELETE FROM add_school WHERE school_id = ?";
    private static final String UPDATE_SCHOOL_SQL = "UPDATE add_school SET school_name = ? WHERE school_id = ?";

    // Insert a new school
    public void insertSchool(SchoolModel school) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_SCHOOL_SQL)) {
            preparedStatement.setString(1, school.getSchoolName());

            preparedStatement.executeUpdate();
        }
    }

    // Retrieve a school by ID
    public SchoolModel selectSchool(int id) throws SQLException {
        SchoolModel school = null;
        try (PreparedStatement stmt = con.prepareStatement(SELECT_SCHOOL_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("school_name");

                school = new SchoolModel(id, name);
            }
        }
        return school;
    }

    // Retrieve all schools
    public List<SchoolModel> selectAllSchools() throws SQLException {
        List<SchoolModel> schools = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_SCHOOLS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("school_id");
                String name = resultSet.getString("school_name");

                schools.add(new SchoolModel(id, name));
            }
        }
        return schools;
    }

    // Delete a school by ID
    public boolean deleteSchool(int id) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(DELETE_SCHOOL_SQL)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    // Update an existing school
    public boolean updateSchool(SchoolModel school) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_SCHOOL_SQL)) {
            preparedStatement.setString(1, school.getSchoolName());

            preparedStatement.setInt(2, school.getSchoolId());
            return preparedStatement.executeUpdate() > 0;
        }
    }
}
