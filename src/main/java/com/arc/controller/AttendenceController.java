package com.arc.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arc.model.AttendenceModel;
import com.arc.model.AttendenceServices;

@WebServlet("/AttendenceController")
public class AttendenceController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AttendenceServices attendenceServices;

    public AttendenceController() {
        try {
            attendenceServices = new AttendenceServices();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null || action.trim().isEmpty()) {
                response.getWriter().append("Invalid or Missing Action");
                return;
            }

            switch (action) {
                case "view":
                    viewAllAttendance(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteAttendance(request, response);
                    break;
                default:
                    response.getWriter().append("Invalid Action");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null || action.trim().isEmpty()) {
                response.getWriter().append("Invalid or Missing Action");
                return;
            }

            switch (action) {
                case "add":
                    addAttendance(request, response);
                    break;
                case "update":
                    updateAttendance(request, response);
                    break;
                default:
                    response.getWriter().append("Invalid Action");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void addAttendance(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String studentIdParam = request.getParameter("student_id");
        String attendanceDateParam = request.getParameter("attendence_date");
        String attendanceParam = request.getParameter("attendence");

        if (isAnyNullOrEmpty(studentIdParam, attendanceDateParam, attendanceParam)) {
            response.getWriter().append("Invalid or Missing Parameters for Adding Attendance");
            return;
        }

        int studentId = Integer.parseInt(studentIdParam);
        Date attendanceDate = Date.valueOf(attendanceDateParam);
        Double attendance = Double.parseDouble(attendanceParam);

        AttendenceModel attendenceModel = new AttendenceModel(studentId, attendanceDate, attendance);
        attendenceServices.insertAttendance(attendenceModel);

        response.sendRedirect("AttendenceController?action=view");
    }

    private void viewAllAttendance(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	List<AttendenceModel> attendenceList = attendenceServices.selectAllAttendance();
    	request.setAttribute("attendenceList", attendenceList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/attendence.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String studentIdParam = request.getParameter("student_id");

        if (isNullOrEmpty(studentIdParam)) {
            response.getWriter().append("Invalid or Missing Student ID for Editing");
            return;
        }

        int studentId = Integer.parseInt(studentIdParam);
        List<AttendenceModel> attendance = attendenceServices.selectAttendanceById(studentId);

        request.setAttribute("attendence", attendance);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/attendence.jsp");
        dispatcher.forward(request, response);
    }

    private void updateAttendance(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String studentIdParam = request.getParameter("student_id");
        String attendanceDateParam = request.getParameter("attendence_date");
        String attendanceParam = request.getParameter("attendence");

        if (isAnyNullOrEmpty(studentIdParam, attendanceDateParam, attendanceParam)) {
            response.getWriter().append("Invalid or Missing Parameters for Updating Attendance");
            return;
        }

        int studentId = Integer.parseInt(studentIdParam);
        Date attendanceDate = Date.valueOf(attendanceDateParam);
        Double attendance = Double.parseDouble(attendanceParam);

        AttendenceModel attendanceModel = new AttendenceModel(studentId, attendanceDate, attendance);
        attendenceServices.updateAttendance(attendanceModel);

        response.sendRedirect("AttendenceController?action=view");
    }

    private void deleteAttendance(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String studentIdParam = request.getParameter("student_id");
        String attendanceDateParam = request.getParameter("attendence_date");

        if (isAnyNullOrEmpty(studentIdParam, attendanceDateParam)) {
            response.getWriter().append("Invalid or Missing Parameters for Deleting Attendance");
            return;
        }

        int studentId = Integer.parseInt(studentIdParam);
        Date attendanceDate = Date.valueOf(attendanceDateParam);

        attendenceServices.deleteAttendance(studentId, attendanceDate);

        response.sendRedirect("AttendenceController?action=view");
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private boolean isAnyNullOrEmpty(String... strings) {
        for (String str : strings) {
            if (isNullOrEmpty(str)) {
                return true;
            }
        }
        return false;
    }
}
