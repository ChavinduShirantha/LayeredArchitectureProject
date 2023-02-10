package lk.ijse.institute.bo.custom.impl;

import lk.ijse.institute.bo.custom.ExamBO;
import lk.ijse.institute.dao.DAOFactory;
import lk.ijse.institute.dao.custom.ExamDAO;
import lk.ijse.institute.dto.ExamDTO;
import lk.ijse.institute.entity.Exam;

import java.sql.SQLException;

/**
 * @author : Chavindu
 * created : 1/23/2023-9:52 AM
 **/
public class ExamBOImpl implements ExamBO {
    ExamDAO examDAO = (ExamDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EXAM);

    @Override
    public boolean addExam(ExamDTO dto) throws SQLException, ClassNotFoundException {
        return examDAO.add(new Exam(dto.getExam_id(), dto.getSub_id(), dto.getSub_name(), dto.getDate(), dto.getTime()));
    }

    @Override
    public boolean updateExam(ExamDTO dto) throws SQLException, ClassNotFoundException {
        return examDAO.update(new Exam(dto.getExam_id(), dto.getSub_id(), dto.getSub_name(), dto.getDate(), dto.getTime()));
    }

    @Override
    public boolean deleteExam(String id) throws SQLException, ClassNotFoundException {
        return examDAO.delete(id);
    }

    @Override
    public String generateNewExamID() throws SQLException, ClassNotFoundException {
        return examDAO.generateNewID();
    }

    @Override
    public ExamDTO searchExam(String id) throws SQLException, ClassNotFoundException {
        Exam e = examDAO.search(id);
        return new ExamDTO(e.getExam_id(), e.getSub_id(), e.getSub_name(), e.getDate(), e.getTime());
    }
}
