public class ComplexNumbers extends CalcModel<ComplexNumbers> {
    private int realPart;
    private int imaginaryPart;

    private ComplexNumbers() {}

    public static ComplexNumbers createComplexNumber(){
        return new ComplexNumbers();
    }

    private ComplexNumbers(int realPart, int imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    public static ComplexNumbers createComplexNumber(int realPart, int imaginaryPart){
        return new ComplexNumbers(realPart, imaginaryPart);
    }


    @Override
    public CalcModel result(String op, CalcModel x, CalcModel y) throws ComplexNumbersException {
        if (!op.equals("+") & !op.equals("-") & !op.equals("*") & !op.equals("/"))
            throw new ComplexNumbersException("invalid operation");
        else {
            switch (op) {
                case "+":
                    return add((ComplexNumbers) x, (ComplexNumbers) y);
                case "-":
                    return sub((ComplexNumbers) x, (ComplexNumbers) y);
                case "*":
                    return mul((ComplexNumbers) x, (ComplexNumbers) y);
                case "/":
                    return div((ComplexNumbers) x, (ComplexNumbers) y);
                default:
                    return new ComplexNumbers(0, 0);
            }
        }
    }

    private ComplexNumbers add(ComplexNumbers x, ComplexNumbers y){
        return new ComplexNumbers(
                x.realPart + y.realPart,
                x.imaginaryPart + y.imaginaryPart);
    }

    private ComplexNumbers sub(ComplexNumbers x, ComplexNumbers y){
        return new ComplexNumbers(
                x.realPart - y.realPart,
                x.imaginaryPart - y.imaginaryPart);
    }

    private ComplexNumbers mul(ComplexNumbers x, ComplexNumbers y){
        return new ComplexNumbers(
                x.realPart * y.realPart - x.imaginaryPart * y.imaginaryPart,
                x.imaginaryPart * y.realPart  + y.imaginaryPart * x.realPart);
    }

    private ComplexNumbers div(ComplexNumbers x, ComplexNumbers y){
        int denominator =
                y.realPart * y.realPart - y.imaginaryPart * y.imaginaryPart +
                2 * y.imaginaryPart * y.realPart;
        return new ComplexNumbers(
                (x.realPart * y.realPart + x.imaginaryPart * y.imaginaryPart) / denominator,
                (x.imaginaryPart * y.realPart - y.imaginaryPart * x.realPart) / denominator);
    }

    @Override
    public String toString() {
        if (realPart == 0 & imaginaryPart == 0) return "0";
        if (realPart == 0) return String.valueOf(imaginaryPart);
        if (imaginaryPart == 0) return String.valueOf(realPart);
        if (imaginaryPart < 0) return "(" + realPart + " - " + (-1) * imaginaryPart + "j)";
        return "(" + realPart + " + " + imaginaryPart + "j)";
    }
}
