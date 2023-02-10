package lk.ijse.institute.bo.custom.impl;

import lk.ijse.institute.bo.custom.StudentBO;
import lk.ijse.institute.dao.DAOFactory;
import lk.ijse.institute.dao.custom.StudentDAO;
import lk.ijse.institute.dto.StudentDTO;
import lk.ijse.institute.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/19/2023-9:19 AM
 **/
public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean addStudent(StudentDTO dto) throws SQLException, ClassNotFoundException {
        return studentDAO.add(new Student(dto.getStd_id(), dto.getFirstName(), dto.getLastName(), dto.getAddress(), dto.getGender(), dto.getContact(), dto.getEmail(), dto.getDate_of_birth()));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws SQLException, ClassNotFoundException {
        return studentDAO.update(new Student(dto.getStd_id(), dto.getFirstName(), dto.getLastName(), dto.getAddress(), dto.getGender(), dto.getContact(), dto.getEmail(), dto.getDate_of_birth()));
    }

    @Override
    public boolean deleteStudent(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.delete(id);
    }

    @Override
    public String generateNewStudentID() throws SQLException, ClassNotFoundException {
        return studentDAO.generateNewID();
    }

    @Override
    public StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException {
        Student s = studentDAO.search(id);
        return new StudentDTO(s.getStd_id(), s.getFirstName(), s.getLastName(), s.getAddress(), s.getGender(), s.getContact(), s.getEmail(), s.getDate_of_birth());
    }

    @Override
    public ArrayList<StudentDTO> getAllStudents() throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> allStudents = new ArrayList<>();
        ArrayList<Student> all = studentDAO.getAll();
        for (Student s : all) {
            allStudents.add(new StudentDTO(s.getStd_id(), s.getFirstName(), s.getLastName(), s.getAddress(), s.getGender(), s.getContact(), s.getEmail(), s.getDate_of_birth()));
        }
        return allStudents;
    }

    public int countAllStudent(int count) throws SQLException, ClassNotFoundException {
        int countAll = studentDAO.countAll(count);
        return countAll;
    }

    public int countAllMaleStudent(int count) throws SQLException, ClassNotFoundException {
        int countAllMale = studentDAO.countAllMale(count);
        return countAllMale;
    }

    public int countAllFemaleStudent(int count) throws SQLException, ClassNotFoundException {
        int countAllFemale = studentDAO.countAllFemale(count);
        return countAllFemale;
    }
}
