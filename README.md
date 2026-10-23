# Zoo Management System

An Object-Oriented Java simulation application designed to model a comprehensive zoo ecosystem. The system handles animal species requirements, visitor registries, personnel maintenance tasks, food stock consumption tracking, and custom exception validations.

## Features

- **Polymorphic Class Hierarchy:** Models species-specific diets and habitats via inheritance (`Animal` -> `Lion`, `Elephant`, `Penguin`, `Chimpanzee`) and dynamic age-based food calculation formulas.
- **Role-Based Authority (Person Model):** Differentiates between `Visitor` and `Personnel` permissions (e.g., visitors are blocked from feeding animals and can only visit).
- **Food Stock & Inventory Management:** Tracks `Meat`, `Fish`, and `Plant` stocks, validating inventory sufficiency before fulfilling feeding commands.
- **Custom Exception Control:** Enforces strict domain logic using custom exceptions (`PersonNotFoundException`, `UnauthorizedOperationException`, `InsufficientFoodException`).
- **Command-Driven Processing Engine:** Parses external files to process event workflows like visitation, habitat cleaning, and feeding operations sequentially.

## Tech Stack

- **Language:** Java 8+
- **Core Concepts:** Object-Oriented Programming (OOP), Polymorphism, Abstract Classes, File I/O, Custom Exception Handling, Collections Framework (`HashMap`)

## How to Run

Compile and execute the program with input datasets:

javac *.java
java Main animals.txt persons.txt foods.txt commands.txt output.txt
