package com.buaa.cloudstore.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8205659028048553653L;

	private static final String CONTENT_TYPE = "text/html; charset=GBK";

	// Initialize global variables
	public void init() throws ServletException {
	}

	// Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		// 得到下载文件的名字
		// String filename=request.getParameter("filename");

		// 解决中文乱码问题
		String filename = new String(request.getParameter("filename").getBytes("iso-8859-1"), "gbk");

		// 创建file对象
		File file = new File("E:\\tmp" + filename);

		// 设置response的编码方式
		response.setContentType("application/x-msdownload");

		// 写明要下载的文件的大小
		response.setContentLength((int) file.length());

		// 设置附加文件名
		// response.setHeader("Content-Disposition","attachment;filename="+filename);

		// 解决中文乱码
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(filename.getBytes("GBK"), "iso-8859-1"));

		// 读出文件到i/o流
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream buff = new BufferedInputStream(fis);

		byte[] b = new byte[1024];// 相当于我们的缓存

		long k = 0;// 该值用于计算当前实际下载了多少字节

		// 从response对象中得到输出流,准备下载

		OutputStream resposeOutput = response.getOutputStream();

		// 开始循环下载

		while (k < file.length()) {

			int j = buff.read(b, 0, 1024);
			k += j;

			// 将b中的数据写到客户端的内存
			resposeOutput.write(b, 0, j);

		}

		// 将写入到客户端的内存的数据,刷新到磁盘
		resposeOutput.flush();
		if (buff != null) {
			buff.close();
		}
		if (fis != null) {
			fis.close();
		}
	}

	// Process the HTTP Post request
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// Clean up resources
	public void destroy() {
	}
}