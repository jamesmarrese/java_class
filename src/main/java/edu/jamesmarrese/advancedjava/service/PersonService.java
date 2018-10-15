package edu.jamesmarrese.advancedjava.service;

import edu.jamesmarrese.advancedjava.model.Person;
import edu.jamesmarrese.advancedjava.model.PersonQuotes;
import edu.jamesmarrese.advancedjava.model.Quotes;
import edu.jamesmarrese.advancedjava.util.DatabaseConnectionException;
import edu.jamesmarrese.advancedjava.util.DatabaseInitializationException;

import java.util.List;

/**
 * An interface for Person objects and stock Quote objects.
 * Stock quote objects can be added to Person objects.
 */
public interface PersonService {

    /**
     * Get a list of all people
     *
     * @return a list of Person instances
     * @throws PersonServiceException if a service can not read or write the requested data
     *                                    or otherwise perform the requested operation.
     */
    List<Person> getPerson() throws PersonServiceException;

    /**
     * Get a list of all a person's stock quotes.
     *
     * @return a list of quote instances
     * @throws PersonServiceException if a service can not read or write the requested data
     *                                    or otherwise perform the requested operation.
     */
    List<Quotes> getQuotes (Person person) throws PersonServiceException;

}
