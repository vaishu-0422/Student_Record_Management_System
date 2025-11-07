package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.DBConnection;
import com.model.Marks;
import com.model.Student;
import com.model.Subject;

public class MarksDAO {

    // ✅ Add Marks
    public static void addMarks(Marks m) throws SQLException {
        String sql = "INSERT INTO Marks (MARK_ID, STUDENT_ID, SUBJECT_ID, MARKS_OBTAINED) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, m.getMarkId());
            ps.setInt(2, m.getStudent().getStudentId());   // assumes Marks has Student object
            ps.setInt(3, m.getSubject().getSubjectId());   // assumes Marks has Subject object
            ps.setDouble(4, m.getMarksObtained());

            ps.executeUpdate();
            System.out.println("✅ Marks added successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ View All Marks
    public List<Marks> getAllMarks() {
        List<Marks> marksList = new ArrayList<>();
        try {
            Connection con = DBConnection.createConnection();
            String sql = "SELECT * FROM Marks";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Marks mark = new Marks();
                Student student = new Student();
                Subject subject = new Subject();

                mark.setMarkId(rs.getInt("MARK_ID"));
                student.setStudentId(rs.getInt("STUDENT_ID"));
                subject.setSubjectId(rs.getInt("SUBJECT_ID"));
                mark.setMarksObtained(rs.getDouble("MARKS_OBTAINED"));

                mark.setStudent(student);
                mark.setSubject(subject);

                marksList.add(mark);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return marksList;
    }

    // ✅ Update Marks
    public static void updateMarks(Marks m) throws SQLException {
        String sql = "UPDATE Marks SET STUDENT_ID=?, SUBJECT_ID=?, MARKS_OBTAINED=? WHERE MARK_ID=?";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, m.getStudent().getStudentId());
            ps.setInt(2, m.getSubject().getSubjectId());
            ps.setDouble(3, m.getMarksObtained());
            ps.setInt(4, m.getMarkId());

            int rows = ps.executeUpdate();
            if (rows > 0) {
				System.out.println("✅ Marks updated successfully");
			} else {
				System.out.println("⚠️ No record found with ID " + m.getMarkId());
			}

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Delete Marks
    public static void deleteMarks(int id) throws SQLException {
        String sql = "DELETE FROM Marks WHERE MARK_ID=?";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
				System.out.println("✅ Marks deleted successfully");
			} else {
				System.out.println("⚠️ No record found with ID " + id);
			}

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Get Marks by ID
    public static Marks getMarksById(int id) throws SQLException {
        Marks m = null;
        String sql = "SELECT * FROM Marks WHERE MARK_ID=?";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                m = new Marks();
                m.setMarkId(rs.getInt("MARK_ID"));
                m.setStudent(null);
                m.setSubject(null);
                m.setMarksObtained(rs.getDouble("MARKS_OBTAINED"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return m;
    }
}
