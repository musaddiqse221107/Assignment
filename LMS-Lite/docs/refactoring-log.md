# Refactoring Log

## 1. Extract Method — Book validation
**Smell:** constructor mixed boundary validation and field initialization.
**After:** `validateDetails(...)` owns boundary checks before initialization.

## 2. Extract Method — Search normalization
**Smell:** search input validation/normalization mixed with catalog filtering.
**After:** `normalizeSearchQuery(...)` prepares the query before filtering.

## 3. Extract Method — Availability decision
**Smell:** copy availability and failure handling were coupled in borrowing logic.
**After:** `Book.borrowCopy()` owns the availability invariant and throws a named exception.

All refactorings are intended to preserve behavior through the unit test suite.