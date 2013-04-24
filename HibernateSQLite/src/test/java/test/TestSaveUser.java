package test;

import java.util.ArrayList;

import javax.jws.soap.SOAPBinding.Use;

import junit.framework.TestCase;
import model.User;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import util.HibernateUtil;

public class TestSaveUser extends TestCase{
	
	@Test
	public void testSave(){
		User user = new User();
		user.setName("apple");
		user.setPassword("123456");
		user.setLogin("vasa");
		user.setFirstName("Vasya");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		
		tx.commit();
		session.close();
		HibernateUtil.shutdown();
	}
	
	@Test
	public void testGetAll(){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		ArrayList<User> users = (ArrayList<User>) session.createSQLQuery("SELECT * FROM users;").addEntity(User.class).list();
		
		assertEquals(users.size(), 5);
		
		tx.commit();
		session.close();
		HibernateUtil.shutdown();
	}
}
