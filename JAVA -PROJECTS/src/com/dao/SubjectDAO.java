package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.db.DBConnection;
import com.model.Subject;

public class SubjectDAO {

    // ✅ Add Subject
    public void addSubject(Subject s) throws SQLException {
        String sql = "INSERT INTO Subject (SUBJECT_ID, SUBJECT_NAME) VALUES (?, ?)";
        
        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, s.getSubjectId());
            ps.setString(2, s.getSubjectName());
            ps.executeUpdate();
            
            System.out.println("✅ Subject added successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ View All Subjects
    public List<Subject> getAllSubject() throws SQLException {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM Subject"; // 🟢 Correct SQL

        try (Connection con = DBConnection.createConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt("SUBJECT_ID"));    // 🟢 Correct column names
                s.setSubjectName(rs.getString("SUBJECT_NAME"));
                list.add(s);
            }

            System.out.println("✅ Subjects fetched successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ✅ Update Subject
    public void updateSubject(Subject s) throws SQLException {
        String sql = "UPDATE Subject SET SUBJECT_NAME = ? WHERE SUBJECT_ID = ?"; // 🟢 Fixed SQL

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, s.getSubjectName());
            ps.setInt(2, s.getSubjectId());
            ps.executeUpdate();

            System.out.println("✅ Subject updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Delete Subject
    public void deleteSubject(int id) throws SQLException {
        String sql = "DELETE FROM Subject WHERE SUBJECT_ID = ?"; // 🟢 Fixed spelling (was Suject)

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("✅ Subject deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Get Subject By ID
    public static Subject getSubjectById(int id) throws SQLException {
        Subject s = null;
        String sql = "SELECT * FROM Subject WHERE SUBJECT_ID = ?";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                s = new Subject();
                s.setSubjectId(rs.getInt("SUBJECT_ID"));
                s.setSubjectName(rs.getString("SUBJECT_NAME"));
                System.out.println("✅ Subject found successfully");
            } else {
                System.out.println("⚠️ No subject found with ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }
}
