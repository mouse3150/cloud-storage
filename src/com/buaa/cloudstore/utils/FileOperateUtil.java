package com.buaa.cloudstore.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileOperateUtil {
	public static final String REALNAME = "realName";
	public static final String STORENAME = "storeName";
	public static final String SIZE = "size";
	public static final String SUFFIX = "suffix";
	public static final String CONTENTTYPE = "contentType";
	public static final String UPLOADDIR = "uploadDir";
	public static final String RELATIVE_PATH = "relativePath";

	/**
	 * 将上传的文件进行重命名
	 * 
	 * @param name
	 * @return
	 */
	public static String rename(String name) {

		Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		Long random = (long) (Math.random() * now);
		String fileName = now + "" + random;

		if (name.indexOf(".") != -1) {
			fileName += name.substring(name.lastIndexOf("."));
		}

		return fileName;
	}

	/**
	 * 压缩后的文件名
	 * 
	 * @param name
	 * @return
	 */
	private static String zipName(String name) {
		String prefix = "";
		if (name.indexOf(".") != -1) {
			prefix = name.substring(0, name.lastIndexOf("."));
		} else {
			prefix = name;
		}
		return prefix + ".zip";
	}

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param params
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> uploadMultiFiles(HttpServletRequest request,
			String[] params, Map<String, Object[]> values) throws Exception {

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();

		String uploadDir = request.getSession().getServletContext().getRealPath("/")
				+ FileOperateUtil.UPLOADDIR;
		File file = new File(uploadDir);

		if (!file.exists()) {
			file.mkdirs();
		}

		String fileName = null;
		int i = 0;
		for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it
				.hasNext(); i++) {

			Map.Entry<String, MultipartFile> entry = it.next();
			MultipartFile mFile = entry.getValue();

			fileName = mFile.getOriginalFilename();

			String storeName = rename(fileName);

			String noZipName = uploadDir + storeName;
			String zipName = zipName(noZipName);

			// 上传成为压缩文件
			ZipOutputStream outputStream = new ZipOutputStream(new BufferedOutputStream(
					new FileOutputStream(zipName)));
			outputStream.putNextEntry(new ZipEntry(fileName));
			// outputStream.setEncoding("GBK");

			FileCopyUtils.copy(mFile.getInputStream(), outputStream);

			Map<String, Object> map = new HashMap<String, Object>();
			// 固定参数值对
			map.put(FileOperateUtil.REALNAME, zipName(fileName));
			map.put(FileOperateUtil.STORENAME, zipName(storeName));
			map.put(FileOperateUtil.SIZE, new File(zipName).length());
			map.put(FileOperateUtil.SUFFIX, "zip");
			map.put(FileOperateUtil.CONTENTTYPE, "application/octet-stream");

			// 自定义参数值对
			for (String param : params) {
				map.put(param, values.get(param)[i]);
			}

			result.add(map);
		}
		return result;
	}

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param params
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> uploadSingleFile(HttpServletRequest request, String path) throws Exception {

		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		MultipartFile mFile = mRequest.getFile("file");
		
		StringBuffer relativePath = new StringBuffer(FileOperateUtil.UPLOADDIR);
		relativePath.append(path);
		
		String uploadDir = request.getSession().getServletContext().getRealPath("/")
				+ relativePath.toString();
		
		File file = new File(uploadDir);

		if (!file.exists()) {
			file.mkdirs();
		}

		String fileName = null;

		fileName = mFile.getOriginalFilename();
		String storeName = rename(fileName);
		
		
		String noZipName = uploadDir + "/" + storeName;
		String zipName = zipName(noZipName);
		
		
		relativePath.append("/").append(zipName(storeName));
		
		// 上传成为压缩文件
		ZipOutputStream outputStream = new ZipOutputStream(new BufferedOutputStream(
				new FileOutputStream(zipName)));
		outputStream.putNextEntry(new ZipEntry(fileName));
		// outputStream.setEncoding("GBK");

		FileCopyUtils.copy(mFile.getInputStream(), outputStream);

		Map<String, Object> map = new HashMap<String, Object>();
		// 固定参数值对
		map.put(FileOperateUtil.REALNAME, zipName(fileName));
		map.put(FileOperateUtil.STORENAME, zipName(storeName));
		map.put(FileOperateUtil.SIZE, new File(zipName).length());
		map.put(FileOperateUtil.SUFFIX, "zip");
		map.put(FileOperateUtil.CONTENTTYPE, "application/octet-stream");
		map.put(FileOperateUtil.RELATIVE_PATH, relativePath.toString());

		return map;
	}

	/**
	 * 下载
	 * 
	 * @param request
	 * @param response
	 * @param storeName
	 * @param contentType
	 * @param realName
	 * @throws Exception
	 */
	public static void download(HttpServletRequest request, HttpServletResponse response,
			String storeName, String contentType, String realName) {

		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null; 

		String ctxPath = request.getSession().getServletContext().getRealPath("/");
		String downLoadPath = ctxPath + storeName;

		long fileLength = new File(downLoadPath).length();
		response.reset();

		response.setContentType(contentType);
		try {
			response.setHeader("Content-Disposition",
					"attachment; filename=" + new String(realName.getBytes("UTF-8"), "ISO8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setHeader("Content-Length", String.valueOf(fileLength));

		try {
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			bos = new BufferedOutputStream(response.getOutputStream());

			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
