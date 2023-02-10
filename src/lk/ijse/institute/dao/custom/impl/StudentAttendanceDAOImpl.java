package lk.ijse.institute.dao.custom.impl;

import lk.ijse.institute.dao.SQLUtil;
import lk.ijse.institute.dao.custom.StudentAttendanceDAO;
import lk.ijse.institute.entity.StudentAttendance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/23/2023-10:02 AM
 **/
public class StudentAttendanceDAOImpl implements StudentAttendanceDAO {
    @Override
    public boolean add(StudentAttendance entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Student_Attendence VALUES(?,?,?,?)", entity.getStd_id(), entity.getStd_name(), entity.getDate(), entity.getAttendance());
    }

    @Override
    public boolean update(StudentAttendance entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Student_Attendence SET std_name=?,dateAttend=?,attendance=? WHERE std_id=?", entity.getStd_name(), entity.getDate(), entity.getAttendance(), entity.getStd_id());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Student_Attendence WHERE std_id=?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public StudentAttendance search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT  * FROM Student_Attendence WHERE std_id = ?", id + "");
        rst.next();
        return new StudentAttendance(id + "", rst.getString("std_name"), rst.getString("dateAttend"), rst.getString("attendance"));
    }

    @Override
    public ArrayList<StudentAttendance> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

}