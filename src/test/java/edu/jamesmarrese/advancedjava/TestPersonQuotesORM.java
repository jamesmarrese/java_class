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
public class TestPersonQuotesORM {

    /**
     * Testing helper method for generating PersonQuotes test data
     *
     * @return a PersonQuotes object that uses Person and Hobby
     * return from their respective create method.
     */
    public static PersonQuotes createPersonQuotes() {
        Person person = TestPersonORM.createPerson();
        Quotes quotes = TestQuotesORM.createQuotes();
        return new PersonQuotes(person, quotes);
    }

    @Test
    public void testPersonQuotesGetterAndSetters() {
        Quotes quotes = TestQuotesORM.createQuotes();
        Person person = TestPersonORM.createPerson();
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
        Quotes quotes = TestQuotesORM.createQuotes();
        Person person = new Person();
        Timestamp birthDate = new Timestamp(TestPersonORM.birthday.getTimeInMillis() + 10000);
        person.setBirthDate(birthDate);
        person.setFirstName(TestPersonORM.firstName);
        person.setLastName(TestPersonORM.lastName);

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
        assertTrue("toString has last name", personQuotes.toString().contains(TestPersonORM.lastName));
        assertTrue("toString has first name", personQuotes.toString().contains(TestPersonORM.firstName));
        assertTrue("toString has birthdate", personQuotes.toString().contains(TestPersonORM.birthDate.toString()));
        assertTrue("toString has stock symbol", personQuotes.toString().contains(TestQuotesORM.symbol));
        assertTrue("toString has stock price", personQuotes.toString().contains(TestQuotesORM.price.toString()));
        assertTrue("toString has stock timestamp", personQuotes.toString().contains(TestQuotesORM.time.toString()));
    }

}
