import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws RationalNumbersException, ComplexNumbersException, IOException {
        String mode;
        Logger logger = Logger.getLogger("Calculations");
        FileHandler fileHandler = new FileHandler();
        Scanner scanner = new Scanner(System.in);

        label:
        while (true) {
            System.out.print("Enter mode (1 - complex, 2 - rational, e - exit): ");
            mode = scanner.nextLine();
            switch (mode) {
                case "e":
                    break label;
                case "1":
                    var pC = new Presenter<>(ComplexNumbers.createComplexNumber(), new View());
                    pC.buttonClickComplex();
                    break;
                case "2":
                    var pR = new Presenter<>(RationalNumbersM.createRationalNumbersM(), new View());
                    pR.buttonClickRational();
                    break;
                default:
                    System.out.println("invalid data");
                    break;
            }
        }
        scanner.close();
    }
}