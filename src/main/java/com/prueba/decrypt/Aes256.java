package com.prueba.decrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Aes256 {

    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int GCM_IV_LENGTH = 12;
    private static final int GCM_TAG_LENGTH = 128;

    public static String decrypt(String encryptedData, String secretKey) throws Exception {
        byte[] decodedMessage = Base64.getDecoder().decode(encryptedData);

        // Extraer el IV de los primeros 12 bytes
        byte[] iv = new byte[GCM_IV_LENGTH];
        System.arraycopy(decodedMessage, 0, iv, 0, GCM_IV_LENGTH);

        // Extraer el texto cifrado restante
        int cipherTextLength = decodedMessage.length - GCM_IV_LENGTH;
        byte[] cipherText = new byte[cipherTextLength];
        System.arraycopy(decodedMessage, GCM_IV_LENGTH, cipherText, 0, cipherTextLength);

        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec);

        byte[] plainText = cipher.doFinal(cipherText);
        return new String(plainText, StandardCharsets.UTF_8);
    }

}
