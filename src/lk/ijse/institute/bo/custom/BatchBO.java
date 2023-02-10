package lk.ijse.institute.bo.custom;

import lk.ijse.institute.bo.SuperBO;
import lk.ijse.institute.dto.BatchDTO;

import java.sql.SQLException;

/**
 * @author : Chavindu
 * created : 1/23/2023-9:41 AM
 **/
public interface BatchBO extends SuperBO {
    boolean addBatch(BatchDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateBatch(BatchDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteBatch(String id) throws SQLException, ClassNotFoundException;

    String generateNewBatchID() throws SQLException, ClassNotFoundException;

    BatchDTO searchBatch(String id) throws SQLException, ClassNotFoundException;

    BatchDTO searchBatchAgain(String id) throws SQLException, ClassNotFoundException;

}
