/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.email_and_password;
// Project based in JavaMaven
// email  regex check as well a light encription and decription with the approprate output for mini demo

/////////////////////
////////////////////

// relevant imports for mini project

import javax.crypto.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.*;

/////////////////////
////////////////////
        


public class Pass_Email_Module {
    
    
    
    // main class
    public static void main(String[] args) {
                 
        
        
        
        // console input declaration (variable name input)
     Scanner input = new Scanner(System.in);
     
     
     
     // user prompt and user input caputre for email 
        System.out.println("Enter an email address: ");
        String emailIn = input.nextLine();
        
        
        
        
             // user prompt and user input caputre for password
        System.out.println("Enter a Password: ");
        String passwordIn = input.nextLine();
        
        
        
        // true / flase check for email (uses regex)
        boolean isEmailValid = validateEmail(emailIn);
        
        
        
        /*
if the email is valid I.E. boolean = true then the password will be encrypted and decrypted 
and the user will see the encrypted version and the decrypted 
*/        
        if (isEmailValid) {
            
            // method where the AES algorithm is used with 128 bits as the key size
            SecretKey secretKey = generatingSymmetricKey();
            
            // (type of varuble) the varuble name = method (vaubles used for caputring data (passwordIn) , and the security key )
            String encryptedPassword = encryptPassword(passwordIn, secretKey);
            System.out.println("Valid email address. \nEncrypted Password: " + encryptedPassword);
            // output for the user
            
              // (type of varuble) the varuble name = method (vaubles used for caputring data (encryptedPassword) , and the security key )
            String dencryptedPassword = dencryptPassword(encryptedPassword, secretKey);
            System.out.println("Decrypted Password: " + dencryptedPassword);
                   // output for the user
            // else generic error
        }else
        {
            System.out.println("Email not Valid.");
        }
        
               
    }
   // end of main
    
    
    
    // method for regex check for email
    public static boolean validateEmail(String email) {
        // regex pattern declaration 
        String regexPattern = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9]+\\.)+[A-Za-z]{2,4}$";
        
        
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(email);
        
        return matcher.matches();
        
        
    }
    
    
    // key genarator method used for the 128 bit AES key genaration
    
         public static SecretKey generatingSymmetricKey() {
             try {
                 KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                 keyGenerator.init(128);
                 return keyGenerator.generateKey();
                 
             } catch (Exception e) {
                 e.printStackTrace();
             }
             return  null;
                    
    }
         
         
         
    // encryption method that uses the user provided password and the generated secret key to encrypt the password
public static String encryptPassword(String password, SecretKey secretKey) {
    try {
        // Create a Cipher instance for the AES algorithm
        Cipher cipher = Cipher.getInstance("AES");

        // Initialize the cipher in ENCRYPT_MODE with the provided secret key
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Convert the password string to bytes and perform encryption
        byte[] encryptedBytes = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));

        // Encode the encrypted bytes to Base64 for easy storage or transmission
        return Base64.getEncoder().encodeToString(encryptedBytes);
    } catch (Exception e) {
        // Handle any exceptions that might occur during encryption
        e.printStackTrace();
    }
    // Return null in case of an exception
    return null;
}

        
        
        
public static String dencryptPassword(String encryptedPassword, SecretKey secretKey) {
    try {
        // Create a Cipher instance for the AES algorithm
        Cipher cipher = Cipher.getInstance("AES");

        // Initialize the cipher in DECRYPT_MODE with the provided secret key
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decode the Base64-encoded encrypted password
        byte[] decryptedBytes = Base64.getDecoder().decode(encryptedPassword);

        // Perform decryption
        byte[] decryptedBytesFinal = cipher.doFinal(decryptedBytes);

        // Convert the decrypted bytes back to a string using UTF-8 encoding
        return new String(decryptedBytesFinal, StandardCharsets.UTF_8);
    } catch (Exception e) {
        // Handle any exceptions that might occur during decryption
        e.printStackTrace();
    }
    // Return null in case of an exception
    return null;
}

     
     
     
    
}