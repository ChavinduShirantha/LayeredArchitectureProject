package lk.ijse.institute.dao.custom.impl;

import lk.ijse.institute.dao.SQLUtil;
import lk.ijse.institute.dao.custom.StudentDAO;
import lk.ijse.institute.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/19/2023-9:23 AM
 **/
public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean add(Student entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Student VALUES(?,?,?,?,?,?,?,?)", entity.getStd_id(), entity.getFirstName(), entity.getLastName(), entity.getAddress(), entity.getGender(), entity.getContact(), entity.getEmail(), entity.getDate_of_birth());
    }

    @Override
    public boolean update(Student entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Student SET first_name=?,last_name=?,address=?,gender=?,contact=?,email=?,date_of_birth=? WHERE std_id=?", entity.getFirstName(), entity.getLastName(), entity.getAddress(), entity.getGender(), entity.getContact(), entity.getEmail(), entity.getDate_of_birth(), entity.getStd_id());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Student WHERE std_id=?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT std_id FROM Student ORDER BY std_id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("std_id");
            int newBatchId = Integer.parseInt(id.replace("S00-", "")) + 1;
            return String.format("S00-%03d", newBatchId);
        } else {
            return "S00-001";
        }
    }

    @Override
    public Student search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT  * FROM Student WHERE std_id = ?", id + "");
        rst.next();
        return new Student(id + "", rst.getString("first_name"), rst.getString("last_name"), rst.getString("address"), rst.getString("gender"), rst.getString("contact"), rst.getString("email"), rst.getString("date_of_birth"));
    }

    @Override
    public ArrayList<Student> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Student> allStudents = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Student");
        while (rst.next()) {
            Student student = new Student(rst.getString("std_id"), rst.getString("first_name"), rst.getString("last_name"), rst.getString("address"), rst.getString("gender"), rst.getString("contact"), rst.getString("email"), rst.getString("date_of_birth"));
            allStudents.add(student);
        }
        return allStudents;
    }

    @Override
    public int countAll(int count) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(std_id) FROM Student");
        if (rst.next()) {
            count = rst.getInt("COUNT(std_id)");
        }
        return count;
    }

    @Override
    public int countAllMale(int count) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(std_id) FROM Student WHERE gender='Male'");
        if (rst.next()) {
            count = rst.getInt("COUNT(std_id)");
        }
        return count;
    }

    @Override
    public int countAllFemale(int count) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(std_id) FROM Student WHERE gender='Female'");
        if (rst.next()) {
            count = rst.getInt("COUNT(std_id)");
        }
        return count;
    }

}