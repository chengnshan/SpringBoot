package com.cxp.util.pwd;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * DES加密工具类
 */
public class DESUtil {

	// 算法名称
	public static final String KEY_ALGORITHM = "DES";
	// 算法名称/加密模式/填充方式
	// DES共有四种工作模式-->>ECB：电子密码本模式、CBC：加密分组链接模式、CFB：加密反馈模式、OFB：输出反馈模式
	private static final String DEFAULT_CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";// 默认的加密算法

	/**
	 * DES 加密操作
	 *
	 * @param content
	 *            待加密内容
	 * @param key
	 *            加密密钥
	 * @return 返回Base64转码后的加密数据
	 */
	public static String encrypt(String content, String key) {
		try {
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));// 初始化为加密模式的密码器
			byte[] result = cipher.doFinal(byteContent);// 加密
			return Base64.encodeBase64String(result);// 通过Base64转码返回
		} catch (Exception ex) {
			Logger.getLogger(DESUtil.class.getName()).log(org.apache.log4j.Level.DEBUG, ex.getMessage());
		}

		return null;
	}

	/**
	 * DES 解密操作
	 *
	 * @param content
	 * @param key
	 * @return
	 */
	public static String decrypt(String content, String key) {

		try {
			// 实例化
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			// 使用密钥初始化，设置为解密模式
			cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));
			// 执行操作
			byte[] result = cipher.doFinal(Base64.decodeBase64(content));

			return new String(result, "utf-8");
		} catch (Exception ex) {
			Logger.getLogger(DESUtil.class.getName()).log(org.apache.log4j.Level.DEBUG, ex.getMessage());
		}

		return null;
	}

	/**
	 * 生成加密秘钥
	 *
	 * @return
	 */
	private static SecretKeySpec getSecretKey(final String key) {
		// 返回生成指定算法密钥生成器的 KeyGenerator 对象
		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance(KEY_ALGORITHM);
			// DES 要求密钥长度为 56
			kg.init(56, new SecureRandom(key.getBytes()));
			// 生成一个密钥
			SecretKey secretKey = kg.generateKey();
			return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为DES专用密钥
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(DESUtil.class.getName()).log(org.apache.log4j.Level.DEBUG, ex.getMessage());
		}
		return null;
	}
}
