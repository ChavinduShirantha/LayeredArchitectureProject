package lk.ijse.institute.dao.custom;

import lk.ijse.institute.dao.CrudDAO;
import lk.ijse.institute.entity.Teacher;

import java.sql.SQLException;

/**
 * @author : Chavindu
 * created : 1/23/2023-10:22 AM
 **/
public interface TeacherDAO extends CrudDAO<Teacher> {
    int countAll(int count) throws SQLException, ClassNotFoundException;

    int countAllMale(int count) throws SQLException, ClassNotFoundException;

    int countAllFemale(int count) throws SQLException, ClassNotFoundException;

}