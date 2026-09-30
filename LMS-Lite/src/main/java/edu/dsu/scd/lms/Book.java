package edu.dsu.scd.lms;

import java.util.Objects;

public final class Book {
    private final String title;
    private final String author;
    private final String isbn;
    private final int totalCopies;
    private int availableCopies;

    public Book(String title, String author, String isbn, int totalCopies) {
        validateDetails(title, author, isbn, totalCopies);
        this.title = title.trim();
        this.author = author.trim();
        this.isbn = isbn.trim();
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    private static void validateDetails(String title, String author, String isbn, int totalCopies) {
        if (isBlank(title) || isBlank(author) || isBlank(isbn)) {
            throw new InvalidInputException("Title, author and ISBN are required.");
        }
        if (totalCopies <= 0) {
            throw new InvalidInputException("Total copies must be positive.");
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getTotalCopies() { return totalCopies; }
    public int getAvailableCopies() { return availableCopies; }

    public boolean hasAvailableCopy() { return availableCopies > 0; }

    public void borrowCopy() {
        if (!hasAvailableCopy()) {
            throw new BookUnavailableException("No available copy for ISBN " + isbn + ".");
        }
        availableCopies--;
    }

    public void returnCopy() {
        if (availableCopies >= totalCopies) {
            throw new InvalidInputException("All copies are already in the library.");
        }
        availableCopies++;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Book book)) return false;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() { return Objects.hash(isbn); }
}
