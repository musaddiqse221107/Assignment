# Late-Fee TDD Evidence

## Cycle 1 — Red / Green / Refactor
- Red: exact due-date return has zero fee.
- Green: implement late-day calculation.
- Refactor: introduce DAILY_LATE_FEE constant.

## Cycle 2 — Red / Green / Refactor
- Red: one day late charges one daily fee.
- Green: calculate days after due date.
- Refactor: use ChronoUnit.DAYS.

## Cycle 3 — Red / Green / Refactor
- Red: three days late charges three daily fees.
- Green: verify multiplication by the named constant.
- Refactor: keep date calculation independent from system time.

Production code injects Clock for current-date behavior and accepts explicit dates for deterministic fee tests.