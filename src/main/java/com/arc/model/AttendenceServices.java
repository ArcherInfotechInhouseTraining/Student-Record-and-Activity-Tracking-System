package com.arc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.arc.dbutil.DBConnect;

public class AttendenceServices {

    private Connection connection = null;

    public AttendenceServices() throws SQLException {
        DBConnect dbconnect = DBConnect.getInstance();
        connection = dbconnect.getConnection();
        System.out.println("Connection Successfully Established...");
    }

    private static final String INSERT_ATTENDANCE = "INSERT INTO attendence(student_id, attendence_date, attendence) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_ATTENDANCE = "SELECT * FROM attendence";
    private static final String SELECT_ATTENDANCE_BY_ID = "SELECT * FROM attendence WHERE student_id = ?";
    private static final String UPDATE_ATTENDANCE = "UPDATE attendence SET attendence_date = ?, attendence = ? WHERE student_id = ?";
    private static final String DELETE_ATTENDANCE = "DELETE FROM attendence WHERE student_id = ? AND attendence_date = ?";
    private static final String CHECK_STUDENT_ID = "SELECT COUNT(*) FROM attendence WHERE student_id = ?";

    // Insert a new attendance record
    public void insertAttendance(AttendenceModel attendanceModel) throws SQLException {
        try (PreparedStatement ptmt = connection.prepareStatement(INSERT_ATTENDANCE)) {
            ptmt.setInt(1, attendanceModel.getStudentId());
            ptmt.setDate(2, attendanceModel.getAttendenceDate());
            ptmt.setDouble(3, attendanceModel.getAttendence());
            ptmt.executeUpdate();
        }
    }

    // Retrieve all attendance records
    public List<AttendenceModel> selectAllAttendance() throws SQLException {
        List<AttendenceModel> attendanceList = new ArrayList<>();
        try (PreparedStatement ptmt = connection.prepareStatement(SELECT_ALL_ATTENDANCE)) {
            ResultSet resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                Date attendanceDate = resultSet.getDate("attendence_date");
                Double attendancePercentage = resultSet.getDouble("attendence");
                attendanceList.add(new AttendenceModel(studentId, attendanceDate, attendancePercentage));
            }
        }
        return attendanceList;
    }

    // Retrieve attendance by student ID
    public List<AttendenceModel> selectAttendanceById(int studentId) throws SQLException {
        List<AttendenceModel> attendanceList = new ArrayList<>();
        if (!isStudentIdPresent(studentId)) {
            System.out.println("Error: Student ID " + studentId + " is not present in the database.");
            return attendanceList;
        }
        try (PreparedStatement ptmt = connection.prepareStatement(SELECT_ATTENDANCE_BY_ID)) {
            ptmt.setInt(1, studentId);
            ResultSet resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                Date attendanceDate = resultSet.getDate("attendence_date");
                Double attendancePercentage = resultSet.getDouble("attendence");
                attendanceList.add(new AttendenceModel(studentId, attendanceDate, attendancePercentage));
            }
        }
        return attendanceList;
    }

    // Update attendance record
    public boolean updateAttendance(AttendenceModel attendanceModel) throws SQLException {
        if (!isStudentIdPresent(attendanceModel.getStudentId())) {
            System.out.println("Error: Student ID " + attendanceModel.getStudentId() + " is not present in the database.");
            return false;
        }
        try (PreparedStatement ptmt = connection.prepareStatement(UPDATE_ATTENDANCE)) {
            ptmt.setDate(1, attendanceModel.getAttendenceDate());
            ptmt.setDouble(2, attendanceModel.getAttendence());
            ptmt.setInt(3, attendanceModel.getStudentId());
            return ptmt.executeUpdate() > 0;
        }
    }

    // Delete attendance record
    public boolean deleteAttendance(int studentId, Date attendanceDate) throws SQLException {
        if (!isStudentIdPresent(studentId)) {
            System.out.println("Error: Student ID " + studentId + " is not present in the database.");
            return false;
        }
        try (PreparedStatement ptmt = connection.prepareStatement(DELETE_ATTENDANCE)) {
            ptmt.setInt(1, studentId);
            ptmt.setDate(2, attendanceDate);
            return ptmt.executeUpdate() > 0;
        }
    }

    // Check if the student ID exists in the database
    private boolean isStudentIdPresent(int studentId) throws SQLException {
        try (PreparedStatement ptmt = connection.prepareStatement(CHECK_STUDENT_ID)) {
            ptmt.setInt(1, studentId);
            ResultSet resultSet = ptmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }
        return false;
    }
}
