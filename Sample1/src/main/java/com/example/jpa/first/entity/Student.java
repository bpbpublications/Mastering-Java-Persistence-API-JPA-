package com.example.jpa.first.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

//Named queries defined so that it can be used by the other classes directly on the entity. 
@NamedQueries({ @NamedQuery(name = "findStudByFirstName", query = "from Student s where s.firstName = :name"),
		@NamedQuery(name = "findStudByLastName", query = "from Student s where s.lastName = :name"),
		@NamedQuery(name = "findStudByStd", query = "from Student s where s.standard = :std") })
@Entity
@Table(name = "Student")
public class Student implements Serializable {

	
	private static final long serialVersionUID = -8772916061500670014L;

	//Id generated using the sequence style generator so that the initial value and increment size are set. 
	@Id
	@GeneratedValue(generator = "seq")
    @GenericGenerator(
      name = "seq",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = "student_id"),
        @Parameter(name = "initial_value", value = "1001"),
        @Parameter(name = "increment_size", value = "1")
        }
    )
	private int id;

	@Column(name = "fName")
	private String firstName;

	@Column(name = "lName")
	private String lastName;

	@Column(name = "std")
	private String standard;

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String toString() {
		return "First name = " + this.firstName + " Last Name = " + this.lastName + " Standard = " + this.standard;
	}
}
