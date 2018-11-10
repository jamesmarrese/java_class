package edu.jamesmarrese.advancedjava.service;

import edu.jamesmarrese.advancedjava.model.Person;
import edu.jamesmarrese.advancedjava.model.PersonQuotes;
import edu.jamesmarrese.advancedjava.model.Quotes;
import edu.jamesmarrese.advancedjava.util.DatabaseUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Methods for getting a list of people and their quotes from a SQL database
 */
public class DatabasePersonService implements PersonService {

    /**
     * Get a list of all people
     *
     * @return a list of Person instances
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getPerson() throws PersonServiceException {
        Session session = DatabaseUtils.getSessionFactory().openSession();
        List<Person> returnValue = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class);

            returnValue = criteria.list();

        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();  // close transaction
            }
            throw new PersonServiceException("Could not get Person data. " + e.getMessage(), e);
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }

        return returnValue;

    }

    /**
     * Get a list of all a person's stock quotes.
     *
     * @param person the person
     * @return a list of quote instances
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Quotes> getPersonQuotes(Person person) throws PersonServiceException {
        Session session =  DatabaseUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Quotes> quotes = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(PersonQuotes.class);
            criteria.add(Restrictions.eq("person", person));

            List<PersonQuotes> list = criteria.list();
            for (PersonQuotes personQuotes : list) {
                quotes.add(personQuotes.getQuote());
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();  // close transaction
            }
            throw new PersonServiceException("Could not get Person data. " + e.getMessage(), e);
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }
        return quotes;

    }

}
