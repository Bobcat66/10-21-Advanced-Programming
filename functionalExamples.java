import java.util.function.DoubleSupplier;

public class functionalExamples {
    public static void main(String[] args){
        /*
        This code creates a lambda function that returns 10.0, 
        and assigns it to the supplier variable. It then calls
        the lambda function with the .getAsDouble() method;
        */
        DoubleSupplier supplier = () -> 10.0;
        System.out.println(supplier.getAsDouble());
    }
}
