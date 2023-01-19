import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    public static void main(String[] args) throws RationalNumbersException, ComplexNumbersException, IOException {
        String mode;
        Logger logger = Logger.getLogger("Calculations");
        FileHandler fileHandler = new FileHandler("MyLogFile.log", true);
        logger.addHandler(fileHandler);
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
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
                    logger.info(Presenter.res + "\n");
                    break;
                case "2":
                    var pR = new Presenter<>(RationalNumbersM.createRationalNumbersM(), new View());
                    pR.buttonClickRational();
                    logger.info(Presenter.res+ "\n");
                    break;
                default:
                    System.out.println("invalid data");
                    logger.info("invalid data+ \n");
                    break;
            }
        }
        scanner.close();
    }
}