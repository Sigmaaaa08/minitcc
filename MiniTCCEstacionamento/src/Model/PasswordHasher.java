package Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Em um ambiente de produção, isso deve ser logado e tratado de forma mais robusta.
            // Para este projeto, retornaremos a senha original (inseguro, mas evita quebrar o CRUD).
            // No entanto, para fins de segurança, vamos lançar uma RuntimeException.
            throw new RuntimeException("Erro ao gerar hash da senha: Algoritmo SHA-256 não encontrado.", e);
        }
    }
}
