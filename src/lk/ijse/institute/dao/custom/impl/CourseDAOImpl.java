package lk.ijse.institute.dao.custom.impl;

import lk.ijse.institute.dao.SQLUtil;
import lk.ijse.institute.dao.custom.CourseDAO;
import lk.ijse.institute.entity.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/23/2023-10:00 AM
 **/
public class CourseDAOImpl implements CourseDAO {
    @Override
    public boolean add(Course entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Course VALUES(?,?,?,?,?)", entity.getCourse_id(), entity.getCourse_name(), entity.getCourse_fee(), entity.getSub_id(), entity.getSub_name());
    }

    @Override
    public boolean update(Course entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Course SET name=?,coursefee=?,sub_id=?,sub_name=? WHERE c_id=?", entity.getCourse_name(), entity.getCourse_fee(), entity.getSub_id(), entity.getSub_name(), entity.getCourse_id());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Course WHERE c_id=?", id);

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT c_id FROM Course ORDER BY c_id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("c_id");
            int newBatchId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newBatchId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public Course search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT  * FROM Course WHERE c_id = ?", id + "");
        rst.next();
        return new Course(id + "", rst.getString("name"), rst.getDouble("coursefee"), rst.getString("sub_id"), rst.getString("sub_name"));
    }

    @Override
    public ArrayList<Course> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Course> allCourses = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT  * FROM Course");
        while (rst.next()) {
            Course course = new Course(rst.getString("c_id"), rst.getString("name"), rst.getDouble("coursefee"), rst.getString("sub_id"), rst.getString("sub_name"));
            allCourses.add(course);
        }
        return allCourses;
    }

}
