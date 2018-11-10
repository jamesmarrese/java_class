package edu.jamesmarrese.advancedjava;

import edu.jamesmarrese.advancedjava.model.Person;
import edu.jamesmarrese.advancedjava.model.PersonQuotes;
import edu.jamesmarrese.advancedjava.model.Quotes;
import org.junit.Test;

import java.sql.Timestamp;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for PersonQuotes class
 */
public class TestPersonQuotes {

    /**
     * Testing helper method for generating PersonQuotes test data
     *
     * @return a PersonQuotes object that uses Person and Hobby
     * return from their respective create method.
     */
    public static PersonQuotes createPersonQuotes() {
        Person person = TestPerson.createPerson();
        Quotes quotes = TestQuotes.createQuotes();
        return new PersonQuotes(person, quotes);
    }

    @Test
    public void testPersonQuotesGetterAndSetters() {
        Quotes quotes = TestQuotes.createQuotes();
        Person person = TestPerson.createPerson();
        PersonQuotes personQuotes = new PersonQuotes();
        int id = 10;
        personQuotes.setID(id);
        personQuotes.setPerson(person);
        personQuotes.setQuotes(quotes);
        assertEquals("person matches", person, personQuotes.getPerson());
        assertEquals("quotes match", quotes, personQuotes.getQuote());
        assertEquals("id matches", id, personQuotes.getID());
    }

    @Test
    public void testEqualsNegativeDifferentPerson() {
        PersonQuotes personQuotes = createPersonQuotes();
        personQuotes.setID(10);
        Quotes quotes = TestQuotes.createQuotes();
        Person person = new Person();
        Timestamp birthDate = new Timestamp(TestPerson.birthday.getTimeInMillis() + 10000);
        person.setBirthDate(birthDate);
        person.setFirstName(TestPerson.firstName);
        person.setLastName(TestPerson.lastName);

        PersonQuotes personQuotes2 = new PersonQuotes(person, quotes);
        assertFalse("Different person", personQuotes.equals(personQuotes2));
    }

    @Test
    public void testEquals() {
        PersonQuotes personQuotes = createPersonQuotes();
        assertTrue("Same objects are equal", personQuotes.equals(createPersonQuotes()));
    }

    @Test
    public void testToString() {
        PersonQuotes personQuotes = createPersonQuotes();
        assertTrue("toString has last name", personQuotes.toString().contains(TestPerson.lastName));
        assertTrue("toString has first name", personQuotes.toString().contains(TestPerson.firstName));
        assertTrue("toString has birthdate", personQuotes.toString().contains(TestPerson.birthDate.toString()));
        assertTrue("toString has stock symbol", personQuotes.toString().contains(TestQuotes.symbol));
        assertTrue("toString has stock price", personQuotes.toString().contains(TestQuotes.price.toString()));
        assertTrue("toString has stock timestamp", personQuotes.toString().contains(TestQuotes.time.toString()));
    }

}
