package com.model;

public class Marks {
    private int markId;
    private Student student;
    private Subject subject;
    private double marksObtained;

    public Marks() {
        super();
    }

    public Marks(int markId, Student student, Subject subject, double marksObtained) {
        super();
        this.markId = markId;
        this.student = student;
        this.subject = subject;
        this.marksObtained = marksObtained;
    }

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

    public Student getStudent() {   // ✅ FIXED HERE
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public double getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(double marksObtained) {
        this.marksObtained = marksObtained;
    }

    @Override
    public String toString() {
        return "Marks [markId=" + this.markId + ", student=" + this.student + ", subject=" + this.subject
                + ", marksObtained=" + this.marksObtained + "]";
    }
}
