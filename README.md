## Banking System

This repository contains a simple educational banking system that demonstrates OOP principles and UML modeling.

### Contents
- `docs/requirements.md`: Functional and non-functional requirements, mock interview record
- `docs/diagrams.md`: Mermaid UML diagrams (use case, class, sequence, state)
- Core domain model under `src/main/java/com/example/banking/model`
- Service API under `src/main/java/com/example/banking/service`
- JavaFX UI skeleton under `src/main/java/com/example/banking/ui`

### Build
Maven is required. If using GitHub Codespaces, ensure Maven is installed or use the Maven container.

- Compile:
```bash
mvn -DskipTests package
```

- Run tests:
```bash
mvn test
```

- Run JavaFX demo (Linux classifier):
```bash
mvn -Pwith-javafx javafx:run
```

### Java Version
- Requires Java 17+

### Notes
- Interest: InvestmentAccount pays 5% monthly, SavingsAccount pays 0.05% monthly.
- SavingsAccount does not permit withdrawals.
- ChequeAccount requires employment info to open.
