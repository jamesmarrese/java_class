package edu.jamesmarrese.advancedjava.service;

/**
 * This enum class specifies an interval at which StockQuotes
 * can be called (hourly, daily, or weekly).
 */

public enum IntervalEnum {

    /** Get one StockQuote for every daily */
    DAILY,

    /** Get one StockQuote for every week */
    WEEKLY,

    /** Get one StockQuote for every month */
    MONTHLY;
}