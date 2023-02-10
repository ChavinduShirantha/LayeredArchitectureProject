package lk.ijse.institute.dao.custom.impl;

import lk.ijse.institute.dao.SQLUtil;
import lk.ijse.institute.dao.custom.ExamDAO;
import lk.ijse.institute.entity.Exam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/23/2023-10:01 AM
 **/
public class ExamDAOImpl implements ExamDAO {
    @Override
    public boolean add(Exam entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Exam_Shedule VALUES(?,?,?,?,?)", entity.getExam_id(), entity.getSub_id(), entity.getSub_name(), entity.getDate(), entity.getTime());
    }

    @Override
    public boolean update(Exam entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Exam_Shedule SET sub_id=?,sub_name=?,date=?,time=? WHERE exam_id=?", entity.getSub_id(), entity.getSub_name(), entity.getDate(), entity.getTime(), entity.getExam_id());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Exam_Shedule WHERE exam_id=?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT exam_id FROM Exam_shedule ORDER BY exam_id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("exam_id");
            int newBatchId = Integer.parseInt(id.replace("E00-", "")) + 1;
            return String.format("E00-%03d", newBatchId);
        } else {
            return "E00-001";
        }
    }

    @Override
    public Exam search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT  * FROM Exam_Shedule WHERE exam_id = ?", id + "");
        rst.next();
        return new Exam(id + "", rst.getString("sub_id"), rst.getString("sub_name"), rst.getString("date"), rst.getString("time"));
    }

    @Override
    public ArrayList<Exam> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

}
