package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.DBConnection;
import com.model.Department;

public class DepartmentDAO {

    public void addDepartment(Department d) throws SQLException {
        String sql = "INSERT INTO Department (DEPTID, DEPTNAME, LOCATION) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, d.getDeptId());
            ps.setString(2, d.getDeptName());
            ps.setString(3, d.getLocation());
            ps.executeUpdate();

            System.out.println("✅ Department added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void updateDepartment(Department d) throws SQLException {
        String sql = "UPDATE Department SET DEPTNAME = ?, LOCATION = ? WHERE DEPTID = ?";
        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, d.getDeptName());
            ps.setString(2, d.getLocation());
            ps.setInt(3, d.getDeptId());
            ps.executeUpdate();

            System.out.println("✅ Department updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDepartment(int id) throws SQLException {
        String sql = "DELETE FROM Department WHERE DEPTID = ?";
        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("✅ Department deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Department> getAllDepartments() throws SQLException {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM Department";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Department d = new Department();
                d.setDeptId(rs.getInt("DEPTID"));
                d.setDeptName(rs.getString("DEPTNAME"));
                d.setLocation(rs.getString("LOCATION"));
                list.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Department getDepartmentById(int deptId) {
        Department dept = null;
        String sql = "SELECT * FROM Department WHERE DEPTID = ?";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, deptId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dept = new Department();
                    dept.setDeptId(rs.getInt("DEPTID"));
                    dept.setDeptName(rs.getString("DEPTNAME"));
                    dept.setLocation(rs.getString("LOCATION"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dept;
    }

}
