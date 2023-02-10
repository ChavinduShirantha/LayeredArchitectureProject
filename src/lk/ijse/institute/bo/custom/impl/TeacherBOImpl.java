package lk.ijse.institute.bo.custom.impl;

import lk.ijse.institute.bo.custom.TeacherBO;
import lk.ijse.institute.dao.DAOFactory;
import lk.ijse.institute.dao.custom.TeacherDAO;
import lk.ijse.institute.dto.TeacherDTO;
import lk.ijse.institute.entity.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/23/2023-9:54 AM
 **/
public class TeacherBOImpl implements TeacherBO {
    TeacherDAO teacherDAO = (TeacherDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TEACHER);

    @Override
    public boolean addTeacher(TeacherDTO dto) throws SQLException, ClassNotFoundException {
        return teacherDAO.add(new Teacher(dto.getT_id(), dto.getFirstName(), dto.getLastName(), dto.getAddress(), dto.getGender(), dto.getContact(), dto.getEmail(), dto.getDate_of_birth()));
    }

    @Override
    public boolean updateTeacher(TeacherDTO dto) throws SQLException, ClassNotFoundException {
        return teacherDAO.update(new Teacher(dto.getT_id(), dto.getFirstName(), dto.getLastName(), dto.getAddress(), dto.getGender(), dto.getContact(), dto.getEmail(), dto.getDate_of_birth()));
    }

    @Override
    public boolean deleteTeacher(String id) throws SQLException, ClassNotFoundException {
        return teacherDAO.delete(id);
    }

    @Override
    public String generateNewTeacherID() throws SQLException, ClassNotFoundException {
        return teacherDAO.generateNewID();
    }

    @Override
    public TeacherDTO searchTeacher(String id) throws SQLException, ClassNotFoundException {
        Teacher t = teacherDAO.search(id);
        return new TeacherDTO(t.getT_id(), t.getFirstName(), t.getLastName(), t.getAddress(), t.getGender(), t.getContact(), t.getEmail(), t.getDate_of_birth());
    }

    @Override
    public ArrayList<TeacherDTO> getAllTeachers() throws SQLException, ClassNotFoundException {
        ArrayList<TeacherDTO> allTeachers = new ArrayList<>();
        ArrayList<Teacher> all = teacherDAO.getAll();
        for (Teacher t : all) {
            allTeachers.add(new TeacherDTO(t.getT_id(), t.getFirstName(), t.getLastName(), t.getAddress(), t.getGender(), t.getContact(), t.getEmail(), t.getDate_of_birth()));
        }
        return allTeachers;
    }

    public int countAllTeacher(int count) throws SQLException, ClassNotFoundException {
        int countAll = teacherDAO.countAll(count);
        return countAll;
    }

    public int countAllMaleTeacher(int count) throws SQLException, ClassNotFoundException {
        int countAllMale = teacherDAO.countAllMale(count);
        return countAllMale;
    }

    public int countAllFemaleTeacher(int count) throws SQLException, ClassNotFoundException {
        int countAllFemale = teacherDAO.countAllFemale(count);
        return countAllFemale;
    }
}
