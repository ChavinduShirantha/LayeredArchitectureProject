package lk.ijse.institute.bo.custom.impl;

import lk.ijse.institute.bo.custom.SubjectBO;
import lk.ijse.institute.dao.DAOFactory;
import lk.ijse.institute.dao.custom.SubjectDAO;
import lk.ijse.institute.dto.SubjectDTO;
import lk.ijse.institute.entity.Subject;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/23/2023-9:53 AM
 **/
public class SubjectBOImpl implements SubjectBO {
    SubjectDAO subjectDAO = (SubjectDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUBJECT);

    @Override
    public boolean addSubject(SubjectDTO dto) throws SQLException, ClassNotFoundException {
        return subjectDAO.add(new Subject(dto.getSubId(), dto.getSubName(), dto.getSubHours(), dto.getT_id(), dto.getT_name()));
    }

    @Override
    public boolean updateSubject(SubjectDTO dto) throws SQLException, ClassNotFoundException {
        return subjectDAO.update(new Subject(dto.getSubId(), dto.getSubName(), dto.getSubHours(), dto.getT_id(), dto.getT_name()));
    }

    @Override
    public boolean deleteSubject(String id) throws SQLException, ClassNotFoundException {
        return subjectDAO.delete(id);
    }

    @Override
    public String generateNewSubjectID() throws SQLException, ClassNotFoundException {
        return subjectDAO.generateNewID();
    }

    @Override
    public SubjectDTO searchSubject(String id) throws SQLException, ClassNotFoundException {
        Subject s = subjectDAO.search(id);
        return new SubjectDTO(s.getSubId(), s.getSubName(), s.getSubHours(), s.getT_id(), s.getT_name());
    }

    @Override
    public ArrayList<SubjectDTO> getAllSubjects() throws SQLException, ClassNotFoundException {
        ArrayList<SubjectDTO> allSubjects = new ArrayList<>();
        ArrayList<Subject> all = subjectDAO.getAll();
        for (Subject s : all) {
            allSubjects.add(new SubjectDTO(s.getSubId(), s.getSubName(), s.getSubHours(), s.getT_id(), s.getT_name()));
        }
        return allSubjects;
    }
}
