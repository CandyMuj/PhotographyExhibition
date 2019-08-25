package com.cc.utils.secret;

import java.security.MessageDigest;

/**
 * 采用SHAA加密 因为二者均由MD4导出，SHA-1和MD5彼此很相似。相应的，他们的强度和其他特性也是相似，但还有以下几点不同：
 * 
 * 1）对强行攻击的安全性：最显著和最重要的区别是SHA-1摘要比MD5摘要长32
 * 位。使用强行技术，产生任何一个报文使其摘要等于给定报摘要的难度对MD5是2^
 * 128数量级的操作，而对SHA-1则是2^160数量级的操作。这样，SHA-1对强行攻击有更大的强度。
 * 
 * 2）对密码分析的安全性：由于MD5的设计，易受密码分析的攻击，SHA-1显得不易受这样的攻击。
 * 
 * 3）速度：在相同的硬件上，SHA-1的运行速度比MD5慢。
 */
// SHA是一种数据加密算法，该算法经过加密专家多年来的发展和改进已日益完善，现在已成为公认的最安全的散列算法之一，并被广泛使用。该算法的思想是接收一段明文，然后以一种不可逆的方式将它转换成一段（通常更小）密文，也可以简单的理解为取一串输入码（称为预映射或信息），并把它们转化为长度较短、位数固定的输出序列即散列值（也称为信息摘要或信息认证代码）的过程。散列函数值可以说是对明文的一种“指纹”或是“摘要”所以对散列值的数字签名就可以视为对此明文的数字签名。
public class SHAUtil {
	/***
	 * SHA加密 生成40位SHA码
	 * 
	 * @param inStr 待加密字符串
	 * @return 返回40位SHA码
	 */
	public static String shaEncode(String inStr) throws Exception {
		MessageDigest sha = null;
		try {
			sha = MessageDigest.getInstance("SHA");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}

		byte[] byteArray = inStr.getBytes("UTF-8");
		byte[] md5Bytes = sha.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * 测试主函数
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		String str = new String("000000");
		System.out.println("原始：" + str);
		System.out.println("SHA后：" + shaEncode(str));
	}
}