package lk.ijse.institute.bo.custom.impl;

import lk.ijse.institute.bo.custom.BatchBO;
import lk.ijse.institute.dao.DAOFactory;
import lk.ijse.institute.dao.custom.BatchDAO;
import lk.ijse.institute.dto.BatchDTO;
import lk.ijse.institute.entity.Batch;

import java.sql.SQLException;

/**
 * @author : Chavindu
 * created : 1/23/2023-9:47 AM
 **/
public class BatchBOImpl implements BatchBO {
    BatchDAO batchDAO = (BatchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BATCH);

    @Override
    public boolean addBatch(BatchDTO dto) throws SQLException, ClassNotFoundException {
        return batchDAO.add(new Batch(dto.getBatchId(), dto.getBatchName(), dto.getCourseId(), dto.getCourseName(), dto.getStd_id(), dto.getStd_name()));
    }

    @Override
    public boolean updateBatch(BatchDTO dto) throws SQLException, ClassNotFoundException {
        return batchDAO.update(new Batch(dto.getBatchId(), dto.getBatchName(), dto.getCourseId(), dto.getCourseName(), dto.getStd_id(), dto.getStd_name()));
    }

    @Override
    public boolean deleteBatch(String id) throws SQLException, ClassNotFoundException {
        return batchDAO.delete(id);
    }

    @Override
    public String generateNewBatchID() throws SQLException, ClassNotFoundException {
        return batchDAO.generateNewID();
    }

    @Override
    public BatchDTO searchBatch(String id) throws SQLException, ClassNotFoundException {
        Batch b = batchDAO.search(id);
        return new BatchDTO(b.getBatchId(), b.getBatchName(), b.getCourseId(), b.getCourseName(), b.getStd_id(), b.getStd_name());
    }

    @Override
    public BatchDTO searchBatchAgain(String id) throws SQLException, ClassNotFoundException {
        Batch b = batchDAO.searchAgain(id);
        return new BatchDTO(b.getBatchId(), b.getBatchName(), b.getCourseId(), b.getCourseName(), b.getStd_id(), b.getStd_name());
    }
}
