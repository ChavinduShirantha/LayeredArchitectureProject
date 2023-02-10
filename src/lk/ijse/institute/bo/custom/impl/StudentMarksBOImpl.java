package lk.ijse.institute.bo.custom.impl;

import lk.ijse.institute.bo.custom.StudentMarksBO;
import lk.ijse.institute.dao.DAOFactory;
import lk.ijse.institute.dao.custom.StudentMarksDAO;
import lk.ijse.institute.dto.StudentMarkDTO;
import lk.ijse.institute.entity.StudentMark;

import java.sql.SQLException;

/**
 * @author : Chavindu
 * created : 1/23/2023-9:53 AM
 **/
public class StudentMarksBOImpl implements StudentMarksBO {
    StudentMarksDAO marksDAO = (StudentMarksDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT_MARKS);

    @Override
    public boolean addStudentMarks(StudentMarkDTO dto) throws SQLException, ClassNotFoundException {
        return marksDAO.add(new StudentMark(dto.getStd_id(), dto.getStd_name(), dto.getSub_id(), dto.getSub_name(), dto.getMarks()));
    }

    @Override
    public boolean updateStudentMarks(StudentMarkDTO dto) throws SQLException, ClassNotFoundException {
        return marksDAO.update(new StudentMark(dto.getStd_id(), dto.getStd_name(), dto.getSub_id(), dto.getSub_name(), dto.getMarks()));
    }

    @Override
    public boolean deleteStudentMarks(String id) throws SQLException, ClassNotFoundException {
        return marksDAO.delete(id);
    }

    @Override
    public StudentMarkDTO searchStudentMarks(String id) throws SQLException, ClassNotFoundException {
        StudentMark m = marksDAO.search(id);
        return new StudentMarkDTO(m.getStd_id(), m.getStd_name(), m.getSub_id(), m.getSub_name(), m.getMarks());
    }
}
