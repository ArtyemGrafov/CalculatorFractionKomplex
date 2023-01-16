public interface Model<T> {
    T result(String op, T x, T y) throws RationalNumbersExeption;
}
