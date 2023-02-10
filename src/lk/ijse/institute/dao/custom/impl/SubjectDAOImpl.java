package lk.ijse.institute.dao.custom.impl;

import lk.ijse.institute.dao.SQLUtil;
import lk.ijse.institute.dao.custom.SubjectDAO;
import lk.ijse.institute.entity.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/23/2023-10:03 AM
 **/
public class SubjectDAOImpl implements SubjectDAO {

    @Override
    public boolean add(Subject entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Subject VALUES(?,?,?,?,?)", entity.getSubId(), entity.getSubName(), entity.getSubHours(), entity.getT_id(), entity.getT_name());
    }

    @Override
    public boolean update(Subject entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Subject SET sub_name=?,Sub_hours=?,t_id=?,t_name=? WHERE sub_id=?", entity.getSubName(), entity.getSubHours(), entity.getT_id(), entity.getT_name(), entity.getSubId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Subject WHERE sub_id=?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT sub_id FROM Subject ORDER BY sub_id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("sub_id");
            int newBatchId = Integer.parseInt(id.replace("SUB00-", "")) + 1;
            return String.format("SUB00-%03d", newBatchId);
        } else {
            return "SUB00-001";
        }
    }

    @Override
    public Subject search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT  * FROM Subject WHERE sub_id = ?", id + "");
        rst.next();
        return new Subject(id + "", rst.getString("sub_name"), rst.getInt("Sub_hours"), rst.getString("t_id"), rst.getString("t_name"));
    }

    @Override
    public ArrayList<Subject> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Subject> allSubjects = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Subject");
        while (rst.next()) {
            Subject subject = new Subject(rst.getString("sub_id"), rst.getString("sub_name"), rst.getInt("Sub_hours"), rst.getString("t_id"), rst.getString("t_name"));
            allSubjects.add(subject);
        }
        return allSubjects;
    }

}