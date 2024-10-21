import java.util.ArrayList;
import java.util.function.Function;

public class functorExample {
    public static void main(String[] args) {
        FunctorList<Double> tempsCelsius = new FunctorList<>(); 
        tempsCelsius.add(22.1);
        tempsCelsius.add(22.0);
        tempsCelsius.add(17.9);
        tempsCelsius.add(25.6);
        tempsCelsius.add(18.5);

        // Non-functor code
        ArrayList<Double> tempsFahrenheitNonFunctor = new ArrayList<>();
        for (Double temp : tempsCelsius){
            tempsFahrenheitNonFunctor.add((temp * 1.8) + 32);
        }

        // Functor code
        FunctorList<Double> tempsFahrenheit = tempsCelsius.map(n -> (n * 1.8) + 32);

        // Casts values of tempsFahrenheit to integers
        FunctorList<Integer> tempsFahrenheitInt = tempsFahrenheit.map(n -> n.intValue());
        System.out.println(tempsCelsius);
        System.out.println(tempsFahrenheitNonFunctor);
        System.out.println(tempsFahrenheit);
        System.out.println(tempsFahrenheitInt);
    }
}

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
