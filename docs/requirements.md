## Banking System Requirements

### 1. Functional Requirements
- **Customer registration**: Create a customer with `firstName`, `surname`, `address`, optional `employmentInfo` (company name, company address). Generate a unique `customerId`.
- **User authentication**: Customers can log in using email/ID and password. Passwords are stored securely (hashed). Failed logins are rate-limited.
- **Open account**:
  - **SavingsAccount**: Allows deposits only; no withdrawals. Pays 0.05% monthly interest on balance.
  - **InvestmentAccount**: Allows deposits and withdrawals; requires minimum initial deposit of BWP 500.00; pays 5% monthly interest on balance.
  - **ChequeAccount**: Allows deposits and withdrawals; requires customer to be employed (employment info mandatory). No interest.
  - An account cannot exist without a `Customer` owner.
- **Deposit funds**: Deposit to any owned account; positive amounts only; record transaction.
- **Withdraw funds**: Allowed for Investment and Cheque accounts; blocked for Savings; prevent overdraft; record transaction.
- **View balances**: Show current balance for each account a customer holds.
- **List accounts**: Retrieve all accounts for a given customer.
- **Transaction history**: List deposit/withdrawal/interest transactions per account in reverse chronological order.
- **Monthly interest payment**: Apply interest to all interest-bearing accounts at month-end. Interest credited as a transaction.
- **Account number generation**: System-generated, unique, human-readable account numbers.
- **Error handling**: Clear errors for invalid amounts, insufficient funds, missing employment info, or policy violations.

### 2. Non-Functional Requirements
- **Security**: Protect user data; hash passwords; least-privilege services; validate inputs; audit trail of transactions.
- **Performance**: Common operations (deposit/withdraw/balance) complete under 200ms in typical usage; batch interest posting completes within an hourly window for 100k+ accounts.
- **Reliability**: Operations are atomic; no partial updates; interest posting is idempotent and retryable.
- **Usability**: Simple, self-explanatory GUI with clear affordances; validation with actionable messages.
- **Maintainability**: Modular architecture separating domain, services, and GUI; high cohesion and low coupling; code documented and unit-testable.
- **Scalability**: Service layer designed to scale horizontally; batch jobs chunked; no shared mutable global state.
- **Portability**: Java 17+; JavaFX UI; no platform-specific code in core.
- **Auditability/Compliance**: Immutable transaction log kept for each account; timestamps in UTC; unique IDs for traceability.
- **Internationalization**: Amount formatting configurable; currency BWP by default.

### 3. Appendix: Mock Interview Record (condensed)
- **Q**: Who are the primary users?
  - **A**: Bank customers and bank clerks/admins. Clerks may assist with onboarding; customers use self-service for day-to-day.
- **Q**: What accounts are supported and constraints?
  - **A**: Savings (no withdrawals, 0.05% monthly interest), Investment (5% monthly interest, min opening BWP 500, withdrawals allowed), Cheque (salary account; employment info required; withdrawals allowed; no interest).
- **Q**: Must a customer have multiple accounts?
  - **A**: Yes, a customer may hold one or more accounts of different types.
- **Q**: What about transaction history?
  - **A**: Yes, track deposits, withdrawals, and interest credits per account.
- **Q**: How and when is interest applied?
  - **A**: Monthly. Savings: 0.05% of current balance; Investment: 5% of current balance. Credited as a transaction.
- **Q**: Any authentication requirements?
  - **A**: Yes. Username/password; secure storage; lockout/rate-limits for repeated failures.
- **Q**: Any other business rules?
  - **A**: Prevent overdrafts; validate positive amounts; Cheque account requires employment info; account must be owned by a customer.
- **Q**: Any UI guidance?
  - **A**: Minimal JavaFX UI: login screen, accounts screen (list, deposit, withdraw), clear feedback.
