/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

/**
 *
 * @author levilliard
 */

import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

public class DataHash {
    public static String getHash(byte[] input, String algorithm){
        String hashValue = "";
        
        try{
            MessageDigest msg = MessageDigest.getInstance(algorithm);
            msg.update(input);
            byte[] digestByte = msg.digest();
            
            hashValue = DatatypeConverter.printHexBinary(digestByte).toLowerCase();
        }catch(Exception ex){
            
        }
        
        return hashValue;
    }
    
    public static boolean loginPassword(String password, String test){
        return password.equalsIgnoreCase(test);
    }
}


/*
    First we define 3 user's priv
        1. The highest system privilege (With this user: BaronSamdi, TizonDifeSOQ)
        2. The admin 
        3. Default User
        4. No 4, and then no Guest



        -------------functions-----------------
        2. Admin
            - Institution, Class, Student, Small-Staff, Report, Payment and User management

        3. Default User
            - Presence, Retard, Student, Teacher management
*/