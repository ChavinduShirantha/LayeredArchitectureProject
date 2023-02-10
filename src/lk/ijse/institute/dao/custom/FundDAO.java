package lk.ijse.institute.dao.custom;

import lk.ijse.institute.dao.CrudDAO;
import lk.ijse.institute.entity.Fund;

import java.sql.SQLException;

/**
 * @author : Chavindu
 * created : 1/23/2023-10:20 AM
 **/
public interface FundDAO extends CrudDAO<Fund> {
    boolean AddPayment(Fund entity) throws SQLException, ClassNotFoundException;

    boolean AddSalary(Fund entity) throws SQLException, ClassNotFoundException;

}