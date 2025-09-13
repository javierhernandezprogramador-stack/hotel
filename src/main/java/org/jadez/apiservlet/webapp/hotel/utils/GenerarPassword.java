package org.jadez.apiservlet.webapp.hotel.utils;

import jakarta.enterprise.context.ApplicationScoped;

import java.security.SecureRandom;

@ApplicationScoped
public class GenerarPassword {

    public String generarPassword() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String symbols = "!@#$%^&*()-_=+[]{}|;:,.<>?";
        SecureRandom random = new SecureRandom();
        int length = 12;
        String all = upper + lower + digits + symbols;
        StringBuilder password = new StringBuilder();

        // Garantizar al menos un carácter de cada tipo
        password.append(upper.charAt(random.nextInt(upper.length())));
        password.append(lower.charAt(random.nextInt(lower.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(symbols.charAt(random.nextInt(symbols.length())));

        // Completar el resto de la longitud
        for (int i = 4; i < length; i++) {
            password.append(all.charAt(random.nextInt(all.length())));
        }

        // Mezclar los caracteres para evitar patrón fijo
        return shuffle(password.toString(), random);
    }

    private String shuffle(String input, SecureRandom random) {
        char[] array = input.toCharArray();

        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return new String(array);
    }
}
