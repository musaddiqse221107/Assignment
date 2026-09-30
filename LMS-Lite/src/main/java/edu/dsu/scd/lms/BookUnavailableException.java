package edu.dsu.scd.lms;

public class BookUnavailableException extends IllegalStateException {
    public BookUnavailableException(String message) { super(message); }
}
