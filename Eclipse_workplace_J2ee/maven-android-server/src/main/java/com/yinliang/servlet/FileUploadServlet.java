package com.yinliang.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FileUploadServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		System.out.println("FileUploadServlet");

		if (isMultipart) {
			String realPath = "/Users/liang/Desktop/uploadfiles";

			File file = new File(realPath);
			if (file.exists() == false) {
				file.mkdirs();
			}

			FileItemFactory factory = new DiskFileItemFactory();

			ServletFileUpload upload = new ServletFileUpload(factory);

			upload.setHeaderEncoding("UTF-8");

			try {
				List<FileItem> fileItems = upload.parseRequest(request);
				for (FileItem item : fileItems) {
					if (item.isFormField()) {
						String name1 = item.getFieldName(); // 得到请求参数名
						String value1 = item.getString("UTF-8"); // 得到参数值
						System.out.println("name = " + name1 + ", value = " + value1);
					} else {
						item.write(new File(file, System.currentTimeMillis() + "_" + item.getName()));
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
