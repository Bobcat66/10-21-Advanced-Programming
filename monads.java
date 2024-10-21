import java.util.function.Function;
import java.util.ArrayList;


public class monads {
    public static void main(String[] args) {
        ArrayListFunctor<String> sarr = new ArrayListFunctor<>();
        sarr.add("Hello");
        sarr.add("World");
        sarr.add("One");
        sarr.add("Four");
        sarr.add("Seven");
        sarr.add("Nine");
        ArrayListFunctor<Integer> sarrMapped = sarr.map(n -> n.length());
        System.out.println(sarr);
        System.out.println(sarrMapped);
    }
}

class Functor<T>{
    private final T value;
    public Functor(T value){
        this.value = value;
    }
    public <R> Functor<R> map(Function<T,R> mapper){
        return new Functor<R>(mapper.apply(this.value));
    }
    public T get(){
        return value;
    }
}

class ArrayListFunctor<T> extends ArrayList<T> {

    /* An arraylist extended to support .map() */
    public <R> ArrayListFunctor<R> map(Function<T,R> mapper){
        ArrayListFunctor<R> newArr = new ArrayListFunctor<>();
        for (T elem : this) {
            newArr.add(mapper.apply(elem));
        }
        return newArr;
    }

}
