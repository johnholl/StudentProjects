import java.util.Random;

public final class Cipher {
    private static final Random random = new Random();

    private Cipher() {
        throw new UnsupportedOperationException("No creating Ciphers");
    }

    public static String encode(String value) {
        StringBuilder sb = new StringBuilder();
        char sum = charValue(Math.abs(random.nextInt()));
        sb.append(sum);
        for (int i = 0; i < value.length(); i++) {
            for (int j = 0; j < i; j++) {
                char next = charValue(Math.abs(random.nextInt()));
                sb.append(next);
                sum = charValue(next + sum);
            }
            char val = charValue(value.charAt(i) + sum);
            sb.append(val);
            sum = val;
        }
        return sb.toString();
    }

    public static String decode(String value) {
        StringBuilder sb = new StringBuilder();
        char sum = value.charAt(0);
        int chars = 0;
        for (int i = 1; i < value.length(); chars++, i += chars) {
            for (int j = i; j < i + chars; j++) {
                sum = charValue(sum + value.charAt(j));
            }
            char val = unChar(((int) value.charAt(i + chars)) - sum);
            sb.append(val);
            sum = charValue(val + sum);
        }
        return sb.toString();
    }

    private static char charValue(int val) {
        return (char) (((val - 0x20) % (0x7E - 0x20)) + 0x20);
    }

    private static char unChar(int val) {
        for (; val < 0x20; val += 0x7E - 0x20);
        return (char) val;
    }
}
