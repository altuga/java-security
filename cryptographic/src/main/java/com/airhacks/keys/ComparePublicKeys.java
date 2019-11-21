package com.airhacks.keys;

import java.security.PublicKey;
import java.util.Arrays;

public class ComparePublicKeys {

    public static void main(String[] args) throws Exception {
        AsymmetricCryptography ac = new AsymmetricCryptography();
        PublicKey publicKey = ac.getPublic("KeyPair/publicKey");


        PublicKey publicKey2 = ac.getPublic("KeyPair/publicKey");

        if (publicKey.equals(publicKey2)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        if (Arrays.equals(publicKey.getEncoded(), publicKey2.getEncoded())) {
            System.out.println("true");
        }else {
            System.out.println("false");
        }


    }

}
