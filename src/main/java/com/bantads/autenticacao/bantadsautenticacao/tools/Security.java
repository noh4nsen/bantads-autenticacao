package com.bantads.autenticacao.bantadsautenticacao.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Security {
    public static String hash(String password) {
        String finalPassword = null;
        String salt = "bantads";

        try {
            finalPassword = hashPassword(password, salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return finalPassword;
    }

    private static byte[] digest(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes());
        byte[] bytes = md.digest(password.getBytes());
        return bytes;
    }

    private static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        byte[] bytes = digest(password, salt);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return sb.toString();
    }
}
