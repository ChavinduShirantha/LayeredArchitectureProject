package lk.ijse.institute.dao.custom.impl;

import lk.ijse.institute.dao.SQLUtil;
import lk.ijse.institute.dao.custom.SalaryDAO;
import lk.ijse.institute.entity.Salary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/23/2023-10:02 AM
 **/
public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public boolean add(Salary entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Salary VALUES(?,?,?,?,?,?)", entity.getSal_id(), entity.getT_id(), entity.getT_name(), entity.getAmount(), entity.getTime(), entity.getDate());
    }

    @Override
    public boolean update(Salary entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Salary SET t_id=?,t_name=?,amount=?,time=?,date=? WHERE sal_id=?", entity.getT_id(), entity.getT_name(), entity.getAmount(), entity.getTime(), entity.getDate(), entity.getSal_id());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Salary WHERE sal_id=?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT sal_id FROM Salary ORDER BY sal_id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("sal_id");
            int newBatchId = Integer.parseInt(id.replace("SAL00-", "")) + 1;
            return String.format("SAL00-%03d", newBatchId);
        } else {
            return "SAL00-001";
        }
    }

    @Override
    public Salary search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Salary WHERE sal_id = ?", id + "");
        rst.next();
        return new Salary(id + "", rst.getString("t_id"), rst.getString("t_name"), rst.getDouble("amount"), rst.getString("time"), rst.getString("date"));
    }

    @Override
    public ArrayList<Salary> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

}
