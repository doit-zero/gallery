package kr.co.wikibook.gallery.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionUtils {
    private static final String ALGORITHM = "AES"; // 대칭 키 알고리즘 AES
    // 암호화 및 복호화에 사용되는 고정된 비밀 키
    private static final String SECRET_KEY = "test1234!@#$test"; // 테스트 용도임 추후 외부에 노출되지 않도록 작업해야함

    private String injectedKey;
    // 매개변수로 받은 문자열을 암호화
    public static String encrypt(String value){
        try {
            // 1. 문자열인 SECRET_KEY를 바이트 배열로 전환된 값과 알고리즘 사용하여 SecretKeySpec 객체 생성
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);

            // 2. 암호화 알고리즘에 대한 Cipher 인스턴스 가져옴 이 Cipher는 암호화/복호화에 모두 사용됨
            Cipher cipher = Cipher.getInstance(ALGORITHM);

            // 3. SecretKeySpec를 사용하여 cipher 인스턴스를 암호화 모드로 초기화
            cipher.init(Cipher.ENCRYPT_MODE,key);

            // 4. 매개변수를 바이트 배열로 전환 후 cipher로 암호화한다. 결과는 바이트 배열
            byte[] encryptedValue = cipher.doFinal(value.getBytes());

            // 5. 암호화된 바이트 배여을을 Base64 문자열로 인코딩해서 반환 Base64를 쓰는 이윤는 바이너리를 안전하게 문자열로 전환해주기 때문
            return Base64.getEncoder().encodeToString(encryptedValue);
        } catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("데어터 암호화 중 오류가 발생했습니다." + ex.getMessage());
        }
    }


    // 매개변수로 받은 암호화된 문자열을 복호화하는 메서드
    public static String decrypt(String value){
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE,key);
            byte[] decryptedValue = Base64.getDecoder().decode(value);
            return new String(cipher.doFinal(decryptedValue));
        } catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("데어터 복호화 중 오류가 발생했습니다." + ex.getMessage());
        }
    }
}
