import java.util.function.Function;

public class higherOrderFunctions {
    public static Function<Double,Double> L(Function<Double,Double> f){
        return n -> f.apply(f.apply(n));
    }
    public static void main(String[] args){
        Function<Double,Double> f = n -> n * 2;
        Function<Double,Double> Lf = L(f);
        System.out.println(f.apply(3.0)); //Should print "6.0", or 3 * 2
        System.out.println(Lf.apply(3.0)); //Should print "12.0", or (3 * 2) * 2
    }
}
