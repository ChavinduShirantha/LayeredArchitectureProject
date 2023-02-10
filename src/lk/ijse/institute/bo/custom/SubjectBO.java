package lk.ijse.institute.bo.custom;

import lk.ijse.institute.bo.SuperBO;
import lk.ijse.institute.dto.SubjectDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/23/2023-9:46 AM
 **/
public interface SubjectBO extends SuperBO {
    boolean addSubject(SubjectDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateSubject(SubjectDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteSubject(String id) throws SQLException, ClassNotFoundException;

    String generateNewSubjectID() throws SQLException, ClassNotFoundException;

    SubjectDTO searchSubject(String id) throws SQLException, ClassNotFoundException;

    ArrayList<SubjectDTO> getAllSubjects() throws SQLException, ClassNotFoundException;

}
