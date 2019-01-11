package com.github.gongfuboy.utils.signature;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA不对称加密解密tool
 *
 * Created by ZhouLiMing on 2019/1/4.
 */
public class RSAAESignatureTool {

    /**公钥*/
    private final PublicKey publicKey;
    /**私钥*/
    private final PrivateKey privateKey;

    /**
     *
     * @param publicKey
     * @param privateKey
     * @throws NoSuchAlgorithmException
     */
    public RSAAESignatureTool(String publicKey, String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey.getBytes()));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        this.privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey.getBytes()));
        this.publicKey = keyFactory.generatePublic(keySpec);
    }

    public String encryptByPublicKey() {
        new X509EncodedKeySpec(publicKey.getEncoded());
        return null;
    }

}
