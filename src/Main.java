import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) throws RationalNumbersException, ComplexNumbersException {
        Presenter p = new Presenter(ComplexNumbers.createComplexNumber(), new View());
        p.buttonClickComplex();

        p = new Presenter<>(RationalNumbersM.createRationalNumbersM(), new View());
        p.buttonClickRational();
    }
}