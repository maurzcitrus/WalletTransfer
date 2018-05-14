# High Level Design

In this document, only design decisions and designs that deviate from original high level design are mentioned.  Original high level design document (given in class, PRMS HLD v3.2) is still applicable.

* [Arcitecture Layers](#architecture-layers)
* [Architectural Decisions](#architectural-decisions)
* [Programming Language](#programming-language)
* [Transition of Static Structure](#transition-of-static-structure)
* [Transition of Dynamic Interactions](#transition-of-dynamic-interactions)

## Architecture Layers

### REST API Server

Each REST API is implemented with following layers:

Business - Stateless

* Resource - defines REST API end points

Data Access

* Dao - defines interfaces for data access objects
* DaoMySql - implements data access objects for MySql database
* Util - implements low level JDBC implementation for Dao classes.  All Dao classes must use this for their JDBC calls.

## Architectural Decisions

###Technological

1. The system is a with REST API with Jax-Rs Jersey Implemetation.
2. Rest can also be implemented with Java EE or Spring which gives better handling of transactions.
3. Business Stateless layer and Data Access layer are implemented using Java language and JAX-RS compliant application server, GlassFish 4.1.
4. Data is persisted in MySQL Community Server 5.7.
5. Test cases are implemeted using behaviour driven test using cucumber.


## Programming Language

REST API Server

------------------------------------------------------------------------------------------------------------
Package                                           Description
-----------------------------------------------   ----------------------------------------------------------

model                                              domain classes for data transfer purpose

Dao                                                Interace that provides implementations.

DaoMySql                                           Implemenation class where the business resides.

Resource                                           Rest Api End points

util                                               low level implementation of JDBC calls to MySql database

------------------------------------------------------------------------------------------------------------


### Using Lambda Expression for JDBC

> * Always use lambda expression for JDBC database access to MySql database
> * This is to avoid copy-pasting boilerplate JDBC codes in data acess classes
> * Always use prepared statement to parameterized SQL statements with  ```?```
> * **Never** concatenate SQL string and input values to form SQL Statements.  This is to prevent SQL Injection


### REST API Naming Convention

Naming convention for REST API implementation are as follows:

------------------------------------------------------------------------------------------------------------------
Description                       HTTP Verb                 Example (prefix with ```/wallet```)
-------------------               ---------                 ------------------------------------------------------
register account                  POST                      ```/register/{email}```

check Balance                     GET                       ```/account/{email}```

Transfer Amount                   POST                      ```/transaction/{transferer}/{transferee}/{amount}```

check Latest                      GET                       ```/transactions/{email}```
Transaction

------------------------------------------------------------------------------------------------------------------
