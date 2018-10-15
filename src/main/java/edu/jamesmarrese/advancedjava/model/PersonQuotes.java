package edu.jamesmarrese.advancedjava.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Models the Person_Quotes table, which combines person with quotes
 */

@Entity
@Table(name="person_quotes", catalog = "stocks")

public class PersonQuotes {
    private int ID;
    private Person person;
    private Quotes quote;

    /**
     * Create a PersonQuotes that needs to be initialized
     */
    public PersonQuotes () {}

    /**
     * Create a valid PersonQuotes
     *
     * @param person the person to assign the quote to
     * @param quote the quote to associate the person with
     */
    public PersonQuotes(Person person, Quotes quote) {
        setPerson(person);
        setQuotes(quote);
    }

    /**
     * Primary Key - Unique ID for a particular row in the person_quotes table.
     *
     * @return an integer value
     */
    @Id
    @Column(name = "person_quotes_ID", nullable = false, insertable = true, updatable = true)
    public int getID() {return ID;}

    /**
     * Set the unique ID for a particular row in the person_quotes table.
     * This method should not be called by client code. The value is managed in internally.
     *
     * @param ID a unique value.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     *
     * @return get the Person associated with this quote
     */
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "ID", nullable = false)
    public Person getPerson() {
        return person;
    }

    /**
     * Specify the Person associated with the quote.
     *
     * @param person a person instance
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     *
     * @return get the Quotes associated with this Person
     */
    @ManyToOne
    @JoinColumn(name = "quotes_id", referencedColumnName = "ID", nullable = false)
    public Quotes getQuote() {
        return quote;
    }

    /**
     * Specify the Quotes associated with the Person.
     *
     * @param quote a person instance
     */
    public void setQuotes(Quotes quote) {
        this.quote = quote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonQuotes that = (PersonQuotes) o;

        if (ID != that.ID) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + person.hashCode();
        result = 31 * result + quote.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PersonQuotes{" +
                "person_quotes_ID=" + ID +
                ", person=" + person +
                ", quote=" + quote +
                '}';
    }

}
