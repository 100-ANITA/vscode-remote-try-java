# Non-Functional Requirements

- Security: input validation, no negative/zero amounts; encapsulated balances; future authentication/authorization.
- Performance: operations complete in O(1) per account; handle thousands of customers/accounts in-memory.
- Reliability: clear exceptions for invalid operations; deterministic arithmetic using `BigDecimal` and scale(2).
- Usability: clear APIs in `Bank` service; meaningful error messages.
- Maintainability: layered model (`model`, `service`, `exception`), interfaces (`InterestBearing`), abstraction (`Account`).
- Testability: JUnit tests covering rules and interest logic.
- Portability: Java 11, Maven build.
- Compliance: respects business constraints (minimum deposit, employment info).
