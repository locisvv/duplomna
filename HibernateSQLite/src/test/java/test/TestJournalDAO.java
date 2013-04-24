package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import model.Journal;

import org.junit.Test;

import dao.JournalDAOImpl;

public class TestJournalDAO {

	@Test
	public void testSaveUser() throws SQLException {
		JournalDAOImpl dao = new JournalDAOImpl();
		
		Journal journal = new Journal();
		journal.setDateTime("12.12.2012");	
		journal.setTime("12:12");
		journal.setUser("tanya");
		journal.setAction("loginization");
		assertTrue(dao.addItem(journal));
	}
	
	@Test
	public void testGetUser() throws SQLException {
		JournalDAOImpl dao = new JournalDAOImpl();
		assertEquals(dao.getItemByLogin("tanya").getTime(), 20);
	}
	
	@Test
	public void testGetAllUser() throws SQLException {
		JournalDAOImpl dao = new JournalDAOImpl();
		assertEquals(dao.getAllItem().size(), 2);
	}
}