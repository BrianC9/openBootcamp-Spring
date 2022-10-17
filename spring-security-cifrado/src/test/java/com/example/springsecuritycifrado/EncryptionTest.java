package com.example.springsecuritycifrado;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class EncryptionTest {

    @Test
    void bCryptTest(){
        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        String hashedPassword = bCryptEncoder.encode("admin");
        System.out.println(hashedPassword);
        System.out.println(bCryptEncoder.matches("admin",hashedPassword) ? "Coinciden las passwords":"NO coinciden");
    }
}
