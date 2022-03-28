package com.example.jpa.first;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.example.jpa.first.entity.Student;

public class MainApp {

	private static final String PERSISTENCE_UNIT_NAME = "psfirst";
	private static EntityManagerFactory emFactory;
	private static EntityManager em;

	public static void main(String[] args) {

		MainApp app = new MainApp();
		app.initialize();
		app.insertData();
		app.selectBy("fName", "ABC");
		app.selectBy("lName", "XYZ");
		app.selectBy("std", "5");
		em.close();
	}

	//Initialize the entity manager
	private void initialize() {
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = emFactory.createEntityManager();
	}

	//Inserts data into the table
	private void insertData() {
		em.getTransaction().begin();
		Student student = new Student();
		student.setFirstName("ABC");
		student.setLastName("DEF");
		student.setStandard("5");
		em.persist(student);
		student = new Student();
		student.setFirstName("MNO");
		student.setLastName("PQR");
		student.setStandard("5");
		em.persist(student);
		student = new Student();	
		student.setFirstName("UVW");
		student.setLastName("XYZ");
		student.setStandard("5");
		em.persist(student);
		em.getTransaction().commit();
	}

	// Queries the data based on the named queries already defined in the entity.
	@SuppressWarnings("unchecked")
	private void selectBy(String type, String value) {
		Session session = em.unwrap(Session.class);
		switch (type) {
		case "fName": {

			Query query = session.getNamedQuery("findStudByFirstName");
			query.setParameter("name", value);
			List<Student> students = query.list();
			System.out.println("Students with first name as '" + value + "' is " + students.toString());
			break;
		}
		case "lName": {
			Query query = session.getNamedQuery("findStudByLastName");
			query.setParameter("name", value);
			List<Student> students = query.list();
			System.out.println("Students with last name as '" + value + "' is " + students.toString());
			break;
		}
		case "std": {
			Query query = session.getNamedQuery("findStudByStd");
			query.setParameter("std", value);
			List<Student> students = query.list();
			System.out.println("Students with Standard as '" + value + "': \n" + students.toString());
			break;
		}
		}
	}
}