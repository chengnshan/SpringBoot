package com.test.main;

import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import com.cxp.util.pwd.DESUtil;
import com.cxp.util.pwd.HexUtils;
import com.cxp.util.pwd.RSAUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TestPwd {

	@Test
	public void testDes() throws Exception {
		String str = "123456";

		// 密钥生成器
		KeyGenerator keyGen = KeyGenerator.getInstance("DES");
		keyGen.init(56);// 初始化密钥生成器
		SecretKey secretKey = keyGen.generateKey();// 生成密钥
		byte[] key = secretKey.getEncoded();// 密钥字节数组
		// key转换
		DESKeySpec desKeySpec = new DESKeySpec(key);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
		// 生成密钥
		SecretKey convertSecreKey = factory.generateSecret(desKeySpec);
		// Cipher完成加密或解密工作类
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		// 对Cipher初始化，加密模式
		cipher.init(Cipher.ENCRYPT_MODE, convertSecreKey);
		// 加密data
		byte[] encrypt = cipher.doFinal(str.getBytes());
		// 把加密后的数组转换为16进制字符串
		String encodeHexString = Hex.encodeHexString(encrypt);
		System.out.println(encodeHexString);
		// 把加密后的数组转换成base64字符串
		String encodeToString = Base64.getEncoder().encodeToString(encrypt);
		System.out.println(encodeToString);

		// 解密
		cipher.init(Cipher.DECRYPT_MODE, convertSecreKey);
		// 解密16进制字符串
		byte[] decryptHex = cipher.doFinal(HexUtils.decodeHex(encodeHexString.toCharArray()));
		System.out.println(new String(decryptHex));
		// 解密Base64字符串
		byte[] decryptBase64 = cipher.doFinal(Base64.getDecoder().decode(encodeToString));
		System.out.println(new String(decryptBase64));

	}

	@Test
	public void testDes2() throws Exception {
		String str = "123456";
		String key = "abcdef&HhDidsfE*sdrElIJZ";

		String encrypt2 = DESUtil.encrypt(str, key);
		System.out.println(encrypt2);
		String decrypt2 = DESUtil.decrypt(encrypt2, key);
		System.out.println(decrypt2);
	}

	@Test
	public void test3DES() throws Exception {
		String data = "123456"; // 需要加密的数据
		String strKey = "abcdefg&HOP#"; // 密钥
		// 密钥生成器
		KeyGenerator keyGen = KeyGenerator.getInstance("DESede");
		// 可指定密钥长度为112或168，默认为168
		keyGen.init(168, new SecureRandom(strKey.getBytes()));
		SecretKey secretKey = keyGen.generateKey();// 生成密钥
		byte[] keyByte = secretKey.getEncoded();// 密钥字节数组

		// ==================3DES加密====================
		// 传入共同约定的密钥（keyBytes）以及算法（Algorithm），来构建SecretKey密钥对象
		SecretKeySpec secretKeySpecEn = new SecretKeySpec(keyByte, "DESede");
		// 根据算法实例化Cipher对象。它负责加密/解密
		Cipher cipherEn = Cipher.getInstance("DESede");
		// 传入加密/解密模式以及SecretKey密钥对象，实例化Cipher对象
		cipherEn.init(Cipher.ENCRYPT_MODE, secretKeySpecEn);
		// 传入字节数组，调用Cipher.doFinal()方法，实现加密/解密，并返回一个byte字节数组
		byte[] cipherByte = cipherEn.doFinal(data.getBytes());
		// 把加密后的字节数组转换为base64字符串
		String encodeBase64String = org.apache.commons.codec.binary.Base64.encodeBase64String(cipherByte);
		System.out.println("加密后的Base64 字符串 : " + encodeBase64String);
		// 把加密后的字节数组转换为16进制字符串
		String hexString = HexUtils.encodeHexStr(cipherByte);
		System.out.println("加密后的16进制字符串 : " + hexString);

		// ==================3DES解密====================
		SecretKeySpec secretKeySpecDe = new SecretKeySpec(keyByte, "DESede");// 恢复密钥
		Cipher cipherDecode = Cipher.getInstance("DESede");
		cipherDecode.init(Cipher.DECRYPT_MODE, secretKeySpecDe);
		// 把Base64字符串转换为字节数组
		byte[] decodeBase64 = org.apache.commons.codec.binary.Base64.decodeBase64(encodeBase64String);
		byte[] cipherDe = cipherDecode.doFinal(decodeBase64);
		System.out.println("解密后的Base64 字符串 : " + new String(cipherDe));

		// 把16进制字符串转换为字节数组
		byte[] decodeHex = HexUtils.decodeHex(hexString.toCharArray());
		byte[] doFinal = cipherDecode.doFinal(decodeHex);
		System.out.println("解密后的16进制字符串 : " + new String(doFinal));
		System.out.println(new String());
	}

	@Test
	public void testPBE() {
		String src = "abc123";
		try {
			// 初始化盐
			SecureRandom random = new SecureRandom();
			byte[] salt = random.generateSeed(8);

			// 加密
			// 定义密码
			String password = "imooc";
			// 把密码转换成密钥
			PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
			Key key = factory.generateSecret(pbeKeySpec);

			// 加密过程
			PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, 100);// 盐和迭代次数
			Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
			cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out
					.println("jdk pbe encrypt : " + org.apache.commons.codec.binary.Base64.encodeBase64String(result));

			// 解密
			cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
			result = cipher.doFinal(result);
			System.out.println("jdk pbe decrypt : " + new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBase64() throws IOException {
		String src = "面向对象编程，object-oriented！@#*5"; // 需要加密的原始字符串
		System.out.println("原始字符串：\t\t\t" + src);

		// 如果这里没有出现sun的base64可以从Build Path中删除然后添加
		BASE64Encoder encoder = new BASE64Encoder();
		String encode = encoder.encode(src.getBytes());// 编码
		BASE64Decoder decoder = new BASE64Decoder();
		String decode = new String(decoder.decodeBuffer(encode));// 解码
		System.out.println("JDK实现的base64编码：\t\t" + encode);
		System.out.println("JDK实现的base64解码：\t\t" + decode);
		
		
		/** Commons Codec实现base64编码 */
		byte[] encodeCC = org.apache.commons.codec.binary.Base64.encodeBase64(src.getBytes());
        byte[] decodeCC = org.apache.commons.codec.binary.Base64.decodeBase64(encodeCC);
        System.out.println("Commons Codec实现base64编码：\t" + new String(encodeCC));
        System.out.println("Commons Codec实现base64解码：\t" + new String(decodeCC));
        
        
        /**Bouncy Castle实现base64编码 */
        byte[] encodeBC = org.bouncycastle.util.encoders.Base64.encode(src.getBytes());
        byte[] decodeBC = org.bouncycastle.util.encoders.Base64.decode(encodeBC);
        System.out.println("Bouncy Castle实现base64编码：\t"+new String(encodeBC));
        System.out.println("Bouncy Castle实现base64解码：\t"+new String(decodeBC));
        
        /**Java 8提供的Base64拥有更好的效能*/
        byte[] encodeJdk8 = Base64.getEncoder().encode(src.getBytes());
        byte[] decodeJdk8 = Base64.getDecoder().decode(encodeJdk8);
        System.out.println("JDK8实现base64编码：\t\t"+new String(encodeJdk8));
        System.out.println("JDK8实现base64解码：\t\t"+new String(decodeJdk8));
	}
	
	@Test
	public void testRSA() {
		String key = "abcdef";
		String src = "我是被加密的内容123";
		try {
			//1.初始化密钥
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(512, new SecureRandom(key.getBytes()));
//			keyPairGenerator.initialize(512);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
			System.out.println("Public Key" + Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded()));
			System.out.println("Private Key" + Base64.getEncoder().encodeToString(rsaPrivateKey.getEncoded()));
			
			//2.私钥加密、公钥解密 -- 加密
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			byte[] result = cipher.doFinal(src.getBytes("utf-8"));
			System.out.println("私钥加密、公钥解密 -- 加密: "+Base64.getEncoder().encodeToString(result));
			
			//3.私钥加密、公钥解密 -- 解密
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			result = cipher.doFinal(result);
			System.out.println("私钥加密、公钥解密 -- 解密: " + new String(result,"utf-8"));
			
			//=======================================================================================
			//4.公钥加密、私钥解密 -- 加密
			x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("RSA");
			publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			result = cipher.doFinal(src.getBytes("utf-8"));
			System.out.println("公钥加密、私钥解密 -- 加密 : "+ Base64.getEncoder().encodeToString(result));
			
			//5.公钥加密、私钥解密 -- 解密
			pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
			keyFactory = KeyFactory.getInstance("RSA");
			privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			result = cipher.doFinal(result);
			System.out.println("公钥加密、私钥解密 -- 解密 : " + new String(result,"utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRSAUtil() throws Exception {
		Map<String, Object> genKeyPair = RSAUtils.genKeyPair();
		
	}
}
