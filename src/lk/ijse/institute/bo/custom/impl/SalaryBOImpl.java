package lk.ijse.institute.bo.custom.impl;

import lk.ijse.institute.bo.custom.SalaryBO;
import lk.ijse.institute.dao.DAOFactory;
import lk.ijse.institute.dao.custom.FundDAO;
import lk.ijse.institute.dao.custom.SalaryDAO;
import lk.ijse.institute.db.DBConnection;
import lk.ijse.institute.dto.SalaryDTO;
import lk.ijse.institute.entity.Fund;
import lk.ijse.institute.entity.Salary;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author : Chavindu
 * created : 1/23/2023-9:53 AM
 **/
public class SalaryBOImpl implements SalaryBO {
    SalaryDAO salaryDAO = (SalaryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SALARY);
    FundDAO fundDAO = (FundDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FUND);

    @Override
    public boolean addSalary(SalaryDTO dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        boolean add = salaryDAO.add(new Salary(dto.getSal_id(), dto.getT_id(), dto.getT_name(), dto.getAmount(), dto.getTime(), dto.getDate()));
        if (add) {
            Salary search = salaryDAO.search(dto.getSal_id());
            double amount = search.getAmount();
            boolean b = fundDAO.AddSalary(new Fund(amount));
            if (b) {
                connection.commit();
            }
        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;

    }

    @Override
    public boolean updateSalary(SalaryDTO dto) throws SQLException, ClassNotFoundException {
        return salaryDAO.update(new Salary(dto.getSal_id(), dto.getT_id(), dto.getT_name(), dto.getAmount(), dto.getTime(), dto.getDate()));
    }

    @Override
    public boolean deleteSalary(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.delete(id);
    }

    @Override
    public String generateNewSalaryID() throws SQLException, ClassNotFoundException {
        return salaryDAO.generateNewID();
    }

    @Override
    public SalaryDTO searchSalary(String id) throws SQLException, ClassNotFoundException {
        Salary s = salaryDAO.search(id);
        return new SalaryDTO(s.getSal_id(), s.getT_id(), s.getT_name(), s.getAmount(), s.getDate(), s.getTime());
    }
}
