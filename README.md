# Simple Programming Language Interpreter

## Description

This project is a simple interpreter for a custom programming language that allows the assignment of variables and performing scoped operations. The language supports basic variable assignment, scope management, and printing variable values.

The interpreter is built using an Abstract Syntax Tree (AST) and includes JUnit tests for validating various operations such as scopes, variable assignments, and undefined variables.

## Features

- **Variable Assignment**: You can assign integer values to variables or copy values from other variables. Assigning the value of an undefined valuable to another results in a runtime exception.
- **Scoped Variables**: Variables declared inside a scope are temporary and disappear once the scope is exited.
- **Printing Variables**: The interpreter can print the current value of a variable or "null" if the variable does not exist.

## Syntax

- **Variable Assignment**:
  x = 99 or x = y

  
- **Scope Declaration**:
- scope { ... }

  Any variable assignments inside this block are scoped and will disappear once the scope is exited.

- **Print Variable**:
- print x

## Technical Details

The interpreter is built using an Abstract Syntax Tree (AST) to handle expressions and scope management. The interpreter also includes JUnit tests to validate the following:

- Variable assignment
- Scoped operations and variable life cycle
- Undefined variables and behavior when accessing non-existent variables
- Output verification, especially after scope exits

## Installation

To build and run the project:
1. Clone the repository:
   git clone https://github.com/marijaparezanin/SimpleInterpreter.git
2. Navigate to directory: 
   cd SimpleInterpreter
   




