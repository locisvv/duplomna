package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import util.HibernateUtil;

import model.User;

public class UserDAOImpl implements UsersDAO {

	@Override
	public boolean addUser(User user) throws SQLException {
		Session session = null;
		boolean isSave = false;
		try {
			
			if(getUserByLogin(user.getLogin()) == null){
			    session = HibernateUtil.getSessionFactory().openSession();
			    session.beginTransaction();
			    session.save(user);
			    session.getTransaction().commit();
			    isSave = true;
			}
			else{
				JOptionPane.showMessageDialog(null, "Користувач з таким логіном існує", "Помилка INSERT", JOptionPane.OK_OPTION);
			}
			
		} catch (Exception e) {
			isSave = false; 
		    System.out.println("Помилка I/O" + e.getMessage());
		} finally {
		    if (session != null && session.isOpen()) {
		        session.close();
		    }
		}
		return isSave;
	}

	@Override
	public User getUserById(Long id) throws SQLException {
		Session session = null;
        User user = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
        	user = (User) session.createSQLQuery("SELECT * FROM users WHERE id = " + id + ";").addEntity(User.class).list().get(0);
        } catch (Exception e) {
        	System.out.println("Помилка GET BY ID" + e.getMessage());
        } finally {
        	if (session != null && session.isOpen()) {
        		session.close();
        	}
        }
        return user;
	}
	
	
	public User getUserByLogin(String login) throws SQLException {
		Session session = null;
        User user = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
        	user = (User) session.createSQLQuery("SELECT * FROM users WHERE login = '" + login + "';").addEntity(User.class).list().get(0);
        } catch (Exception e) {
        	System.out.println("Помилка GET BY LOGIN" + e.getMessage());
        } finally {
        	if (session != null && session.isOpen()) {
        		session.close();
        	}
        }
        return user;
	}

	@Override
	public ArrayList<User> getAllUsers() throws SQLException {
		Session session = null;
		ArrayList<User> users = new ArrayList<User>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			users = (ArrayList<User>) session.createCriteria(User.class).list();
		} catch (Exception e) {
			System.out.println("Помилка GET ALL USER" + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return users;
	}

	@Override
	public boolean deleteUser(String login) throws SQLException {
		Session session = null;
        boolean isSuccess = false;
		try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createQuery("delete User where login = '" + login + "'").executeUpdate();
            session.getTransaction().commit();
            isSuccess = true;
        } catch (Exception e) {
        	isSuccess = false;
        	System.out.println("Помилка DELETE" + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
		return isSuccess;
	}

}