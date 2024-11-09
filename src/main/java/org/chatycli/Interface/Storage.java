package org.chatycli.Interface;

import org.chatycli.Data.Session;
import org.chatycli.Data.DataModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;

public class Storage {
    private static Storage instance;
    private static final String FILE_PATH = Config.getStorageFile(); // Encrypted storage file
    private static final String AES_ALGORITHM = "AES/GCM/NoPadding";
    private static final int AES_KEY_SIZE = 128; // AES key size
    private static final int GCM_IV_LENGTH = 12; // GCM IV length in bytes
    private static final int GCM_TAG_LENGTH = 16; // GCM Tag length in bytes
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final SecretKey secretKey = generateSecretKey();
    private static DataModel dataModel;

    static {
        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Session getStoredSession() {
        return dataModel.getSession();
    }
    // Encrypts and saves a POJO to the storage file
    public static void saveData() throws Exception {
        String jsonData = gson.toJson(dataModel);
        byte[] encryptedData = encrypt(jsonData);
        try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
            fos.write(encryptedData);
        }
    }

    // Loads and decrypts a POJO from the storage file
    public static void loadData() throws Exception {
        File storageFile = new File(FILE_PATH);
        if (!storageFile.exists()) {
            try {
                System.out.println("storage.dat does not exist :: Creating new file in :"+FILE_PATH);
                storageFile.createNewFile();
                dataModel = new DataModel();
                String modelJson = gson.toJson(dataModel); // or any default JSON structure for DataModel
                BufferedWriter writer = new BufferedWriter(new FileWriter(storageFile));
                writer.write(encrypt(modelJson).toString());
                writer.close();
//                System.out.println("storage.dat does not exist");
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to create storage.dat file", e);
            }
        } else {
            System.out.println("storage.dat already exists.");
            byte[] encryptedData = Files.readAllBytes(storageFile.toPath());
            String decryptedJson = decrypt(encryptedData);
            System.out.println(":: Loaded Data from Storage :: "+decryptedJson);
            dataModel = gson.fromJson(decryptedJson, DataModel.class);
        }
    }

    // Encrypt a JSON string using AES-GCM
    private static byte[] encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        byte[] iv = new byte[GCM_IV_LENGTH];
        new SecureRandom().nextBytes(iv);
        GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmSpec);

        byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

        // Combine IV and encrypted data for storage
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(iv);
        outputStream.write(encryptedData);
        return outputStream.toByteArray();
    }

    // Decrypt an encrypted JSON string using AES-GCM
    private static String decrypt(byte[] encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        try {
            // Separate IV and encrypted data
            byte[] iv = new byte[GCM_IV_LENGTH];
            System.arraycopy(encryptedData, 0, iv, 0, GCM_IV_LENGTH);
            byte[] actualEncryptedData = new byte[encryptedData.length - GCM_IV_LENGTH];
            System.arraycopy(encryptedData, GCM_IV_LENGTH, actualEncryptedData, 0, actualEncryptedData.length);

            GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmSpec);
            byte[] decryptedData = cipher.doFinal(actualEncryptedData);

            return new String(decryptedData, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR :: Unable to decrypt data :: "+e.getMessage());
            File storageFile = new File(FILE_PATH);
            storageFile.delete();
//            loadData();
            return "";
        }

    }

    public static String getPassword(String username) {
        return "shadowy";
    }
    private static SecretKey generateSecretKey() {
    try {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(AES_KEY_SIZE);
        return keyGen.generateKey();
    } catch (Exception e) {
        throw new RuntimeException("Error generating secret key", e);
    }
}
}

