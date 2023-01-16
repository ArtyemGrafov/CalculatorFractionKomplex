public class Main {
    public static void main(String[] args) throws RationalNumbersExeption {
        Presenter p = new Presenter(RationalNumbersM.createRationalNumbersM(), new View());
        p.buttonClick();


    }
}