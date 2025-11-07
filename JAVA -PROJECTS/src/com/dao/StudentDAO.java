package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.db.DBConnection;
import com.model.Department;
import com.model.Student;

public class StudentDAO {

    // -------------------- ADD STUDENT --------------------
    public void addStudent(Student s) throws SQLException {

        // Include DEPT_ID column (foreign key)
        String sql = "INSERT INTO Student(STUDENT_ID, NAME, ROLL_NO, EMAIL, DEPT_ID) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, s.getStudentId());
            ps.setString(2, s.getName());
            ps.setString(3, s.getRollNo());
            ps.setString(4, s.getEmail());
            ps.setInt(5, s.getDept().getDeptId()); // make sure Student object has Department set

            ps.executeUpdate();
            System.out.println("✅ Student added successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -------------------- VIEW ALL STUDENTS --------------------
    public List<Student> getAllStudents() throws SQLException {
        List<Student> list = new ArrayList<>();

        String sql =
            "SELECT s.STUDENT_ID, s.NAME, s.ROLL_NO, s.EMAIL, d.DEPTID, d.DEPTNAME AS DEPT_NAME " +
            "FROM Student s JOIN Department d ON s.DEPT_ID = d.DEPTID";

        try (Connection con = DBConnection.createConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("--------------------------------------------------------------------------");
            System.out.printf("%-10s %-20s %-15s %-25s %-15s%n",
                    "ID", "NAME", "ROLL_NO", "EMAIL", "DEPARTMENT");
            System.out.println("--------------------------------------------------------------------------");

            while (rs.next()) {
                // --- Read columns ---
                int id = rs.getInt("STUDENT_ID");
                String name = rs.getString("NAME");
                String roll = rs.getString("ROLL_NO");
                String email = rs.getString("EMAIL");
                String deptName = rs.getString("DEPT_NAME");

                // --- Print formatted output ---
                System.out.printf("%-10d %-20s %-15s %-25s %-15s%n",
                        id, name, roll, email, deptName);

                // --- Add to list ---
                Student s = new Student();
                s.setStudentId(id);
                s.setName(name);
                s.setRollNo(roll);
                s.setEmail(email);

                Department d = new Department();
                d.setDeptName(deptName);
                s.setDept(d);

                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // -------------------- UPDATE STUDENT --------------------
    public void updateStudent(Student s) throws SQLException {

        String sql = "UPDATE Student SET NAME = ?, ROLL_NO = ?, EMAIL = ?, DEPT_ID = ? WHERE STUDENT_ID = ?";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getName());
            ps.setString(2, s.getRollNo());
            ps.setString(3, s.getEmail());
            ps.setInt(4, s.getDept().getDeptId());
            ps.setInt(5, s.getStudentId());

            ps.executeUpdate();
            System.out.println("✅ Student updated successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -------------------- DELETE STUDENT --------------------
    public void deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM Student WHERE STUDENT_ID = ?";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("🗑️ Student deleted successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -------------------- GET STUDENT BY ID --------------------
    public Student getStudentById(int id) throws SQLException {
        String sql =
            "SELECT s.STUDENT_ID, s.NAME, s.ROLL_NO, s.EMAIL, d.DEPTNAME AS DEPT_NAME " +
            "FROM Student s JOIN Department d ON s.DEPT_ID = d.DEPTID WHERE s.STUDENT_ID = ?";

        Student s = null;

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                s = new Student();
                s.setStudentId(rs.getInt("STUDENT_ID"));
                s.setName(rs.getString("NAME"));
                s.setRollNo(rs.getString("ROLL_NO"));
                s.setEmail(rs.getString("EMAIL"));

                Department d = new Department();
                d.setDeptName(rs.getString("DEPT_NAME"));
                s.setDept(d);

                System.out.println("✅ Student found: " + s.getName());
            } else {
                System.out.println("❌ No student found with ID " + id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}
