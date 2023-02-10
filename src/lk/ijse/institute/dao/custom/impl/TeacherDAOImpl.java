package lk.ijse.institute.dao.custom.impl;

import lk.ijse.institute.dao.SQLUtil;
import lk.ijse.institute.dao.custom.TeacherDAO;
import lk.ijse.institute.entity.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/23/2023-10:03 AM
 **/
public class TeacherDAOImpl implements TeacherDAO {
    @Override
    public boolean add(Teacher entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Teacher VALUES(?,?,?,?,?,?,?,?)", entity.getT_id(), entity.getFirstName(), entity.getLastName(), entity.getAddress(), entity.getGender(), entity.getContact(), entity.getEmail(), entity.getDate_of_birth());
    }

    @Override
    public boolean update(Teacher entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Teacher SET first_name=?,last_name=?,address=?,gender=?,contact=?,email=?,date_of_birth=? WHERE t_id=?", entity.getFirstName(), entity.getLastName(), entity.getAddress(), entity.getGender(), entity.getContact(), entity.getEmail(), entity.getDate_of_birth(), entity.getT_id());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Teacher WHERE t_id=?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT t_id FROM Teacher ORDER BY t_id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("t_id");
            int newBatchId = Integer.parseInt(id.replace("T00-", "")) + 1;
            return String.format("T00-%03d", newBatchId);
        } else {
            return "T00-001";
        }
    }

    @Override
    public Teacher search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT  * FROM Teacher WHERE t_id = ?", id + "");
        rst.next();
        return new Teacher(id + "", rst.getString("first_name"), rst.getString("last_name"), rst.getString("address"), rst.getString("gender"), rst.getString("contact"), rst.getString("email"), rst.getString("date_of_birth"));
    }

    @Override
    public ArrayList<Teacher> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Teacher> allTeachers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Teacher");
        while (rst.next()) {
            Teacher teacher = new Teacher(rst.getString("t_id"), rst.getString("first_name"), rst.getString("last_name"), rst.getString("address"), rst.getString("gender"), rst.getString("contact"), rst.getString("email"), rst.getString("date_of_birth"));
            allTeachers.add(teacher);
        }
        return allTeachers;
    }

    @Override
    public int countAll(int count) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(t_id) FROM Teacher");
        if (rst.next()) {
            count = rst.getInt("COUNT(t_id)");
        }
        return count;
    }

    @Override
    public int countAllMale(int count) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(t_id) FROM Teacher WHERE gender='Male'");
        if (rst.next()) {
            count = rst.getInt("COUNT(t_id)");
        }
        return count;
    }

    @Override
    public int countAllFemale(int count) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(t_id) FROM Teacher WHERE gender='Female'");
        if (rst.next()) {
            count = rst.getInt("COUNT(t_id)");
        }
        return count;
    }

}