package edu.hw8.Task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
class PasswordCracker {
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
    private final int maxPasswordSize;
    private Map<String, String> passwords;
    private final Map<String, String> crackedPasswords;

    PasswordCracker(int passwordSize, String filepath) {
        maxPasswordSize = passwordSize;
        crackedPasswords = new HashMap<>();
        readPasswords(filepath);
    }

    public void crackPasswords() {
        for (String password : nextPassword()) {
            for (Map.Entry<String, String> entry : passwords.entrySet()) {
                String username = entry.getValue();
                String actualHash = entry.getKey();
                if (Objects.equals(hashPassword(password), actualHash)) {
                    crackedPasswords.put(username, password);
                    break;
                }
            }
        }
    }

    private Iterable<String> nextPassword() {
        return () -> new java.util.Iterator<>() {
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < Math.pow(alphabet.length(), maxPasswordSize);
            }

            @Override
            public String next() {
                StringBuilder password = new StringBuilder();
                int temp = count;
                for (int i = 0; i < maxPasswordSize; i++) {
                    int index = temp % alphabet.length();
                    password.insert(0, alphabet.charAt(index));
                    temp = temp / alphabet.length();
                }

                padding(password);

                count++;
                return password.toString();
            }

            private void padding(StringBuilder password) {
                while (password.length() < maxPasswordSize) {
                    password.insert(0, '0');
                }
            }
        };
    }

    protected String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(password.getBytes());

            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                result.append(String.format("%02x", b));
            }

            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("no such algorithm");
            return null;
        }
    }

    protected void readPasswords(String filepath) {
        passwords = new HashMap<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(filepath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parsedLine = line.split(":");
                passwords.put(parsedLine[0], parsedLine[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
