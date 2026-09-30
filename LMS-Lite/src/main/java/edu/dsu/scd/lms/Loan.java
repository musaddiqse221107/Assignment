package edu.dsu.scd.lms;

import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class Loan {
    public static final int LOAN_DAYS = 14;
    public static final double DAILY_LATE_FEE = 10.0;

    private final Book book;
    private final Member member;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;
    private boolean returned;

    public Loan(Book book, Member member, LocalDate borrowDate) {
        if (book == null || member == null || borrowDate == null) {
            throw new InvalidInputException("Book, member and borrow date are required.");
        }
        this.book = book;
        this.member = member;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(LOAN_DAYS);
    }

    public Book getBook() { return book; }
    public Member getMember() { return member; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }
    public boolean isReturned() { return returned; }

    public void markReturned() {
        if (returned) {
            throw new InvalidInputException("Loan has already been returned.");
        }
        returned = true;
    }

    public boolean isOverdue(LocalDate currentDate) {
        if (currentDate == null) throw new InvalidInputException("Current date is required.");
        return !returned && currentDate.isAfter(dueDate);
    }

    public boolean isOverdue(Clock clock) {
        if (clock == null) throw new InvalidInputException("Clock is required.");
        return isOverdue(LocalDate.now(clock));
    }

    public double lateFee(LocalDate returnDate) {
        if (returnDate == null) throw new InvalidInputException("Return date is required.");
        long lateDays = Math.max(0, ChronoUnit.DAYS.between(dueDate, returnDate));
        return lateDays * DAILY_LATE_FEE;
    }
}
