package edu.dsu.scd.lms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test void createsBookWithAllCopiesAvailable() {
        Book b = new Book("Clean Code", "Robert Martin", "9780132350884", 2);
        assertEquals(2, b.getAvailableCopies());
        assertTrue(b.hasAvailableCopy());
    }
    @Test void rejectsInvalidBook() {
        assertThrows(InvalidInputException.class, () -> new Book("", "Author", "ISBN", 1));
        assertThrows(InvalidInputException.class, () -> new Book("Title", "Author", "ISBN", 0));
    }
    @Test void borrowAndReturnCopies() {
        Book b = new Book("T", "A", "I", 1);
        b.borrowCopy();
        assertFalse(b.hasAvailableCopy());
        assertThrows(BookUnavailableException.class, b::borrowCopy);
        b.returnCopy();
        assertEquals(1, b.getAvailableCopies());
    }
    @Test void cannotReturnWhenAllCopiesPresent() {
        Book b = new Book("T", "A", "I", 1);
        assertThrows(InvalidInputException.class, b::returnCopy);
    }
}
