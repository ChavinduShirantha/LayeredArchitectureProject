package lk.ijse.institute.bo.custom;

import lk.ijse.institute.bo.SuperBO;
import lk.ijse.institute.dto.TeacherDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/23/2023-9:47 AM
 **/
public interface TeacherBO extends SuperBO {
    boolean addTeacher(TeacherDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateTeacher(TeacherDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteTeacher(String id) throws SQLException, ClassNotFoundException;

    String generateNewTeacherID() throws SQLException, ClassNotFoundException;

    TeacherDTO searchTeacher(String id) throws SQLException, ClassNotFoundException;

    ArrayList<TeacherDTO> getAllTeachers() throws SQLException, ClassNotFoundException;

    int countAllTeacher(int count) throws SQLException, ClassNotFoundException;

    int countAllMaleTeacher(int count) throws SQLException, ClassNotFoundException;

    int countAllFemaleTeacher(int count) throws SQLException, ClassNotFoundException;

}
