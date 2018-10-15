package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.model.Person;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Calendar;

import static junit.framework.Assert.assertEquals;

/**
 * Tests for the Person ORM class
 */
public class TestPersonORM {

    public  static final Calendar birthday = Calendar.getInstance();

    static {
        birthday.set(1915, Calendar.OCTOBER, 18);
    }

    public static final String firstName = "James";
    public static final String lastName = "Marrese";
    public static final Timestamp birthDate = new Timestamp(birthday.getTimeInMillis());

    /**
     * Testing helper method for generating Person test data
     *
     * @return a Person object that uses static constants for data.
     */
    public static Person createPerson() {
        Person person = new Person();
        person.setBirthDate(birthDate);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        return person;
    }

    @Test
    public void testPersonGettersAndSetters() {
        Person person = createPerson();
        int id = 10;
        person.setID(id);
        assertEquals("first name matches", firstName, person.getFirstName());
        assertEquals("last name matches", lastName, person.getLastName());
        assertEquals("birthday matches", birthDate, person.getBirthDate());
        assertEquals("id matches", id, person.getId());

    }

}
