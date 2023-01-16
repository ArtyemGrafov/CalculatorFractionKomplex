public class RationalNumbersM extends CalcModel<RationalNumbersM> {
    private int wholeFractionPart;
    private int numerator;
    private int denominator;

    public RationalNumbersM() {}

    private RationalNumbersM(int numerator, int denominator) {
        this.wholeFractionPart = 0;
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static RationalNumbersM createRationalNumbersM() {
        return new RationalNumbersM();
    }

    private RationalNumbersM(int wholeFractionPart, int numerator, int denominator) {
        this.wholeFractionPart = wholeFractionPart;
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static RationalNumbersM createRationalNumbersM(int wholeFractionPart, int numerator, int denominator)
            throws RationalNumbersExeption {
        if (numerator < 1 || denominator < 2) throw new RationalNumbersExeption("invalid data");
        else return new RationalNumbersM(wholeFractionPart, numerator, denominator);
    }

    public RationalNumbersM result(String op, RationalNumbersM x, RationalNumbersM y)
            throws RationalNumbersExeption {
        if (!op.equals("+") & !op.equals("-") & !op.equals("*") & !op.equals("/"))
            throw new RationalNumbersExeption("invalid operation");
        else {
            switch (op) {
                case "+":
                    return add(x, y);
                case "-":
                    return sub(x, y);
                case "*":
                    return mul(x, y);
                case "/":
                    return div(x, y);
                default:
                    return new RationalNumbersM(0, 0, 1);
            }
        }
    }

    public RationalNumbersM add(RationalNumbersM x, RationalNumbersM y) {
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

    public RationalNumbersM sub(RationalNumbersM x, RationalNumbersM y) {
        RationalNumbersM result = new RationalNumbersM(1, 1, 1);
        int greatestCommonDivisor = GCD (x.denominator, y.denominator);
        result.wholeFractionPart = x.wholeFractionPart - y.wholeFractionPart;
        result.numerator =
                x.numerator * (greatestCommonDivisor / x.denominator) -
                        y.numerator * (greatestCommonDivisor / y.denominator);
        result.denominator = greatestCommonDivisor;
        if (result.numerator < 0) {
            result.wholeFractionPart -= 1;
            result.numerator += result.denominator;
        }
        return fractionReduction(result);
    }

    public RationalNumbersM mul(RationalNumbersM x, RationalNumbersM y) {
        RationalNumbersM result = new RationalNumbersM(0, 1, 1);
        x = rationalToFraction(x);
        y = rationalToFraction(y);
        result.numerator = x.numerator * y.numerator;
        result.denominator = x.denominator * y.denominator;
        return fractionToRational(result);
    }

    public RationalNumbersM div(RationalNumbersM x, RationalNumbersM y) {
        RationalNumbersM result = new RationalNumbersM(1, 1, 1);
        x = rationalToFraction(x);
        y = rationalToFraction(y);
        result.numerator = x.numerator * y.denominator;
        result.denominator = x.denominator * y.numerator;
        return fractionToRational(fractionReduction(result));
    }

    private RationalNumbersM rationalToFraction(RationalNumbersM x) {
        return new RationalNumbersM((x.wholeFractionPart * x.denominator + x.numerator), x.denominator);
    }

    private RationalNumbersM fractionToRational(RationalNumbersM x) {
        return new RationalNumbersM(
                x.numerator / x.denominator,
                (x.numerator % x.denominator), x.denominator);
    }

    private RationalNumbersM fractionReduction(RationalNumbersM x) {

        int smallestCommonMultiplier = SCM(x.numerator, x.denominator);
        x.wholeFractionPart += x.numerator / x.denominator;
        x.numerator = (x.numerator % x.denominator) / smallestCommonMultiplier;
        x.denominator /= smallestCommonMultiplier;
        return x;
    }


    private int GCD(int a, int b){
        return (a * b) / SCM(a, b);
    }

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



}

