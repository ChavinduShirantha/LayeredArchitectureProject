package lk.ijse.institute.bo.custom;

import lk.ijse.institute.bo.SuperBO;
import lk.ijse.institute.dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/19/2023-9:18 AM
 **/
public interface StudentBO extends SuperBO {
    boolean addStudent(StudentDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateStudent(StudentDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteStudent(String id) throws SQLException, ClassNotFoundException;

    String generateNewStudentID() throws SQLException, ClassNotFoundException;

    StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException;

    ArrayList<StudentDTO> getAllStudents() throws SQLException, ClassNotFoundException;

    int countAllStudent(int count) throws SQLException, ClassNotFoundException;

    int countAllMaleStudent(int count) throws SQLException, ClassNotFoundException;

    int countAllFemaleStudent(int count) throws SQLException, ClassNotFoundException;

}
