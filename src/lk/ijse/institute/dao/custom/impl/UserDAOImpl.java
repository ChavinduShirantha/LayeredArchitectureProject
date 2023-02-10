package lk.ijse.institute.dao.custom.impl;

import lk.ijse.institute.dao.SQLUtil;
import lk.ijse.institute.dao.custom.UserDAO;
import lk.ijse.institute.entity.Student;
import lk.ijse.institute.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Chavindu
 * created : 1/23/2023-10:03 AM
 **/
public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(User entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO User VALUES(?,?,?,?,?,?,?,?)", entity.getName(), entity.getSurName(), entity.getCity(), entity.getContact(), entity.getEmail(), entity.getUserName(), entity.getPassword(), entity.getRole());
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<User> allUsers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT user_name,password,role FROM User");
        while (rst.next()) {
            User user = new User(rst.getString("user_name"), rst.getString("password"), rst.getString("role"));
            allUsers.add(user);
        }
        return allUsers;
    }

}