# Banking System - Requirements

## Functional Requirements
- Customer registration: create `Customer` with personal details and assign unique ID.
- Open accounts: Savings, Investment (min BWP500 opening), Cheque (requires employment details).
- Deposit funds: allow deposits to all account types.
- Withdraw funds: allowed for Investment and Cheque; disallowed for Savings.
- Interest accrual: monthly interest of 5% for Investment, 0.05% for Savings.
- Account lookup: find account by number.
- Apply monthly interest: batch operation across all interest-bearing accounts.

## Non-Functional Requirements
- Security: no sensitive data persisted; enforce input validation; exceptions for invalid ops.
- Performance: in-memory operations O(1) for account lookup; suitable for small scale.
- Usability: clear error messages; simple API.
- Reliability: precise monetary calculations using `BigDecimal` with 2-decimal scale.
- Maintainability: layered design (model, service, util), interfaces for extension.
- Testability: unit tests covering key rules and flows.

## Interview Summary (Mock)
- Client prioritizes: simple opening of accounts, rule enforcement, and interest runs.
- Constraints: no GUI/DB now; CLI demo acceptable.
- Future: authentication and transaction history may be added later.
