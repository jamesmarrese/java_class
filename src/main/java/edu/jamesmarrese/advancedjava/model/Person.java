package edu.jamesmarrese.advancedjava.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Models the Person table
 */

@Entity
@Table(name="person", catalog = "stocks")
public class Person {

    private int ID;
    private String firstName;
    private String lastName;
    private Timestamp birthDate;

    /**
     * Empty constructor
     */
    public Person() {}

    /**
     * Primary Key - Unique ID for a particular row in the person table.
     *
     * @return an integer value
     */
    @Id
    @Column(name = "person_ID", nullable = false, insertable = true, updatable = true)
    public int getId() { return ID; }

    /**
     * Set the unique ID for a particular row in the person table.
     *
     * @param ID a unique value.
     */
    public void setID (int ID) {this.ID = ID;}

    /**
     * @return the person's first name
     */
    @Basic
    @Column (name = "first_name", nullable = false, insertable = true, updatable = true, length = 256)
    public String getFirstName () { return firstName;}

    /**
     * Specify the person's first name
     * @param firstName a String value
     */
    public void setFirstName (String firstName) {this.firstName = firstName;}

    /**
     * @return the person's last name
     */
    @Basic
    @Column(name = "last_name", nullable = false, insertable = true, updatable = true, length = (256))
    public String getLastName () {return lastName;}

    /**
     * Specify the person's last name
     * @param lastName a String value
     */
    public void setLastName (String lastName) {this.lastName = lastName;}

    /**
     * @return the person's birthdate.
     */
    @Basic
    @Column(name = "birth_date", nullable = false, insertable = true, updatable = true)
    public Timestamp getBirthDate () {return birthDate;}

    /**
     * Specify the person's date of birth.
     * @param birthDate  the time the person was born.
     */

    public void setBirthDate (Timestamp birthDate) {this.birthDate = birthDate;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (ID != person.ID) return false;
        if (birthDate != null ? !birthDate.equals(person.birthDate) : person.birthDate != null)
            return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null)
            return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

}
