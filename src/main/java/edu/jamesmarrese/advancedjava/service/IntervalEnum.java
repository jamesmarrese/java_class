package edu.jamesmarrese.advancedjava.service;

/**
 * This enum class specifies an interval at which StockQuotes
 * can be called (hourly, daily, or weekly).
 */

public enum IntervalEnum {

    /** Get one StockQuote for every hour */
    HOURLY,

    /** Get one StockQuote for every day */
    DAILY,

}