package lk.ijse.institute.dao.custom.impl;

import lk.ijse.institute.dao.SQLUtil;
import lk.ijse.institute.dao.custom.FundDAO;
import lk.ijse.institute.entity.Fund;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/23/2023-10:01 AM
 **/
public class FundDAOImpl implements FundDAO {
    @Override
    public boolean add(Fund entity) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public boolean update(Fund entity) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public Fund search(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public ArrayList<Fund> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public boolean AddPayment(Fund entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Fund SET money=money+?", entity.getMoney());
    }

    @Override
    public boolean AddSalary(Fund entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Fund SET money=money-?", entity.getMoney());
    }

}
