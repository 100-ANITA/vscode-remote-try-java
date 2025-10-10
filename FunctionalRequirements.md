# Functional Requirements

- Customer registration: create and manage a `Customer` with name and address; optional `EmploymentInfo` for working customers.
- Account opening: `SavingsAccount`, `InvestmentAccount` (min initial deposit BWP500.00), `ChequeAccount` (requires `EmploymentInfo`). Unique account numbers.
- Multiple accounts per customer: a customer may hold any combination of accounts.
- Deposits: allowed on all accounts; positive amounts only.
- Withdrawals: allowed on `ChequeAccount` and `InvestmentAccount`; disallowed on `SavingsAccount`; cannot overdraw.
- Interest: monthly application to interest-bearing accounts: `InvestmentAccount` 5%; `SavingsAccount` 0.05%.
- Transaction history (optional future scope): maintain list of operations per account.
- Account inquiry: view balances per account and list customer accounts.
