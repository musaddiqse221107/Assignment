package edu.dsu.scd.lms;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    private final Clock clock = Clock.fixed(Instant.parse("2026-01-20T00:00:00Z"), ZoneOffset.UTC);

    @Test void searchesByTitleOrAuthor() {
        Library library = new Library(clock);
        library.addBook(new Book("Clean Code", "Robert Martin", "I1", 1));
        assertEquals(1, library.searchBooks("clean").size());
        assertEquals(1, library.searchBooks("martin").size());
    }
    @Test void borrowCreatesFourteenDayLoan() {
        Library library = new Library(clock);
        library.addBook(new Book("T", "A", "I1", 1));
        library.registerMember(new Member("Alice", "M1"));
        Loan loan = library.borrowBook("I1", "M1");
        assertEquals(LocalDate.of(2026, 2, 3), loan.getDueDate());
    }
    @Test void returnCalculatesLateFeeAndRestoresCopy() {
        Library library = new Library(clock);
        Book b = new Book("T", "A", "I1", 1);
        library.addBook(b); library.registerMember(new Member("A", "M1"));
        Loan loan = library.borrowBook("I1", "M1");
        assertEquals(10, library.returnBook(loan, LocalDate.of(2026, 2, 4)));
        assertEquals(1, b.getAvailableCopies());
    }
    @Test void overdueListUsesInjectedClock() {
        Clock late = Clock.fixed(Instant.parse("2026-02-04T00:00:00Z"), ZoneOffset.UTC);
        Library library = new Library(late);
        library.addBook(new Book("T", "A", "I1", 1));
        library.registerMember(new Member("A", "M1"));
        library.borrowBook("I1", "M1");
        assertEquals(1, library.overdueLoans().size());
    }
}
