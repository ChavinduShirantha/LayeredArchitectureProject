package lk.ijse.institute.dao.custom;

import lk.ijse.institute.dao.CrudDAO;
import lk.ijse.institute.entity.Student;

import java.sql.SQLException;

/**
 * @author : Chavindu
 * created : 1/19/2023-9:22 AM
 **/
public interface StudentDAO extends CrudDAO<Student> {
    int countAll(int count) throws SQLException, ClassNotFoundException;

    int countAllMale(int count) throws SQLException, ClassNotFoundException;

    int countAllFemale(int count) throws SQLException, ClassNotFoundException;

}