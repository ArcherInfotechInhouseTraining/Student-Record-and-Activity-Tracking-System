package com.arc.model;

import java.sql.Date;

public class AttendenceModel {
    private int studentId;
    private Date attendenceDate;
    private double attendence;

    public AttendenceModel() {}

    public AttendenceModel(int studentId, Date attendenceDate, double attendence) {
        this.studentId = studentId;
        this.attendenceDate = attendenceDate;
        this.attendence = attendence;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Date getAttendenceDate() {
        return attendenceDate;
    }

    public void setAttendenceDate(Date attendenceDate) {
        this.attendenceDate = attendenceDate;
    }

    public double getAttendence() {
        return attendence;
    }

    public void setAttendence(double attendence) {
        this.attendence = attendence;
    }
}
