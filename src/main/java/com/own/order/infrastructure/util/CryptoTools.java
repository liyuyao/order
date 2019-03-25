package com.own.order.infrastructure.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

public class CryptoTools {
    private static final byte[] DESkey = { (byte) 0x43, (byte) 0x6f, (byte) 0x72, (byte) 0x65, (byte) 0x5f, (byte) 0x48,
            (byte) 0x5f, (byte) 0x4e };// 设置密钥，略去

    private static final byte[] DESIV = { (byte) 0x43, (byte) 0x72, (byte) 0x79, (byte) 0x44, (byte) 0x65, (byte) 0x73,
            (byte) 0x49, (byte) 0x76 };// 设置向量，略去

    static AlgorithmParameterSpec iv = null;// 加密算法的参数接口，IvParameterSpec是它的一个实现
    private static Key key = null;

    public CryptoTools() throws Exception {
        try {
            DESKeySpec keySpec = new DESKeySpec(DESkey);// 设置密钥参数
            iv = new IvParameterSpec(DESIV);// 设置向量
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
            key = keyFactory.generateSecret(keySpec);// 得到密钥对象
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public String encode(String data) throws Exception {
        Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
        enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
        byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(pasByte);
    }

    public String decode(String data) throws Exception {
        Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        deCipher.init(Cipher.DECRYPT_MODE, key, iv);
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data));
        return new String(pasByte, "UTF-8");
    }
}