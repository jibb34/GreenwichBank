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
	public List<Student> getAllStudents() {
		return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
	}
	public Student findStudentByID(int studentId) {
		TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.id = :id", Student.class);
		query.setParameter("id", studentId);
		return query.getSingleResult();
	}

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
	public List<Account> getAllAccounts() {
		return em.createQuery("Select a FROM Account a", Account.class).getResultList();
	}

	public boolean createAccount(Account account) {
		return false;
		
	}
	public void updateAccount(Account account) {

    }
	public void deleteAccount(int accountID) {
 	}
//TODO: Create Business Logic for operations Withdraw, Deposit & Transfer
	public boolean withdraw(int accountId, float amount) {
		return false;
	}
	public boolean deposit(int accountId, float amount) {
		return false;                    
	}
	public boolean transfer(int fromAccountID, int toAccountID, float amount) {
		return false;
	}
}