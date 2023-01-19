import java.util.Scanner;

public class View {

    public int getValue(String title) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%s", title);
        return scanner.nextInt();

    }

    public String getOperation(String title) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%s", title);
        return scanner.nextLine();
    }

    public void print(String data, String title) {
        show(data);
        System.out.printf("%s %s\n", title, data);
    }

    public String show(String data){
        return data;
    }
}
