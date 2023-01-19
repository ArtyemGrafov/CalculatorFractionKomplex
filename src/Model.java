@SuppressWarnings({"rawtypes"})
public interface Model<T extends CalcModel> {
    T result(String op, T x, T y) throws ComplexNumbersException, RationalNumbersException;
}
