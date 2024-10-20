import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.random.RandomGenerator;


public class functionalInterfaces {
    public static void main(String[] args){
        // This is an integer supplier that supplies integers from a random number generator
        Supplier<Integer> randintSupplier = () -> RandomGenerator.getDefault().nextInt();
        // This is an integer consumer that consumes integers and prints them to the console
        Consumer<Integer> intPrinter = (n) -> System.out.println(n);
        // This is a predicate that checks if an integer is even
        Predicate<Integer> isEven = (n) -> n % 2 == 0;
        // This is a function that adds one to an integer
        Function<Integer,Integer> addOne = (n) -> n + 1;

        // This gets a random integer from randintSupplier and stores it in randint
        int randint = randintSupplier.get();
        // This passes randint to intPrinter, making it print to the console
        intPrinter.accept(randint);
        // This passes randint to isEven, and prints the result
        System.out.println(isEven.test(randint));
        // This passes randint to addOne, and then prints the result
        System.out.println(addOne.apply(randint));
    }
}
