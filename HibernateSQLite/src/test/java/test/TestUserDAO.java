package test;

import java.sql.SQLException;

import model.User;

import org.junit.Test;

import dao.UserDAOImpl;

import junit.framework.TestCase;

public class TestUserDAO extends TestCase {
	
	@Test
	public void testSaveUser() throws SQLException {
		UserDAOImpl dao = new UserDAOImpl();
		User user = new User();
		user.setFirstName("Kola");
		user.setName("Poto");
		user.setLogin("alp");
		user.setPassword("123dsa123");

		assertTrue(dao.addUser(user));
	}
	
	@Test
	public void testSaveUserByLogin() throws SQLException {
		UserDAOImpl dao = new UserDAOImpl();
		User user = new User();
		user.setFirstName("Kola");
		user.setName("Poto");
		user.setLogin("alp");
		user.setPassword("123dsa123");

		assertFalse(dao.addUser(user));
	}
	
	@Test
	public void testGetUser() throws SQLException {
		UserDAOImpl dao = new UserDAOImpl();
		assertEquals(dao.getUserById(6l).getFirstName(), "Kola");
	}

	@Test
	public void testGetAllUser() throws SQLException {
		UserDAOImpl dao = new UserDAOImpl();
		assertEquals(dao.getAllUsers().size(), 5);
	}
	
	@Test
	public void testGetUserByLogin() throws SQLException {
		UserDAOImpl dao = new UserDAOImpl();
		User user = dao.getUserByLogin("kot");
		assertEquals(user.getName(), "Taras");
	}
	
	@Test
	public void testGetUserByLogin2() throws SQLException {
		UserDAOImpl dao = new UserDAOImpl();
		User user = dao.getUserByLogin("kot11");
		assertNull(user);
	}
	
	@Test
	public void testDeleteUserByLogin() throws SQLException {
		UserDAOImpl dao = new UserDAOImpl();
		boolean isDelete = dao.deleteUser("alp");
		assertTrue(isDelete);
	}
}
