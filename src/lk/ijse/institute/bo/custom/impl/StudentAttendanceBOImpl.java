package lk.ijse.institute.bo.custom.impl;

import lk.ijse.institute.bo.custom.StudentAttendanceBO;
import lk.ijse.institute.dao.DAOFactory;
import lk.ijse.institute.dao.custom.StudentAttendanceDAO;
import lk.ijse.institute.dto.StudentAttendanceDTO;
import lk.ijse.institute.entity.StudentAttendance;

import java.sql.SQLException;

/**
 * @author : Chavindu
 * created : 1/23/2023-9:53 AM
 **/
public class StudentAttendanceBOImpl implements StudentAttendanceBO {
    StudentAttendanceDAO studentAttendanceDAO = (StudentAttendanceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT_ATTENDANCE);

    @Override
    public boolean addStudentAttendance(StudentAttendanceDTO dto) throws SQLException, ClassNotFoundException {
        return studentAttendanceDAO.add(new StudentAttendance(dto.getStd_id(), dto.getStd_name(), dto.getDate(), dto.getAttendance()));
    }

    @Override
    public boolean updateStudentAttendance(StudentAttendanceDTO dto) throws SQLException, ClassNotFoundException {
        return studentAttendanceDAO.update(new StudentAttendance(dto.getStd_id(), dto.getStd_name(), dto.getDate(), dto.getAttendance()));
    }

    @Override
    public boolean deleteStudentAttendance(String id) throws SQLException, ClassNotFoundException {
        return studentAttendanceDAO.delete(id);
    }

    @Override
    public StudentAttendanceDTO searchStudentAttendance(String id) throws SQLException, ClassNotFoundException {
        StudentAttendance s = studentAttendanceDAO.search(id);
        return new StudentAttendanceDTO(s.getStd_id(), s.getStd_name(), s.getDate(), s.getAttendance());
    }
}
