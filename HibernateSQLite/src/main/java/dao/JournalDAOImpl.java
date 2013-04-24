package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Journal;
import model.User;

import org.hibernate.Session;

import util.HibernateUtil;

public class JournalDAOImpl{

	public boolean addItem(Journal journal) throws SQLException {
		Session session = null;
		boolean isSave = false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
		    session.beginTransaction();
		    session.save(journal);
		    session.getTransaction().commit();
		    isSave = true;
			
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

	public Journal getItemByLogin(String login) throws SQLException {
		Session session = null;
        Journal journal = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
        	journal = (Journal) session.createSQLQuery("SELECT * FROM journal WHERE user = '" + login + "';").addEntity(Journal.class).list().get(0);
        } catch (Exception e) {
        	System.out.println("Помилка GET BY LOGIN" + e.getMessage());
        } finally {
        	if (session != null && session.isOpen()) {
        		session.close();
        	}
        }
        return journal;
	}

	public ArrayList<Journal> getAllItem() throws SQLException {
		Session session = null;
		ArrayList<Journal> journals = new ArrayList<Journal>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			journals = (ArrayList<Journal>) session.createCriteria(Journal.class).list();
		} catch (Exception e) {
			System.out.println("Помилка GET ALL Journal" + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return journals;
	}

	public boolean deleteJournal(String login) throws SQLException {
		Session session = null;
        boolean isSuccess = false;
		try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createQuery("DELETE Journal WHERE login = '" + login + "'").executeUpdate();
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