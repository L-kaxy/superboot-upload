/**
 * Copyright (c) 2007-2017 Wteam.  All rights reserved. 网维网络技术创业团队 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteam.superboot.upload.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wteam.superboot.core.result.ResultMessage;
import com.wteam.superboot.upload.service.UploadService;

/**
 * 上传文件 Controller.
 * 
 * @authod 罗佳欣
 * 
 */
@RestController
public class UploadController {

	@Autowired
	private UploadService service;

	/**
	 * 文件保存路径 "apache-tomcat-8.0.30/webapps/upload/temp/file".
	 */
	private static final String TEMP_FILE_PATH;

	static {
		File classFile = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath());

		File rootFile = classFile.getParentFile().getParentFile().getParentFile();

		TEMP_FILE_PATH = rootFile.getPath() + File.separator + "upload" + File.separator + "file";
	}

	/**
	 * 上传文件
	 * 
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/uploadFile")
	public ResultMessage uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws Exception {
		return service.addUploadFile(file.getOriginalFilename(), file.getInputStream(), TEMP_FILE_PATH);
	}

}
