package lk.ijse.institute.dao.custom.impl;

import lk.ijse.institute.dao.SQLUtil;
import lk.ijse.institute.dao.custom.StudentMarksDAO;
import lk.ijse.institute.entity.StudentMark;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/23/2023-10:02 AM
 **/
public class StudentMarksDAOImpl implements StudentMarksDAO {
    @Override
    public boolean add(StudentMark entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Student_Marks VALUES(?,?,?,?,?)", entity.getStd_id(), entity.getStd_name(), entity.getSub_id(), entity.getSub_name(), entity.getMarks());
    }

    @Override
    public boolean update(StudentMark entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Student_Marks SET std_name=?,sub_id=?,sub_name=?,marks=? WHERE std_id=?", entity.getStd_name(), entity.getSub_id(), entity.getSub_name(), entity.getMarks(), entity.getStd_id());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Student_Marks WHERE std_id=?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public StudentMark search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM student_marks WHERE std_id=?", id + "");
        rst.next();
        return new StudentMark(id + "", rst.getString("std_name"), rst.getString("sub_id"), rst.getString("sub_name"), rst.getDouble("marks"));
    }

    @Override
    public ArrayList<StudentMark> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

}