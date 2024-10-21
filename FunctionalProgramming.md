# Functional Programming 

>This is a followup to the "Introduction to Command-Based Programming" tutorial, and assumes you already have some experience with Java and the FRC software ecosystem. Familiarity with polymorphism, inheritance, and algebra is also highly recommended.

As we have previously discussed, Java is an **Object-Oriented Language**. While this concept was briefly touched upon in the last lesson, we didn't go into too much detail about what that actually means. In Java, programs are **always** organized as objects. Every variable and every function is merely an attribute of an object. However, this is not the only way that programming languages can be structured. Another common paradigm is **Functional Programming**. In a Functional paradigm, programs are oriented around *functions*, not objects. Additionally, functions are **First-Class Citizens** in functional languages. This means that functions can be passed as parameters, returned by other functions, and assigned to variables, just like objects in Java

## A Brief Introduction
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

>In the functionalInterfaces.java file, there are some basic examples of functional interfaces and lambda expressions. Take some time to play around and experiment with them in order to familiarize yourself with how they work, before proceeding on to the next section.

## Functional Composition

If you have taken Precalculus (or are currently taking it), then you may have heard of the concept of **Function Composition**. Functional Composition is one of the most important operations in functional programming. For those who aren't familiar with it, Function Composition is a special operator that combines two functions:
$$
(f \circ g)(x) = f(g(x))
$$

Java supports functional composition, with the `compose` method. As an example, consider the following series of equations:
$$
f(x) = 3x + 1\\
g(x) = x + 2\\
h(x) = (f \circ g)(x)
$$
Below is that same series of equations, but rewritten in java:
```java
Function<Double,Double> f = (x) -> (3 * x) + 1;
Function<Double,Double> g = (x) -> x + 2;
Function<Double,Double> h = f.compose(g);
```
>In the functionComposition.java file, this java code has already been implemented. Write some code to make sure that `h.apply(x)` is equivalent to `f.apply(g.apply(x))`. Take some time to familiarize yourself with java's function composition before proceeding to the next section.

## Higher-Order Functions

Another important concept in Functional Programming is the concept of a **Higher-Order Function**. A Higher-Order function is a function that takes a *function* as a parameter, or returns one as its output. While at first they may seem daunting, you've already worked with one before. The `compose` method we used in the last section is an example of a higher-order function. It takes a function as its input, and returns another function as its output. Another example of a higher-order function you may have already worked with is the Derivative operator from Calculus. It takes a function as its input, and produces that function's derivative (another function) as its output (In the cases where the derivative is a constant, such as when differentiating linear equations, we can simply treat the constant as a function that always produces the same output, regardless of input). Below is an example of a simple higher-order function $L$ that takes a function $f$ and returns a new function $L[f]$ that applies $f$ to its input twice:

$$
L[f] = f \circ f = x \mapsto f(f(x))
$$

>You may have noticed that we used $f$ to denote the function, not $f(x)$. This is because, strictly speaking, $f$ refers to the function *itself*, while $f(x)$ refers to the function evaluated with some arbitrary input $x$. The two symbols are often used interchangeably, but this is technically an abuse of notation.

>$ x\mapsto f(f(x)) $ is an example of **Arrow notation** for functions. Arrow notation essentially works the same way as lambda expressions in Java, with the function's input being to the left of the arrow, and the function's output being to the right of the arrow

Here is how we would implement this function in Java:
```java
public Function<Double,Double> L(Function<Double,Double> f){
    return n -> f.apply(f.apply(n));
}
```
>When a lambda expression takes exactly one argument, the parenthesis are optional

Alternatively, we could also implement it with the `compose` method:
```java
public Function<Double,Double> L(Function<Double,Double> f){
    return f.compose(f);
}
```

> In the higherOrderFunctions.java file, this code has been implemented. Take some time to experiment with it before proceeding

## Functors and Monads

Two constructs that come up frequently in Functional Programming are Functors and Monads. Monads are essentially just Functors with additional structure, so we shall deal with Functors first.

### What is a Functor

A functor is a container that can map functions to its values while preserving their structure. As an example, let take a look at the following class:

```java
class FunctorList<T> extends ArrayList<T> {

    /* An arraylist extended to support .map() */
    public <R> FunctorList<R> map(Function<T,R> mapper){
        FunctorList<R> newArr = new FunctorList<>();
        for (T elem : this) {
            newArr.add(mapper.apply(elem));
        }
        return newArr;
    }

}


```
The `FunctorList` class extends `ArrayList`, but adds a new `map` method. The `map` method takes a function, `mapper`, applies it to every element of the `FunctorList`, and returns a new `FunctorList` containing the values returned from `mapper`. For a more concrete example, consider the following list:
```java
FunctorList<Double> tempsCelsius = new FunctorList<>(); 
tempsCelsius.add(22.1);
tempsCelsius.add(22.0);
tempsCelsius.add(17.9);
tempsCelsius.add(25.6);
tempsCelsius.add(18.5);
// [22.1, 22.0, 17.9, 25.6, 18.5]
```
Let us say that we wanted to convert the list of temperatures from celsius to Fahrenheit. If we were doing this with a normal `ArrayList`, we would need to use a for loop:
```java
ArrayList<Double> tempsFahrenheit = new ArrayList<>();
for (Double temp : tempsCelsius){
    tempsFahrenheit.add((temp * 1.8) + 32);
}
```
However, `FunctorList`'s `map` method abstracts this into a simpler and more readable interface:
```java
FunctorList<Double> tempsFahrenheit = tempsCelsius.map(n -> (n * 1.8) + 32);
```
Additionally, the `map` function allows for mapping from one type to another, meaning that the type returned by the `mapper` function does not have to match the type of the inital `FunctorList`'s elements. Below is some code that will cast all of `tempFahrenheit` values to Integers:
```java
FunctorList<Integer> tempsFahrenheitInt = tempsFahrenheit.map(n -> (int) n);
```
>You can test this code out for yourself in the functorExample.java file

### What is a Monad

A burrito is a burritoid in the category of endotortillas