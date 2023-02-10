package lk.ijse.institute.bo.custom;

import lk.ijse.institute.bo.SuperBO;
import lk.ijse.institute.dto.ExamDTO;

import java.sql.SQLException;

/**
 * @author : Chavindu
 * created : 1/23/2023-9:44 AM
 **/
public interface ExamBO extends SuperBO {
    boolean addExam(ExamDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateExam(ExamDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteExam(String id) throws SQLException, ClassNotFoundException;

    String generateNewExamID() throws SQLException, ClassNotFoundException;

    ExamDTO searchExam(String id) throws SQLException, ClassNotFoundException;

}
