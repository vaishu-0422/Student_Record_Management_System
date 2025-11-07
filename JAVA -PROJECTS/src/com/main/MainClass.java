package com.main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.dao.DepartmentDAO;
import com.dao.MarksDAO;
import com.dao.StudentDAO;
import com.dao.SubjectDAO;
import com.model.Department;
import com.model.Marks;
import com.model.Student;
import com.model.Subject;

public class MainClass {

    public static void main(String[] args) throws SQLException {

        try (Scanner sc = new Scanner(System.in)) {

            StudentDAO studentdao = new StudentDAO();
            SubjectDAO subjectdao = new SubjectDAO();
            MarksDAO marksdao = new MarksDAO();
            DepartmentDAO departmentdao = new DepartmentDAO();

            int choice;

            do {
                System.out.println("\n***** STUDENT RECORD MANAGEMENT SYSTEM *****");
                System.out.println("1.  Add Student");
                System.out.println("2.  View All Students");
                System.out.println("3.  Update Student");
                System.out.println("4.  Delete Student");
                System.out.println("5.  Get Student Information By Id");
                System.out.println("6.  Add Subject");
                System.out.println("7.  View All Subjects");
                System.out.println("8.  Update Subject");
                System.out.println("9.  Delete Subject");
                System.out.println("10. Get Subject Information By Id");
                System.out.println("11. Add Marks");
                System.out.println("12. View All Marks");
                System.out.println("13. Update Marks");
                System.out.println("14. Delete Marks");
                System.out.println("15. Get Marks Information By Id");
                System.out.println("16. Add Department");
                System.out.println("17. View All Departments");
                System.out.println("18. Update Department");
                System.out.println("19. Delete Department");
                System.out.println("20. Get Department Information By Id");
                System.out.println("0.  Exit");
                System.out.print("Enter your choice: ");

                choice = sc.nextInt();
                sc.nextLine(); // ✅ consume newline

                switch (choice) {

                    // ------------------ STUDENT SECTION ------------------
                    case 1:
                        System.out.print("Enter Student Id: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Student Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Roll No: ");
                        String roll = sc.nextLine();
                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();

                        Student student = new Student(id, name, roll, email, null, null, null);
                        studentdao.addStudent(student);
                        break;

                    case 2:
                        studentdao.getAllStudents();
                        break;

                    case 3:
                        System.out.print("Enter Student Id to update: ");
                        int id1 = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter New Student Name: ");
                        String name1 = sc.nextLine();

                        System.out.print("Enter New Roll No: ");
                        String roll1 = sc.nextLine();

                        System.out.print("Enter New Email: ");
                        String email1 = sc.nextLine();

                        System.out.print("Enter Department ID: ");
                        int deptId = sc.nextInt();

                        Student s = new Student();
                        s.setStudentId(id1);
                        s.setName(name1);
                        s.setRollNo(roll1);
                        s.setEmail(email1);

                        Department d = new Department();
                        d.setDeptId(deptId);
                        s.setDept(d);

                        studentdao.updateStudent(s);
                        break;

                    case 4:
                        System.out.print("Enter Student Id to delete: ");
                        int stuid = sc.nextInt();
                        studentdao.deleteStudent(stuid);
                        break;

                    case 5:
                        System.out.print("Enter Student Id to view details: ");
                        int stuid2 = sc.nextInt();
                        studentdao.getStudentById(stuid2);
                        break;

                    // ------------------ SUBJECT SECTION ------------------
                    case 6:
                        System.out.print("Enter Subject Id: ");
                        int subId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Subject Name: ");
                        String subName = sc.nextLine();
                        subjectdao.addSubject(new Subject(subId, subName));
                        break;

                    case 7:
                        // ✅ FIXED: display subject list neatly
                        List<Subject> subjects = subjectdao.getAllSubject();

                        if (subjects.isEmpty()) {
                            System.out.println("⚠️ No subjects found.");
                        } else {
                            System.out.println("\n--- All Subjects -----");
                            System.out.printf("%-10s %-20s%n", "ID", "Name");
                            System.out.println("----------------------------");
                            for (Subject sub : subjects) {
                                System.out.printf("%-10d %-20s%n", sub.getSubjectId(), sub.getSubjectName());
                            }
                        }
                        break;

                    case 8:
                        System.out.print("Enter Subject Id to update: ");
                        int upSubId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter New Subject Name: ");
                        String newSubName = sc.nextLine();
                        subjectdao.updateSubject(new Subject(upSubId, newSubName)); // ✅ instance call
                        break;

                    case 9:
                        System.out.print("Enter Subject Id to delete: ");
                        int delSubId = sc.nextInt();
                        subjectdao.deleteSubject(delSubId);
                        break;

                    case 10:
                        System.out.print("Enter Subject Id to view details: ");
                        int viewSubId = sc.nextInt();
                        Subject subjectFound = SubjectDAO.getSubjectById(viewSubId);
                        if (subjectFound != null) {
                            System.out.println("✅ Subject Found: ID=" + subjectFound.getSubjectId() +
                                    ", Name=" + subjectFound.getSubjectName());
                        }
                        break;

                    // ------------------ MARKS SECTION ------------------
                    case 11:
                        System.out.print("Enter Marks Id: ");
                        int mid = sc.nextInt();
                        System.out.print("Enter Marks Obtained: ");
                        double marksObtained = sc.nextDouble();
                        Marks marks = new Marks(mid, null, null, marksObtained);
                        MarksDAO.addMarks(marks);
                        break;

                    case 12:
                        System.out.println(" DATABASE CONNECTION VERIFIED ✅");
                        List<Marks> marksList = marksdao.getAllMarks();

                        if (marksList.isEmpty()) {
                            System.out.println("⚠️ No marks found in database.");
                        } else {
                            System.out.println("\n---- All Marks ----");
                            System.out.printf("%-10s %-12s %-12s %-12s%n", "MarkID", "StudentID", "SubjectID", "Marks");
                            System.out.println("-------------------------------------------------");
                            for (Marks m : marksList) {
                                System.out.printf("%-10d %-12d %-12d %-12.2f%n",
                                        m.getMarkId(),
                                        m.getStudent().getStudentId(),
                                        m.getSubject().getSubjectId(),
                                        m.getMarksObtained());
                            }
                        }
                        break;


                    case 13:
                        System.out.print("Enter Marks Id to update: ");
                        int umid = sc.nextInt();
                        System.out.print("Enter New Obtained Marks: ");
                        double newMarks = sc.nextDouble();
                        Marks updatedMarks = new Marks(umid, null, null, newMarks);
                        MarksDAO.updateMarks(updatedMarks);
                        break;


                    case 14:
                        System.out.print("Enter Marks Id to delete: ");
                        int delMid = sc.nextInt();
                        MarksDAO.deleteMarks(delMid);
                        break;

                    case 15:
                        System.out.print("Enter Marks Id to view details: ");
                        int viewMid = sc.nextInt();
                        MarksDAO.getMarksById(viewMid);
                        break;

                    // ------------------ DEPARTMENT SECTION ------------------
                    case 16:                        System.out.print("Enter Department Id: ");
                        int did = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Department Name: ");
                        String deptName = sc.nextLine();
                        
                        System.out.print("Enter location : ");
                        String location = sc.nextLine();
                        
                        departmentdao.addDepartment(new Department(did, deptName,location));
                        break;

                    case 17:
                        System.out.println(" DATABASE CONNECTION VERIFIED ✅");
                        List<Department> deptList = departmentdao.getAllDepartments();

                        if (deptList.isEmpty()) {
                            System.out.println("⚠️ No departments found in database.");
                        } else {
                            System.out.println("\n---- All Departments ----");
                            System.out.printf("%-10s %-20s%n", "Dept ID", "Dept Name");
                            System.out.println("------------------------------------");
                            for (Department d1 : deptList) {
                                System.out.printf("%-10d %-20s%n", d1.getDeptId(), d1.getDeptName());
                            }
                        }
                        break;


                    case 18:
                        System.out.print("Enter Department Id to update: ");
                        int udid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter New Department Name: ");
                        String newDeptName = sc.nextLine();
                        
                        System.out.print("Enter location : ");
                        String location1 = sc.nextLine();
                        
                        departmentdao.updateDepartment(new Department(udid, newDeptName, location1));
                        break;

                    case 19:
                        System.out.print("Enter Department Id to delete: ");
                        int ddid = sc.nextInt();
                        departmentdao.deleteDepartment(ddid);
                        break;

                    case 20:
                        System.out.print("Enter Department Id to view details: ");
                        int deptId1 = sc.nextInt();
                        Department d2 = departmentdao.getDepartmentById(deptId1);

                        if (d2 != null) {
                            System.out.println("\n--- Department Details ---");
                            System.out.println("ID: " + d2.getDeptId());
                            System.out.println("Name: " + d2.getDeptName());
                            System.out.println("Location: " + d2.getLocation());
                        } else {
                            System.out.println("⚠️ No department found with ID: " + deptId1);
                        }
                        break;

                    // ------------------ EXIT ------------------
                    case 0:
                        System.out.println("👋 Exiting... Thank you!");
                        break;

                    default:
                        System.out.println("❌ Invalid choice! Please try again.");
                }

            } while (choice != 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
