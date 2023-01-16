public class Presenter<T extends CalcModel> {

    View view;
    Model<RationalNumbersM> model;

    public Presenter(RationalNumbersM rationalNumbersM, View v) {
        model = rationalNumbersM;
        view = v;
    }

    public void buttonClick() throws RationalNumbersExeption {
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
        //System.out.println(operation);
        RationalNumbersM result = model.result(operation, a, b);
        view.print(a + " " + operation + " " + b + " = " + result, "Result: ");
    }
}
