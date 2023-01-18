import java.util.InputMismatchException;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws RationalNumbersException, ComplexNumbersException {
        Logger logger = Logger.getLogger("Calculations");

        Presenter p = new Presenter(ComplexNumbers.createComplexNumber(), new View());
        p.buttonClickComplex();

        p = new Presenter<>(RationalNumbersM.createRationalNumbersM(), new View());
        p.buttonClickRational();
    }
}