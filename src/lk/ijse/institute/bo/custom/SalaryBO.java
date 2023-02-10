package lk.ijse.institute.bo.custom;

import lk.ijse.institute.bo.SuperBO;
import lk.ijse.institute.dto.SalaryDTO;

import java.sql.SQLException;

/**
 * @author : Chavindu
 * created : 1/23/2023-9:45 AM
 **/
public interface SalaryBO extends SuperBO {
    boolean addSalary(SalaryDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateSalary(SalaryDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteSalary(String id) throws SQLException, ClassNotFoundException;

    String generateNewSalaryID() throws SQLException, ClassNotFoundException;

    SalaryDTO searchSalary(String id) throws SQLException, ClassNotFoundException;

}
