package com.flarebo.wx.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import com.flarebo.wx.constant.Constant;

/**
 * Created by Administrator on 2014/9/7.
 */
public class SignUtil {
	public static boolean  checkSignature(String signature,String timestamp,String nonce){
		String[] arr=new String[]{Constant.TOKEN,timestamp,nonce};
		Arrays.sort(arr);
		StringBuilder content=new StringBuilder();
		for(String s:arr ){
			content.append(s);
		}
		MessageDigest md=null;
		String tmpStr=null;
		try{
			md=MessageDigest.getInstance("SHA-1");
			byte[] digist=md.digest(content.toString().getBytes());
			tmpStr=byteToStr(digist);

		}catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		 return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}
	/**
	 * 将字节数组转换为十六进制字符�?
	 *
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符�?
	 *
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];

		String s = new String(tempArr);
		return s;
	}
}
