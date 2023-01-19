import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class RationalNumbersM extends CalcModel<RationalNumbersM> {
    private int wholeFractionPart;
    private int numerator;
    private int denominator;

    private RationalNumbersM() {}

    private RationalNumbersM(int numerator, int denominator) {
        this.wholeFractionPart = 0;
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Contract(" -> new")
    public static @NotNull RationalNumbersM createRationalNumbersM() {
        return new RationalNumbersM();
    }

    private RationalNumbersM(int wholeFractionPart, int numerator, int denominator) {
        this.wholeFractionPart = wholeFractionPart;
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Contract("_, _, _ -> new")
    public static @NotNull RationalNumbersM createRationalNumbersM(int wholeFractionPart, int numerator, int denominator)
            throws RationalNumbersException {
        if (numerator < 1 || denominator < 2) throw new RationalNumbersException("invalid data");
        else return new RationalNumbersM(wholeFractionPart, numerator, denominator);
    }

    private RationalNumbersM resultN(String op, RationalNumbersM x, RationalNumbersM y)
            throws RationalNumbersException {
        if (!op.equals("+") & !op.equals("-") & !op.equals("*") & !op.equals("/"))
            throw new RationalNumbersException("invalid operation");
        else {
            return switch (op) {
                case "+" -> add(x, y);
                case "-" -> sub(x, y);
                case "*" -> mul(x, y);
                case "/" -> div(x, y);
                default -> new RationalNumbersM(0, 0, 1);
            };
        }
    }

    private RationalNumbersM add(RationalNumbersM x, RationalNumbersM y) {
        RationalNumbersM result = new RationalNumbersM(1, 1, 1);
        int greatestCommonDivisor = GCD (x.denominator, y.denominator);
        result.wholeFractionPart = x.wholeFractionPart + y.wholeFractionPart;
        result.numerator =
                x.numerator * (greatestCommonDivisor / x.denominator) +
                        y.numerator * (greatestCommonDivisor / y.denominator);
        result.denominator = greatestCommonDivisor;
        if (result.numerator < 0) {
            result.wholeFractionPart -= 1;
            result.numerator += result.denominator;
        }
        return fractionReduction(result);
    }

    private RationalNumbersM sub(RationalNumbersM x, RationalNumbersM y) {
        RationalNumbersM result = new RationalNumbersM(1, 1, 1);
        int greatestCommonDivisor = GCD (x.denominator, y.denominator);
        result.wholeFractionPart = x.wholeFractionPart - y.wholeFractionPart;
        result.numerator =
                x.numerator * (greatestCommonDivisor / x.denominator) -
                        y.numerator * (greatestCommonDivisor / y.denominator);
        result.denominator = greatestCommonDivisor;
        if (result.numerator < 0 & result.wholeFractionPart < 0) {
            result.wholeFractionPart -= 1;
            result.numerator += result.denominator;
        }
        return fractionReduction(result);
    }

    private RationalNumbersM mul(RationalNumbersM x, RationalNumbersM y) {
        RationalNumbersM result = new RationalNumbersM(0, 1, 1);
        x = rationalToFraction(x);
        y = rationalToFraction(y);
        result.numerator = x.numerator * y.numerator;
        result.denominator = x.denominator * y.denominator;
        return fractionReduction(result);
    }

    private RationalNumbersM div(RationalNumbersM x, RationalNumbersM y) {
        RationalNumbersM result = new RationalNumbersM(0, 1, 1);
        x = rationalToFraction(x);
        y = rationalToFraction(y);
        result.numerator = x.numerator * y.denominator;
        result.denominator = x.denominator * y.numerator;
        return fractionReduction(result);
    }

    private RationalNumbersM rationalToFraction(RationalNumbersM x) {
        return new RationalNumbersM((x.wholeFractionPart * x.denominator + x.numerator), x.denominator);
    }

    private RationalNumbersM fractionReduction(RationalNumbersM x) {

        int smallestCommonMultiplier = SCM(x.numerator, x.denominator);
        x.wholeFractionPart += x.numerator / x.denominator;
        x.numerator = (x.numerator % x.denominator) / smallestCommonMultiplier;
        x.denominator /= smallestCommonMultiplier;
        if (x.denominator < 0) {
            x.denominator *= -1;
            x.numerator *= -1;
        }
        return x;
    }

   /** returns the greatest common divisor*/
    private int GCD(int a, int b){
        return (a * b) / SCM(a, b);
    }

    /** returns the smallest common multiple*/
    private int SCM(int a, int b){
        if (b == 0) return a;
        return SCM(b, a % b);
    }

    @Override
    public String toString() {
        if (wholeFractionPart == 0 & numerator == 0) return "0";
        if (wholeFractionPart == 0) return numerator + "/" + denominator;
        if (numerator == 0) return String.valueOf(wholeFractionPart);
        return wholeFractionPart + "•" + numerator + "/" + denominator;
    }

    @Override
    @SuppressWarnings({"rawtypes"})
    /*
      returns the result of the selected operation on rational numbers
     */
    public CalcModel result(String op, CalcModel x, CalcModel y) throws RationalNumbersException {
        return resultN(op, (RationalNumbersM) x, (RationalNumbersM) y);
    }
}

