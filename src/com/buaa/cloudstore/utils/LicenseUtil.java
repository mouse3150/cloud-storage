package com.buaa.cloudstore.utils;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import com.buaa.cloudstore.core.SuperProperties;


public class LicenseUtil {
	
	private static int interval = 3;

	private static int interval2 = interval * 2;
	
	private static String LICENSE_NAME = "license.dat";
	public final static String CONSUMER_NAME = "consumer_name";
	public final static String PROJECT_NAME = "project_name";
	public final static String BARGAIN_ID = "order_id";
	public final static String LICENSE_TYPE = "licence_type";
	public final static String CREATE_DATE = "create_date";
	public final static String END_DATE = "end_date";



	/**
	 * 把一个明文的字符串加密并且按一定规则进行补齐
	 * @param s：需要加密的字符串
	 * @return: 经过加密补齐后的密文
	 */
	public static String base64Encode(String s) {
		String ts1 = "";
		String ts2 = "";
		String base64Content = "";

		try {
			byte[] b = s.getBytes();
			String base64String = Base64Encoder.toBase64String(b);
			int len = base64String.length();
			for (int j = 0; j < len; j += interval2) {

				if (j + interval2 >= len)
					for (int k = len; k < j + interval2; k++)
						base64String += " ";

				ts1 = base64String.substring(j, j + interval);
				ts2 = base64String.substring(j + interval, j + interval2);

				base64Content += ts2;
				if (ts2.length() < interval)
					base64Content +=ts1+'\n';
				else
					base64Content +=ts1;
			}
			return base64Content;
		} catch (Exception e) {
			e.printStackTrace();
			return base64Content;
		}
	}

	
	/**
	 * 读取加密后的配置文件，返回明文
	 * @param f1: 文件名
	 * @return: 解密后的一个串
	 */
	private static String base64Decode(String f1) {
		String s = "";
		try {

			BufferedReader in = new BufferedReader(new FileReader(f1));
			int interval = 3;
			int interval2 = interval * 2;
			String line;
			String ts1 = "";
			String ts2 = "";
			String fl = "";
			while ((line = in.readLine()) != null) {

				for (int i = 0; i < line.length(); i += interval2) {
					if (i + interval >= line.length()) {
						ts1 = line.substring(i, line.length());
						ts2 = "";
					} else {
						ts1 = line.substring(i, i + interval);
						if (i + interval2 >= line.length())
							ts2 = line.substring(i + interval, line.length());
						else
							ts2 = line.substring(i + interval, i + interval2);
					}
					fl += ts2 + ts1;
				}
				byte[] b = Base64Encoder.fromBase64String(fl);
				s = new String(b);

			}
			in.close();
			return s;
		} catch (IOException e) {
			System.out.println("Cannot read license file!!!");
			return "";
		}
	}
	
	/**
	 * 根据一个license的路径来获取此License的内容
	 * @param licensePath: license的路径
	 * @param licenseName: license的名称，如果传入NULL,则默认为license.dat
	 * @return: 返回一个Hashtable结构的license内容，其中，单值对应的都是String,多值得对应的是List,使用者根据需求来转换即可
	 */
	public static Hashtable getLicesneContent(String licensePath,String licenseName){
		Hashtable hashTable = new Hashtable();
		
		String t = licensePath.substring(licensePath.length()-1);
		if (t.equals("\\")){
			if (licenseName == null)
				licensePath += LICENSE_NAME;
			else
				licensePath += licenseName;
		}else{
			if (licenseName == null)
				licensePath += "\\" + LICENSE_NAME;
			else
				licensePath += "\\" + licenseName;
		}
		String s = base64Decode(licensePath);
		try{
		   InputStream in = new ByteArrayInputStream(s.getBytes());
		   SuperProperties myPro = new SuperProperties(System.getProperties());
		   myPro.load(in);
		   Enumeration enumKey = myPro.keys();
		   while(enumKey.hasMoreElements()){
			   String keyStr = enumKey.nextElement().toString();
			   if (keyStr.indexOf("__") > 0){ // 说明是多值类型的
				   List attList = new ArrayList();
				   int index = keyStr.indexOf("__");
				   String tempKey = keyStr.substring(0,index);
				   NumberdPropertyKey[] keyArr = NumberdPropertyKey.getNumberdPropertyKeys(myPro,tempKey+"__");
				   for (int i = 0; i < keyArr.length; i++){
					   attList.add(myPro.getProperty(keyArr[i].key));
				   }
				   hashTable.put(tempKey,attList);
			   }else{
				   hashTable.put(keyStr,myPro.getProperty(keyStr));
			   }
		   }
		   return hashTable;
		   
		}catch(Exception ex){
			ex.printStackTrace();
		}

		return hashTable;
	}

	
	
	/**
	 * 打印当前产品对应的License的内容，在各产品安装后，可以做一个可执行的文件来运行打印License的内容，用户也可以自行查询。
	 * @param licensePath: license的路径
	 * @param licenseName: license的名称，如果传入NULL,则默认为license.dat 
	 * @return: 返回一个Hashtable结构的license内容，其中，单值对应的都是String,多值得对应的是List,使用者根据需求来转换即可
	 */
	public static void printLicenseContent(String licensePath,String licenseName){
		
		String t = licensePath.substring(licensePath.length()-1);
		if (t.equals("\\")){
			if (licenseName == null)
				licensePath += LICENSE_NAME;
			else
				licensePath += licenseName;
		}else{
			if (licenseName == null)
				licensePath += "\\" + LICENSE_NAME;
			else
				licensePath += "\\" + licenseName;
		}
		String s = base64Decode(licensePath);
		System.out.println("-----------------------------------------");
		System.out.println("-----------License 信息-------------------");
		System.out.println(s);
		System.out.println("-----------License 信息-------------------");
		System.out.println("-----------------------------------------");
	}
	
	/**
	 * 
	 * @param encodeStr 编码字符
	 */
	public static void printLicenseContent(String encodeStr){
		
		String ts1 = "";
		String ts2 = "";
		String fl = "";
		
		for (int i = 0; i < encodeStr.length(); i += interval2) {
			if (i + interval >= encodeStr.length()) {
				ts1 = encodeStr.substring(i, encodeStr.length());
				ts2 = "";
			} else {
				ts1 = encodeStr.substring(i, i + interval);
				if (i + interval2 >= encodeStr.length())
					ts2 = encodeStr.substring(i + interval, encodeStr.length());
				else
					ts2 = encodeStr.substring(i + interval, i + interval2);
			}
			fl += ts2 + ts1;
		}
		
		byte[] b = Base64Encoder.fromBase64String(fl);
		String s = new String(b);
		System.out.println("-----------------------------------------");
		System.out.println("-----------License 信息-------------------");
		System.out.println(s);
		System.out.println("-----------License 信息-------------------");
		System.out.println("-----------------------------------------");
	}	
	public static void main(String[] args) {
		printLicenseContent("vZHcHJF9uVjdlPUYW1G91dDbwcmZApWN09kdlcnX3Z249NpbwCnMS42plBybfbmY3RT13FtZnc3YW5m9qRwc0MgZWN25zpjblcldW1W1l9uYhbmPXdApvdzdlclcmRD0x9pZwMTMDAGljAKbzZVZW5XBl90elbXPXRGljAKbzZVZW5XZl9sZBZHbD1mNlZhbjcmZApGVfVhd0ZTZGFDE10yMyLTLTAmVuAyCkYXZF9S0xRlPheFCm1HVf9jctPTbnVg==IwC");
	}

}
