package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.User;

public interface DAO {
    public boolean addItem(Object object) throws SQLException;
    public User getItemById(Long id) throws SQLException;
    public ArrayList<Object> getAllItem() throws SQLException;
	public boolean deleteObject(String login) throws SQLException;
}
