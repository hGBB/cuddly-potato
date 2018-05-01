import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public final class Shell {
    private Shell() {
    }

    private static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        execute(stdin);
    }

    private static void execute(BufferedReader stdin) throws IOException {
        boolean quit = false;
        while (!quit) {
            System.out.println("prompt");
            String input = stdin.readLine();
            if (input == null) {
                break;
            }
            Scanner scanner = new Scanner(input);
            scanner.useDelimiter("\\s+");
            if (scanner.hasNext()) {
                String command = scanner.next();
            } else {
                System.out.println("Error! No command.");
            }
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
            } else {
                System.out.println("Error! No number!.");
            }

            scanner.close();
        }
    }
}
