package lk.ijse.institute.dao.custom.impl;

import lk.ijse.institute.dao.SQLUtil;
import lk.ijse.institute.dao.custom.BatchDAO;
import lk.ijse.institute.entity.Batch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/23/2023-10:00 AM
 **/
public class BatchDAOImpl implements BatchDAO {
    @Override
    public boolean add(Batch entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Batch VALUES(?,?,?,?,?,?)", entity.getBatchId(), entity.getBatchName(), entity.getCourseId(), entity.getCourseName(), entity.getStd_id(), entity.getStd_name());
    }

    @Override
    public boolean update(Batch entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Batch SET batch_name=?,c_id=?,course_name=?,std_id=?,std_name=? WHERE b_id=?", entity.getBatchName(), entity.getCourseId(), entity.getCourseName(), entity.getStd_id(), entity.getStd_name(), entity.getBatchId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Batch WHERE b_id=?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT b_id FROM Batch ORDER BY b_id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("b_id");
            int newBatchId = Integer.parseInt(id.replace("B00-", "")) + 1;
            return String.format("B00-%03d", newBatchId);
        } else {
            return "B00-001";
        }
    }

    @Override
    public Batch search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Batch WHERE b_id = ?", id + "");
        rst.next();
        return new Batch(id + "", rst.getString("batch_name"), rst.getString("c_id"), rst.getString("course_name"), rst.getString("std_id"), rst.getString("std_name"));
    }

    @Override
    public Batch searchAgain(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Batch WHERE std_id = ?", id);
        rst.next();
        return new Batch(id, rst.getString("b_id"), rst.getString("batch_name"), rst.getString("c_id"), rst.getString("course_name"), rst.getString("std_name"));
    }

    @Override
    public ArrayList<Batch> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

}