# LMS-Lite — Library Management System

DHA Suffa University — Software Construction & Development Project-Based Midterm.

## Requirements implemented
- Add and search books by title or author.
- Register library members.
- Borrow a book with a 14-day due date.
- Return a book and calculate late fees.
- List currently overdue loans.
- Defensive validation with custom exceptions.
- JUnit 5 tests, deterministic injected Clock, JaCoCo coverage and Checkstyle.

## Build
Requires JDK 21 and Maven 3.9+.

```bash
mvn clean verify
```

JaCoCo output is generated at `target/site/jacoco/index.html`.

## Project structure
- `src/main/java` — application and domain code
- `src/test/java` — unit tests
- `docs/` — specifications, refactoring log, TDD evidence and workflow notes
