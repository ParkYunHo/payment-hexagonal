package com.john.payment.common.utils;

import com.john.payment.common.exception.BadRequestException;
import jakarta.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 * @author john.09
 * @since 2022.12.19
 */
@Slf4j
public class CipherUtils {

    private static Cipher cipher;
    private static SecretKeySpec keySpec;

    static {
        try{
            cipher = Cipher.getInstance("AES");
            keySpec = new SecretKeySpec("parkyoonhosecret".getBytes(), "AES");
        }catch (Exception e){
            log.error(" >>> [CipherUtils] Exception occurs - message: {}", e.getMessage());
        }
    }

    public static String encode(String value) {
        try{
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            return new String(Base64.encodeBase64(cipher.doFinal(value.getBytes()), false), StandardCharsets.UTF_8);
//            return Base64.encodeBase64String(cipher.doFinal(value.getBytes()));
        }catch (Exception e){
            log.error(" >>> [encode] Exception occurs - message: {}", e.getMessage());
            throw new BadRequestException("암호화 실패");
        }
    }

    public static String decode(String value) {
        try{
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            return new String(cipher.doFinal(Base64.decodeBase64(value)), StandardCharsets.UTF_8);
        }catch (Exception e){
            log.error(" >>> [decode] Exception occurs - message: {}", e.getMessage());
            throw new BadRequestException("복호화 실패");
        }
    }
}
