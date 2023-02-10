package lk.ijse.institute.bo.custom;

import lk.ijse.institute.bo.SuperBO;
import lk.ijse.institute.dto.StudentMarkDTO;

import java.sql.SQLException;

/**
 * @author : Chavindu
 * created : 1/23/2023-9:46 AM
 **/
public interface StudentMarksBO extends SuperBO {
    boolean addStudentMarks(StudentMarkDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateStudentMarks(StudentMarkDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteStudentMarks(String id) throws SQLException, ClassNotFoundException;

    StudentMarkDTO searchStudentMarks(String id) throws SQLException, ClassNotFoundException;

}
