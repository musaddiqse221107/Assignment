package edu.dsu.scd.lms;

import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class Library {
    private final List<Book> books = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<Loan> loans = new ArrayList<>();
    private final Clock clock;

    public Library(Clock clock) {
        if (clock == null) throw new InvalidInputException("Clock is required.");
        this.clock = clock;
    }

    public void addBook(Book book) {
        if (book == null) throw new InvalidInputException("Book is required.");
        if (books.contains(book)) throw new InvalidInputException("ISBN already exists.");
        books.add(book);
    }

    public void registerMember(Member member) {
        if (member == null) throw new InvalidInputException("Member is required.");
        if (members.stream().anyMatch(m -> m.getMemberId().equals(member.getMemberId()))) {
            throw new InvalidInputException("Member ID already exists.");
        }
        members.add(member);
    }

    public List<Book> searchBooks(String query) {
        String normalized = normalizeSearchQuery(query);
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase(Locale.ROOT).contains(normalized)
                        || book.getAuthor().toLowerCase(Locale.ROOT).contains(normalized))
                .toList();
    }

    public Loan borrowBook(String isbn, String memberId) {
        Book book = findBook(isbn);
        findMember(memberId);
        book.borrowCopy();
        Loan loan = new Loan(book, findMember(memberId), LocalDate.now(clock));
        loans.add(loan);
        return loan;
    }

    public double returnBook(Loan loan, LocalDate returnDate) {
        if (loan == null || returnDate == null) throw new InvalidInputException("Loan and return date are required.");
        loan.markReturned();
        loan.getBook().returnCopy();
        return loan.lateFee(returnDate);
    }

    public List<Loan> overdueLoans() {
        return loans.stream().filter(loan -> loan.isOverdue(clock)).toList();
    }

    private String normalizeSearchQuery(String query) {
        if (query == null || query.isBlank()) throw new InvalidInputException("Search query is required.");
        return query.trim().toLowerCase(Locale.ROOT);
    }

    private Book findBook(String isbn) {
        return books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst()
                .orElseThrow(() -> new InvalidInputException("Book not found: " + isbn));
    }

    private Member findMember(String memberId) {
        return members.stream().filter(m -> m.getMemberId().equals(memberId)).findFirst()
                .orElseThrow(() -> new InvalidInputException("Member not found: " + memberId));
    }
}
