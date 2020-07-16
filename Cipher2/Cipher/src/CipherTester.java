import java.util.Scanner;

public final class CipherTester {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("Enter a sentence to be decoded (or a blank line to exit)");
                String line = sc.nextLine();
                if (line.isEmpty()) {
                    break;
                } else {
                    String code = Cipher.encode(line);
                    System.out.println("Encoded:");
                    System.out.println(code);
                    System.out.println("Decoded (just because I can):");
                    System.out.println(Cipher.decode(code));
                }
            }
        }
    }
}
