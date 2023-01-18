public class Presenter<T extends CalcModel> {

    View view;
    Model model;

    public Presenter(T m, View v) {
        model = m;
        view = v;
    }

    public void buttonClickComplex() throws ComplexNumbersException, RationalNumbersException {
        ComplexNumbers a = ComplexNumbers.createComplexNumber(
                view.getValue("realPartX: "),
                view.getValue("imaginaryPartX: ")
        );

        ComplexNumbers b = ComplexNumbers.createComplexNumber(
                view.getValue("realPartY: "),
                view.getValue("imaginaryPartY: ")
        );
        String operation = view.getOperation("operation: ");

        CalcModel result = model.result(operation, a, b);
        view.print(a + " " + operation + " " + b + " = " + result, "Result: ");
    }

    public void buttonClickRational() throws RationalNumbersException, ComplexNumbersException {
        RationalNumbersM a = RationalNumbersM.createRationalNumbersM(
                view.getValue("wholeFractionPartX: "),
                view.getValue("numeratorX: "),
                view.getValue("denominatorX: ")
        );

        RationalNumbersM b = RationalNumbersM.createRationalNumbersM(
                view.getValue("wholeFractionPartY: "),
                view.getValue("numeratorY: "),
                view.getValue("denominatorY: ")
        );
        String operation = view.getOperation("operation: ");

        CalcModel result = model.result(operation, a, b);
        view.print(a + " " + operation + " " + b + " = " + result, "Result: ");
    }

}
