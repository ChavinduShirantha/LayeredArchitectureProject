package lk.ijse.institute.bo.custom;

import lk.ijse.institute.bo.SuperBO;
import lk.ijse.institute.dto.StudentAttendanceDTO;

import java.sql.SQLException;

/**
 * @author : Chavindu
 * created : 1/23/2023-9:45 AM
 **/
public interface StudentAttendanceBO extends SuperBO {
    boolean addStudentAttendance(StudentAttendanceDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateStudentAttendance(StudentAttendanceDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteStudentAttendance(String id) throws SQLException, ClassNotFoundException;

    StudentAttendanceDTO searchStudentAttendance(String id) throws SQLException, ClassNotFoundException;

}
