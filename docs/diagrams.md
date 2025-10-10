## UML Diagrams (Mermaid)

### Use Case Diagram
Note: Mermaid does not have a native UML Use Case syntax; we approximate with a flowchart and labeled relationships.

```mermaid
flowchart LR
  actorCustomer([Customer])
  actorClerk([Bank Clerk])
  actorScheduler([System Scheduler])

  subgraph System[Banking System]
    UC_Login((Login))
    UC_Register((Register Customer))
    UC_OpenAccount((Open Account))
    UC_Deposit((Deposit Funds))
    UC_Withdraw((Withdraw Funds))
    UC_ViewBalance((View Balances))
    UC_ListAccounts((List Accounts))
    UC_ViewTx((View Transactions))
    UC_PayInterest((Pay Monthly Interest))

    UC_Auth((Authenticate Credentials))
    UC_RecordTx((Record Transaction))
    UC_ValidateBalance((Validate Available Balance))
  end

  actorCustomer --> UC_Login
  actorCustomer --> UC_Deposit
  actorCustomer --> UC_Withdraw
  actorCustomer --> UC_ViewBalance
  actorCustomer --> UC_ListAccounts
  actorCustomer --> UC_ViewTx

  actorClerk --> UC_Register
  actorClerk --> UC_OpenAccount

  actorScheduler --> UC_PayInterest

  UC_Login -- <<include>> --> UC_Auth
  UC_Deposit -- <<include>> --> UC_RecordTx
  UC_Withdraw -- <<include>> --> UC_ValidateBalance
  UC_Withdraw -- <<include>> --> UC_RecordTx
  UC_PayInterest -- <<include>> --> UC_RecordTx
```

### Class Diagram

```mermaid
classDiagram
  direction LR

  class Customer {
    - UUID id
    - String firstName
    - String surname
    - String address
    - EmploymentInfo employmentInfo
    + getId() UUID
    + getFirstName() String
    + getSurname() String
    + getAddress() String
    + getEmploymentInfo() EmploymentInfo
  }

  class EmploymentInfo {
    - String companyName
    - String companyAddress
    + getCompanyName() String
    + getCompanyAddress() String
  }

  class Account {
    <<abstract>>
    - String accountNumber
    - BigDecimal balance
    - String branchCode
    - Customer holder
    + getAccountNumber() String
    + getBalance() BigDecimal
    + getBranchCode() String
    + getHolder() Customer
    + deposit(amount: BigDecimal) void
    + deposit(amount: double) void
    + withdraw(amount: BigDecimal) void
  }

  class InterestBearing {
    <<interface>>
    + calculateMonthlyInterest() BigDecimal
    + applyMonthlyInterest() void
  }

  class SavingsAccount {
    - static monthlyRate = 0.0005
    + calculateMonthlyInterest() BigDecimal
    + applyMonthlyInterest() void
    + withdraw(amount: BigDecimal) void
  }

  class InvestmentAccount {
    - static monthlyRate = 0.05
    + calculateMonthlyInterest() BigDecimal
    + applyMonthlyInterest() void
  }

  class ChequeAccount {
  }

  class Bank {
    - Map~String, Account~ accountNumberToAccount
    - Map~UUID, Customer~ idToCustomer
    + registerCustomer(firstName:String, surname:String, address:String, employment:EmploymentInfo): Customer
    + openSavingsAccount(customer:Customer, branch:String): SavingsAccount
    + openInvestmentAccount(customer:Customer, branch:String, initialDeposit:BigDecimal): InvestmentAccount
    + openChequeAccount(customer:Customer, branch:String): ChequeAccount
    + deposit(accountNumber:String, amount:BigDecimal): void
    + withdraw(accountNumber:String, amount:BigDecimal): void
    + payMonthlyInterest(): void
    + getAccounts(customer:Customer): List~Account~
  }

  class InvalidAmountException
  class InsufficientFundsException
  class OperationNotAllowedException
  class EmploymentInformationRequiredException
  class MinimumInitialDepositException
  class AccountNotFoundException

  Customer *-- Account : owns
  Bank o-- Customer
  Bank o-- Account

  Account <|-- SavingsAccount
  Account <|-- InvestmentAccount
  Account <|-- ChequeAccount

  InterestBearing <|.. SavingsAccount
  InterestBearing <|.. InvestmentAccount
```

### Sequence Diagram: Login

```mermaid
sequenceDiagram
  autonumber
  actor U as User
  participant LV as LoginView (GUI)
  participant AS as AuthService
  participant UR as UserRepository

  U->>LV: Enter username & password
  LV->>AS: authenticate(username, password)
  AS->>UR: findByUsername(username)
  UR-->>AS: UserRecord / not found
  AS->>AS: verifyPassword(hash, password)
  AS-->>LV: AuthResult (success/failure)
  LV-->>U: Show dashboard or error
```

### Sequence Diagram: Deposit Funds

```mermaid
sequenceDiagram
  autonumber
  actor C as Customer
  participant AV as AccountView (GUI)
  participant B as Bank
  participant A as Account

  C->>AV: Enter amount, select account
  AV->>B: deposit(accountNumber, amount)
  B->>A: deposit(amount)
  A->>A: validatePositive(amount)
  A->>A: balance = balance + amount
  A-->>B: OK
  B-->>AV: OK
  AV-->>C: Show updated balance
```

### State Diagram: Pay Monthly Interest

```mermaid
stateDiagram-v2
  [*] --> Idle
  Idle --> Scheduled : scheduleTriggered
  Scheduled --> Calculating : startBatch
  Calculating --> Applying : per-account interest computed
  Applying --> Recording : credit interest transactions
  Recording --> Completed : all accounts updated
  Completed --> [*]
  Calculating --> Failed : validationError / systemError
  Applying --> Failed : postingError
  Recording --> Failed : writeError
  Failed --> Idle : retry/backoff
```
