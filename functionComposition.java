import java.util.function.Function;

public class functionComposition {
    public static void main(String[] args){
        Function<Double,Double> f = (x) -> 3 * x + 1;
        Function<Double,Double> g = (x) -> x + 2;
        Function<Double,Double> h = f.compose(g);
        
        double x = 5.0;
        
        //Write your code here

    }
}
