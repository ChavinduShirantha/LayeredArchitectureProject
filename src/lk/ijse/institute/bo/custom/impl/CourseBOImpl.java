package lk.ijse.institute.bo.custom.impl;

import lk.ijse.institute.bo.custom.CourseBO;
import lk.ijse.institute.dao.DAOFactory;
import lk.ijse.institute.dao.custom.CourseDAO;
import lk.ijse.institute.dto.CourseDTO;
import lk.ijse.institute.entity.Course;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/23/2023-9:47 AM
 **/
public class CourseBOImpl implements CourseBO {
    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public boolean addCourse(CourseDTO dto) throws SQLException, ClassNotFoundException {
        return courseDAO.add(new Course(dto.getCourse_id(), dto.getCourse_name(), dto.getCourse_fee(), dto.getSub_id(), dto.getSub_name()));
    }

    @Override
    public boolean updateCourse(CourseDTO dto) throws SQLException, ClassNotFoundException {
        return courseDAO.update(new Course(dto.getCourse_id(), dto.getCourse_name(), dto.getCourse_fee(), dto.getSub_id(), dto.getSub_name()));
    }

    @Override
    public boolean deleteCourse(String id) throws SQLException, ClassNotFoundException {
        return courseDAO.delete(id);
    }

    @Override
    public String generateNewCourseID() throws SQLException, ClassNotFoundException {
        return courseDAO.generateNewID();
    }

    @Override
    public CourseDTO searchCourse(String id) throws SQLException, ClassNotFoundException {
        Course c = courseDAO.search(id);
        return new CourseDTO(c.getCourse_id(), c.getCourse_name(), c.getCourse_fee(), c.getSub_id(), c.getSub_name());
    }

    @Override
    public ArrayList<CourseDTO> getAllCourses() throws SQLException, ClassNotFoundException {
        ArrayList<CourseDTO> allCourses = new ArrayList<>();
        ArrayList<Course> all = courseDAO.getAll();
        for (Course c : all) {
            allCourses.add(new CourseDTO(c.getCourse_id(), c.getCourse_name(), c.getCourse_fee(), c.getSub_id(), c.getSub_name()));
        }
        return allCourses;
    }
}
