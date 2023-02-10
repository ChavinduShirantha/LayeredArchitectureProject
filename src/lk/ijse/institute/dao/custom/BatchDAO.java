package lk.ijse.institute.dao.custom;

import lk.ijse.institute.dao.CrudDAO;
import lk.ijse.institute.entity.Batch;

import java.sql.SQLException;

/**
 * @author : Chavindu
 * created : 1/23/2023-10:19 AM
 **/
public interface BatchDAO extends CrudDAO<Batch> {
    Batch searchAgain(String id) throws SQLException, ClassNotFoundException;

}
