package com.bank.dao;

import java.util.List;
import com.bank.model.*;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

/**
 * Session Bean implementation class BankDAO
 */
@Stateless
@LocalBean
public class BankDAO {
	@PersistenceContext(unitName="Bank")
	private EntityManager em;
//TODO: Create functions for CRUD operations for Students and Accounts
    public BankDAO() {
    }
	public String whatDB() {
		Query query = em.createNativeQuery("SELECT DATABASE()");
		return (String) query.getSingleResult();
	}

// ================ Student CRUD ========================
	public boolean createStudent(Student student) {
		//attempt to make student entry in database
		try {
	        em.persist(student);
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        return false;
	    }
		//TODO: Implement duplication checking.
	}
	public List<Student> getAllStudents() {
		return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
	}

	public Student getStudentByID(int studentId) {
		TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.studentID = :id", Student.class);
		query.setParameter("id", studentId);
		return query.getSingleResult();
	}

	public Student getStudentByName(String username) throws NoResultException {
		try {
		TypedQuery<Student> query = em.createQuery("SELECT S FROM Student s WHERE s.studentName LIKE :name", Student.class);
		query.setParameter("name", username.toLowerCase());
		return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	public boolean updateStudent(Student student) {
		try {
	        em.merge(student);
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean deleteStudent(int studentId) {
		//TODO: deleteStudent should also verify all accounts are deleted
		return false;
 	}
	
	// =========== Account CRUD ======================

	public boolean createAccount(Account account) {
		try {
	        em.persist(account);
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        return false;
	    }
	}
	public List<Account> getAllAccounts() {
		return em.createQuery("Select a FROM Account a", Account.class).getResultList();
	}
	public Account getAccountByID(int accountID) {
		try{
			return em.createQuery("Select a FROM Account a WHERE a.accountID = :ID", Account.class).setParameter("id", accountID).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Account> getAccountsByStudentID(int studentID) {
		TypedQuery<Account> query = em.createQuery(
	            "SELECT a FROM Account a WHERE a.student.studentID = :studentID", Account.class);
	    query.setParameter("studentID", studentID);
	    return query.getResultList();
	}
	public boolean updateAccount(Account account) {
		try {
	        em.merge(account);
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
    }
	public void deleteAccount(int accountID) {
 	}

//TODO: Create Business Logic for operations Withdraw, Deposit & Transfer
	public boolean withdraw(int accountId, float amount) {
		Account account = em.find(Account.class, accountId);
		if (account != null && account.getAccountBalance() >= amount){
			// Set balance to be amount less than before
			account.setAccountBalance(account.getAccountBalance() - amount);
			em.merge(account);
			return true;
		}
		return false; // Insufficient Funds
	}
	public boolean deposit(int accountId, float amount) {
		// Literally the same as withdraw except + amount
		Account account = em.find(Account.class, accountId);
		if (account != null && account.getAccountBalance() >= amount){
			// Set balance to be amount less than before
			account.setAccountBalance(account.getAccountBalance() + amount);
			em.merge(account);
			return true;
		}
		return false; // Insufficient Funds
	}
	public boolean transfer(int fromAccountID, int toAccountID, float amount) {
		return false;
	}
}