package lk.ijse.institute.bo.custom.impl;

import lk.ijse.institute.bo.custom.CreateNewAccountBO;
import lk.ijse.institute.dao.DAOFactory;
import lk.ijse.institute.dao.custom.UserDAO;
import lk.ijse.institute.dto.ExamDTO;
import lk.ijse.institute.dto.StudentDTO;
import lk.ijse.institute.dto.UserDTO;
import lk.ijse.institute.entity.Exam;
import lk.ijse.institute.entity.Student;
import lk.ijse.institute.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/23/2023-9:48 AM
 **/
public class CreateNewAccountBOImpl implements CreateNewAccountBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CREATE_NEW_ACCOUNT);

    @Override
    public boolean addUser(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.add(new User(dto.getName(), dto.getSurName(), dto.getCity(), dto.getContact(), dto.getEmail(), dto.getUserName(), dto.getPassword(), dto.getRole()));
    }
    @Override
    public ArrayList<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException {
        ArrayList<UserDTO> allUsers = new ArrayList<>();
        ArrayList<User> all = userDAO.getAll();
        for (User s : all) {
            allUsers.add(new UserDTO(s.getUserName(), s.getPassword(),s.getRole()));
        }
        return allUsers;
    }
}
