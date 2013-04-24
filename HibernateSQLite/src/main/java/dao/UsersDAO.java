package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.User;

public interface UsersDAO {
    public boolean addUser(User user) throws SQLException;
    public User getUserById(Long id) throws SQLException;
    public ArrayList<User> getAllUsers() throws SQLException;
	public boolean deleteUser(String login) throws SQLException;
}
