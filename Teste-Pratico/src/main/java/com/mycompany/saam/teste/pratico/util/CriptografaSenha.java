package com.mycompany.saam.teste.pratico.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptografaSenha {

    /**
     * Criptografa a senha do usuário utilizando o algoritmo SHA-256.
     *
     * @param senha Senha a ser criptografada.
     * @return Senha criptografada.
     * Fonte: https://medium.com/@AlexanderObregon/what-is-sha-256-hashing-in-java-0d46dfb83888
     */

    public static boolean autenticarSenha(String senha, String senhaCriptografada) {
        return criptografarSenha(senha).equals(senhaCriptografada);
    }
    public static String criptografarSenha(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(senha.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
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
