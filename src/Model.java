public interface Model<T extends CalcModel> {
    T result(String op, T x, T y) throws RationalNumbersException, ComplexNumbersException;
}
