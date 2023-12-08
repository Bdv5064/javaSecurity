import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string:");
        String text = scanner.nextLine();

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(text.getBytes());
        System.out.println("Hash Value: " + bytesToHex(hash));

        String spacePersonString = SpaceAlphabet.convertToMorseCode(text);
        System.out.println("Space Person String: " + spacePersonString);



        String cipherText = CaesarCipher.encrypt(text, 5);
        System.out.println("Cipher Text: " + cipherText);

        System.out.println("Brute Force:");
        BruteForce.crack(cipherText);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}

class SpaceAlphabet {
    public static String convertToMorseCode(String text) {
        HashMap<Character, String> morseCodeMap = new HashMap<>();
        //Map of Morse Code...
        morseCodeMap.put('A', "!"); morseCodeMap.put('B', "@"); morseCodeMap.put('C', "#");
        morseCodeMap.put('D', "!@"); morseCodeMap.put('E', "!#"); morseCodeMap.put('F', "@!");
        morseCodeMap.put('G', "@#"); morseCodeMap.put('H', "#!"); morseCodeMap.put('I', "#@");
        morseCodeMap.put('J', "!!"); morseCodeMap.put('K', "!@#"); morseCodeMap.put('L', "@!#");
        morseCodeMap.put('M', "@#!"); morseCodeMap.put('N', "#!@"); morseCodeMap.put('O', "#@!");
        morseCodeMap.put('P', "!!!"); morseCodeMap.put('Q', "!!@"); morseCodeMap.put('R', "!!#");
        morseCodeMap.put('S', "!@!"); morseCodeMap.put('T', "!@#"); morseCodeMap.put('U', "!#!");
        morseCodeMap.put('V', "!#@"); morseCodeMap.put('W', "@!!"); morseCodeMap.put('X', "@!@");
        morseCodeMap.put('Y', "@!#"); morseCodeMap.put('Z', "@#@"); morseCodeMap.put('1', "@##");
        morseCodeMap.put('2', "#!!!"); morseCodeMap.put('3', "#!!@"); morseCodeMap.put('4', "#!!#");
        morseCodeMap.put('5', "#!@!"); morseCodeMap.put('6', "#!@#"); morseCodeMap.put('7', "#!#!");
        morseCodeMap.put('8', "#!#@"); morseCodeMap.put('9', "#@!!"); morseCodeMap.put('0', "#@!@");

        StringBuilder morseCodeBuilder = new StringBuilder();
        text = text.toUpperCase();

        for (char c : text.toCharArray()) {
            if (c == ' ') {
                morseCodeBuilder.append(" ");
            } else if (morseCodeMap.containsKey(c)) {
                morseCodeBuilder.append(morseCodeMap.get(c)).append(" ");
            }
        }

        return morseCodeBuilder.toString().trim();
    }
}

class CaesarCipher {
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) {
                char ch = (char)(((int)c + shift - 65) % 26 + 65);
                result.append(ch);
            } else if (Character.isLowerCase(c)) {
                char ch = (char)(((int)c + shift - 97) % 26 + 97);
                result.append(ch);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}

class BruteForce {
    public static void crack(String text) {
        for (int i = 0; i < 26; i++) {
            String decryptedText = CaesarCipher.encrypt(text, 26 - i);
            System.out.println(i + ": " + decryptedText);
        }
    }
}
