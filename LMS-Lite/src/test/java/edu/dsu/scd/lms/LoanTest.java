package edu.dsu.scd.lms;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoanTest {
    private final Book book = new Book("T", "A", "I", 1);
    private final Member member = new Member("Alice", "M1");

    @Test void dueDateIsFourteenDaysAfterBorrow() {
        Loan loan = new Loan(book, member, LocalDate.of(2026, 1, 14));
        assertEquals(LocalDate.of(2026, 1, 28), loan.getDueDate());
    }
    @Test void exactDueDateIsNotOverdue() {
        Loan loan = new Loan(book, member, LocalDate.of(2026, 1, 14));
        assertFalse(loan.isOverdue(LocalDate.of(2026, 1, 28)));
    }
    @Test void lateFeeIsZeroOnTime() {
        Loan loan = new Loan(book, member, LocalDate.of(2026, 1, 14));
        assertEquals(0, loan.lateFee(LocalDate.of(2026, 1, 28)));
    }
    @Test void lateFeeForOneDay() {
        Loan loan = new Loan(book, member, LocalDate.of(2026, 1, 14));
        assertEquals(10, loan.lateFee(LocalDate.of(2026, 1, 29)));
    }
    @Test void lateFeeForThreeDays() {
        Loan loan = new Loan(book, member, LocalDate.of(2026, 1, 14));
        assertEquals(30, loan.lateFee(LocalDate.of(2026, 1, 31)));
    }
    @Test void returnedLoanIsNotOverdue() {
        Loan loan = new Loan(book, member, LocalDate.of(2026, 1, 14));
        loan.markReturned();
        assertTrue(loan.isReturned());
        assertFalse(loan.isOverdue(LocalDate.of(2026, 2, 10)));
    }
}