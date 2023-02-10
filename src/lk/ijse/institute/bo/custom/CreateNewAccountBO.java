package lk.ijse.institute.bo.custom;

import lk.ijse.institute.bo.SuperBO;
import lk.ijse.institute.dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/23/2023-9:42 AM
 **/
public interface CreateNewAccountBO extends SuperBO {
    boolean addUser(UserDTO dto) throws SQLException, ClassNotFoundException;

    ArrayList<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException;

}
