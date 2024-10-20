# Functional Programming 

>This is a followup to the "Introduction to Command-Based Programming" tutorial, and assumes you already have some experience with Java and the FRC software ecosystem. Familiarity with the fundamental concepts of Object-Oriented Programming, such as inheritance and polymorphism is also highly recommended.

As we have previously discussed, Java is an **Object-Oriented Language**. While this concept was briefly touched upon in the last lesson, we didn't go into too much detail about what that actually means. In Java, programs are **always** organized as objects. Every variable and every function is merely an attribute of an object. However, this is not the only way that programming languages can be structured. Another common paradigm is **Functional Programming**. In a Functional paradigm, programs are oriented around *functions*, not objects. Additionally, functions are **First-Class Citizens** in functional languages. This means that functions can be passed as parameters, returned by other functions, and assigned to variables, just like objects in Java

## Functional Programming in Java
Despite being an Object-Oriented Language, Java has implemented many features of Functional Programming. Command-Based FRC Programming makes heavy use of Java's functional features, since functional programming lends itself well to concurrency. In fact, you have already used one of Java's functional features in the last lesson, in the form of a `doubleSupplier()`.

### Functional Interfaces
Earlier, we learned that everything in Java is an object, and this still holds true for Java's functional programming features. In Java, for a function to behave as a First-Class Citizen, it must first be wrapped in a special class called a **Functional Interface**. A Functional Interface is essentially just a class with exactly one abstract method, which implements the function. There are four types of Functional Interfaces that are implemented by default in Java:

- **Suppliers**: A supplier is a functional interface that takes no parameters and returns a value. Suppliers are often used for lazy evaluation. Values are returned from Suppliers using the `get` method.

- **Consumers**: A consumer is the opposite of a Supplier, it takes a single parameter and returns nothing. If we wanted to pass data to an arbitrary method, we would likely implement it as a Consumer. Values are passed to Consumers using the `accept` method.

- **Predicates**: A predicate takes an arbitrary value and returns a boolean value. Predicates are useful for filtering data. Predicates are called using the `test` method.

- **Functions**: A function takes one arbitrary parameter and returns an arbitrary value. Functions are called using the `apply` method.

>In computer science, "Lazy Evaluation" means the value of an expression is only evaluated when it is needed. Usually, when an expression is passed to a function in Java, its value is evaluated immediately. However, there are many cases where we wish to evaluate an expression at an arbitrary time (for example, when we want to poll the position of a controller stick in a command), so we use a functional interface to evaluate the expression's value lazily.

### Lambda Expressions

Another important component of Java's support for Functional Programming are **Lambda Expressions**. A Lambda expression is a function that can be created without an explicit name. the syntax for a lambda function is as follows:
```java
(parameters) -> result;
```
For example, a lambda function that took an integer n, and returned n+1 would be written like this:
```java
(n) -> n + 1;
```
In Java, Lambda Expressions are Functional Interfaces, and can be treated the same as any other object. They can be passed as parameters, returned from other functions, and assigned to variables.

>In the functionalInterfaces.java file, there are some basic examples of functional interfaces and lambda expressions. Take some time to play around and experiment with them in order to familiarize yourself with how they work, before proceeding on to thhe next section