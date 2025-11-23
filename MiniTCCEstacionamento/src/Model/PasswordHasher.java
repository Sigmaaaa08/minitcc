/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
/**
 *
 * @author Karen
 */
public class PasswordHasher {

    // Constantes de Segurança
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int ITERATIONS = 10000; // Mínimo de 10.000, 150.000 é preferível
    private static final int KEY_LENGTH = 256;   // 256 bits

    /**
     * Gera um hash seguro da senha usando Salting e PBKDF2.
     *
     * @param password A senha em texto puro.
     * @return Uma String contendo o salt e o hash, separados por dois pontos
     * (ex: "salt:hash").
     */
    public static String hashPassword(String password) {
        try {
            // 1. Geração de um Salt (Valor Aleatório)
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16]; // Salt de 16 bytes (128 bits)
            random.nextBytes(salt);

            // 2. Criação da Especificação da Chave
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);

            // 3. Geração do Hash (Key Derivation)
            byte[] hash = skf.generateSecret(spec).getEncoded();

            // 4. Combinação e Codificação em Base64
            // Armazena o salt e o hash juntos para uso na verificação
            String saltStr = Base64.getEncoder().encodeToString(salt);
            String hashStr = Base64.getEncoder().encodeToString(hash);

            return saltStr + ":" + hashStr;

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Erro ao gerar hash da senha.", e);
        }
    }

    /**
     * Verifica se a senha fornecida corresponde ao hash armazenado.
     *
     * @param password A senha em texto puro fornecida pelo usuário.
     * @param storedHash O hash completo (salt:hash) armazenado no banco de
     * dados.
     * @return true se as senhas corresponderem, false caso contrário.
     */
    public static boolean verifyPassword(String password, String storedHash) {
        try {
            // 1. Extrai o salt e o hash do valor armazenado
            String[] parts = storedHash.split(":");
            if (parts.length != 2) {
                return false;
            }

            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] hash = Base64.getDecoder().decode(parts[1]);

            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] generatedHash = skf.generateSecret(spec).getEncoded();

            return MessageDigest.isEqual(hash, generatedHash);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Erro ao verificar hash da senha.", e);
        }
    }
}