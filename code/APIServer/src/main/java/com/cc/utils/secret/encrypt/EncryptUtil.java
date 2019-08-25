package com.cc.utils.secret.encrypt;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * java支持的加密解密 <br>
 * 单向加密：MD5、SHA1 <br>
 * 双向加密：DES、AES <br>
 * 注意：本工具类不使用Base64转字符串，而是直接将byte[]转为16进制字符串
 * 
 * @author linin
 *
 */
public class EncryptUtil implements EncryptUtilApi {

    public static void main(String... arg) {
        String res = "测试test";
        String key = "秘钥key";
        String mw = "密文，临时用的";
        System.out.println("--MD5--");
        System.out.println(EncryptUtil.getInstance().MD5(res));
        System.out.println(EncryptUtil.getInstance().MD5(res, key));
        System.out.println("--SHA1--");
        System.out.println(EncryptUtil.getInstance().SHA1(res));
        System.out.println(EncryptUtil.getInstance().SHA1(res, key));
        System.out.println("--DES--");
        mw = EncryptUtil.getInstance().DESencode(res, key);
        System.out.println(mw);
        System.out.println(EncryptUtil.getInstance().DESdecode(mw, key));
        System.out.println("--AES--");
        mw = EncryptUtil.getInstance().AESencode(res, key);
        System.out.println(mw);
        System.out.println(EncryptUtil.getInstance().AESdecode(mw, key));

        System.out.println("--异或加密--");
        mw = EncryptUtil.getInstance().XORencode(res, key);
        System.out.println(mw);
        System.out.println(EncryptUtil.getInstance().XORdecode(mw, key));
        int i = 12345;
        int ii = EncryptUtil.getInstance().XOR(i, key);
        int iii = EncryptUtil.getInstance().XOR(ii, key);
        System.out.println(String.format(i + "异或一次：%s；异或两次：%s", ii, iii));
    }

    public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA1";
    public static final String HmacMD5 = "HmacMD5";
    public static final String HmacSHA1 = "HmacSHA1";
    public static final String DES = "DES";
    public static final String AES = "AES";

    /** 编码格式；默认null为GBK */
    public String charset = null;
    /** DES */
    public int keysizeDES = 0;
    /** AES */
    public int keysizeAES = 128;
    public static EncryptUtil me;

    private EncryptUtil() {
        // 单例
    }

    public static EncryptUtil getInstance() {
        if (me == null) {
            me = new EncryptUtil();
        }
        return me;
    }

    /** 使用MessageDigest进行单向加密（无密码） */
    private String messageDigest(String res, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] resBytes = charset == null ? res.getBytes() : res.getBytes(charset);
            return base64(md.digest(resBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 使用KeyGenerator进行单向/双向加密（可设密码） */
    private String keyGeneratorMac(String res, String algorithm, String key) {
        try {
            SecretKey sk = null;
            if (key == null) {
                KeyGenerator kg = KeyGenerator.getInstance(algorithm);
                sk = kg.generateKey();
            } else {
                byte[] keyBytes = charset == null ? key.getBytes() : key.getBytes(charset);
                sk = new SecretKeySpec(keyBytes, algorithm);
            }
            Mac mac = Mac.getInstance(algorithm);
            mac.init(sk);
            byte[] result = mac.doFinal(res.getBytes());
            return base64(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 使用KeyGenerator双向加密，DES/AES，注意这里转化为字符串的时候是将2进制转为16进制格式的字符串，不是直接转，因为会出错 */
    private String keyGeneratorES(String res, String algorithm, String key, int keysize, boolean isEncode) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(algorithm);
            if (keysize == 0) {
                byte[] keyBytes = charset == null ? key.getBytes() : key.getBytes(charset);
                kg.init(new SecureRandom(keyBytes));
            } else if (key == null) {
                kg.init(keysize);
            } else {
                byte[] keyBytes = charset == null ? key.getBytes() : key.getBytes(charset);
                kg.init(keysize, new SecureRandom(keyBytes));
            }
            SecretKey sk = kg.generateKey();
            SecretKeySpec sks = new SecretKeySpec(sk.getEncoded(), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            if (isEncode) {
                cipher.init(Cipher.ENCRYPT_MODE, sks);
                byte[] resBytes = charset == null ? res.getBytes() : res.getBytes(charset);
                return parseByte2HexStr(cipher.doFinal(resBytes));
            } else {
                cipher.init(Cipher.DECRYPT_MODE, sks);
                return new String(cipher.doFinal(parseHexStr2Byte(res)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String base64(byte[] res) {
        return Base64.encode(res);
    }

    /** 将二进制转换成16进制 */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /** 将16进制转换为二进制 */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    @Override
    public String MD5(String res) {
        return messageDigest(res, MD5);
    }

    @Override
    public String MD5(String res, String key) {
        return keyGeneratorMac(res, HmacMD5, key);
    }

    @Override
    public String SHA1(String res) {
        return messageDigest(res, SHA1);
    }

    @Override
    public String SHA1(String res, String key) {
        return keyGeneratorMac(res, HmacSHA1, key);
    }

    @Override
    public String DESencode(String res, String key) {
        return keyGeneratorES(res, DES, key, keysizeDES, true);
    }

    @Override
    public String DESdecode(String res, String key) {
        return keyGeneratorES(res, DES, key, keysizeDES, false);
    }

    @Override
    public String AESencode(String res, String key) {
        return keyGeneratorES(res, AES, key, keysizeAES, true);
    }

    @Override
    public String AESdecode(String res, String key) {
        return keyGeneratorES(res, AES, key, keysizeAES, false);
    }

    @Override
    public String XORencode(String res, String key) {
        byte[] bs = res.getBytes();
        for (int i = 0; i < bs.length; i++) {
            bs[i] = (byte) ((bs[i]) ^ key.hashCode());
        }
        return parseByte2HexStr(bs);
    }

    @Override
    public String XORdecode(String res, String key) {
        byte[] bs = parseHexStr2Byte(res);
        for (int i = 0; i < bs.length; i++) {
            bs[i] = (byte) ((bs[i]) ^ key.hashCode());
        }
        return new String(bs);
    }

    @Override
    public int XOR(int res, String key) {
        return res ^ key.hashCode();
    }

}