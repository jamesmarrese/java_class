
/* delete tables if they exist already - ensuring a clean db */
DROP TABLE IF EXISTS stocks.person CASCADE;
DROP TABLE IF EXISTS stocks.quotes CASCADE;
DROP TABLE IF EXISTS stocks.person_quotes CASCADE;

/** creates a table to store a list of persons */
CREATE TABLE stocks.person
(
  person_ID INT PRIMARY KEY NOT NULL,
  first_name VARCHAR(256) NOT NULL,
  last_name VARCHAR(256) NOT NULL,
  birth_date DATETIME NOT NULL
);

/* creates a table to store a list of quotes */
CREATE TABLE stocks.quotes
(
  quotes_ID INT PRIMARY KEY NOT NULL,
  symbol VARCHAR(256) NOT NULL,
  quote_time DATETIME NOT NULL,
  price DECIMAL NOT NULL
);

/** A list of people and their quotes */
CREATE TABLE stocks.person_quotes
(
  person_quotes_ID INT PRIMARY KEY NOT NULL,
  person_id INT NOT NULL,
  quotes_id INT NOT NULL,
  FOREIGN KEY (person_id) REFERENCES person (person_ID),
  FOREIGN KEY (quotes_id) REFERENCES quotes (quotes_ID)
);

/** add some sample data */

INSERT INTO quotes (quotes_ID,symbol,quote_time,price) VALUES (1, 'GOOG', '2018-09-21 00:00:01', 85);
INSERT INTO quotes (quotes_ID,symbol,quote_time,price) VALUES (2, 'GOOG', '2018-09-21 00:00:59', 95);
INSERT INTO quotes (quotes_ID,symbol,quote_time,price) VALUES (3, 'GOOG', '2018-09-21 01:00:01', 105);
INSERT INTO quotes (quotes_ID,symbol,quote_time,price) VALUES (4, 'GOOG', '2018-09-21 02:00:01', 115);
INSERT INTO quotes (quotes_ID,symbol,quote_time,price) VALUES (5, 'GOOG', '2018-09-21 03:00:01', 125);
INSERT INTO quotes (quotes_ID,symbol,quote_time,price) VALUES (6, 'GOOG', '2018-09-22 00:00:01', 135);
INSERT INTO quotes (quotes_ID,symbol,quote_time,price) VALUES (7, 'GOOG', '2018-09-22 01:00:01', 145);
INSERT INTO quotes (quotes_ID,symbol,quote_time,price) VALUES (8, 'GOOG', '2018-09-22 02:00:01', 155);
INSERT INTO quotes (quotes_ID,symbol,quote_time,price) VALUES (9, 'GOOG', '2018-09-22 03:00:01', 165);
INSERT INTO quotes (quotes_ID,symbol,quote_time,price) VALUES (10, 'GOOG', '2018-09-23 00:00:01', 175);
INSERT INTO quotes (quotes_ID,symbol,quote_time,price) VALUES (11, 'GOOG', '2018-09-24 00:00:01', 185);
INSERT INTO quotes (quotes_ID,symbol,quote_time,price) VALUES (12, 'GOOG', '2018-09-25 00:00:01', 195);
INSERT INTO quotes (quotes_ID,symbol,quote_time,price) VALUES (13, 'GOOG', '2018-09-26 00:00:01', 205);
INSERT INTO quotes (quotes_ID,symbol,quote_time,price) VALUES (14, 'GOOG', '2018-09-27 00:00:01', 215);
INSERT INTO quotes (quotes_ID,symbol,quote_time,price) VALUES (15, 'GOOG', '2018-09-28 00:00:01', 225);
INSERT INTO quotes (quotes_ID,symbol,quote_time,price) VALUES (16, 'GOOG', '2018-09-29 00:00:01', 235);
INSERT INTO quotes (quotes_ID,symbol,quote_time,price) VALUES (17, 'GOOG', '2018-09-30 00:00:01', 245);
INSERT INTO quotes (quotes_ID,symbol,quote_time,price) VALUES (18, 'GOOG', '2018-09-30 00:00:01', 255);
INSERT INTO quotes (quotes_ID,symbol,quote_time,price) VALUES (19, 'GOOG', '2018-10-01 00:00:01', 265);
INSERT INTO quotes (quotes_ID,symbol,quote_time,price) VALUES (20, 'GOOG', '2018-10-02 00:00:01', 275);

INSERT INTO person (person_ID,first_name,last_name,birth_date) VALUES (1, 'Drew', 'Hope', '1999/10/10');
INSERT INTO person (person_ID,first_name,last_name,birth_date) VALUES (2, 'Lang', 'Heckman', '1959/11/11');
INSERT INTO person (person_ID,first_name,last_name,birth_date) VALUES (3, 'Lucy', 'Jones', '2010/1/1');
INSERT INTO person (person_ID,first_name,last_name,birth_date) VALUES (4, 'Stew', 'Hammer', '1990/3/28');
INSERT INTO person (person_ID,first_name,last_name,birth_date) VALUES (5, 'Dan', 'Lane', '1986/4/18');
INSERT INTO person (person_ID,first_name,last_name,birth_date) VALUES (6, 'James', 'Marrese', '1915/10/18');

INSERT INTO person_quotes (person_quotes_ID, person_id, quotes_id) VALUES (1, 1, 2);
INSERT INTO person_quotes (person_quotes_ID, person_id, quotes_id) VALUES (2, 1, 1);
INSERT INTO person_quotes (person_quotes_ID, person_id, quotes_id) VALUES (3, 2, 1);
INSERT INTO person_quotes (person_quotes_ID, person_id, quotes_id) VALUES (4, 3, 1);
INSERT INTO person_quotes (person_quotes_ID, person_id, quotes_id) VALUES (5, 3, 3);
INSERT INTO person_quotes (person_quotes_ID, person_id, quotes_id) VALUES (6, 3, 4);
INSERT INTO person_quotes (person_quotes_ID, person_id, quotes_id) VALUES (7, 4, 7);


/* delete table if it exists already - ensuring a clean db */
DROP TABLE IF EXISTS stocks.stock CASCADE;

/* creates a table to store a list of stocks */
CREATE TABLE stocks.stock
(
  stock_ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  symbol VARCHAR(256) NOT NULL,
  stock_time DATETIME NOT NULL,
  price DECIMAL NOT NULL
);