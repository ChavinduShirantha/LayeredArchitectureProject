package lk.ijse.institute.bo.custom;

import lk.ijse.institute.bo.SuperBO;
import lk.ijse.institute.dto.CourseDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/23/2023-9:41 AM
 **/
public interface CourseBO extends SuperBO {
    boolean addCourse(CourseDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateCourse(CourseDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteCourse(String id) throws SQLException, ClassNotFoundException;

    String generateNewCourseID() throws SQLException, ClassNotFoundException;

    CourseDTO searchCourse(String id) throws SQLException, ClassNotFoundException;

    ArrayList<CourseDTO> getAllCourses() throws SQLException, ClassNotFoundException;

}
