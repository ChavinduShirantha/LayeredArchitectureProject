package lk.ijse.institute.dao.custom.impl;

import lk.ijse.institute.dao.SQLUtil;
import lk.ijse.institute.dao.custom.PaymentDAO;
import lk.ijse.institute.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/23/2023-10:01 AM
 **/
public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean add(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Payment VALUES(?,?,?,?,?,?,?,?)", entity.getPayment_id(), entity.getStd_id(), entity.getStd_name(), entity.getBatch_id(), entity.getCourse_name(), entity.getAmount(), entity.getDate(), entity.getTime());
    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Payment SET std_id=?,std_name=?,batch_id=?,course_name=?,amount=?,date=?,time=? WHERE pid=?", entity.getStd_id(), entity.getStd_name(), entity.getBatch_id(), entity.getCourse_name(), entity.getAmount(), entity.getDate(), entity.getTime(), entity.getPayment_id());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Payment WHERE pid=?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT pid FROM Payment ORDER BY pid DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("pid");
            int newBatchId = Integer.parseInt(id.replace("P00-", "")) + 1;
            return String.format("P00-%03d", newBatchId);
        } else {
            return "P00-001";
        }
    }

    @Override
    public Payment search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT  * FROM Payment WHERE pid =?", id + "");
        rst.next();
        return new Payment(id + "", rst.getString("std_id"), rst.getString("std_name"), rst.getString("batch_id"), rst.getString("course_name"), rst.getDouble("amount"), rst.getString("date"), rst.getString("time"));
    }

    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

}
