# Visitor Pattern — File System

**Course:** Software Design Patterns  
**Assignment:** #6 — Visitor Pattern  
**Student:** Nurlankyzy Zhanshuak  
**Build tool:** Maven

> This is a learning implementation. Review the code, run the tests, and make sure the final submission reflects your own understanding and course requirements.

## Idea

The program models a small file-system tree made of `File` and `Directory` elements. Two visitors add independent operations without adding those operations to the element classes:

- `SizeCalculatorVisitor` totals the byte sizes of all files.
- `SearchVisitor` finds file paths whose names contain a query (case-insensitive).

## Project structure

```text
visitor-pattern-assignment/
├── pom.xml
├── README.md
└── src/
    ├── main/java/org/example/
    │   ├── Main.java
    │   └── visitor/
    │       ├── Element.java
    │       ├── Visitor.java
    │       ├── File.java
    │       ├── Directory.java
    │       ├── SizeCalculatorVisitor.java
    │       └── SearchVisitor.java
    └── test/java/org/example/visitor/
        └── VisitorTest.java
```

## Run in IntelliJ IDEA

1. Open the project folder or `pom.xml`.
2. Set the Project SDK to JDK 17 (or a compatible newer JDK with Maven release 17).
3. Wait for Maven to import dependencies.
4. Run `org.example.Main`.
5. Run `VisitorTest` or use the Maven test command below.

## Maven commands

```bash
mvn clean test
mvn exec:java -Dexec.mainClass="org.example.Main"
```

If the `exec:java` command is not configured in your local Maven setup, run `Main` directly from IntelliJ.

## Sample output

```text
Total file size: 6000 bytes
Files containing 'p':
- workspace/documents/report.pdf
- workspace/photos/trip.jpg
- workspace/photos/profile.png
```

## Design notes

- `Element` declares `accept(Visitor)`.
- Each concrete element calls the matching visitor method and passes itself.
- `Directory` stores children and exposes a read-only view.
- Each visitor performs one operation and traverses directories.
- Visitor instances keep operation state. Create a fresh visitor per independent traversal; do not share a visitor concurrently without redesigning its state management.

## UML

Open `UML.puml` with a PlantUML extension or renderer.

## Before submission

- Run all tests and capture your actual output.
- Verify the course's current submission instructions and deadline on Moodle.
- Add your own explanation, screenshots, and repository link to `Report.md`.
- Commit your work incrementally; do not fabricate commit history.
