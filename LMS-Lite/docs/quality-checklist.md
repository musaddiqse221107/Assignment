# Quality Checklist

- Encapsulation: domain state is private.
- Defensive validation: invalid names, IDs, copy counts, dates and missing entities fail fast.
- Testing: JUnit 5 tests cover normal, boundary and invalid cases.
- Deterministic time: Library accepts an injected `Clock`.
- Coverage: JaCoCo is configured with a 70% instruction coverage gate.
- Static analysis: Checkstyle is configured in the Maven build.
- Refactoring: three refactoring entries are documented.
